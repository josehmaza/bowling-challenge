package com.jobsity.challenge.controller;

import com.jobsity.challenge.constants.BowlingConstants;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Game;
import com.jobsity.challenge.model.Result;

import java.util.List;

public class GameController {
    private FrameController frameController;

    private static GameController single_instance = null;
    public GameController(){
        this.frameController = FrameController.getInstance();
    }
    public static GameController getInstance() {
        if (single_instance == null)
            single_instance = new GameController();

        return single_instance;
    }

    public void addRoll(Result result, Game gamePlayer) throws BreakRuleBowlingException {
        List<Frame> frames = gamePlayer.getFrames();

        if(frames.size() == 0){
            frames.add(new Frame(1));
        }

        if(frameController.canAddRollToThisFrame(frames.get(frames.size() -1))){
            frameController.addRoll(frames.get(frames.size() -1), result.getPoints());
        }else{
            if(frames.size() == BowlingConstants.LAST_FRAME){
                throw new BreakRuleBowlingException("No more rolls allowed in the last frame");
            }else{
                frames.add(new Frame(frames.size()+1));
                frameController.addRoll(frames.get(frames.size() -1), result.getPoints());
            }
        }
    }

}
