package adventofcode.solutions.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adventofcode.interfaces.ISolver;
import adventofcode.solutions.day3.Day3.PartNumber;

public class Day3Part1 implements ISolver<Integer>{

    private Integer width = 0;
    public Integer getWidth() {
        return width;
    }

    private Integer lineCount = 0;
    
    /**
     *  Constainer used to house found values. key is start index of number as it is unique 
     */
    private SortedMap<Integer, PartNumber> numbers = new TreeMap<>();
    private List<Integer> specialIndices = new ArrayList<>();


    @Override
    public Integer solve(List<String> input) {

        input.forEach(s -> processLine(s));

        List<PartNumber> result = Day3.findNeighbors(specialIndices, numbers, width);
        Integer sum = 0;
        for (PartNumber part : result) {
            sum += part.value();
        }
        return sum;
    }
    

    public void processLine(String line) {
        if (width == 0) {
            width = line.length();
        }

        findSpecials(line, specialIndices);
        Day3.findNumbers(line, numbers,width, lineCount);
        lineCount++;
    }


    public void findSpecials(String line, List<Integer> found) {
        final Pattern specialPattern = Pattern.compile(Day3.allSpecialPattern);
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            Integer index = (width * lineCount) +  matcher.start();
            found.add(index);
        }
    }

    public SortedMap<Integer, PartNumber> getNumbers() {
        return numbers;
    }
    public List<Integer> getSpecialIndices() {
        return specialIndices;
    }


}

