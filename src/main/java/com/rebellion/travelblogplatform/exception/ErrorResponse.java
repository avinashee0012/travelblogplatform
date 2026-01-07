package com.rebellion.travelblogplatform.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private int code;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.code = status.value();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }

    
}