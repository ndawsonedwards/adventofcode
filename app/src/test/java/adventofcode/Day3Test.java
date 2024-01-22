package adventofcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import adventofcode.solutions.day3.Day3Part1;
import adventofcode.solutions.day3.PartNumber;

public class Day3Test {
    

    @Test
    public void testFindSpecial() {
        String line = "..380.................553..559..105.....749.............*..........*.........*.............-968....@.....*..*....908..603...................";
        List<Integer> found = new ArrayList<>();

        List<Integer> expected = new ArrayList<>();
        Collections.addAll(expected, 56,67,77,91,99,105,108);
        
        Day3Part1.findSpecial(line, found);
        assertEquals(expected.size(), found.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i) , found.get(i));
        }   
    }
    @Test
    public void testFindNumber() {
        String line = "..380.................553..559..105.....749.............*..........*.........*.............-968....@.....*..*....908..603...................";
        Map<Integer, PartNumber> expected = new HashMap<>();
        expected.put(2, new PartNumber(2, 5, 380));
        expected.put(22, new PartNumber(22, 25, 553));
        expected.put(27, new PartNumber(27, 30, 559));
        expected.put(32, new PartNumber(32, 35, 105));
        expected.put(40, new PartNumber(40, 43, 749));
        expected.put(92, new PartNumber(92, 95, 968));
        expected.put(113, new PartNumber(113, 116, 908));
        expected.put(118, new PartNumber(118, 121, 603));


        Map<Integer, PartNumber> found = new HashMap<>();     
        Day3Part1.findNumbers(line, found);
        assertEquals(expected.size(), found.size());

        expected.forEach((key, value) -> {
            assertTrue(found.containsKey(key));
            
            assertEquals(value.endIndex, found.get(key).endIndex);
            assertEquals(value.startIndex, found.get(key).startIndex);
            assertEquals(value.value, found.get(key).value);
        });
    }



    @Test
    void testDay1Sample() {
        String[] sample = {"467..114..",
                        "...*......",
                        "..35..633.",
                        "......#...",
                        "617*......",
                        ".....+.58.",
                        "..592.....",
                        "......755.",
                        "...$.*....",
                        ".664.598.."};
        final Integer expected = 4361;

        Integer value = new Day3Part1().solve(Stream.of(sample));
        assertEquals(expected, value);

    }
}
