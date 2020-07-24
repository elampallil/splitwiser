package com.example.splitwize.splitwize.service;

import java.io.IOException;
import java.security.Principal;

import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.response.SuccessResponse;

import javax.mail.MessagingException;

public interface PaymentService {
    UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, Principal principal);
    UserPaymentDetails saveUserPaymentUpdatedDetails(PaymentDetailRequest paymentDetailRequest, Principal principal);
    String userDetailsDeleted(Principal principal);
    SuccessResponse<User> forgotPassword(Principal principal) throws IOException, MessagingException;
}
