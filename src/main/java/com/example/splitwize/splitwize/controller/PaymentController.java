package com.example.splitwize.splitwize.controller;

import java.security.Principal;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.response.SuccessResponse;
import com.example.splitwize.splitwize.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PaymentController
 */
@RestController()
@RequestMapping("v1/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/")
    public SuccessResponse<UserPaymentDetails> saveUserPaymentDetails(
            @RequestBody PaymentDetailRequest paymentDetailRequest, Principal principal) {
        return new SuccessResponse<UserPaymentDetails>(
                paymentService.saveUserPaymentDetails(paymentDetailRequest, principal));
    }
}