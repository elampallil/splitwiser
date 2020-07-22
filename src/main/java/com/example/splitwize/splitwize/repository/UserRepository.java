package com.example.splitwize.splitwize.repository;

import java.util.Optional;

import com.example.splitwize.splitwize.entity.User;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}