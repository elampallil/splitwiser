package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.classes.CustomUserDetails;
import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.request.AuthRequest;
import com.example.splitwize.splitwize.request.UserRegisterRequest;
import com.example.splitwize.splitwize.response.AuthResponse;
import com.example.splitwize.splitwize.response.UserDetailsResponse;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {

    @Override
    CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    AuthResponse<CustomUserDetails> login(AuthRequest authRequest);
    
    User addUser(UserRegisterRequest userRegisterRequest);

	UserDetailsResponse getUserDetailsFromId(int id);
}