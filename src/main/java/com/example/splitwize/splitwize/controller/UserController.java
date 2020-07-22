package com.example.splitwize.splitwize.controller;

import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.request.AuthRequest;
import com.example.splitwize.splitwize.request.UserRegisterRequest;
import com.example.splitwize.splitwize.response.SuccessResponse;
import com.example.splitwize.splitwize.response.UserDetailsResponse;
import com.example.splitwize.splitwize.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/session/register")
    public User register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return customUserDetailsService.addUser(userRegisterRequest);
        //return login(new AuthRequest(userRegisterRequest.getUsername(), userRegisterRequest.getPassword()));
    }

    @PostMapping(  value = "/session/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(customUserDetailsService.login(authRequest));
    }

    @GetMapping("{id}")
    public SuccessResponse<UserDetailsResponse> getUserDetails(@PathVariable int id) {
        return new SuccessResponse<UserDetailsResponse>(customUserDetailsService.getUserDetailsFromId(id));
    }

}
