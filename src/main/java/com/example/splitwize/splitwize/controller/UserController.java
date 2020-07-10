package com.example.splitwize.splitwize.controller;

import com.example.splitwize.splitwize.data.UserRegiData;
import com.example.splitwize.splitwize.response.ResponseMessages;
import com.example.splitwize.splitwize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public ResponseMessages addUserDetails(@RequestBody UserRegiData userRegiData){
     return userService.addUserDetails(userRegiData);
    }
}
