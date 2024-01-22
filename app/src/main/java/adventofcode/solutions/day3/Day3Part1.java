package adventofcode.solutions.day3;

import java.util.stream.Stream;
import java.util.regex.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;



import adventofcode.interfaces.ISolver;

public class Day3Part1 implements ISolver<Integer>{

    public final Pattern numberPattern = Pattern.compile("(\\d+)");

    private int width = 0;
    private int line = 0;
    
    /**
     *  Constainer used to house found values. key is start index of number as it is unique 
     */
    private Map<Integer, PartNumber> found = new HashMap<>();
    private List<Integer> specialIndices = new ArrayList<>();


    @Override
    public Integer solve(Stream<String> input) {

        width = input.findFirst().get().length();
        return input.mapToInt(this::processLine)
            .sum();
    }
    

    public Integer processLine(String line) {
        return 0;
    }

    public static void findSpecial(String line, List<Integer> found) {
        final Pattern specialPattern = Pattern.compile("[^A-Za-z0-9\\.]");
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            found.add(matcher.start());
        }
    }

    public static void findNumbers(String line, Map<Integer, PartNumber> found) {
        final Pattern specialPattern = Pattern.compile("(\\d+)");
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            PartNumber number = new PartNumber(matcher.start(),matcher.end(),Integer.parseInt(matcher.group(1)));
            found.put(number.startIndex, number);
        }
    }

    private void addSurroundingValues(int line, int index) {

    }

}

