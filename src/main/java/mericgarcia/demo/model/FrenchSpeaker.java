package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 27/01/15.
 */
public interface FrenchSpeaker extends Person{

    default void speek(){
        System.out.println(name() + " dit Bonjour");
    }

}
