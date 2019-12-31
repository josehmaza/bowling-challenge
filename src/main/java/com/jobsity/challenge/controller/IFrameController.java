package com.jobsity.challenge.controller;

import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Frame;

public interface IFrameController {
    void addRoll(Frame frame, Integer rollScore) throws BreakRuleBowlingException;

    boolean canAddRollToThisFrame(Frame frame) throws BreakRuleBowlingException;
}
