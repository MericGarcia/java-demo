package mericgarcia.demo;

import mericgarcia.demo.model.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class InterfaceTests extends DemoTests{

    @Test
    public void doWork() throws Exception {

        methodeHead("doWork()");

        Worker boulanger = new Boulanger("Aimable Castanier");
        Worker traducteurFrancais = new Traducteur("Aurelien LeJeune");
        Worker traducteurAnglais = new Interpreter("James Henry");
        Worker inconnu = new Worker() {
            @Override
            public String name() {
                return "Un inconnu";
            }
        };

        List<Worker> workers = Arrays.asList(boulanger,traducteurFrancais,traducteurAnglais,inconnu);

        workers.forEach((w)-> w.work());

        out("");

    }

    @Test
    public void doSpeek() throws Exception {

        methodeHead("doSpeek()");

        Person boulanger = new Boulanger("Aimable Castanier");
        Person traducteurFrancais = new Traducteur("Aurelien LeJeune");
        Person traducteurAnglais = new Interpreter("James Henry");

        List<Person> persons = Arrays.asList(boulanger,traducteurFrancais,traducteurAnglais);

        persons.forEach((w)-> w.speek());


    }


}
