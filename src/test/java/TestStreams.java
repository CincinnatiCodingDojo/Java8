import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dnorton on 5/27/14.
 */
public class TestStreams extends StreamExamples{

    @Test
    public void testStreams(){
        String sortedWords = StreamExamples.sortWords("alpha zeta beta");
        assertEquals(sortedWords, "alpha beta zeta");
    }
}
