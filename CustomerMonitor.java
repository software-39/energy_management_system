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
public class CustomerMonitor {
	public int cusID;
	public static ElectricityMeter em;
	public static GasMeter gm;
	public static volatile int orderNum = 1;
	public static volatile Object object;
	
	public void checkBill() throws IOException {
		try(
				FileReader fr = new FileReader("customer_list/customer_" + this.cusID + "_1_mail.txt");
				BufferedReader br = new BufferedReader(fr);
				){
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
	
	public void viewTariff() throws IOException {
		// view tariff
		System.out.println("The current electricity tariff: " + Tariff.getElecTariff());
		System.out.println("The current gas tariff: " + Tariff.getGasTariff());
	}
	
	public void viewReading() throws IOException {            
		while(true) {
			try {
				Thread.sleep(1000);
				System.out.println(em.reading);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void viewHistory(int cusID) throws FileNotFoundException, IOException {
		double dailyUse = 0;
		double weeklyUse = 0;
		double monthlyUse = 0;
		double dailyCost = 0;
		double weeklyCost = 0;
		double monthlyCost = 0;
		int lineNum = 0;
		
		try(
				FileReader fr = new FileReader("customer_list/customer_" + cusID + "_elec.csv");
				BufferedReader br = new BufferedReader(fr);
				){

			// get number of lines

			while(br.readLine() != null) {
				lineNum++;
			}
		}
		try(
				FileReader fr = new FileReader("customer_list/customer_" + cusID + "_elec.csv");
				BufferedReader br = new BufferedReader(fr);
				){
			String line = "";
			if(lineNum <= 1) {
				dailyUse = 0;
				weeklyUse = 0;
				monthlyUse = 0;
			} else if(lineNum > 1 && lineNum <= 7) {
				int index = 1;
				double dayStart = 0;
				double dayEnd = 0;
				while((line = br.readLine()) != null) {
					if(index == lineNum - 1) {
						String item[] = line.split(",");
						dayStart = Double.parseDouble(item[0]);
						dayEnd = Double.parseDouble(item[7]);
					}
					index++;
				}
				dailyUse = dayEnd - dayStart;
				weeklyUse = 0;
				monthlyUse = 0;
			} else if(lineNum > 7 && lineNum <= 28) {
				int index = 1;
				double dayStart = 0;
				double dayEnd = 0;
				double weekStart = 0;
				double weekEnd = 0;
				while((line = br.readLine()) != null) {
					if(index == lineNum - (lineNum % 7) - 6) {
						String item[] = line.split(",");
						weekStart = Double.parseDouble(item[0]);
					}
					
					if(index == lineNum - (lineNum % 7) + 1) {
						String item[] = line.split(",");
						weekEnd = Double.parseDouble(item[0]);
					}
					
					if(index == lineNum - 1) {
						String item[] = line.split(",");
						dayStart = Double.parseDouble(item[0]);
						dayEnd = Double.parseDouble(item[7]);
					}
					index++;
				}
				dailyUse = dayEnd - dayStart;
				weeklyUse = weekEnd - weekStart;
				monthlyUse = 0;
			} else {
				int index = 1;
				double dayStart = 0;
				double dayEnd = 0;
				double weekStart = 0;
				double weekEnd = 0;
				double monthStart = 0;
				double monthEnd = 0;
				while((line = br.readLine()) != null) {
					if(index == lineNum - (lineNum % 28) - 27) {
						String item[] = line.split(",");
						monthStart = Double.parseDouble(item[0]);
					}
					
					if(index == lineNum - (lineNum % 28) + 1) {
						String item[] = line.split(",");
						monthEnd = Double.parseDouble(item[0]);
					}
					
					if(index == lineNum - (lineNum % 7) - 6) {
						String item[] = line.split(",");
						weekStart = Double.parseDouble(item[0]);
					}
					
					if(index == lineNum - (lineNum % 7) + 1) {
						String item[] = line.split(",");
						weekEnd = Double.parseDouble(item[0]);
					}
					
					if(index == lineNum - 1) {
						String item[] = line.split(",");
						dayStart = Double.parseDouble(item[0]);
						dayEnd = Double.parseDouble(item[7]);
					}
					index++;
				}
				dailyUse = dayEnd - dayStart;
				weeklyUse = weekEnd - weekStart;
				monthlyUse = monthEnd - monthStart;
			}
		}
		
		dailyCost = dailyUse * Tariff.getElecTariff();
		weeklyCost = weeklyUse * Tariff.getElecTariff();
		monthlyCost = monthlyUse * Tariff.getElecTariff();
		
		System.out.println("Last day electricity usage: " + dailyUse + "kW/h");
		System.out.println("Last day electricity cost: " + dailyCost + "£");
		System.out.println("Last week electricity usage: " + weeklyUse + "kW/h");
		System.out.println("Last week electricity cost: " + weeklyCost + "£");
		System.out.println("Last month electricity usage: " + monthlyUse + "kW/h");
		System.out.println("Last month electricity cost: " + monthlyCost + "£");

	}
	
	public boolean verifyId() throws FileNotFoundException, IOException {
		boolean result = false;
		Scanner readId = new Scanner(System.in);
		do {
			System.out.println("Please input your id: ");

			int id = readId.nextInt();

			try (
					FileReader fr = new FileReader("customer_list.csv");
					BufferedReader br = new BufferedReader(fr);
				) {
					int index = 1;
					while(br.readLine() != null) {
						if(index == id) {
							result = true;
							System.out.println("ID exist.");
							this.cusID = id;
							break;
						}
						index++;
					}
				}
		} while(!result);
	//	readId.close();
		return result;
	}
	
	public boolean verifyPw(int id) throws FileNotFoundException, IOException {
		boolean result = false;
		Scanner readPw = new Scanner(System.in);
		do {
		//	System.out.println("your id is " + this.cusID);
			System.out.println("Please input your password: ");
			String pw = readPw.nextLine();

			try (
					FileReader fr = new FileReader("customer_list.csv");
					BufferedReader br = new BufferedReader(fr);
				) {
					int index = 1;
					String line = "";
					while((line = br.readLine()) != null) {
						if(index == id) {
							String item[] = line.split(",");
							System.out.println(item[4]);
							if(pw.equals(item[4])) {
								result = true;
								System.out.println("Password matches.");
								break;
							}
						}
						index++;
					}
				}
		} while(!result);
	//	readPw.close();
		return result;
	}
	
	public void option3() throws IOException {
		System.out.println("3.1 Electricity tariff.");
		System.out.println("3.2 Electricity reading.");
		System.out.println("3.3 Electricity history.");
		System.out.println("3.4 Electricity budget.");
		
		System.out.print("Please input your option number: ");
		Scanner readOption = new Scanner(System.in);
		String op = readOption.nextLine();
		
		switch(op) {
		case "3.1":
			viewTariff();
		break;
		
		case "3.2":
			viewReading();
		break;
		
		case "3.3":
			viewHistory(this.cusID);
		break;
			
		case "3.4":
			// invoke a method to set budget
		}
	}
	
	public void option4() {
		System.out.println("4.1 Gas tariff.");
		System.out.println("4.2 Gas reading.");
		System.out.println("4.3 Gas history.");
		System.out.println("4.4 Gas budget");
	}
	
	
	public void optionIni() throws IOException {
		System.out.println("1. View your personal information.");
		System.out.println("2. View the current time.");
		System.out.println("3. View your electricity meter.");
		System.out.println("4. View your gas meter.");
		System.out.println("5. View your monthly bill.");
		
		System.out.println("Please input your option number: ");
		Scanner readOption = new Scanner(System.in);
		String op = readOption.nextLine();
		
		switch(op) {
		
		// view personal info
		case "1":
			
		break;
		
		// view current time
		case "2":
			// print "current time" each actual second	
			while(true) {
				try {
					Thread.sleep(333);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println(Time.dynamicTime);
			}
			
		case "3":
			option3();
		break;
		
		case "4":
			option4();
		break;
		
		case "5":
			checkBill();
		}		
		
	//	readOption.close();
		
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// initialize a monitor
		CustomerMonitor cm = new CustomerMonitor();
		
		// get the customer id
		cm.verifyId();
		cm.verifyPw(cm.cusID);
	
		// start a new timer
		Time time = new Time();
		time.start();
		time.setPriority(1);

		// start a new electricity meter 
		em = new ElectricityMeter(cm.cusID);
		Thread emt = new Thread(em);
		emt.start();
		emt.setPriority(10);
		
		// start a new gas meter
		gm = new GasMeter(cm.cusID);
		Thread gmt = new Thread(gm);
		gmt.start();
		gmt.setPriority(10);
			
		// send and read bill
		Bill bill = new Bill(cm.cusID);
		bill.start();
		bill.setPriority(10);
		
		// enter the option interface
		cm.optionIni();
	}
}
