package com.example.splitwize.splitwize.repo;

import com.example.splitwize.splitwize.data.UserRegiData;

import java.util.Optional;

public interface UserRepo {
    public UserRegiData addUserDetails(UserRegiData userRegiData);
    public Optional<UserRegiData> getUserDetails(int id);
}
