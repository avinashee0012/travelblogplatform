package com.rebellion.travelblogplatform.exception;

public class InactiveUserException extends RuntimeException{
    private static final String message = "Inactive Account! Please contact support for help.";

    public InactiveUserException(){
        super(message);
    }
}
