package com.jobsity.challenge.model;

import com.jobsity.challenge.model.lambdas.CalculateScore;

public interface Scoreable {
    void calculateScore(CalculateScore cal);
}
