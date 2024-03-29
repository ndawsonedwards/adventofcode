package adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import adventofcode.scraper.InputScraper;
import adventofcode.solutions.day2.Day2Part1;
import adventofcode.solutions.day2.Day2Part2;

public class Day2Test {
    
    @Test
    void testPatternMatcher() {
        String sampleLine  = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertTrue(Day2Part1.isLinePossible(sampleLine));

        sampleLine  = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        assertTrue(Day2Part1.isLinePossible(sampleLine));

        sampleLine = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertFalse(Day2Part1.isLinePossible(sampleLine));

        sampleLine = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        assertFalse(Day2Part1.isLinePossible(sampleLine));
        
        sampleLine = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        assertTrue(Day2Part1.isLinePossible(sampleLine));

    }

    @Test
    void testColorCount() {

        String sampleLine  = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        Integer Bluecount = Day2Part1.findInvalidCount(sampleLine, Day2Part1.BLUE_PATTERN);
        assertEquals(0, Bluecount);
 
        Integer GreenCount = Day2Part1.findInvalidCount(sampleLine, Day2Part1.GREEN_PATTERN);
        assertEquals(0, GreenCount);

        Integer RedCount = Day2Part1.findInvalidCount(sampleLine, Day2Part1.RED_PATTERN);
        assertEquals(0, RedCount);


        sampleLine  = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        Bluecount = Day2Part1.findInvalidCount(sampleLine, Day2Part1.BLUE_PATTERN);
        assertEquals(15, Bluecount);

        GreenCount = Day2Part1.findInvalidCount(sampleLine, Day2Part1.GREEN_PATTERN);
        assertEquals(0, GreenCount);

        RedCount = Day2Part1.findInvalidCount(sampleLine, Day2Part1.RED_PATTERN);
        assertEquals(14, RedCount);

    }

    @Test
    void testGameCount()
    {
        Day2Part1 solver = new Day2Part1();
        String sampleLine  = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertEquals("1", solver.getGameCount(sampleLine));

        sampleLine  = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        assertEquals("2", solver.getGameCount(sampleLine));

        sampleLine = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertEquals("3", solver.getGameCount(sampleLine));

        sampleLine = "Game 44: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        assertEquals("44", solver.getGameCount(sampleLine));
        
        sampleLine = "Game 94351: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        assertEquals("94351", solver.getGameCount(sampleLine));
    }

    @Test
    void testProvidedSample() {
        String[] sample = {"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n",
                                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n",
                                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n",
                                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n",
                                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        };
        List<String> input = Arrays.asList(sample);
        
        Integer value = new Day2Part1().solve(input);
        assertEquals(8, value);
    }

    @Test
    public void testGetMaxValue() {
        String input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";

        assertEquals(6, Day2Part2.getMaxValue(input, "blue"));
        assertEquals(20, Day2Part2.getMaxValue(input, "red"));
        assertEquals(13, Day2Part2.getMaxValue(input, "green"));

    }


    @Test 
    void testDay1Part1() {
        try {
            List<String> input = InputScraper.getInput("2023", "2");
            Integer value = new Day2Part1().solve(input);
            System.out.println("Answer for Day 2 part 1 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test 
    void testDay2Part2Sample() {

        String[] sample = {"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        };

        List<String> input = Arrays.asList(sample);

        Integer value = new Day2Part2().solve(input);
        assertEquals(2286, value);
    }

    @Test 
    void testDay2Part2() {
        try {
            List<String> input = InputScraper.getInput("2023", "2");
            Integer value = new Day2Part2().solve(input);
            System.out.println("Answer for Day 2 part 2 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
