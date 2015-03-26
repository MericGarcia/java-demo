package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 27/01/15.
 */
public interface EnglishSpeaker extends Person{

    default void speek(){
        System.out.println(name() + " says Hello");
    }

}
