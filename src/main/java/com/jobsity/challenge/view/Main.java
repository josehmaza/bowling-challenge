package com.jobsity.challenge.view;

import com.jobsity.challenge.exception.BadInputException;
import com.jobsity.challenge.exception.BowlingFileException;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.services.BowlingService;
import com.jobsity.challenge.services.InputService;
import com.jobsity.challenge.services.TextInputService;

import java.util.List;

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

            bowLingService.getGames().forEach(game -> {
                System.out.println(game.getPlayer());
                game.getFrames().forEach(frame -> {
                    frame.getPinFall().getRolls().forEach(r -> {
                        System.out.print(r+" - ");
                    });
                    System.out.print("\t|");
                });

                System.out.println();

                game.getFrames().forEach(frame -> {
                    System.out.print(frame.getScoreFrame()+"\t");
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
