package com.jobsity.challenge.model;

import com.jobsity.challenge.constants.BowlingConstants;

public enum RollType {
    STRIKE(BowlingConstants.STRIKE),
    SPARE(BowlingConstants.SPARE),
    FOUL(BowlingConstants.FOUL),
    OPEN,
    VALID;
    private String value;

    private RollType() {}
    private RollType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
