package ElectricityMeter;


public class Time extends Thread {

	public static volatile int year = 2000;
	public static volatile int month = 1;
	public static volatile int day = 1;
	
	public static volatile int hour = 0;
	public static volatile int minute = 0;
	public static volatile int second = 0;
	
	public static volatile String dynamicTime;

	public void getDynamicTime() {
		
		for(int year = 2000; year <= 2999; year++) {
			Time.year = year;
			for(int month = 1; month <= 12; month ++) {
				Time.month = month;
				for(int day = 1; day <= 28; day++) {
					Time.day = day;
					for(int hour = 0; hour <= 23; hour++) {
						Time.hour = hour;
						for(int minute = 0; minute <= 59; minute++) {
							Time.minute = minute;
								for(int second = 0; second <= 59; second++) {
									Time.second = second; 
									// control how fast time flies
//									try {
//										Thread.sleep(0);
//									}
//									catch(InterruptedException ex) {
//										System.exit(-1);
//									}
									dynamicTime = "" + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
//									System.out.println(dynamicTime);
								}
								
						}
						
						if(Time.hour % 3 == 2) {
							if(hour == 23) {
								TimeStamp.perDay1 = true;
								TimeStamp.perDay2 = true;
							}
							TimeStamp.perThreeHour1 = true;
							TimeStamp.perThreeHour2 = true;
//							try {
//								while(CustomerMonitor.orderNum != 1) {
//									wait();
//								}
//								CustomerMonitor.orderNum = 2;
//								notifyAll();
//							}
//							catch(InterruptedException ex) {
//								System.exit(-1);
//							}
							
							try {
								Thread.sleep(333);
							}
							catch(InterruptedException ex) {
								System.exit(-1);
							}
							
							}
						}

					
					}

						
//					try {
//						while(CustomerMonitor.orderNum != 1) {
//							wait();
//						}
//						CustomerMonitor.orderNum = 2;
//						notifyAll();
//					}
//					catch(InterruptedException ex) {
//						System.exit(-1);
//					}
					
					try {
						Thread.sleep(3000);
					}
					catch(InterruptedException ex) {
						System.exit(-1);
					}
					TimeStamp.perMonth = true;	
				}

				try {
					Thread.sleep(3000);
				}
				catch(InterruptedException ex) {
					System.exit(-1);
				}

		}
	}	
	
	public void run() {
		getDynamicTime();
	}
	
	
	
	public static void main(String[] args) {
		Time time = new Time();
		time.start();
		
//		while(true) {
//			System.out.println(TimeStamp.perThreeHour);
//		}
		
		while(true) {
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println(Time.dynamicTime);
		}
		

	}

}
