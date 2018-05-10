/**
 * 
 */
package ElectricityMeter;

import java.util.*;
import java.io.*;
/**
 * @author Shang Liu
 *
 */
public class ProviderManagement {
	
	public int getLineNum() throws IOException {
		int i = 0;
		
		File file = new File("customer_list.csv");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		try(
			FileReader fr = new FileReader("customer_list.csv");
			BufferedReader br = new BufferedReader(fr);
		) {
			
			while(br.readLine() != null) {
				i++;
			}
		}
		return i;
	}
	
	
	public void createCustomerFile(Customer customer) throws IOException {
		try {
			File ls = new File("customer_list");
			if(!ls.exists()) {
				ls.mkdir();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			
		try(
				FileWriter fw = new FileWriter("customer_list/customer_" + customer.getId() + "_mailBox.csv", true);
				){
		}
	}
	
	
	public void writeToFile(Customer customer) throws IOException {
		try(
			FileWriter fw = new FileWriter("customer_list.csv", true);
			BufferedWriter bw = new BufferedWriter(fw);
		) {
			bw.write(customer.getId() + ",");
			bw.write(customer.getFirstName() + ",");
			bw.write(customer.getLastName() + ",");
			bw.write(customer.getAddress() + ",");
			bw.write(customer.getPassword());
			bw.newLine();
		}
	}

	
	public void removeFromFile(int id) throws IOException {
		// init a StringBuffer
		StringBuffer sb = new StringBuffer(""); 
		
		// delete the specific line
		try(
				FileReader fr = new FileReader("customer_list.csv");
				BufferedReader br = new BufferedReader(fr);
			){
			int index = 1;
			String line = "";
			while((line = br.readLine()) != null) {
				if(index == id) {
					sb.append(id + ",,,,\n");
				}
				else {
					sb.append(line + "\n");
				}
				index++;
			}
		}
		
		// rewrite the file
		try(
				FileWriter fw = new FileWriter("customer_list.csv");
				BufferedWriter bw = new BufferedWriter(fw);
			) {
				bw.write(sb + "");
			}
	}
	
	
	public void readFromFile(int id) throws IOException {
		Customer customer = new Customer();
		try (
			FileReader fr = new FileReader("customer_list.csv");
			BufferedReader br = new BufferedReader(fr);
		) {
			int index = 1;
			String line = "";
			while((line = br.readLine()) != null) {
				if(index == id) {
					String item[] = line.split(",");
					customer.setId(Integer.parseInt(item[0]));
					customer.setFirstName(item[1]);
					customer.setLastName(item[2]);
					customer.setAddress(item[3]);
					customer.setPassword(item[4]);
				}
				index++;
			}
			System.out.println("ID: " + customer.getId());
			System.out.println("First Name: " + customer.getFirstName());
			System.out.println("Last Name: " + customer.getLastName());
			System.out.println("Address: " + customer.getAddress());	
		}
	}
	
	
	// add customer
	public void addCustomer() throws IOException {
		Customer customer = new Customer();
		Scanner readNewCust = new Scanner(System.in);
		
		// input the customer's first name
		System.out.println("Please input your first name:");
		String firstName = readNewCust.nextLine();
		customer.setFirstName(firstName);
		
		// input the customer's last name
		System.out.println("Please input your last name:");
		String lastname = readNewCust.nextLine();
		customer.setLastName(lastname);
		
		// input the customer's address
		System.out.println("Please input your address:");
		String address = readNewCust.nextLine();
		customer.setAddress(address);
		
		// input the customer's password (lock of his/her house)
		System.out.println("Please input your password:");
		String password = readNewCust.nextLine();
		customer.setPassword(password);
		
		// validation check for all info (to avoid null)
		
		// input the password again and verify it
		
		// close the Scanner
		readNewCust.close();
		
		// assign the customer's id by the system automatically
		customer.setId(getLineNum() + 1);
		
		// add the customer to the CSV file
		writeToFile(customer);	
		
		// in GUI, all the info can be edited, and there exists a button to verify
		System.out.println("Please verify and get your id number.");
		System.out.println("*************YOUR ID: " + customer.getId()+ "**************");
		
		// create a specific file for the customer, e.g., "customer_1.csv".
		createCustomerFile(customer);
	}
	
	
	public void removeCustomer() throws IOException {
		Scanner readId = new Scanner(System.in);
		System.out.println("Please input the customer your want to view in the form of ID: ");
		int id = readId.nextInt();
		readId.close();
		removeFromFile(id);
	}
	
	public void viewCustomer() throws IOException {
		System.out.println("Please input the customer your want to view in the form of ID: ");
		Scanner readId = new Scanner(System.in);
		int id = readId.nextInt();
		readId.close();
		readFromFile(id);
	}
	
	
	public void setTariff() throws IOException {
		// view tariff
		System.out.println("The current electricity tariff: " + Tariff.getElecTariff());
		System.out.println("The current gas tariff: " + Tariff.getGasTariff());
		
		Scanner sc = new Scanner(System.in);
		
		// set elec tariff
		System.out.print("Reset the electricity tariff? [Y/N] ");
		String op1 = sc.nextLine();
		if(op1.equals("Y")) {
			System.out.print("Please reset the electricity tariff (kWh/£): ");
			Tariff.setElecTariff(Double.parseDouble(sc.nextLine()));
		}
		
		// set gas tariff
		System.out.print("Reset the gas tariff? [Y/N] ");
		String op2 = sc.nextLine();
		if(op2.equals("Y")) {
			System.out.print("Please reset the electricity tariff (kWh/£): ");
			Tariff.setGasTariff(Double.parseDouble(sc.nextLine()));
		}
		
		sc.close();
	}
		
	
	public void option() throws IOException {
		// options
		System.out.println("1. Add a new customer");
		System.out.println("2. Remove an existing customer");
		System.out.println("3. View info for a customer");
		System.out.println("4. Set tariff");
		System.out.println("5. quit");
		
		// provider input
		Scanner readOption = new Scanner(System.in);
		System.out.print("Please input your option number: ");
		
		// read input
		int op = readOption.nextInt();
		switch(op) {
		
		// add a new customer
		case 1:
			addCustomer();
		break;
			
		// remove an existing customer
		case 2:
			removeCustomer();
		break;
		
		// view info of an existing customer
		case 3:
			viewCustomer();
		break;	
		
		// exit the program
		case 4:
			setTariff();
		break;
		
		// set tariff
		case 5:
			System.exit(-1);
		break;
		
		// wrong input
		default:
			System.out.println("Please enter correct option number!");
		break;
		}
		// close the Scanner
		readOption.close();
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ProviderManagement pd = new ProviderManagement();

		// invoke the method option
		pd.option();
	}
}
