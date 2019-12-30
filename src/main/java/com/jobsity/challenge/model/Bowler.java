package com.jobsity.challenge.model;

public class Bowler {
    private String name;

    public Bowler(String namePlayer){
        this.name = namePlayer;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
