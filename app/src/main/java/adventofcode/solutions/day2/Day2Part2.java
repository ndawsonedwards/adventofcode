package adventofcode.solutions.day2;

import java.util.stream.Stream;

import java.util.regex.*;
import adventofcode.interfaces.ISolver;

public class Day2Part2 implements ISolver<Integer> {


    @Override
    public Integer solve(Stream<String> input) {
        return input.mapToInt(this::getPowerPerGame)
            .sum();
    }
    

    public static Integer getMaxValue(String input, String color) {

        Integer value= Integer.MIN_VALUE;
        Pattern pattern = Pattern.compile("(\\d+)\\s+" + color);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            Integer gameValue =Integer.parseInt(matcher.group(1));
            value = (value < gameValue) ? gameValue : value;
        }
        return value;
    }
    private Integer getPowerPerGame(String input) {
        Integer power = getMaxValue(input, "blue") * getMaxValue(input, "green") * getMaxValue(input, "red");
        return power;
    }

}
