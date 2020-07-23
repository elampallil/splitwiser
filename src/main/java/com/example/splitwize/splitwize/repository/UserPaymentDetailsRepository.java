package com.example.splitwize.splitwize.repository;

import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.entity.UserPaymentDetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentDetailsRepository extends JpaRepository<UserPaymentDetails, Integer > {
    UserPaymentDetails findByOpponame(String name);
    Long deleteByUser(User user);
}
