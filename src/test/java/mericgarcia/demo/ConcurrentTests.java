package mericgarcia.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import mericgarcia.demo.model.Chauffage;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mericgarcia on 27/01/15.
 */
public class ConcurrentTests extends DemoTests {


	@Test
	public void concurrentAddersDemo() throws Exception {

		methodeHead("concurrentAddersDemo()");

		LongAdder myLong = new LongAdder();

		myLong.add(2);
		myLong.increment();

		out(myLong.longValue());

	}

	@Test
	public void stampedLocksDemo() throws Exception {

		methodeHead("stampedLocksDemo()");

		// construction de notre unique chaudiere
		final Chauffage chauffage = new Chauffage(20L, 30L);

		Runnable personne = new Runnable() {

			public void run() {
				int i = 0;
				while (i < 5) {
					chauffage.useTemperature();
					chauffage.augmente();
					i++;
				}
			}
		};


		Thread[] personnes = new Thread[30];

		for (int i = 0; i < personnes.length; i++) {
			personnes[i] = new Thread(personne);
		}

		// lancement de nos habitants
		for (int i = 0; i < personnes.length; i++) {
			personnes[i].start();
		}

		// on attend que chaque thread ait fini son exécution
		for (int i = 0; i < personnes.length; i++) {
			// jette InterruptedException
			personnes[i].join();
		}

		// on affiche la température de notre chaudière
		out("Temperature finale = " + chauffage.getTemperature() + ", " + chauffage.totalAttentePourRegard + " get avec attente / " + chauffage.totalRegard + " get en tout" );

	}

}
