package adventofcode.solutions.day4;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import adventofcode.interfaces.ISolver;

public class Day4Part1 implements ISolver<Integer>{

    @Override
    public Integer solve(List<String> input) {
        return input.stream()
            .mapToInt(this::calcWinners)
            .sum();
    }

    protected Integer calcWinners(String line) {
        String[] split = line.split("[:|]");
        String[] winners = split[1].trim().replaceAll("\\s+","-").split("-");
        String[] yourNumbers = split[2].trim().replaceAll("\\s+","-").split("-");

        Set<String> winnerSet = new HashSet<String>(Arrays.asList(winners));
        Set<String> numberSet = new HashSet<String>(Arrays.asList(yourNumbers));

        numberSet.retainAll(winnerSet);

        Double points = Math.pow(2, numberSet.size()-1 );
        return  points.intValue();

    }

}
