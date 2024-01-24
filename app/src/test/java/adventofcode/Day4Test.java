package adventofcode;


import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import adventofcode.scraper.InputScraper;
import adventofcode.solutions.day4.Day4Part1;
import adventofcode.solutions.day4.Day4Part2;

public class Day4Test {

    @Test
    void testDay4Part1Sample(){

        String[] sample  = {"Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                          "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                          "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                          "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                          "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                          "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"};
        List<String> input = Arrays.asList(sample);

        Integer expected = 13;
        Integer value = new Day4Part1().solve(input);
        assertEquals(expected, value);

    }

    @Test
    void testDay4Part2Sample(){

        String[] sample  = {"Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"};
        List<String> input = Arrays.asList(sample);

        Integer expected = 30;
        Integer value = new Day4Part2().solve(input);
        assertEquals(expected, value);

    }


    @Test 
    void testDay4Part1() {
        try {
            List<String> input = InputScraper.getInput("2023", "4");
            Integer value = new Day4Part1().solve(input);
            System.out.println("Answer for Day 4 part 1 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test 
    void testDay4Part2() {
        try {
            List<String> input = InputScraper.getInput("2023", "4");
            Integer value = new Day4Part2().solve(input);
            System.out.println("Answer for Day 4 part 2 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
