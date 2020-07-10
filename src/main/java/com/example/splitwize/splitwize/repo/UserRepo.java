package com.example.splitwize.splitwize.repo;

import com.example.splitwize.splitwize.data.UserRegiData;

public interface UserRepo {
    public  int addUserDetails(UserRegiData userRegiData,String token,String id, String hashResult);
}
