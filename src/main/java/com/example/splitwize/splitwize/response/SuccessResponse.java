package com.example.splitwize.splitwize.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SuccessResponse<T> {
    private int status = 200;
    @JsonUnwrapped
    private T data;
    private List<T> details;

    public SuccessResponse() {
    }
    
    public SuccessResponse(T data) {
        this.data = data;
    }

    public SuccessResponse(List<T> details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

}
