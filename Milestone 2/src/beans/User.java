/*
 * Class to represent an account and all of its detailed
 * Creator: Thomas
 */

package beans;

import java.io.IOException;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.validation.constraints.Size;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

import database.RegisteredUserAccess;

/**
 * Session Bean implementation class User
 */

@LocalBean
@Stateless
@Local(UserInterface.class)
@Alternative
public class User implements UserInterface{

    /**
     * Default constructor. 
     */
    public User() 
    {
        //Initializes all the fields as blank strings
    	firstName = "";
    	lastName = "";
    	email = "";
    	phoneNum = "";
    	address = "";
    	username = "";
    	password = "";
    }
    
    //Secondary constructor to set all field values, I don't think this is used
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
    
    //Each field is a string value 
    //All fields except email, phoneNum, and address must have values
    @Size(min=5, max=15, message="You must have between 5 and 15 characters")
    private String firstName = "thomas";
    @Size(min=5, max=15, message="You must have between 5 and 15 characters")
    private String lastName = "thomas";
    @Size(min=5, max=15, message="You must have between 5 and 15 characters")
    private String email = "thomas";
    private String phoneNum = "thomas";
    private String address = "thomas";
    @Size(min=5, max=15, message="You must have between 5 and 15 characters")
    private String username = "thomas";
    @Size(min=5, max=15, message="You must have between 5 and 15 characters")
    private String password = "thomas";
    
    
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
    
	//Method to register the user in the list of users
    public boolean registerUser() throws IOException
	{
    	//Test Statements
    	//System.out.println("Beginning register");
    	//System.out.println("User firstName: " + this.firstName);
    	
    	//If the user is in the list of registered users, return false
		if (RegisteredUserAccess.contains(this) != -1) 
		{
			return false;
		}
		
		//If the user is not already on the list of users, add it 
		//and return true
		else RegisteredUserAccess.addUser(this);
		return true;
    }
    
    //Method to login the user
    public boolean login() throws IOException
    {
    	//If the list of users contains a user with the same username or email, run conditional
    	if (RegisteredUserAccess.contains(this)!=-1)
    	{
    		//Get the user that is at the specified location in the list
    		User user = RegisteredUserAccess.getUser(RegisteredUserAccess.contains(this));
    		
    		//If the passwords are equal, run the second condtional
    		if (this.getPassword().equals(user.getPassword()))
			{
    			//Set all of the user bean values and return true
				this.firstName = user.getFirstName();
				this.lastName = user.getLastName();
				this.address = user.getAddress();
				this.email = user.getEmail();
				this.phoneNum = user.getPhoneNum();
				return true;
    		}
    	}
    	
    	//If there are no mathces for username and password, return false
    	return false;
    }
    
    

}
