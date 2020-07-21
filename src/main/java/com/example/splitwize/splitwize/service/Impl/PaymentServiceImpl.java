package com.example.splitwize.splitwize.service.Impl;

import java.security.Principal;
import java.util.Optional;

import com.example.splitwize.splitwize.classes.CustomUserDetails;
import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.exception.UserNotFoundException;
import com.example.splitwize.splitwize.repository.UserPaymentDetailsRepository;
import com.example.splitwize.splitwize.repository.UserRepository;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.service.CustomUserDetailsService;
import com.example.splitwize.splitwize.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    UserPaymentDetailsRepository userPaymentDetailsRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
        UserPaymentDetails userPaymentDetails = new UserPaymentDetails();
        userPaymentDetails.setBorrowedAmount(paymentDetailRequest.getBorrowedAmount());
        userPaymentDetails.setLentAmount(paymentDetailRequest.getLentAmount());
        userPaymentDetails.setOppoName(paymentDetailRequest.getOppoName());
        userPaymentDetails.setUser(user);
        return userPaymentDetailsRepository.save(userPaymentDetails);
    }

}