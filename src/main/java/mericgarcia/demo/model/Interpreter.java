package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 27/01/15.
 */
public class Interpreter implements FrenchSpeeker, EnglishSpeeker, Worker{

    private String name;

    public Interpreter(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void speek(){
        EnglishSpeeker.super.speek();
    }


    @Override
    public void work() {
        System.out.println(name + " translate a document");
    }
};
