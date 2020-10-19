package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class User 
{
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String firstName = "";
	
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String lastName = "";
	
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String email = "";
	
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String username = "";
	
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String password = "";
	
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String address = "";
	
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String phoneNum = "";
	
	public User()
	{
		firstName = "";
		lastName = "";
		email = "";
		username = "";
		password = "";
		address = "";
		phoneNum = "";
	}
	
	public User(String firstName, String lastName, String email, String phoneNum, String address, String username, String password)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.username = username;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (this.email == ((User) object).email || this.username == ((User) object).username)
		{
			return true;
		}
		return false;
	}
}
