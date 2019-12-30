package com.jobsity.challenge.model;

import lombok.Data;

@Data
public class Roll {
    private Integer knockedDownPins;
    private RollType rollType;

    public Roll(RollType rollType, Integer rollScore) {
        this.rollType = rollType;
        this.knockedDownPins = rollScore;
    }
}
