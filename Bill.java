package ElectricityMeter;

import java.io.*;

public class Bill extends Thread {
	public int cusID;
	public double monthlyElecUsage;
	public double monthlyElecCost;
	public double monthlyGasUsage;
	public double monthlyGasCost;
	public String billContent;
	
	public Bill(int cusID) {
		this.cusID = cusID;
	}
	
	public void generateBill() throws IOException{
		int lineNum = 0;
		int monthNum = 0;
		while(true) {
			if(TimeStamp.perMonth == true) {
				TimeStamp.perMonth = false;
				try(
						FileReader fr = new FileReader("customer_list/customer_" + this.cusID + "_elec.csv");
						BufferedReader br = new BufferedReader(fr);
						){

					while(br.readLine() != null) {
						lineNum++;
					}
				}
				
				monthNum = (int)(lineNum / 28);
				
				try(
						FileReader fr = new FileReader("customer_list/customer_" + this.cusID + "_elec.csv");
						BufferedReader br = new BufferedReader(fr);
						){
					int index = 1;
					String line = "";
					double monthStart = 0;
					double monthEnd = 0;
					while((line = br.readLine()) != null) {
						// 本月最后一个数减上月最后一个数
						if(index == 28 * (monthNum - 1)) {
							String item[] = line.split(",");
							monthStart = Double.parseDouble(item[0]);
						}
						
						if(index == 28 * monthNum) {
							String item[] = line.split(",");
							monthEnd = Double.parseDouble(item[0]);
						}
						index++;
					}
					this.monthlyElecUsage = monthEnd - monthStart;
					this.monthlyElecCost = monthlyElecUsage * Tariff.getElecTariff();
				}

				try(
						FileReader fr = new FileReader("customer_list/customer_" + this.cusID + "_gas.csv");
						BufferedReader br = new BufferedReader(fr);
						){
					int index = 1;
					String line = "";
					double monthStart = 0;
					double monthEnd = 0;
					while((line = br.readLine()) != null) {
						if(index == 28 * (monthNum - 1) + 1) {
							String item[] = line.split(",");
							monthStart = Double.parseDouble(item[0]);
						}
						
						if(index == 28 * monthNum + 1) {
							String item[] = line.split(",");
							monthEnd = Double.parseDouble(item[0]);
						}
						index++;
					}
					this.monthlyGasUsage = monthEnd - monthStart;
					this.monthlyGasCost = monthlyGasUsage * Tariff.getGasTariff();
				}	
				
				this.billContent = "Dear Customer, in last month, your electricity usage is " + 
						this.monthlyElecUsage + "kWh, your electricity cost is " + 
						this.monthlyElecCost + "£, your gas usage is " + 
						this.monthlyGasUsage + "kWh, your gas cost is " + 
						this.monthlyGasCost + "£, and your total cost is " +
						(this.monthlyElecCost + this.monthlyGasCost) + "£";
	
				this.monthlyGasUsage = 0;
				this.monthlyGasCost = 0;	
				this.monthlyElecUsage = 0;
				this.monthlyElecCost = 0;
			
				sendBill(monthNum);
			}
		}
	}
	
	public void sendBill(int monthNum) throws IOException {
		try(
				FileWriter fr = new FileWriter("customer_list/customer_" + this.cusID + "_" + monthNum + "_mail.txt", true);
				BufferedWriter bw = new BufferedWriter(fr);
				){
			bw.write(this.billContent + "\n");
		}
	}
	
//	public void readBill() throws IOException{
//		
//	}
	
	public void run() {
		try {
			Thread.sleep(10000);
			generateBill();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
