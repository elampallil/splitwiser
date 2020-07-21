package com.example.splitwize.splitwize.response;

public class AuthResponse<T> extends SuccessResponse<T> {
    
    private final String token;

    public AuthResponse(String jwt) {
        this.token = jwt;
    }

    public String getToken() {
        return token;
    }


    
}