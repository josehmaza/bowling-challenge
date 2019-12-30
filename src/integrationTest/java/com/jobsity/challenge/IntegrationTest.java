package com.jobsity.challenge;

import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Game;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.services.BowlingService;
import com.jobsity.challenge.services.IBowlingService;
import com.jobsity.challenge.services.impl.TextInputService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

public class IntegrationTest {
    private static TextInputService textInputService;
    private static IBowlingService bowlingService;

    @BeforeAll
    static void setUp() {
        textInputService = TextInputService.getInstance();
        bowlingService = BowlingService.getInstance();
    }

    @BeforeEach
    void cleanGames() {
        bowlingService.cleanGames();
    }

    @DisplayName("Test for sample input: Expected score 167 for Jeff and 151 score for John. Also will not throw exception")
    @Test
    public void sample_input() {
        assertDoesNotThrow(() -> {
            //try {
            List<Result> results = this.textInputService.getResults("sample-input.txt");
            for (Result result : results) {
                bowlingService.addRoll(result);
            }

            bowlingService.calculateScores();
            Game jeffGame = bowlingService.getGames().stream().filter(g -> g.getPlayer().getName().equals("Jeff")).findAny().orElse(null);
            Integer actualScoreForJeff = jeffGame.getFrames().get(jeffGame.getFrames().size() - 1).getScoreFrame();
            Integer expectScoreForJeff = 167;
            assertEquals(expectScoreForJeff, actualScoreForJeff);

            Game johnGame = bowlingService.getGames().stream().filter(g -> g.getPlayer().getName().equals("John")).findAny().orElse(null);

            Integer actualScoreForJohn = johnGame.getFrames().get(jeffGame.getFrames().size() - 1).getScoreFrame();
            Integer expectScoreForJohn = 151;
            assertEquals(expectScoreForJohn, actualScoreForJohn);
        });


    }

    @DisplayName("Test for 1 player with perfect score: Expected 300")
    @Test
    public void perfect_score() {
        assertDoesNotThrow(() -> {

            List<Result> results = this.textInputService.getResults("perfect-score-input.txt");
            for (Result result : results) {
                bowlingService.addRoll(result);
            }

            bowlingService.calculateScores();
            Game jeffGame = bowlingService.getGames().stream().filter(g -> g.getPlayer().getName().equals("Jeff")).findAny().orElse(null);
            Integer actualScoreForJeff = jeffGame.getFrames().get(jeffGame.getFrames().size() - 1).getScoreFrame();
            Integer expectScoreForJeff = 300;
            assertEquals(expectScoreForJeff, actualScoreForJeff);
        });
    }

    @DisplayName("Test for 1 player with zero score: Expected 0")
    @Test
    public void zero_score() {
        assertDoesNotThrow(() -> {

            List<Result> results = this.textInputService.getResults("zero-score-input.txt");
            for (Result result : results) {
                bowlingService.addRoll(result);
            }
            bowlingService.calculateScores();
            Game jeffGame = bowlingService.getGames().stream().filter(g -> g.getPlayer().getName().equals("Jeff")).findAny().orElse(null);
            Integer actualScoreForJeff = jeffGame.getFrames().get(jeffGame.getFrames().size() - 1).getScoreFrame();
            Integer expectScoreForJeff = 0;
            assertEquals(expectScoreForJeff, actualScoreForJeff);
        });
    }

    @DisplayName("When last frame has less than 3 rolls throw BreakRuleBowlingException")
    @Test
    void throw_error_for_incomplete_rolls_last_frame() {
        Exception exception = assertThrows(BreakRuleBowlingException.class, () -> {
            List<Result> results = this.textInputService.getResults("jh-sample-input.txt");
                for (Result result : results) {
                    bowlingService.addRoll(result);
                }
                bowlingService.calculateScores();

          });
        String expectedMessage = "must throw 3 rolls in the last frame";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }


}
