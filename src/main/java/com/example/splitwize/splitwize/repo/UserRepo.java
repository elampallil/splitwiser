package com.example.splitwize.splitwize.repo;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;

import java.util.Optional;

public interface UserRepo {
    public UserRegiData addUserDetails(UserRegiData userRegiData);
    public Optional<UserRegiData> getUserDetails(int id);
    public UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, String password);
}
