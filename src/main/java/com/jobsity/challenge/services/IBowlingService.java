package com.jobsity.challenge.services;

import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Game;
import com.jobsity.challenge.model.Result;

import java.util.List;

public interface IBowlingService {
    void addRoll(Result result) throws BreakRuleBowlingException;

    List<Game> getGames();

    void calculateScores() throws BreakRuleBowlingException;

    void cleanGames();
}
