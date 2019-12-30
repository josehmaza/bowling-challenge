package com.jobsity.challenge.controller;

import com.jobsity.challenge.constants.BowlingConstants;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Roll;
import com.jobsity.challenge.model.RollType;

public class RollController {
    private static RollController single_instance = null;
    public static RollController getInstance() {
        if (single_instance == null)
            single_instance = new RollController();

        return single_instance;
    }

    public Roll createRoll(Integer rollScore, Integer lastRollScore, boolean isLastFrame) throws BreakRuleBowlingException {
        if(rollScore != null){
            switch (rollScore){
                case 10:
                    return new Roll(RollType.STRIKE, rollScore);
                case 0:
                    return new Roll(RollType.OPEN, rollScore);
                default:
                    Integer sumRollScores = rollScore + lastRollScore;
                    if(sumRollScores > 10 && !isLastFrame){
                        throw new BreakRuleBowlingException("The pinFall score must not be more than 10");
                    }
                    if(sumRollScores == BowlingConstants.PIN_NUMBERS){
                        return new Roll(RollType.SPARE, rollScore);
                    }else{
                        return new Roll(RollType.VALID, rollScore);
                    }
            }
        }else{
            return new Roll(RollType.FOUL, 0);
        }



    }
}
