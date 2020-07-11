package com.example.splitwize.splitwize.exception;

import com.example.splitwize.splitwize.enums.ErrorCode;
import com.example.splitwize.splitwize.response.GenericExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<GenericExceptionResponse> handleAll(Exception ex, WebRequest req) {

        GenericExceptionResponse exception = new GenericExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<GenericExceptionResponse>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //creating custom exception handling for user not found
    @ExceptionHandler(UserNotFoundEx.class)
    private ResponseEntity<GenericExceptionResponse> usernotfound(UserNotFoundEx ex, WebRequest req) {

        GenericExceptionResponse exception = new GenericExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getCode(), "sorry");
        return new ResponseEntity<GenericExceptionResponse>(exception, HttpStatus.NOT_FOUND);
    }

}
