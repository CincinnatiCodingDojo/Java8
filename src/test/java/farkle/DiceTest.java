package farkle;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiceTest {

  Dice dice;

  @Before
  public void setup(){
    dice = new Dice();
  }

  @Test
  public void testGetRollReturnsReasonableValues(){
    int rollValues = 100;

    List<Integer> rolls = dice.getRoll(rollValues);

    assertEquals(rollValues, rolls.size());

    for(Integer i:rolls){
      assertTrue(i > 0 && i <= 6 );
    }
  }

  @Test
  public void testScoreTriples(){
    assertEquals(300, dice.score(asList(1, 1, 1)));
    assertEquals(200, dice.score(Arrays.asList(2, 2, 2)));
    assertEquals(500, dice.score(Arrays.asList(1, 2, 1, 2, 1, 2)));
    assertEquals(600, dice.score(Arrays.asList(6,6,6)));
    assertEquals(400, dice.score(Arrays.asList(4,2,4,6,1,4)));
  }

  @Test
  public void testScoreSingles(){
    assertEquals(150, dice.score(asList(1, 2, 3, 4, 5, 6)));

  }

  @Test
  public void testScoreDoubles(){
    assertEquals(200, dice.score(asList(1,1,2,3,4,6)));
    assertEquals(100, dice.score(asList(2,3,4,5,5,6)));
    assertEquals(1500, dice.score(asList(1,1,5,5,6,6)));
    assertEquals(1500, dice.score(asList(1,1,1,1,5,5)));
  }

  @Test
  public void testMapCounts(){
    assertEquals(asList(0, 3, 2, 1, 0, 0, 0), dice.mapToCounts(asList(1, 2, 3, 2, 1, 1)));
}

  @Test
  public void testMapToFrequencies() {
    Map<Integer, Set<Integer>> pairs = new HashMap();
    Set<Integer> pairValues = new HashSet<>();
    pairValues.add(2);
    pairValues.add(1);
    pairs.put(new Integer(3), pairValues);
    assertEquals(pairs, dice.mapToFrequencies(asList(1, 1, 1, 2, 2, 2)));
  }

}
