// package com.example.splitwize.splitwize.repo;


// import com.example.splitwize.splitwize.entity.UserPaymentDetails;
// import com.example.splitwize.splitwize.entity.UserRegiData;
// import com.example.splitwize.splitwize.repository.UserDataRepository;
// import com.example.splitwize.splitwize.repository.UserPaymentDetailsRepository;
// import com.example.splitwize.splitwize.repository.UserRepository;
// import com.example.splitwize.splitwize.request.PaymentDetailRequest;
// import com.example.splitwize.splitwize.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;

// import java.util.Optional;

// @Repository
// public class UserRepositoryImpl implements UserRepository {
//     @Autowired
//     private UserDataRepository userDataRepository;

//     @Autowired
//     UserPaymentDetailsRepository userPaymentDetailsRepository;

//     @Autowired
//     UserService userService;

//     @Override
//     public UserRegiData addUserDetails(UserRegiData userRegiData) {
//         return userDataRepository.save(userRegiData);
//     }

//     @Override
//     public Optional<UserRegiData> getUserDetails(int id) {
//         return userDataRepository.findById(id);

//     }

//     @Override
//     public UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, String id) {
//         UserRegiData user = userDataRepository.findById(Integer.parseInt(id)).get();
//         UserPaymentDetails details = new UserPaymentDetails();
//         details.setBorrowed_amount(paymentDetailRequest.getBorrowed_amount());
//         details.setLent_amount(paymentDetailRequest.getLent_amount());
//         details.setOppo_name(paymentDetailRequest.getOppo_name());
//         details.setUserRegiData(user);
//         return userPaymentDetailsRepository.save(details);
//     }

// }
