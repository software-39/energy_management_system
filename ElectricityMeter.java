/**
 * 
 */
package ElectricityMeter;

import java.io.*;

/**
 * @author Shang Liu
 *
 */
public class ElectricityMeter extends Meter implements Runnable {	
	public ElectricityMeter(int cusID) {
		super(cusID);
	}

	public void recordReading(int cusID) throws IOException {
		try(
				FileWriter fw = new FileWriter("customer_list/customer_" + cusID + "_elec.csv", true);
				BufferedWriter bw = new BufferedWriter(fw);
				){
			
			// a failed try
			if(TimeStamp.perDay1 == true) {
				TimeStamp.perDay1 = false;
				bw.write(this.reading + "\n");
			} 
//			else if(TimeStamp.perMonth == true) {
//				bw.write(this.elecReading + "\n\n");
//			}
			else {
				bw.write(this.reading + ",");
			}
			
//		if(i % 224 == 0) {
//			bw.write(this.reading + "\n\n");
//		} else 
//		if(i % 8 == 0) {
//			bw.write(this.reading + "\n");
//		} else {
//			bw.write(this.reading + ",");
//		}
			
		}	
	}
	
	public void freshReading() throws IOException {
		if(TimeStamp.perThreeHour1) {
			TimeStamp.perThreeHour1 = false;
			this.reading = this.reading + getCurrentUsage(1, 4);
			
			// record it
			recordReading(this.cusID);
		}
	}
	
	public void run() {
//		int i = 1;
		while(true) {
//			try {
//				if(freshReading(i)) {
//					i++;
//				}
//				Thread.sleep(1000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				while(CustomerMonitor.orderNum != 2) {
//					wait();
//				}
//				freshReading();
//				CustomerMonitor.orderNum = 3;
//				notifyAll();
//			} catch (IOException | InterruptedException e) {
//				// TODO Auto-generated catch block
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
			
		ElectricityMeter em = new ElectricityMeter(1);
		Thread emt = new Thread(em);
		emt.start();

	}
}
