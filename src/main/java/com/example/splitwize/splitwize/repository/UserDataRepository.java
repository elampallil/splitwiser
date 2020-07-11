package com.example.splitwize.splitwize.repository;

import com.example.splitwize.splitwize.data.UserRegiData;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataRepository extends JpaRepository<UserRegiData, Integer> {

}
