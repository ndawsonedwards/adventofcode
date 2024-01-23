package adventofcode.solutions.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adventofcode.interfaces.ISolver;
import adventofcode.solutions.day3.Day3.PartNumber;

public class Day3Part2 implements ISolver<Integer> {


    private Integer width = 0;
    private Integer lineCount =  0;
    

    private SortedMap<Integer, PartNumber> numbers = new TreeMap<>();
    private List<Integer> specialIndices = new ArrayList<>();

    @Override
    public Integer solve(List<String> input) {

        input.forEach(s -> processLine(s));

        final int count = 2;
        List<Map<Integer, PartNumber>> found = Day3.findNeighbors(specialIndices, numbers, width, count);

        Integer gearRatio = 0;
        for (Map<Integer, PartNumber> item: found) {
            if (item.size() != count) {
                System.err.println(String.format("Expected %s entrys, found %s at list index %s", 
                                    count, item.size(), found.indexOf(item)));
                return -1;
            }

            Integer result = 1;
            for (Map.Entry<Integer, PartNumber> entry : item.entrySet()) {   
                result *= entry.getValue().value();
            }
            gearRatio += result;
        }
        return gearRatio;
    }


    public void processLine(String line) {
        if (width == 0) {
            width = line.length();
        }

        findSpecials(line, specialIndices);
        Day3.findNumbers(line, numbers, width, lineCount);
        lineCount++;
    }

    public void findSpecials(String line, List<Integer> found) {
        final Pattern specialPattern = Pattern.compile(Day3.asterixPattern);
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            Integer index = (width * lineCount) +  matcher.start();
            found.add(index);
        }
    }



}
