package com.jobsity.challenge.utils;

import com.jobsity.challenge.model.Result;

public class TextUtils {
    public static Result lineToResult(String line) {
        String[] elements = line.split("\t");
        if(elements[1].equals("F")){
            return new Result(elements[0], null);
        }
        return new Result(elements[0], Integer.parseInt(elements[1]));
    }
}
