package com.example.splitwize.splitwize.repository;

import com.example.splitwize.splitwize.entity.UserRegiData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserRegiData, Integer> {
    public UserRegiData findByPassword(String password);
}
