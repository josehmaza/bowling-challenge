package com.jobsity.challenge.model;

import lombok.Data;

@Data
public class Result {
    private String namePlayer;
    private Integer points;

    public Result(String namePlayer, Integer points){
        this.namePlayer = namePlayer;
        this.points = points;
    }

}
