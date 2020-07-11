package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.data.UserRegiData;
import com.example.splitwize.splitwize.response.SuccessResponse;

public interface UserService {
    public SuccessResponse<UserRegiData> addUserDetails(UserRegiData userRegiData);
    public SuccessResponse<UserRegiData> getUserDetails(int id);
    public SuccessResponse<UserRegiData> login(int id,String password);
}
