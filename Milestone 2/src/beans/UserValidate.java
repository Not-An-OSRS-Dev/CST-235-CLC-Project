package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Size;

import database.RegisteredUserAccess;

@ManagedBean
@ViewScoped
public class UserValidate 
{
	@Size(min=5, max=15, message="You must enter a username")
	private String username;
	@Size(min=5, max=15, message="You must enter a password")
	private String password;
	
	private User user;
	
	public UserValidate(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public UserValidate()
	{
		
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

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean validate()
	{
		System.out.println("Running validate method");
		System.out.println("validators: " + this.username + "\t" + this.password);
		for (User user: RegisteredUserAccess.getUsers())
		{
			System.out.println("username and password:" + user.username + "\t" + user.password);
			
			if (user.username.equals(this.username) && user.password.equals(this.password))
			{
				this.user = user;
				return true;
			}
		}
		return false;
	}
}
