import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dnorton on 5/27/14.
 */
public class StreamExamples {

    public static void main(String[] args) {

        System.out.println(sortWords("the quick brown dog jumped"));

    }

    public static String sortWords(String wordsToSort) {
        Stream<String> sortedWords = Stream.of(wordsToSort.split("[\\P{L}]+")).sorted();
        return sortedWords.collect(Collectors.joining(" "));

    }


}
