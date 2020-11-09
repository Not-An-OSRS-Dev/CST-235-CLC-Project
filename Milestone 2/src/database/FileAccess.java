/*
 * Class to read and write users to and from a file
 * Creator: Thomas
 */

package database;

import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
import beans.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;


public class FileAccess 
{
	//The address of the file, this is not a relative address, so it will only work for me unless changed
	private static String filename = "C:\\Users\\tlbie\\EE Eclipse Workspace\\Milestone 2\\users.txt";
	
	//method to append a user to the file
	public static void writeUserToFile(User user) throws IOException {
		//Test print statement
		//System.out.println("Adding Users to file");
		
		//Create a fileWriter, with the append option set to true
		FileWriter writer = new FileWriter(new File(FileAccess.filename), true);
		
		//Use the pipe character as a delimeter for each of the user objects fields
		String userString = (user.getFirstName() + "|" + user.getLastName() + "|" + user.getEmail() + "|"
				+ user.getPhoneNum() + "|" + user.getAddress() + "|" + user.getUsername() + "|" + user.getPassword()
				+ "\n");
		
		//Test print
		//System.out.println(userString);

		//Write the user string to the file
		writer.write(userString);

		//Close the writer
		writer.close();
	}
	
	//Method to read all the users from the file
	public static ArrayList<User> readUsersFromFile() throws FileNotFoundException
	{
		//Create a scanner object
		Scanner scan = new Scanner(new File(FileAccess.filename));

		//Create a list of strings and a list of users
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<User> users = new ArrayList<User>();

		//For each line in the file, add it to the list
		while (scan.hasNextLine()) {
			lines.add(scan.nextLine());
		}
		
		//Test print statement
		//System.out.println("Printing lines:" + lines);
		
		//For each line of the file, run the split method
		for (String line : lines) {
			String[] properties = FileAccess.split(line, "|");
			
			//Create a new user object with each of the specified properties
			User user = new User(properties[0], properties[1], properties[2], properties[3], properties[4], 
					properties[5], properties[6]);

			//Add the user to the list of users
			users.add(user);
		}

		//Close the file scanner
		scan.close();

		//Return the list of users
		return users;
	}
	
	//For some reason the split method wasn't working, so I had to make my own
	private static String[] split(String s, String pattern) 
	{
		//System.out.println("running split");
		
		//Variables to save the property number being found 
		//and the number of times the pipe character has been found
		int numOccurances = 0;
		int ind=0;
		
		//While the next instance of the pipe character can be found
		while (ind!=-1)
		{
			//Increment the number of occurrences
			numOccurances++;
			
			//Reset the index to the next instance of the pipe character
			ind = s.indexOf(pattern, ind+1);
		}
		
		//Create the next property
		String[] items = new String[numOccurances];
		
		//Reset the index value to 0
		ind = 0;
		
		//for each time the pipe character is found
		for (int i = 0; i < numOccurances; i++)
		{
			//Find the next index of the pipe character
			int lastField = s.indexOf(pattern, ind+1);
			
			//If there is none, it is the last field, so use the substring command
			if (lastField==-1) items[i] = s.substring(ind);
			
			//If there is at least one more pipe character, run the substring command cut off at the value of ind+1
			else items[i] = s.substring(ind, s.indexOf(pattern, ind+1));
			//increment the ind value
			ind = s.indexOf(pattern, ind)+1;
		}
		
		//Return the split string
		return items;
	}
	
}
