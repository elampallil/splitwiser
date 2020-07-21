package com.example.splitwize.splitwize.controller;

import com.example.splitwize.splitwize.classes.CustomUserDetails;
import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.request.AuthRequest;
import com.example.splitwize.splitwize.request.UserRegisterRequest;
import com.example.splitwize.splitwize.response.AuthResponse;
import com.example.splitwize.splitwize.response.SuccessResponse;
import com.example.splitwize.splitwize.response.UserDetailsResponse;
import com.example.splitwize.splitwize.service.CustomUserDetailsService;
import com.example.splitwize.splitwize.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/session/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        User user = customUserDetailsService.addUser(userRegisterRequest);

        return login(new AuthRequest(user.getUsername(), user.getPassword()));
    }

    @PostMapping("/session/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect credentials", e);
        }
        final CustomUserDetails customUserDetails = customUserDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(customUserDetails);
        return ResponseEntity.ok(new AuthResponse<String>(jwt));
    }

    @GetMapping("{id}")
    public SuccessResponse<UserDetailsResponse> getUserDetails(@PathVariable int id) {
        return new SuccessResponse<UserDetailsResponse>(customUserDetailsService.getUserDetailsFromId(id));
    }

}
