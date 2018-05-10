/**
 * 
 */
package ElectricityMeter;

import java.io.*;

/**
 * @author Shang Liu
 *
 */
public class GasMeter extends Meter implements Runnable {	
	public GasMeter(int cusID) {
		super(cusID);
	}
	
	public void recordReading(int cusID) throws IOException {
		try(
				FileWriter fw = new FileWriter("customer_list/customer_" + cusID + "_gas.csv", true);
				BufferedWriter bw = new BufferedWriter(fw);
				){			

//		if(i % 8 == 0) {
//			bw.write(this.reading + "\n");
//		} else {
//			bw.write(this.reading + ",");
//		}
//				
			
			if(TimeStamp.perDay2 == true) {
				TimeStamp.perDay2 = false;
				bw.write(this.reading + "\n");
			} 
//			else if(TimeStamp.perMonth == true) {
//				bw.write(this.elecReading + "\n\n");
//			}
			else {
				bw.write(this.reading + ",");
			}
		}
			
	}
	
	public void freshReading() throws IOException {
		if(TimeStamp.perThreeHour2) {
			TimeStamp.perThreeHour2 = false;
			this.reading = this.reading + getCurrentUsage(1, 4);
			
			// record it
			recordReading(this.cusID);
		}
	}
	
	public void run() {
	//	int i = 1;
		while(true) {
//			try {
//				if(freshReading(i)) {
//					i++;
//				}
//				Thread.sleep(1000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			try {
				freshReading();
				Thread.sleep(10);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		Time time = new Time();
		time.start();
			
		GasMeter gm = new GasMeter(1);
		Thread gmt = new Thread(gm);
		gmt.start();

	}
}
