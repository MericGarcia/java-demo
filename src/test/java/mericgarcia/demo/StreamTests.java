package mericgarcia.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class StreamTests {

    List<String> strings = Arrays.asList("Un", "Deux", "Trois", "Quatre", "Cinq", "Six");


    @Test
    public void testStreamCount() throws Exception {

        long n = strings.stream().filter(x -> !x.equals("Deux")).count();

        Assert.assertEquals("Test filtering and count fails.", 5, n);
    }

    @Test
    public void testStreamStringGenerationCollector() throws Exception {
        String resultString = strings.parallelStream().filter(x -> !x.equals("Trois") && !x.equals("Cinq")).map((s) -> s + "!").collect
                (Collectors.joining(","));
        Assert.assertEquals("Test collector fails.", "Un!,Deux!,Quatre!,Six!", resultString);
    }

}
