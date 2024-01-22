package adventofcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import adventofcode.scraper.InputScraper;
import adventofcode.solutions.day3.Day3Part1;
import adventofcode.solutions.day3.Day3Part1.PartNumber;


public class Day3Test {
    

    @Test
    public void testFindSpecial() {
        String line = "..380.................553..559..105.....749.............*..........*.........*.............-968....@.....*..*....908..603...................";
        List<Integer> found = new ArrayList<>();

        List<Integer> expected = new ArrayList<>();
        Collections.addAll(expected, 56,67,77,91,99,105,108);
        
        new Day3Part1().findSpecials(line, found);
        assertEquals(expected.size(), found.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i) , found.get(i));
        }   
    }

    @Test
    public void testFindNumber() {
        String line = "..380.................553..559..105.....749.............*..........*.........*.............-968....@.....*..*....908..603...................";
        SortedMap<Integer, Day3Part1.PartNumber> expected = new TreeMap<>();
        expected.put(2, new PartNumber(2, 4, 380));
        expected.put(22, new PartNumber(22, 24, 553));
        expected.put(27, new PartNumber(27, 29, 559));
        expected.put(32, new PartNumber(32, 34, 105));
        expected.put(40, new PartNumber(40, 42, 749));
        expected.put(92, new PartNumber(92, 94, 968));
        expected.put(113, new PartNumber(113, 115, 908));
        expected.put(118, new PartNumber(118, 120, 603));

        SortedMap<Integer, PartNumber> found = new TreeMap<>();     
        new Day3Part1().findNumbers(line, found);
        assertEquals(expected.size(), found.size());

        expected.forEach((key, value) -> {
            assertTrue(found.containsKey(key));
            
            assertEquals(value.endIndex(), found.get(key).endIndex());
            assertEquals(value.startIndex(), found.get(key).startIndex());
            assertEquals(value.value(), found.get(key).value());
        });
    }


    @Test
    public void testNearestNeighbor(){
        Day3Part1 solver = new Day3Part1();

        String[] sample = {"....................................18..........889.270.....748.280...997.................617..............622........763............476...*",
                           "6..529......434.....191..489...717...@.....................&....................939*7.....*....................606............760....*......",
                           "....*...473....*221................$........182......812........493.84....793..........794.......589..407..41...*.....................68...9"};

        solver.width = sample[0].length();


        for (int i = 0; i < sample.length; i++) {
            solver.processLine(sample[i]);
        }

        List<PartNumber> result = solver.findNeighbors(solver.getSpecialIndices(), solver.getNumbers());

        for (PartNumber i : result) {
            System.out.println(String.valueOf(i.value()));
        }

        assertEquals(12, result.size());

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
        List<String> input = Arrays.asList(sample);
        final Integer expected = 4361;

        Integer value = new Day3Part1().solve(input);
        assertEquals(expected, value);

        String[] sample2 = {"467..114.*",
                            "...*......",
                            "..35..633.",
                            "......#...",
                            "617.*.....",
                            ".....+.58.",
                            "^.592.....",
                            "^.....755.",
                            "...$.*....",
                            ".664.598.."};
        input = Arrays.asList(sample2);
        final Integer expected2 = 3744;

        value = new Day3Part1().solve(input);
        assertEquals(expected2, value);

        String[] sample3 = {"46........",
                            "47.*......",
                            "48........"};
        input = Arrays.asList(sample3);
        value = new Day3Part1().solve(input);
        assertEquals(0, value);

        String[] sample4 = {"....5.....",
                            "..*.55....",
                            "....555..."};
        input = Arrays.asList(sample4);
        value = new Day3Part1().solve(input);
        assertEquals(0, value);


    }


    @Test 
    void testDay3Part1() {
        try {
            List<String> input = InputScraper.getInput("2023", "3");
            Integer value = new Day3Part1().solve(input);
            System.out.println("Answer for Day 3 part 1 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
