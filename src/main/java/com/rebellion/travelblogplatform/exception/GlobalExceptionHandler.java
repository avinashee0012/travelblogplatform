package com.rebellion.travelblogplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthorizedException(Exception ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEntryException(Exception ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
