package mericgarcia.demo;

import mericgarcia.demo.model.Worker;

import org.junit.Before;

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
    
    protected static void out(Object o){
        System.out.println(o.toString());
    }
    
    protected static void out(Worker w){
        System.out.println(w.name());
    }

    protected static void methodeHead(String message){
        System.out.println("----> method : " + message);
    }

}
