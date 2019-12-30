package com.jobsity.challenge.model;

public class Roll {
    private Integer knockedDownPins;
    private RollType rollType;
    public Roll(RollType rollType, Integer rollScore) {
        this.rollType = rollType;
        this.knockedDownPins = rollScore;
    }

    public Integer getKnockedDownPins() {
        return knockedDownPins;
    }

    public void setKnockedDownPins(Integer knockedDownPins) {
        this.knockedDownPins = knockedDownPins;
    }

    public RollType getRollType() {
        return rollType;
    }

    public void setRollType(RollType rollType) {
        this.rollType = rollType;
    }
}
