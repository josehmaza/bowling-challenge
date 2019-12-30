package com.jobsity.challenge.services;

import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.utils.TextUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BowlingServiceTest {
    private static BowlingService bowlingService;
    @BeforeAll
    static void setUp()  {
         bowlingService = BowlingService.getInstance();
    }


    @DisplayName("When a bowler has 2 rolls and its knocked down pins sum is more than 10. expected BreakRuleException")
    @Test
    void throw_break_rule_exeption_sum_more_10() {
        assertThrows(BreakRuleBowlingException.class, () -> {
            bowlingService.addRoll(new Result("Jeff", 3));
            bowlingService.addRoll(new Result("Jeff", 8));
        });
    }
    @DisplayName("When is last frame 3 rolls are allowed")
    @Test
    void throwBreakRuleException_for_more_3_rolls_last_frame() {
        Exception exception = assertThrows(BreakRuleBowlingException.class, () -> {
            List<Result> results = Arrays.asList(
                    "Jeff	10",
                    "Jeff	7",
                    "Jeff	3",
                    "Jeff	9",
                    "Jeff	0",
                    "Jeff	10",
                    "Jeff	0",
                    "Jeff	8",
                    "Jeff	8",
                    "Jeff	2",
                    "Jeff	F",
                    "Jeff	6",
                    "Jeff	10",
                    "Jeff	10",
                    "Jeff	10",//
                    "Jeff	8",//
                    "Jeff	1",//
                    "Jeff	1"// one more roll
            ).stream().map(line -> TextUtils.lineToResult(line)).collect(Collectors.toList());
            for (Result result : results) {
                bowlingService.addRoll(result);
            }
        });
        String expectedMessage = "No more rolls allowed";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void calculateScores() {
    }
}
