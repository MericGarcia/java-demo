package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 27/01/15.
 */
public interface EnglishSpeeker extends Person{

    default void speek(){
        System.out.println(name() + " says Hello");
    }

}
