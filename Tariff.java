/**
 * 
 */
package ElectricityMeter;

import java.io.*;

/**
 * @author Shang Liu
 *
 */
public class Tariff {
	public final static double ELECTARIFFINI = 5.00;
	public final static double GASTARIFFINI= 5.00;
	
	
	public static double getElecTariff() throws IOException {
		double elecTariff = 0.0;
		try {
			File file = new File("tariff.csv");
			if(!file.exists()) {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(Tariff.ELECTARIFFINI + "\n" + Tariff.GASTARIFFINI);
				bw.close();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		try(
				FileReader fr = new FileReader("tariff.csv");
				BufferedReader br = new BufferedReader(fr);
				){
			elecTariff = Double.parseDouble(br.readLine());
		}
		
		return elecTariff;
	}
	
	public static double getGasTariff() throws IOException {
		double gasTariff = 0.0;
		
		try {
			File file = new File("tariff.csv");
			if(!file.exists()) {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(Tariff.ELECTARIFFINI + "\n" + Tariff.GASTARIFFINI);
				bw.close();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		try(
				FileReader fr = new FileReader("tariff.csv");
				BufferedReader br = new BufferedReader(fr);
				){
			br.readLine();
			gasTariff = Double.parseDouble(br.readLine());
		}
		
		return gasTariff;
	}
	
	public static void setElecTariff(double elecTariff) throws IOException {
		double gasTariff = 0.0;
		try {
			File file = new File("tariff.csv");
			if(!file.exists()) {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(Tariff.ELECTARIFFINI + "\n" + Tariff.GASTARIFFINI);
				bw.close();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		gasTariff = getGasTariff();
		
		try(
				FileWriter fw = new FileWriter("tariff.csv");
				BufferedWriter bw = new BufferedWriter(fw);
				){
			bw.write(elecTariff + "\n" + gasTariff);
		}	
	}
	
	public static void setGasTariff(double gasTariff) throws IOException {
		double elecTariff = 0.0;
		try {
			File file = new File("tariff.csv");
			if(!file.exists()) {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(Tariff.ELECTARIFFINI + "\n" + Tariff.GASTARIFFINI);
				bw.close();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		elecTariff = getElecTariff();
		
		try(
				FileWriter fw = new FileWriter("tariff.csv");
				BufferedWriter bw = new BufferedWriter(fw);
				){
			bw.write(elecTariff + "\n" + gasTariff);
		}	
	}
	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	*/

}
