package com.jobsity.challenge.validators;

import com.jobsity.challenge.constants.BowlingConstants;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Game;

public class BowlingValidator {
    /**
     * Validate that in a game a bowler has thrown all his rolls
     * @param game
     */

    public static void validateRollsByGame(Game game) throws BreakRuleBowlingException {
        Frame lastFrame = game.getFrames().stream().filter(f -> f.getName() == BowlingConstants.LAST_FRAME).findAny().orElse(null);

        if(lastFrame.getPinFall().getRolls().size() != 3){
            throw new BreakRuleBowlingException(game.getPlayer()+" must throw 3 rolls in the last frame. Received "+lastFrame.getPinFall().getRolls().size());
        }
    }
}
