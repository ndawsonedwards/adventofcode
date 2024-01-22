package adventofcode.solutions.day3;

import java.util.regex.*;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import java.util.ArrayList;
import adventofcode.interfaces.ISolver;

public class Day3Part1 implements ISolver<Integer>{

    public final Pattern numberPattern = Pattern.compile("(\\d+)");

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


    public List<PartNumber> findNeighbors(List<Integer> target, SortedMap<Integer, PartNumber> numbers) {
        SortedMap<Integer,PartNumber> potential = new TreeMap<>();        
        for (Integer i : target) {
            //Add all start indexes surrounding special char
            System.out.println("---- " + i + " ---- width = " + width);
            System.out.println((i - width - 3) + " to " + (i - width + 1));
            System.out.println((i - 3) + " to " + (i + 1));
            System.out.println((i + width - 3) + " to " + (i + width + 1));

            // subMap: fromKey (inclusive) -> toKey (exclusive)
            potential.putAll(numbers.subMap(i - width - 3, i - width + 2));
            potential.putAll(numbers.subMap(i - 3, i + 2));
            potential.putAll(numbers.subMap(i + width - 3, i + width + 2));
        }

        System.out.println("------- potentials ---------");
        for (Map.Entry<Integer, PartNumber> entry : potential.entrySet()) {
            System.out.println(String.format("[%d,%d,%d]", entry.getValue().startIndex,entry.getValue().endIndex, entry.getValue().value));
        }
        return new ArrayList<PartNumber>(potential.values());
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

