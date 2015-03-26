package mericgarcia.demo;

import java.util.concurrent.atomic.LongAdder;

import mericgarcia.demo.model.concurrent.LaboratoireIsotherme;

import org.junit.Test;

/**
 * Created by mericgarcia on 27/01/15.
 */
public class ConcurrentTests extends DemoTests {


	@Test
	public void concurrentAddersDemo() throws Exception {

		LongAdder myLong = new LongAdder();

		myLong.add(2);
		myLong.increment();

		out(myLong.longValue());

	}
	

	@Test
	public void stampedLocksDemo() throws Exception {

		// construction de notre unique chaudiere
		final LaboratoireIsotherme chauffage = new LaboratoireIsotherme(20L, 30L);

		Runnable personne = new Runnable() {

			public void run() {
				int i = 0;
				while (i < 5) {
					chauffage.effectueUneExperienceIsotherme();
					chauffage.augmente();
					i++;
				}
			}
		};


		Thread[] personnes = new Thread[30];

        //initialisation des threads
		for (int i = 0; i < personnes.length; i++) {
			personnes[i] = new Thread(personne);
		}

		// lancement des thread
		for (int i = 0; i < personnes.length; i++) {
			personnes[i].start();
		}

		// on attend la fin des threads
		for (int i = 0; i < personnes.length; i++) {
			// jette InterruptedException
			personnes[i].join();
		}

		// on affiche la temperature de notre chauffage et l'utlisation des locks
		out("Temperature finale = " + chauffage.getTemperature() + ", " + chauffage.experienceRefaiteAvecReservation
                + " experiences avec reservation / " + chauffage.experienceEffectuee + " experiences" );

	}

}
