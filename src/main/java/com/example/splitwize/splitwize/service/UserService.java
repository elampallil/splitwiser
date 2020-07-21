package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.response.SuccessResponse;

public interface UserService {
    public SuccessResponse<UserRegiData> addUserDetails(UserRegiData userRegiData);
    public SuccessResponse<UserRegiData> getUserDetails(int id);
    public SuccessResponse<UserRegiData> login(int id,String password);
    public SuccessResponse<UserPaymentDetails> saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, String token);
    public  String deleteAllPaymentDetails(int id);
    public  SuccessResponse<UserPaymentDetails> saveUpdatedPayDetails(PaymentDetailRequest paymentDetailRequest);
}
