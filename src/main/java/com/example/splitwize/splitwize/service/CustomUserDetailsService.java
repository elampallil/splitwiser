package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.classes.CustomUserDetails;
import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.request.UserRegisterRequest;
import com.example.splitwize.splitwize.response.UserDetailsResponse;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
    // public SuccessResponse<UserRegiData> addUserDetails(UserRegiData
    // userRegiData);
    // public SuccessResponse<UserRegiData> getUserDetails(int id);
    // public SuccessResponse<UserRegiData> login(int id,String password);

    @Override
    CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    
    User addUser(UserRegisterRequest userRegisterRequest);

	UserDetailsResponse getUserDetailsFromId(int id);
}