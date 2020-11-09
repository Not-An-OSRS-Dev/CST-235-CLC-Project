/*
 * Class to hold the list of registered users
 * Creator: Thomas
 */

package database;

import java.io.IOException;
import java.util.ArrayList;
import beans.User;

public class RegisteredUserAccess
{
	//Field to hold list of registered users
	public static ArrayList<User> users;
	
	//Method to add one user to the list
	public static boolean addUser(User user) throws IOException
	{
		//If there is no user with a matching email or username, add the user
		if (!RegisteredUserAccess.getUsers().contains(user))
		{
			RegisteredUserAccess.getUsers().add(user);
			
			//Must also write the user to the file for persistence
			FileAccess.writeUserToFile(user);
			
			//Return true
			return true;
		}
		
		//If the user cannot be added, return false
		else return false;
	}
	
	//Method to return all the users in the list
	public static ArrayList<User> getUsers() throws IOException
	{
		//If the file hasn't been read yet, do so now
		if (RegisteredUserAccess.users == null)
		{
			RegisteredUserAccess.setUsers(FileAccess.readUsersFromFile());
		}
		
		//Return the list of users
		return RegisteredUserAccess.users;
	}
	
	//Method to reset all the users, not ever used
	public static void setUsers(ArrayList<User> users)
	{
		RegisteredUserAccess.users = users;
	}
	
	//Method to get the user at the specified position
	public static User getUser(int position)
	{
		return RegisteredUserAccess.users.get(position);
	}
	
	//Method to see if there is a user with a matching username or email in the list
	public static int contains(User user) throws IOException
	{
		//Must use the getUsers command to make sure that the list isn't null
		RegisteredUserAccess.getUsers();
		
		//For each user, compare the username and email to the user passed in
		for (int ind = 0; ind < RegisteredUserAccess.users.size(); ind++)
		{
			//If the users match, return the specified index
			if (RegisteredUserAccess.users.get(ind).getUsername().equals(user.getUsername()) || RegisteredUserAccess.users.get(ind).getEmail().equals(user.getEmail()))
			{
				return ind;
			}
		}
		
		//If there is no match, return -1
		return -1;
	}
}
