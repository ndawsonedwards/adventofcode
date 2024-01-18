package adventofcode.solutions.day2;

import java.util.regex.*;
import java.util.stream.Stream;

import adventofcode.interfaces.ISolver;

public class Day2Part1 implements ISolver<Integer>{

    /**
     * Regex that captures a ## Blue, where ## is larger than the max value for the color
     */
    public final static Pattern BLUE_PATTERN = Pattern.compile("(1[5-9]|[2-9]\\d|\\d{3,})\\s+blue");
    public final static Pattern RED_PATTERN = Pattern.compile("(1[3-9]|[2-9]\\d|\\d{3,})\\s+red");
    public final static Pattern GREEN_PATTERN = Pattern.compile("(1[4-9]|[2-9]\\d|\\d{3,})\\s+green");
    public final static Pattern GAME_PATTERN = Pattern.compile("Game (\\d+):");


    @Override
    public Integer solve(Stream<String> input) {
        return input.filter(s -> isLinePossible(s))
            .map(this::getGameCount)
            .mapToInt(Integer::parseInt)
            .sum();
    }
    
    public String getGameCount(String input) {
        
        Matcher matcher = GAME_PATTERN.matcher(input);
        matcher.find();
        return matcher.group(1);
    }

    public static Integer findInvalidCount(String input, Pattern pattern)
    {
        Integer Count = 0;
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            Count += Integer.parseInt(matcher.group(1));
        }

        return Count;
    }

    public static Boolean isLinePossible(String input) {

        if (findInvalidCount(input, RED_PATTERN) > 0) {
            return false;
        }
        if (findInvalidCount(input, BLUE_PATTERN) > 0) {
            return false;
        }
        if (findInvalidCount(input, GREEN_PATTERN) > 0) {
            return false;
        }
        

        return true;
    }
}
