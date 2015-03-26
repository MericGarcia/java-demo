package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 27/01/15.
 */
public class Traducteur implements FrenchSpeaker, EnglishSpeaker, Worker{

    private String name;

    public Traducteur(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void speek(){
        FrenchSpeaker.super.speek();
    }


    @Override
    public void work() {
        System.out.println(name + " traduit un document");
    }
};
