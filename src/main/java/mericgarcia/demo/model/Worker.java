package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 27/01/15.
 */
public interface Worker extends Named{

    default void work(){
        System.out.println(name() + " travaille");
    };

}
