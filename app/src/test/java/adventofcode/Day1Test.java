/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package adventofcode;

import org.junit.jupiter.api.Test;

import adventofcode.scraper.InputScraper;
import adventofcode.solutions.day1.Day1Part1;
import adventofcode.solutions.day1.Day1Part2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Day1Test {


    private String replaceNonInteger(String input) {
        return input.replaceAll("[a-z]", "");
    }

    private String getFirstAndLastDigit(String input) {
        return input.charAt(0) + "" + input.charAt(input.length() - 1);
    }

    @Test 
    void inputFileIsValid() {

        final String knownLine = "8eight1";
        try {
            List<String> input = InputScraper.getInput("2023", "1");
            assertTrue(input.stream().anyMatch(s -> s.equals(knownLine)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }

    @Test
    void replaceNonIntegerTest(){
        Stream<String> knownStream = Stream.of("1abc2","pqr3stu8vwx","a1b2c3d4e5f","treb7uchet");

        Stream<String> replacedStream = knownStream.map(this::replaceNonInteger);
        String content = replacedStream.collect(Collectors.joining(","));

        final String expectedString = "12,38,12345,7";

        assertEquals(expectedString, content);
    }

    @Test
    void getFirstAndLastDigitTest(){
        Stream<String> knownStream = Stream.of("12","38","12345","7");
        Stream<String> modifiedStream = knownStream.map(this::getFirstAndLastDigit);
        String content = modifiedStream.collect(Collectors.joining(","));

        final String expectedString = "12,38,15,77";

        assertEquals(expectedString, content);
    }


    @Test 
    void Day1ExampleTest() {
        final Integer knownAnswer = 142;

        Stream<String> input = Stream.of("1abc2","pqr3stu8vwx","a1b2c3d4e5f","treb7uchet");
        Integer sum = input.map(this::replaceNonInteger)
                .filter(s -> !s.isEmpty())
                .map(this::getFirstAndLastDigit)
                .mapToInt(Integer::parseInt)
                .sum();

        assertEquals(knownAnswer, sum);
    }



    @Test
    void testDay1Part1Example() {

        Stream<String> knownStream = Stream.of("1abc2","pqr3stu8vwx","a1b2c3d4e5f","treb7uchet");

        final Integer knownAnswer = 142;
        Day1Part1 solution = new Day1Part1();
        assertEquals(knownAnswer, solution.solve(knownStream));

    }
    @Test
    void testDay1Part2Sample() {

        Stream<String> knownStream = Stream.of("two1nine",
                    "eightwothree",
                    "abcone2threexyz",
                    "xtwone3four",
                    "4nineeightseven2",
                    "zoneight234",
                    "7pqrstsixteen");

        final Integer knownAnswer = 281;

        Day1Part2 solution = new Day1Part2();
        assertEquals(knownAnswer, solution.solve(knownStream));

    }

    @Test 
    void testDay1Part1() {
        try {
            List<String> input = InputScraper.getInput("2023", "1");
            Integer value = new Day1Part1().solve(input.stream());
            System.out.println("Answer for Day 1 part 1 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test 
    void testDay1Part2() {
        try {
            List<String> input = InputScraper.getInput("2023", "1");
            Integer value = new Day1Part2().solve(input.stream());
            System.out.println("Answer for Day 1 part 2 is: " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}