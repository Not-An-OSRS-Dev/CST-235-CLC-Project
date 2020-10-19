package database;

import java.util.ArrayList;
import beans.User;

public class RegisteredUserAccess
{
	private static ArrayList<User> users;
	
	public static boolean addUser(User user)
	{
		if (!RegisteredUserAccess.getUsers().contains(user))
		{
			RegisteredUserAccess.getUsers().add(user);
			return true;
		}
		else return false;
	}
	
	public static ArrayList<User> getUsers()
	{
		return RegisteredUserAccess.users;
	}
	
	public void setUsers(ArrayList<User> users)
	{
		RegisteredUserAccess.users = users;
	}
	
	public static void readUsers(ArrayList<User> users)
	{
		RegisteredUserAccess.users = users;
	}
}
