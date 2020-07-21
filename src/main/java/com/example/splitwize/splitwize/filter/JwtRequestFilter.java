package com.example.splitwize.splitwize.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.splitwize.splitwize.classes.CustomUserDetails;
import com.example.splitwize.splitwize.service.CustomUserDetailsService;
import com.example.splitwize.splitwize.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                try {
                    final String authorizationHeader = request.getHeader("Authorization");

                    String username = null;
                    String jwt = null;
            
                    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        jwt = authorizationHeader.substring(7);
                        username = jwtTokenUtil.getUsernameFromToken(jwt);
                    }
            
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
                        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            
                        if (jwtTokenUtil.validateToken(jwt, userDetails)) {
            
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            usernamePasswordAuthenticationToken
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    }
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    handlerExceptionResolver.resolveException(request, response, null, e);
                }
       
    }

}