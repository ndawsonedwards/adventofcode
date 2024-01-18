package adventofcode.solutions.day1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import adventofcode.interfaces.ISolver;

public class Day1Part2 implements ISolver<Integer> {


    final Map<String,String> replacementMap = new HashMap<>() ;


    @Override
    public Integer solve(Stream<String> input) {

        replacementMap.put("one", "o1e");
        replacementMap.put("two", "t2o");
        replacementMap.put("three", "th3ee");
        replacementMap.put("four", "fo4r");
        replacementMap.put("five", "fi5e");
        replacementMap.put("six", "s6x");
        replacementMap.put("seven", "se7en");
        replacementMap.put("eight", "ei8ht");
        replacementMap.put("nine", "ni9e");



        return input.map(this::replaceNumericWords)
            .filter(s -> !s.isEmpty())
            .map(this::replaceNonInteger)
            .map(this::getFirstAndLastDigit)
            .mapToInt(Integer::parseInt)
            .sum();
    }

    private String replaceNumericWords(String input) {
        for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
            input = input.replace(entry.getKey(), entry.getValue());
        }
        return input;
    }

    private String replaceNonInteger(String input) {
        return input.replaceAll("[a-z]", "");
    }

    private String getFirstAndLastDigit(String input) {
        return input.charAt(0) + "" + input.charAt(input.length() - 1);
    }

    
}
