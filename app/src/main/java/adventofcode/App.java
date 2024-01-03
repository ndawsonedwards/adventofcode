package adventofcode;

import java.io.IOException;
import java.util.stream.Stream;

import adventofcode.scraper.InputScraper;
import adventofcode.solutions.day1.Day1Part1;

public class App {

    public static void main(String[] args) {

        try ( Stream<String> input = InputScraper.getInput("2023", "1")) {
            Day1Part1 Solution = new Day1Part1();
            Integer value = Solution.solve(input);
            System.out.println("Answer for Day 1 part 1 is: " + value);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
