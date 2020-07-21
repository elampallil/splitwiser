package com.example.splitwize.splitwize.repository;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPaymentDetailsRepository extends JpaRepository<UserPaymentDetails, Integer > {
    public Long deleteByUserRegiData(UserRegiData userRegiData);
    public UserPaymentDetails findByName(String name);
}
