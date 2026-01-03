package com.rebellion.travelblogplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthorizedException(){
        ErrorResponse response = new ErrorResponse(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Unauthorized Access");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
