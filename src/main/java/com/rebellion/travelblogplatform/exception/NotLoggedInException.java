package com.rebellion.travelblogplatform.exception;

public class NotLoggedInException extends RuntimeException{
    private static final String message = "Please login!";

    public NotLoggedInException(){
        super(message);
    }
}
