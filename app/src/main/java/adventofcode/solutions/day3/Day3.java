package adventofcode.solutions.day3;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    
    public record PartNumber(int startIndex, int endIndex, int value) {}

    public static final String numberPattern = "(\\d+)";
    public static  final String allSpecialPattern = "[^A-Za-z0-9\\.]";
    public static final String asterixPattern = "\\*";


    public static void findNumbers(String line, SortedMap<Integer, PartNumber> found, int width, int lineCount) {
        final Pattern specialPattern = Pattern.compile(numberPattern);
        Matcher matcher = specialPattern.matcher(line);
        while (matcher.find()) {
            int baseIndex = width * lineCount;
            
            //end() returns index after last found
            PartNumber number = new PartNumber(matcher.start() + baseIndex,
                                            matcher.end() - 1 + baseIndex,
                                            Integer.parseInt(matcher.group(1)));
            found.put(number.startIndex(), number);
        }
    }



    private static void captureNeighbors(SortedMap<Integer, PartNumber> fullNumberMap, 
                                        SortedMap<Integer,PartNumber> neighborMap,
                                        int charIndex,
                                        int width ) {
            //Add all start indexes surrounding special char
            final int leftReach = 3;
            final int rightReach = 2;
            int leftIndex = ((charIndex % width) > leftReach) ? (charIndex-leftReach) : (charIndex - (charIndex % width));
            int rightIndex = ((charIndex % width + rightReach) >= (width - 1)) ? charIndex : (charIndex + rightReach);

            // subMap: fromKey (inclusive) -> toKey (exclusive)
            neighborMap.putAll(fullNumberMap.subMap(leftIndex - width, rightIndex - width));
            neighborMap.putAll(fullNumberMap.subMap(leftIndex, rightIndex));
            neighborMap.putAll(fullNumberMap.subMap(leftIndex + width, rightIndex + width));
            
    }

    public static  List<Map<Integer,PartNumber>> findNeighbors(List<Integer> specialIndices, 
                                                    SortedMap<Integer, PartNumber> numbers,
                                                    int width,
                                                    int count){

        List<Map<Integer,PartNumber>> found = new ArrayList<>();
        for (Integer i : specialIndices) {    
            SortedMap<Integer,PartNumber> potential = new TreeMap<>();
            captureNeighbors(numbers, potential, i, width);
            removeWraparound(width, i, potential);

            if ( potential.size() == count) {
                found.add(potential);
            }
        }

        System.out.println("----------Found--------------");
        for (Map<Integer,PartNumber> mapEntry : found) {
            for (Map.Entry<Integer, PartNumber> entry : mapEntry.entrySet()) {   
                System.out.println(String.format("[%s,%s,%s]", entry.getValue().value, entry.getValue().startIndex, entry.getValue().endIndex));
            }
        }

        return found;

    }


    public static  List<PartNumber> findNeighbors(List<Integer> specialIndices, 
                                                SortedMap<Integer, PartNumber> numbers,
                                                int width){

        SortedMap<Integer,PartNumber> found = new TreeMap<>();         
        for (Integer i : specialIndices) {

            SortedMap<Integer,PartNumber> potential = new TreeMap<>();
            captureNeighbors(numbers, potential, i, width);
            removeWraparound(width, i, potential);
            found.putAll(potential);
        }

        System.out.println("----------Found--------------");
        for (Map.Entry<Integer, PartNumber> entry : found.entrySet()) {   
            System.out.println(String.format("[%s,%s,%s]", entry.getValue().value, entry.getValue().startIndex, entry.getValue().endIndex));
        }

        return new ArrayList<PartNumber>(found.values());

    }


    private static void removeWraparound(int width, Integer charIndex, SortedMap<Integer, PartNumber> potential) {
        Set<Integer> removalSet = new HashSet<>();
        for (Map.Entry<Integer, PartNumber> entry : potential.entrySet()) {
            if ((entry.getValue().endIndex() % width) < (charIndex % width -1)) {
                removalSet.add(entry.getKey());
            }
        }
        potential.keySet().removeAll(removalSet);
    }


}
