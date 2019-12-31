package com.jobsity.challenge.exception;
/**
 * Exception for errors with read text file as input of bowling-challenge
 */
public class BowlingFileException extends Exception{

    public BowlingFileException(String message) {
        super(message);
    }

    public BowlingFileException(String message, Throwable cause) {
        super(message, cause);
    }

}
