package mericgarcia.demo.model;

/**
 * Created by mericgarcia on 29/01/15.
 */
public class Boulanger implements FrenchSpeeker, Worker{

    String name;

    public Boulanger(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void work() {
        System.out.println(name + " fait une baguette");
    }
}
