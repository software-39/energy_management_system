/**
 * 
 */
package ElectricityMeter;

/**
 * @author Shang Liu
 *
 */
//import java.util.ArrayList;

public class Customer {
	//private ArrayList<Customer> customer = new ArrayList<> ();
	private String firstName;
	private String lastName;
	private String Address;
	private String password;
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



}
