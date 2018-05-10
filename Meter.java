/**
 * 
 */
package ElectricityMeter;

import java.io.*;
import java.util.*;

 /**
 * @author Shang Liu
 *
 */
public abstract class Meter {
	public int cusID;
	public volatile float reading;
	public volatile float currentUsage;
	
	public Meter(int cusID) {
		this.cusID = cusID;
	}

	public abstract void recordReading(int cusID) throws IOException;
	public abstract void freshReading() throws IOException;

	public float getCurrentUsage(float a, float b) {
		do {
			currentUsage = generateGussianNum(a, b);
		} while(currentUsage <= 0);
		return currentUsage;
	}
	
	public float generateGussianNum(float variance, float mean) {
		Random rd = new Random();
		currentUsage = (float) (Math.sqrt(variance) * rd.nextGaussian() + mean);
		return currentUsage;
	}
	
}
