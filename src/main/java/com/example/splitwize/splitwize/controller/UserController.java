package com.example.splitwize.splitwize.controller;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
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

    @RequestMapping(value = "/user/login/{id}/{password}", method = RequestMethod.GET)
    public SuccessResponse<UserRegiData> login(@PathVariable int id ,@PathVariable String password){
        return userService.login(id,password);
    }

   @RequestMapping(value = "/user/payment/details",method = RequestMethod.POST)
    public SuccessResponse<UserPaymentDetails> saveUserPaymentDetails(@RequestHeader("Authorization") String token, @RequestBody PaymentDetailRequest paymentDetailRequest){
       return userService.saveUserPaymentDetails(paymentDetailRequest, token);
   }
}
