package com.jobsity.challenge.controller;

import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Game;
import com.jobsity.challenge.model.Result;

public interface IGameController {
    void addRoll(Result result, Game gamePlayer) throws BreakRuleBowlingException;
}
