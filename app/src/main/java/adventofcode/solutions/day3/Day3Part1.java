package adventofcode.solutions.day3;

import java.util.regex.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import java.util.ArrayList;
import java.util.HashSet;

import adventofcode.interfaces.ISolver;

public class Day3Part1 implements ISolver<Integer>{

    public final Pattern numberPattern = Pattern.compile("(\\d+)");

    public record PartNumber(int startIndex, int endIndex, int value) {}
    

    public Integer width = 0;
    private Integer lineCount = 0;
    
    /**
     *  Constainer used to house found values. key is start index of number as it is unique 
     */
    private SortedMap<Integer, PartNumber> numbers = new TreeMap<>();
    private List<Integer> specialIndices = new ArrayList<>();


    @Override
    public Integer solve(List<String> input) {

        input.forEach(s -> processLine(s));

        List<PartNumber> result = findNeighbors(specialIndices,  numbers);
        Integer sum = 0;
        for (PartNumber part : result) {
            System.out.println(part.value);
            sum += part.value;
        }
        return sum;
    }
    

    public void processLine(String line) {
        if (width == 0) {
            width = line.length();
        }

        findSpecials(line, specialIndices);
        findNumbers(line, numbers);
        lineCount++;

    }


    public List<PartNumber> findNeighbors(List<Integer> specialIndices, SortedMap<Integer, PartNumber> numbers) {
        SortedMap<Integer,PartNumber> found = new TreeMap<>();         
        for (Integer i : specialIndices) {
            //Add all start indexes surrounding special char
            final int leftReach = 3;
            final int rightReach = 2;
            int leftIndex = ((i % width) > leftReach) ? (i-leftReach) : (i - (i % width));
            int rightIndex = ((i % width + rightReach) >= (width - 1)) ? i : (i + rightReach);

            System.out.println("---- " + i + " ---- width = " + width);
            System.out.println((leftIndex - width) + " to " + (rightIndex - width));
            System.out.println((leftIndex) + " to " + (rightIndex));
            System.out.println((leftIndex + width) + " to " + (rightIndex + width));

            // subMap: fromKey (inclusive) -> toKey (exclusive)
            SortedMap<Integer,PartNumber> potential = new TreeMap<>();
            potential.putAll(numbers.subMap(leftIndex - width, rightIndex - width));
            potential.putAll(numbers.subMap(leftIndex, rightIndex));
            potential.putAll(numbers.subMap(leftIndex + width, rightIndex + width));

            Set<Integer> removalSet = new HashSet<>();
            for (Map.Entry<Integer, PartNumber> entry : potential.entrySet()) {
                if ((entry.getValue().endIndex % width) < (i % width -1)) {
                    removalSet.add(entry.getKey());
                }
            }
            potential.keySet().removeAll(removalSet);
            found.putAll(potential);
        }

        return new ArrayList<PartNumber>(found.values());
    }

    public void findSpecials(String line, List<Integer> found) {
        final Pattern specialPattern = Pattern.compile("[^A-Za-z0-9\\.]");
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            Integer index = (width * lineCount) +  matcher.start();
            found.add(index);
        }
    }

    public void findNumbers(String line, SortedMap<Integer, PartNumber> found) {
        final Pattern specialPattern = Pattern.compile("(\\d+)");
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            int baseIndex = width * lineCount;
            //end() returns index after last found
            PartNumber number = new PartNumber(matcher.start() + baseIndex,
                                            matcher.end() - 1 + baseIndex,
                                            Integer.parseInt(matcher.group(1)));
            found.put(number.startIndex, number);
        }
    }

    public SortedMap<Integer, PartNumber> getNumbers() {
        return numbers;
    }
    public List<Integer> getSpecialIndices() {
        return specialIndices;
    }


}

