package mericgarcia.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class StreamTests extends DemoTests{

    private static List<String> strings = Arrays.asList("Un", "Deux", "Trois", "Quatre", "Cinq", "Six");

    @Before
    public void initStream(){
        header(" ## data ##");
        out(strings.stream().collect(Collectors.joining(",","(",")")));
        out("---------------------");
        out("");
    }


    @Test
    public void testStreamCount() throws Exception {

        long n = strings.stream()
                .filter(x -> !x.equals("Deux"))
                .count();

        out("count : " + n);
        Assert.assertEquals("Test filtering and count fails.", 5, n);
    }

    @Test
    public void testStreamStringGenerationCollector() throws Exception {

        String resultString = strings.parallelStream()
                                    .filter(x -> !x.equals("Trois") && !x.equals("Cinq"))
                                    .map((s) -> s + "!")
                                    .collect(Collectors.joining(","));

        out("Result String : " + resultString);
        Assert.assertEquals("Test collector fails.", "Un!,Deux!,Quatre!,Six!", resultString);
    }

}
