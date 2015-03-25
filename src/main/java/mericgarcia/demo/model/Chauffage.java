package mericgarcia.demo.model;

import java.util.concurrent.locks.StampedLock;

public class Chauffage {

	private long temperature;
	private final long max;

	public long totalRegard;
	public long totalAttentePourRegard;

	private final StampedLock sl = new StampedLock();

	public Chauffage(long temperature, long max) {
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

	public double useTemperature() { // A read-only method
		long stamp = sl.tryOptimisticRead();
		long currentTemp = temperature;
		totalRegard++;
		work(currentTemp);
		if (!sl.validate(stamp)) {
			stamp = sl.readLock();
			try {
				currentTemp = temperature;
				work(currentTemp);
				totalAttentePourRegard++;
			} finally {
				sl.unlockRead(stamp);
			}
		}
		return currentTemp;
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