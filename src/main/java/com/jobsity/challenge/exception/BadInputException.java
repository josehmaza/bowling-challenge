package com.jobsity.challenge.exception;


public class BadInputException extends Exception {

    public BadInputException(String message) {
        super(message);
    }

    public BadInputException(String message, Throwable cause) {
        super(message, cause);
    }

}
