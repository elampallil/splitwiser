package com.example.splitwize.splitwize.repo;


import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import com.example.splitwize.splitwize.repository.UserDataRepository;
import com.example.splitwize.splitwize.repository.UserPaymentDetailsRepository;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserRepoImpl implements UserRepo {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    UserPaymentDetailsRepository userPaymentDetailsRepository;

    @Autowired
    UserService userService;

    @Override
    public UserRegiData addUserDetails(UserRegiData userRegiData) {
        return userDataRepository.save(userRegiData);
    }

    @Override
    public Optional<UserRegiData> getUserDetails(int id) {
        return userDataRepository.findById(id);

    }

    @Override
    public UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, String id) {
        UserRegiData user = userDataRepository.findById(Integer.parseInt(id)).get();
        UserPaymentDetails details = new UserPaymentDetails();
        details.setBorrowed_amount(paymentDetailRequest.getBorrowed_amount());
        details.setLent_amount(paymentDetailRequest.getLent_amount());
        details.setName(paymentDetailRequest.getOppo_name());
        details.setUserRegiData(user);
        return userPaymentDetailsRepository.save(details);
    }

    @Override
    @Transactional
    public String deleteAllPaymentDetails(int id) {
       UserRegiData user = userDataRepository.getOne(id);
        return   userPaymentDetailsRepository.deleteByUserRegiData(user).toString();
    }

    @Override
    public UserPaymentDetails saveUpdatedPayDetails(PaymentDetailRequest paymentDetailRequest) {
        UserPaymentDetails userPaymentDetails = new UserPaymentDetails();
      userPaymentDetails =  userPaymentDetailsRepository.findByName(paymentDetailRequest.getOppo_name());
      userPaymentDetails.setLent_amount(paymentDetailRequest.getLent_amount());
      userPaymentDetails.setBorrowed_amount(paymentDetailRequest.getBorrowed_amount());
      return userPaymentDetailsRepository.save(userPaymentDetails);
    }

}
