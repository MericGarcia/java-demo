package mericgarcia.demo.model.concurrent;

import java.util.concurrent.locks.StampedLock;

public class LaboratoireIsotherme {

	private long temperature;
	private final long max;

	public long experienceEffectuee;
	public long experienceRefaiteAvecReservation;

	private final StampedLock sl = new StampedLock();

	public LaboratoireIsotherme(long temperature, long max) {
		this.temperature = temperature;
		this.max = max;
	}

	public void augmente() { // an exclusively locked method
		long stamp = sl.writeLock();
		try {
			if (temperature < max) {
				temperature += 1;
			}
		} finally {
			sl.unlockWrite(stamp);
		}
	}

	public void effectueUneExperienceIsotherme() { // A read-only method
		long stamp = sl.tryOptimisticRead();
		long currentTemp = temperature;
		
		work(currentTemp);
		experienceEffectuee++;
		
		if (!sl.validate(stamp)) {
			stamp = sl.readLock();
			try {
				currentTemp = temperature;
				work(currentTemp);
				experienceRefaiteAvecReservation++;
			} finally {
				sl.unlockRead(stamp);
			}
		}
		
	}
	
	public long getTemperature() { 
		return temperature;
	}
	

	private void work(long currentTemp) {
		for(int i=1;i<Math.random()*1000;i++){
			Math.sqrt(Math.random()*10000*currentTemp);
		}
	}

}