package com.jobsity.challenge.model;

import com.jobsity.challenge.model.lambdas.CalculateScore;
import com.jobsity.challenge.model.lambdas.FormatOutput;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Frame implements Scoreable, PrintableConsole {
    private int name;
    private PinFall pinFall;
    @Setter(AccessLevel.NONE)
    private int scoreFrame;
    private String customFormat;

    public Frame(int nameFrame) {
        this.name = nameFrame;
        this.pinFall = new PinFall();
        this.scoreFrame = 0;

    }

    @Override
    public void calculateScore(CalculateScore cal) {
        this.scoreFrame = cal.calculate(this);
    }


    @Override
    public void formatOutput(FormatOutput format) {
        this.customFormat = format.format(this);
    }

    @Override
    public String toString() {
        return customFormat;
    }
}
