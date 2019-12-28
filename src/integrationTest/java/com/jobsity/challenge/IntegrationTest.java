package com.jobsity.challenge;

import com.jobsity.challenge.model.Greeter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

    @Test
    public void integrationTest(){
        Assertions.assertTrue(Greeter.isGreet());
    }


}
