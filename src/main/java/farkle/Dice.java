package farkle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Dice {
  public List<Integer> getRoll(int numDiceToRoll) {
    Random random = new Random();
    return random.ints(numDiceToRoll, 1, 6).boxed().collect(toList());
//    return IntStream.rangeClosed(1, numDiceToRoll).map(p -> oneRoll()).boxed().collect(toList());
  }

  private Integer oneRoll() {
    return new Double(Math.random() * 6).intValue() + 1;
  }

  public int score(List<Integer> counts){
    int score = 0;
    Map<Integer, Set<Integer>> freqMap = mapToFrequencies(counts);
    score += scoreTriples(score, freqMap);
    score += scoreSingles(score, freqMap);
    return score;
  }

  private int scoreTriples(int score, Map<Integer, Set<Integer>> freqMap) {
    if(freqMap.containsKey(3)) {
      Set<Integer> faces = freqMap.get(3);
      for(Integer face : faces) {
        if (face == 1) {
          score += 300;
        }
        else {
          score += face * 100;
        }
      }
    }
    return score;
  }

  private int scoreSingles(int score, Map<Integer, Set<Integer>> freqMap) {
    if (freqMap.containsKey(1)) {
      Set<Integer> faces = freqMap.get(1);
      for(Integer face : faces) {
        if (face == 1) {
          score += 100;
        } else if (face == 5) {
          score += 50;
        }
      }

    }
    return score;
  }

  private int scoreDoubles(int score,Map<Integer, Set<Integer>> freqMap ){
    if(freqMap.containsKey(2)) {
      Set<Integer> faces = freqMap.get(1);
      if(faces.size()==3) {
        return score + 1500;
      }
      for (Integer face : faces) {
        if (face == 1) {
          score += 200;
        } else if (face == 5) {
          score += 100;
        }
      }
    }
    return score;
  }


  private int countOthers(int num, List<Integer> dies) {
    return 0;
  }


  public List<Integer> mapToCounts(List<Integer> roll) {
    IntStream intStream = IntStream.rangeClosed(0, 6).map(die -> countsFor(die, roll));
    return intStream.boxed().collect(Collectors.toList());
  }

  private int countsFor(int die, List<Integer> roll) {
    return (int) roll.stream().filter(n -> n == die).count();
  }


  public Map<Integer, Set<Integer>> mapToFrequencies(List<Integer> integers) {
    List<Integer> counts = mapToCounts(integers);
    Map<Integer, Set<Integer>> freqMap = new HashMap<>();
    for(int face = 1; face < counts.size(); face++){
      Integer count = counts.get(face);
      if (count == 0) {
        continue; // goto top of for loop
      }
      if (!freqMap.containsKey(count)) {
        freqMap.put(count, new HashSet<>());
      }
      freqMap.get(count).add(face);
    }
    return freqMap;
  }
}
