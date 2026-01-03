package com.rebellion.travelblogplatform.exception;

public class NotAuthorizedException extends RuntimeException{
    
    public NotAuthorizedException(String message){
        super(message);
    }
}
