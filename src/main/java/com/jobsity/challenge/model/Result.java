package com.jobsity.challenge.model;

public class Result {
    private String namePlayer;
    private Integer points;

    public Result(String namePlayer, Integer points){
        this.namePlayer = namePlayer;
        this.points = points;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
