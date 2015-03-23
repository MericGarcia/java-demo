package mericgarcia.demo;

import org.junit.Before;
import org.junit.BeforeClass;

import java.util.StringJoiner;

/**
 * Created by mericgarcia on 23/03/15.
 */
public class DemoTests {

    @Before
    public void init(){
        System.out.println("");
        System.out.println("############  " + this.getClass().getName() + "  ###############");
        System.out.println("");
    }

    protected static void header(String message){
        System.out.println("---- " + message + " ----");
        System.out.println("");
    }

    protected static void out(String message){
        System.out.println(message);
    }

    protected static void methodeHead(String message){
        System.out.println("----> method : " + message);
        System.out.println();
    }

}
