package com.example.splitwize.splitwize.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.example.splitwize.splitwize.classes.CustomUserDetails;

import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
@PropertySource(value = { "classpath:application.properties" })
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 60*60*12;

    // @Value("${jwt.secret}")
    private SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",customUserDetails.getAuthorities());
        return doGenerateToken(claims, customUserDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
        .signWith(secret)
        .compact();
    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
