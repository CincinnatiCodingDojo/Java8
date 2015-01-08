import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class PersonTest {

  List<Person> people;

  @Before
  public void setup() {
    people = new ArrayList<Person>();

    people.add(new Person("dan", 1));
    people.add(new Person("drew", 2));
    people.add(new Person("drew3", 3));

  }

  @Test
  public void should_filter_list_of_people(){
    List<Person> oldPeople = getPeopleOlderThan(2);
    assertEquals(1, oldPeople.size());
    assertEquals("drew3", oldPeople.get(0).getName());
  }


  @Test
  public void listAllAges(){
   IntStream ages = people.stream().mapToInt(Person:: getAge);
    System.out.println(ages.toArray());
  }

  @Test
  public void averageOfAllAges() {
    double average = people.stream().collect(Collectors.averagingDouble(f -> f.getAge()));
    assertEquals(2.0, average, .01);
  }

  @Test
  public void testOfAverageAges() {

    int[] expectedAges = new int[] {1,2,3};
    IntStream intStream = people.stream().mapToInt(Person::getAge);
    OptionalDouble averageAges = intStream.average();
    assertEquals(2.0, averageAges.getAsDouble(), .01);
  }



  private List<Person> getPeopleOlderThan(int age) {
    return people.stream().filter(p -> p.getAge() > age).collect(Collectors.toList());
  }

}
