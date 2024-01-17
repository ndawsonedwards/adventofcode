package adventofcode;

import java.io.IOException;
import java.util.List;
import adventofcode.scraper.InputScraper;
import adventofcode.solutions.day1.Day1Part1;

public class App {

    public static void main(String[] args) {

        try  {
            List<String> input = InputScraper.getInput("2023", "1");

            Integer value = new Day1Part1().solve(input.stream());
            System.out.println("Answer for Day 1 part 1 is: " + value);

            Day1Part1 Day1Part2Solver = new Day1Part1();
            value = Day1Part2Solver.solve(input.stream());
            System.out.println("Answer for Day 1 part 2 is: " + value);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
