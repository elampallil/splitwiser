package com.example.splitwize.splitwize.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserRegiData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    private String cust_id;
    private String name;
    private String email;
    private String phoneNumber;
//    @OneToMany(mappedBy = "UserRegiData", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserPaymentDetails> userPaymentDetails = new ArrayList<>();
    public String getToken() {
        return token;
    }

//    addPayDet(UserPaymentDetails s){
//        userPaymentDetails.add(s);
//        s.setUserRegiData(this);
//    }

//    public List<UserPaymentDetails> getUserPaymentDetails() {
//        return userPaymentDetails;
//    }
//
//    public void setUserPaymentDetails(List<UserPaymentDetails> userPaymentDetails) {
//        this.userPaymentDetails = userPaymentDetails;
//    }

    public void setToken(String token) {
        this.token = token;
    }

    private String password;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
