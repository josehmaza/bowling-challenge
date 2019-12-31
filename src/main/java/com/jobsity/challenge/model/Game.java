package com.jobsity.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Bowler player;
    private List<Frame> frames;

    public Game(){
        frames = new ArrayList<>(10);
    }
    public Bowler getPlayer() {
        return player;
    }

    public void setPlayer(Bowler player) {
        this.player = player;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }
}
