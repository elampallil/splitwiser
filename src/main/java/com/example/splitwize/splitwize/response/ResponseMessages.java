package com.example.splitwize.splitwize.response;

import org.springframework.stereotype.Service;

@Service
public class ResponseMessages {
    private String id;
    private String token;
    private  String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
