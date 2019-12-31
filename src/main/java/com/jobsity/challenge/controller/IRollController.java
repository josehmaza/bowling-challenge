package com.jobsity.challenge.controller;

import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Roll;

public interface IRollController {
    Roll createRoll(Integer rollScore, Integer lastRollScore, boolean isLastFrame) throws BreakRuleBowlingException;
}
