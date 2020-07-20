package com.example.splitwize.splitwize.service;

import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import com.example.splitwize.splitwize.enums.ErrorCode;
import com.example.splitwize.splitwize.exception.UserNotFoundEx;
import com.example.splitwize.splitwize.repo.UserRepo;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.response.SuccessResponse;
import com.example.splitwize.splitwize.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    private String hashPassword;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    //    @Autowired
//    ResponseMessages responseMessages;
    @Autowired
    UserRepo userRepo;

    @Override
    public SuccessResponse<UserRegiData> addUserDetails(UserRegiData userRegiData) {
        //String token = jwtTokenUtil.generateToken(userRegiData);
        //Long id = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        String hashResult = passwordHashing(userRegiData.getPassword());
        userRegiData.setPassword(hashResult);
      //  userRegiData.setToken(token);
        //userRegiData.setCust_id(id.toString());


        SuccessResponse<UserRegiData> userRegiDataResponseMessages = new SuccessResponse<UserRegiData>();
        userRegiDataResponseMessages.setData(userRepo.addUserDetails(userRegiData));

        return userRegiDataResponseMessages;
    }

    @Override
    public SuccessResponse<UserRegiData> getUserDetails(int id) {
        SuccessResponse<UserRegiData> userRegiDataResponseMessages = new SuccessResponse<UserRegiData>();
        if (userRepo.getUserDetails(id).isPresent()) {
            userRegiDataResponseMessages.setData(userRepo.getUserDetails(id).get());
        } else {
            throw new UserNotFoundEx(ErrorCode.USER_NOT_FOUND);
        }
        return userRegiDataResponseMessages;
    }

    @Override
    public SuccessResponse<UserRegiData> login(int id, String password) {
        String paswdHash = passwordHashing(password);
        UserRegiData userRegiData = new UserRegiData();
        SuccessResponse<UserRegiData> userRegiDataResponseMessages = new SuccessResponse<UserRegiData>();
        if (userRepo.getUserDetails(id).isPresent()) {
            userRegiData = userRepo.getUserDetails(id).get();
           String token = jwtTokenUtil.generateToken(userRegiData);
            userRegiData.setToken(token);
            userRegiData.setPassword(paswdHash);
            if (userRegiData.getPassword().equals(paswdHash)) {
                userRegiDataResponseMessages.setData(userRegiData);
                userRegiDataResponseMessages.setMessage("password correct");
            } else {
                throw new RuntimeException("invalid password");
            }
        } else {
            throw new UserNotFoundEx(ErrorCode.USER_NOT_FOUND);
        }
        return userRegiDataResponseMessages;
    }

    @Override
    public SuccessResponse<UserPaymentDetails> saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, String token) {
        SuccessResponse<UserPaymentDetails> userRegiDataResponseMessages = new SuccessResponse<UserPaymentDetails>();
        String id = jwtTokenUtil.getIdFromToken(token);
       String hashPaswd = passwordHashing(id);
         if(jwtTokenUtil.validateToken(token)){
             userRegiDataResponseMessages.setData(userRepo.saveUserPaymentDetails(paymentDetailRequest, id));
         }
         else {
             userRegiDataResponseMessages.setMessage("token expired");
         }

        return userRegiDataResponseMessages;
    }

    @Override
    public String deleteAllPaymentDetails(int id) {

       return userRepo.deleteAllPaymentDetails(id);
    }

    private  String passwordHashing(String passwd) {
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
