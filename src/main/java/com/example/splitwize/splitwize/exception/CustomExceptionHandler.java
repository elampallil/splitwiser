package com.example.splitwize.splitwize.exception;

import com.example.splitwize.splitwize.enums.ErrorCode;
import com.example.splitwize.splitwize.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest req) {
        ErrorResponse exception = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<ErrorResponse>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //creating custom exception handling for user not found
    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ErrorResponse> userNotFound(UsernameNotFoundException ex, WebRequest req) {
        ErrorResponse exception = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ErrorCode.BAD_CREDENTIALS, ex.getMessage());
        return new ResponseEntity<ErrorResponse>(exception, HttpStatus.NOT_FOUND);
    }

}
