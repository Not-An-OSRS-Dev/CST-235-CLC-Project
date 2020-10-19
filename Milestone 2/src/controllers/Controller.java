package controllers;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import beans.User;
import beans.UserValidate;
import database.FileAccess;
import database.RegisteredUserAccess;

@ManagedBean
@ViewScoped
public class Controller 
{
	public String validateLogin(String username, String password)
	{
		return "LoggedIn.xhtml";
	}
	
	public String onSubmit()
	{
		System.out.println("Running onSubmit");
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		System.out.println("Running try/catch");
		FileAccess file = new FileAccess("C:\\Users\\tlbie\\EE Eclipse Workspace\\test\\users.txt");
		try {
			RegisteredUserAccess.setUsers(file.readUsersFromFile());
			
			if (RegisteredUserAccess.getUsers().size() == 0)
				System.out.println("Couldn't find any registered users");
			System.out.println("Number of users is " + RegisteredUserAccess.getUsers().size());
			for (User temp: RegisteredUserAccess.getUsers())
			{
				int i = 0;
				System.out.println(i + ": " + temp.getFirstName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The application was not able to read the users from the file");
		}
		if (RegisteredUserAccess.getUsers().contains(user))
		{
			//TODO: Deciede which properties can't be duplicated and make method to validate
			System.out.println("There is already a user with those properties");
			return "LoginFailed.xhtml";
		}
		else System.out.println("Success! YAY");
		RegisteredUserAccess.addUser(user);
		try {
			file.writeUserToFile(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("The application was not able to add your account to the list of users");
			e.printStackTrace();
		}
//		for (User test: RegisteredUserAccess.getUsers())
//		{
//			System.out.println("FirstName is : " + test.getFirstName());
//		}
		
		//Forward to test response View along with the user managed bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "LoggedIn.xhtml";
	}
	
	public String onLogin() throws IOException
	{
		//return "Index.xhtml";
		System.out.println("Running on login method");
		FileAccess file = new FileAccess("C:\\Users\\tlbie\\EE Eclipse Workspace\\test\\users.txt");
		RegisteredUserAccess.setUsers(file.readUsersFromFile());
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("context determined");
		UserValidate validate = context.getApplication().evaluateExpressionGet(context, "#{userValidate}", UserValidate.class);
		System.out.println("userValidate instance determined");
		
		if (validate.validate())
		{
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", validate.getUser());
			System.out.println("Login Success");
			return "LoggedIn.xhtml";
		}
		System.out.println("Login Failed");
		return "LoginFailed.xhtml";
	}
	
//	public String onSubmit(User user)
//	{
//		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
//		FileAccess file = new FileAccess("/Milestone/Resources/userDB");
//		try 
//		{
//			RegisteredUserAccess.readUsers(file.readUsersFromFile());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if (RegisteredUserAccess.getUsers().contains((User) user))
//		{
//			return "LoginFailed.xhtml";
//		}
//		else RegisteredUserAccess.addUser(user);
//		return "LoggedIn.xhtml";	
//	}
}