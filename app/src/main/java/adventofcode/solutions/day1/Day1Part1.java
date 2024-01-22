package adventofcode.solutions.day1;

import java.util.List;
import adventofcode.interfaces.ISolver;

public class Day1Part1 implements ISolver<Integer>{

    @Override
    public Integer solve(List<String> input) {
        return input.stream()
            .map(this::replaceNonInteger)
            .filter(s -> !s.isEmpty())
            .map(this::getFirstAndLastDigit)
            .mapToInt(Integer::parseInt)
            .sum();
    }

    private String replaceNonInteger(String input) {
        return input.replaceAll("[a-z]", "");
    }

    private String getFirstAndLastDigit(String input) {
        return input.charAt(0) + "" + input.charAt(input.length() - 1);
    }
}
