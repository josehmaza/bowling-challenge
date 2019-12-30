package com.jobsity.challenge.services;

import com.jobsity.challenge.exception.BadInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextInputServiceTest {
    private static TextInputService textInputService;
    @BeforeAll
    static void setUp()  {
        textInputService = TextInputService.getInstance();
    }

    @DisplayName("When incorrect format line throw BadInputException")
    @Test
    void throw_BadInputException_for_incorrect_line_format() {
        Exception exception = assertThrows(BadInputException.class, () -> {
            textInputService.validateLine("Hernan   ");
            textInputService.validateLine("Hernan   -");
        });
        String expectedMessage = "Format line is incorrect";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @DisplayName("When score is not betweeen 0 to 10 throw BadInputException")
    @Test
    void throw_BadInputException_for_score_not_between_0_10() {
        Exception exception = assertThrows(BadInputException.class, () -> {
            //textInputService.validateLine("Hernan\t11");
            textInputService.validateLine("Hernan\t-2");

        });
        String expectedMessage = "The score must be between 0 to 10";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @DisplayName("When score is a string, it must be F")
    @Test
    void throw_BadInputException_for_score_string_different_to_F() {
        Exception exception = assertThrows(BadInputException.class, () -> {
            //Uncomment for test in other cases
            //textInputService.validateLine("Jeff\tf");
            //textInputService.validateLine("Jeff\tG");
            textInputService.validateLine("Jeff\tK");

        });
        String expectedMessage = "The score must be F or number(0-10)";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
