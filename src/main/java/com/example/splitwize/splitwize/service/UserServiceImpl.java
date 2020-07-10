package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.data.UserRegiData;
import com.example.splitwize.splitwize.repo.UserRepo;
import com.example.splitwize.splitwize.response.ResponseMessages;
import com.example.splitwize.splitwize.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    private String hashPassword;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    ResponseMessages responseMessages;
    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseMessages addUserDetails(UserRegiData userRegiData) {
        String token = jwtTokenUtil.generateToken(userRegiData);
        Long id = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        responseMessages.setToken(token);
        responseMessages.setId(Long.toString(id));
        responseMessages.setMessage("success");
        String hashResult = passwordHashing(userRegiData.getPassword());
       int rowCount= userRepo.addUserDetails(userRegiData,token,id.toString(),hashResult);
        if (rowCount != 1) {
            responseMessages.setMessage("Something went to wrong");
        }
        return responseMessages;

    }

    private String passwordHashing(String passwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwd.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }
}
