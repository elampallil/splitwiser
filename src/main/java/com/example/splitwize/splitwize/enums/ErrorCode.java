package com.example.splitwize.splitwize.enums;

public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND"),
    SERVER_ERROR("SERVER_ERROR");

    private String value;

    ErrorCode(String value) {
        this.value = value;
    }
}
