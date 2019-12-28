package com.jobsity.challenge.model;

import org.joda.time.LocalTime;

public class Greeter {
    public static Boolean isGreet(){
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: "+currentTime );
        return true;
    }
    public static Boolean isGreet2(){
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: "+currentTime );
        return true;
    }
}
