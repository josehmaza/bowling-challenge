package com.jobsity.challenge.controller;

import com.jobsity.challenge.constants.BowlingConstants;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Roll;
import com.jobsity.challenge.model.RollType;

public class FrameController {
    private RollController rollController;

    private static FrameController single_instance = null;

    public FrameController() {
        rollController = RollController.getInstance();
    }

    public static FrameController getInstance() {
        if (single_instance == null)
            single_instance = new FrameController();

        return single_instance;
    }

    public void addRoll(Frame frame, Integer rollScore) throws BreakRuleBowlingException {
        Integer rollNumbers = frame.getPinFall().getRolls().size();
        Roll newRoll;
        if (rollNumbers == 0) {
            newRoll = rollController.createRoll(rollScore, 0, frame.getName() == BowlingConstants.LAST_FRAME);

        } else {
            newRoll = rollController.createRoll(rollScore, frame.getPinFall().getRolls().get(rollNumbers - 1).getKnockedDownPins(), frame.getName() == BowlingConstants.LAST_FRAME);
        }
        frame.getPinFall().getRolls().add(newRoll);

    }

    public boolean canAddRollToThisFrame(Frame frame) throws BreakRuleBowlingException {
        boolean isLastFrame = frame.getName() == BowlingConstants.LAST_FRAME;
        Integer rollNumbers = frame.getPinFall().getRolls().size();
        //Puedo agregar si:
        // Not Rolls o
        boolean canAddRoll = rollNumbers == 0 ||
                // Has 1 Roll and not STRIKE and not last Frame o
                (!isLastFrame && rollNumbers == 1 && frame.getPinFall().getRolls().get(0).getRollType() != RollType.STRIKE) ||
                // Is last frame but not has 3 rolls yet
                (isLastFrame && rollNumbers != 3);

        if (!canAddRoll && frame.getName() == BowlingConstants.LAST_FRAME) {
            throw new BreakRuleBowlingException("A Player can't have more than ten throws. Last frame already has 3 rolls");
        }
        return canAddRoll;
    }
}
