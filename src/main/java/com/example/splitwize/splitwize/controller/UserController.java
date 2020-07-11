package com.example.splitwize.splitwize.controller;

import com.example.splitwize.splitwize.data.UserRegiData;
import com.example.splitwize.splitwize.response.SuccessResponse;
import com.example.splitwize.splitwize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public SuccessResponse<UserRegiData> addUserDetails(@RequestBody UserRegiData userRegiData){
     return userService.addUserDetails(userRegiData);
    }

    @RequestMapping(value = "/user/details/{id}", method = RequestMethod.GET)
    public SuccessResponse<UserRegiData> getUserDetails(@PathVariable int id) {
       return userService.getUserDetails(id);
    }
}
