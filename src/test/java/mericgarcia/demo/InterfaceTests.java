package mericgarcia.demo;

import mericgarcia.demo.model.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class InterfaceTests {

    @Test
    public void doWork() throws Exception {

        System.out.println("doWork()");

        Worker boulanger = new Boulanger("Aimable Castanier");
        Worker traducteurFrançais = new Traducteur("Aurelien LeJeune");
        Worker traducteurAnglais = new Interpreter("James Henry");
        Worker inconnu = new Worker() {
            @Override
            public String name() {
                return "Un inconnu";
            }
        };

        List<Worker> workers = Arrays.asList(boulanger,traducteurFrançais,traducteurAnglais,inconnu);

        workers.forEach((w)-> w.work());

        System.out.println("");

    }

    @Test
    public void doSpeek() throws Exception {
        System.out.println("doSpeek()");

        Person boulanger = new Boulanger("Aimable Castanier");
        Person traducteurFrançais = new Traducteur("Aurelien LeJeune");
        Person traducteurAnglais = new Interpreter("James Henry");

        List<Person> persons = Arrays.asList(boulanger,traducteurFrançais,traducteurAnglais);

        persons.forEach((w)-> w.speek());

        System.out.println("");

    }


}
