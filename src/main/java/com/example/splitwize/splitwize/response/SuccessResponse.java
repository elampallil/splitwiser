package com.example.splitwize.splitwize.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Service;

import java.util.List;

// annotation for eliminate null value
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SuccessResponse<T> {
    private int status = 200;
    private T data;
    private List<T> details;

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
