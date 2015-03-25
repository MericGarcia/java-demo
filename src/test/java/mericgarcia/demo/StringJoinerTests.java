package mericgarcia.demo;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by mericgarcia on 23/03/15.
 */
public class StringJoinerTests extends DemoTests {

    @Test
    public void stringJoinerDemo(){

        StringJoiner joiner = new StringJoiner(",",""," ! ");

        joiner.add("Un");
        joiner.add("deux");
        joiner.add("trois");
        joiner.add("quatre");

        out(joiner.toString());

        Assert.assertEquals(joiner.toString(),"Un,deux,trois,quatre ! ");


    }

}
