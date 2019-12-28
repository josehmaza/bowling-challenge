package com.jobsity.challenge;

import com.jobsity.challenge.model.Greeter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HelloWorldTest {

    @Test
    public void helloWorld() {
        Assertions.assertTrue(Greeter.isGreet2());
    }

}
