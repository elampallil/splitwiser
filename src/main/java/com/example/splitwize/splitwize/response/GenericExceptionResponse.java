package com.example.splitwize.splitwize.response;

import com.example.splitwize.splitwize.enums.ErrorCode;

public class GenericExceptionResponse {
    private int status;
    private ErrorCode code;
    private String message;

    public GenericExceptionResponse(int status, ErrorCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
