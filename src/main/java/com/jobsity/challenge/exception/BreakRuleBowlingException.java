package com.jobsity.challenge.exception;

/**
 * Exception for breaked rules of Ten-pin bowling 
 */
public class BreakRuleBowlingException extends Exception {

    public BreakRuleBowlingException(String message) {
        super(message);
    }

    public BreakRuleBowlingException(String message, Throwable cause) {
        super(message, cause);
    }

}
