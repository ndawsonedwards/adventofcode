package adventofcode.solutions.day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;

import adventofcode.interfaces.ISolver;

public class Day4Part2 implements ISolver<Integer> {

    

    @Override
    public Integer solve(List<String> input) {
        Map<Integer, Integer> gameMap = new HashMap<>();
        input.forEach(line -> addToMap(line, gameMap));

        return gameMap.values().stream().reduce(0, (arg0, arg1) -> Integer.sum(arg0, arg1));
    }

    protected void addToMap(String line, Map<Integer, Integer> gameMap) {

        Integer cardNumber = getCardNumber(line);
        Integer winnerCount = getWinnerCount(line);

        addOriginalToMap(gameMap, cardNumber);
        addDuplicatesToMap(gameMap, cardNumber, winnerCount);
    }

    private void addDuplicatesToMap(Map<Integer, Integer> gameMap, Integer cardNumber, Integer winnerCount) {
        for (int i = cardNumber + 1 ; i <= cardNumber + winnerCount; i++) {
            gameMap.put(i, gameMap.getOrDefault(i, 0) + gameMap.get(cardNumber));
        }
    }

    private void addOriginalToMap(Map<Integer, Integer> gameMap, Integer cardNumber) {
        if ( gameMap.containsKey(cardNumber)) 
        {
            gameMap.put(cardNumber, gameMap.get(cardNumber) +1);
        }else {
            gameMap.put(cardNumber, 1);
        }
    }

    private Integer getWinnerCount(String line) {
        String[] split = line.split("[:|]");
        String[] winners = split[1].trim().replaceAll("\\s+","-").split("-");
        String[] yourNumbers = split[2].trim().replaceAll("\\s+","-").split("-");

        Set<String> winnerSet = new HashSet<String>(Arrays.asList(winners));
        Set<String> numberSet = new HashSet<String>(Arrays.asList(yourNumbers));

        numberSet.retainAll(winnerSet);
        return numberSet.size();
    }

    private Integer getCardNumber(String line) {
        Integer gameValue = 0;
        Pattern cardNumber = Pattern.compile("^\\S+\\s*(\\d+)");
        Matcher matcher = cardNumber.matcher(line);
        matcher.find();
        gameValue =Integer.parseInt(matcher.group(1));
        return gameValue;
    }
    
}
