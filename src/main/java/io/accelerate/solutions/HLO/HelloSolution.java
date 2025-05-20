package io.accelerate.solutions.HLO;

import io.accelerate.runner.SolutionNotImplementedException;

public class HelloSolution {
    private static final String defaultTo = "World";
    public String hello(String friendName) {
        String toWhom = isStrValid (friendName) ?  friendName.trim(): defaultTo;
        return "Hello " +  toWhom + "!";
    }

    private boolean isStrValid (String in){
        if (in == null || in.trim().isEmpty()) {
            return false;
        }
        else{
            return true;
        }
    }
}



