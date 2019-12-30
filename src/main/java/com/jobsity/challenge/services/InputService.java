package com.jobsity.challenge.services;

import com.jobsity.challenge.exception.BadInputException;
import com.jobsity.challenge.exception.BowlingFileException;
import com.jobsity.challenge.model.Result;

import java.util.List;

public interface InputService {
    List<Result> getResults(String fileName) throws BowlingFileException, BadInputException;
}
