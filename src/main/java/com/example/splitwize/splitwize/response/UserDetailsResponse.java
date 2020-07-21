package com.example.splitwize.splitwize.response;

import com.example.splitwize.splitwize.entity.User;

public class UserDetailsResponse {
    private String username;
    private String email;
    private String phoneNumber;

    public UserDetailsResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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



    
}