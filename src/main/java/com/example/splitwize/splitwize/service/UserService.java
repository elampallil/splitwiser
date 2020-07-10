package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.data.UserRegiData;
import com.example.splitwize.splitwize.response.ResponseMessages;

public interface UserService {
    public ResponseMessages addUserDetails(UserRegiData userRegiData);
}
