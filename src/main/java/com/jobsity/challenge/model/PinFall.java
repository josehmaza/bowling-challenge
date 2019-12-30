package com.jobsity.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class PinFall implements Scoreable {
    private List<Roll> rolls;
    private Integer scorePinfall;
    public PinFall(){
        rolls = new ArrayList<>(2);
    }

    public List<Roll> getRolls() {
        return rolls;
    }

    public void setRolls(List<Roll> rolls) {
        this.rolls = rolls;
    }


    public Integer getScorePinfall() {
        return scorePinfall;
    }

    @Override
    public void calculateScore(CalculateScore cal) {
        scorePinfall = cal.calculate(this);
    }
}
