package com.example.splitwize.splitwize.service;

import java.security.Principal;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;

public interface PaymentService {
    UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, Principal principal);
    UserPaymentDetails saveUserPaymentUpdatedDetails(PaymentDetailRequest paymentDetailRequest, Principal principal);
    String userDetailsDeleted(Principal principal);
}
