package com.jobsity.challenge.exception;

/**
 * Exception for Bad input of bowling-challenge
 */
public class BadInputException extends Exception {

    public BadInputException(String message) {
        super(message);
    }

    public BadInputException(String message, Throwable cause) {
        super(message, cause);
    }

}
