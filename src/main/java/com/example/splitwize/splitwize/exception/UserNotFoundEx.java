package com.example.splitwize.splitwize.exception;

import com.example.splitwize.splitwize.enums.ErrorCode;

public class UserNotFoundEx extends RuntimeException {
    private ErrorCode code;
    public UserNotFoundEx(ErrorCode code) {
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
}
