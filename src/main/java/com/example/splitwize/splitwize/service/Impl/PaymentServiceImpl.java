package com.example.splitwize.splitwize.service.Impl;

import java.io.IOException;

import java.security.Principal;
import java.util.Date;
import java.util.Properties;

import com.example.splitwize.splitwize.entity.User;
import com.example.splitwize.splitwize.entity.UserPaymentDetails;
import com.example.splitwize.splitwize.exception.UserNotFoundException;
import com.example.splitwize.splitwize.repository.UserPaymentDetailsRepository;
import com.example.splitwize.splitwize.repository.UserRepository;
import com.example.splitwize.splitwize.request.PaymentDetailRequest;
import com.example.splitwize.splitwize.response.SuccessResponse;
import com.example.splitwize.splitwize.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



@Service
@PropertySource(value = {"classpath:application.properties"})
public class PaymentServiceImpl implements PaymentService {
    @Value("${gmail.usename}")
    private String emailId;
    @Value("${gmail.password}")
    private String password;

    @Autowired
    UserPaymentDetailsRepository userPaymentDetailsRepository;
    @Autowired
    UserRepository userRepository;



    @Override
    public UserPaymentDetails saveUserPaymentDetails(PaymentDetailRequest paymentDetailRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
        UserPaymentDetails userPaymentDetails = new UserPaymentDetails();
        userPaymentDetails.setBorrowedAmount(paymentDetailRequest.getBorrowedAmount());
        userPaymentDetails.setLentAmount(paymentDetailRequest.getLentAmount());
        userPaymentDetails.setOpponame(paymentDetailRequest.getOppoName());
        userPaymentDetails.setUser(user);
        return userPaymentDetailsRepository.save(userPaymentDetails);
    }

    @Override
    public UserPaymentDetails saveUserPaymentUpdatedDetails(PaymentDetailRequest paymentDetailRequest, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
        UserPaymentDetails userPaymentDetails = userPaymentDetailsRepository.findByOpponame(paymentDetailRequest.getOppoName());
        userPaymentDetails.setBorrowedAmount(paymentDetailRequest.getBorrowedAmount());
        userPaymentDetails.setLentAmount(paymentDetailRequest.getLentAmount());
        return  userPaymentDetailsRepository.save(userPaymentDetails);
    }

    @Override
    @Transactional
    public String userDetailsDeleted(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());
      return userPaymentDetailsRepository.deleteByUser(user).toString();
    }

    @Override
    public SuccessResponse<User> forgotPassword(Principal principal) throws IOException, MessagingException {
        return sendMail(principal);
    }

    private SuccessResponse<User> sendMail(Principal principal) throws IOException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserNotFoundException());

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailId, password);
            }
        });

        javax.mail.Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailId, false));

        msg.setRecipients(javax.mail.Message.RecipientType.TO
                , InternetAddress.parse(user.getEmail()));
        msg.setSubject("hai this is your token for resetting password");
        msg.setContent("splitWize token for reset password", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("hai your token", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();


        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
          SuccessResponse<User> userSuccessResponse = new SuccessResponse<User>();
          userSuccessResponse.setMessage("successfully sended email");
        return userSuccessResponse;
    }

}