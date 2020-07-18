package com.example.splitwize.splitwize.repo;


import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.entity.UserRegiData;
import com.example.splitwize.splitwize.repository.UserDataRepository;
import com.example.splitwize.splitwize.repository.UserPaymentDetailsRepository;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.service.UserService;
import com.example.splitwize.splitwize.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, String password) {
        UserRegiData user = userDataRepository.findByPassword(password);
        UserPaymentDetails details = new UserPaymentDetails();
        details.setBorrowed_amount(paymentDetailRequest.getBorrowed_amount());
        details.setLent_amount(paymentDetailRequest.getLent_amount());
        details.setOppo_name(paymentDetailRequest.getOppo_name());
        details.setUserRegiData(user);
        return userPaymentDetailsRepository.save(details);
    }

}
