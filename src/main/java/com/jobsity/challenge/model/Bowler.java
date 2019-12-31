package com.jobsity.challenge.model;

import lombok.Data;

@Data
public class Bowler {
    private String name;

    public Bowler(String namePlayer){
        this.name = namePlayer;
    }

    @Override
    public String toString() {
        return name;
    }
}
