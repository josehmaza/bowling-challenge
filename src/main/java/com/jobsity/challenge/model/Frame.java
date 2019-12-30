package com.jobsity.challenge.model;

public class Frame {
    private int name;
    private PinFall pinFall;
    private int scoreFrame;
    private String customFormat;
    public Frame(int nameFrame) {
        this.name = nameFrame;
        this.pinFall = new PinFall();
        this.scoreFrame = 0;

    }

    public PinFall getPinFall() {
        return pinFall;
    }

    public void setPinFall(PinFall pinFall) {
        this.pinFall = pinFall;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }



    public int getScoreFrame() {
        return scoreFrame;
    }


    @Override
    public String toString() {
        return customFormat;
    }
}
