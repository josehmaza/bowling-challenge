package com.jobsity.challenge.view;

import com.jobsity.challenge.constants.BowlingConstants;
import com.jobsity.challenge.exception.BadInputException;
import com.jobsity.challenge.exception.BowlingFileException;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.FormatOutput;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.PinFall;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.model.RollType;
import com.jobsity.challenge.services.BowlingService;
import com.jobsity.challenge.services.InputService;
import com.jobsity.challenge.services.TextInputService;


import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        InputService textInputService = TextInputService.getInstance();
        BowlingService bowLingService = BowlingService.getInstance();
        try {
            List<Result> results = textInputService.getResults("results.txt");
            for (Result result : results) {
                bowLingService.addRoll(result);
            }
            bowLingService.calculateScores();
            //Print
            System.out.print("Frame\t\t");
            IntStream.range(1, 11).mapToObj(n -> n+"\t\t").forEach(System.out::print);
            System.out.println();
            bowLingService.getGames().forEach(game -> {
                //============
                //Print player
                System.out.println(game.getPlayer());
                //============
                //Print pïnfalls
                System.out.print("Pinfalls\t");

                game.getFrames().stream().forEach(frame -> {
                    FormatOutput<PinFall> customFormatPinfall = pinfall -> {
                        if (frame.getName() != BowlingConstants.LAST_FRAME &&
                                pinfall.getRolls().get(0).getRollType() == RollType.STRIKE) {
                            return "\t"+RollType.STRIKE.getValue()+"\t";
                        }
                        return pinfall.getRolls().stream().map(roll -> {
                            switch (roll.getRollType()) {
                                case STRIKE:
                                    return RollType.STRIKE.getValue()+"\t";
                                case VALID:
                                case OPEN:
                                    return roll.getKnockedDownPins() + "\t";
                                case SPARE:
                                    return RollType.SPARE.getValue()+"\t";
                                case FOUL:
                                    return RollType.FOUL.getValue() + "\t";
                                default:
                                    return "";
                            }
                        }).reduce((a, b) -> a + b).orElse("");

                    };

                    frame.getPinFall().formatOutput(customFormatPinfall);

                    System.out.print(frame.getPinFall());
                });
                System.out.println();
                //=================
                //Print score
                System.out.print("Score\t\t");
                game.getFrames().stream().forEach(frame -> {
                    FormatOutput<Frame> customFormatFrame = fr -> {
                        return fr.getScoreFrame()+"\t\t";
                    };
                    frame.formatOutput(customFormatFrame);
                    System.out.print(frame);
                });
                System.out.println();
            });
        } catch (BowlingFileException | BadInputException e) {
            System.out.println("ERROR con el input: => " + e.getMessage());
        } catch (BreakRuleBowlingException e) {
            System.out.println("Error en reglas del Bowling : " + e.getMessage());
        }

        System.out.println("============================");
    }
}
