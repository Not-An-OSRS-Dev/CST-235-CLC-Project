package database;

import java.io.File;
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
	private String filename;
	
	public FileAccess(String filename)
	{
		this.filename = filename;
	}
	
	public void writeUserToFile(User user) throws IOException
	{	
		System.out.println("Adding Users to file");
		FileWriter writer = new FileWriter(new File(this.filename), true);
		
		String userString = (user.getFirstName() + "|" + user.getLastName() + "|" 
				+ user.getEmail() + "|" + user.getPhoneNum() + "|"
				+ user.getAddress() + "|" + user.getUsername() + "|" 
				+ user.getPassword() + "\n");
		
		System.out.println(userString);
		
		writer.write(userString);
		
		writer.close();
		
		this.readUsersFromFile();
		
		
//		ObjectMapper map = new ObjectMapper();
//		
//		map.writeValue(new File(this.filename), user);
	}
	
	public ArrayList<User> readUsersFromFile() throws IOException
	{
		Scanner scan = new Scanner(new File(this.filename));
		
		ArrayList<String> lines = new ArrayList<String>();
		
		while (scan.hasNextLine())
		{
			lines.add(scan.nextLine());
		}
		
//		ObjectMapper map = new ObjectMapper();
		ArrayList<User> users = new ArrayList<User>();
		System.out.println("Printing lines:" + lines);
		for (String line : lines)
		{
//			System.out.println("line is " + line);
//			String[] properties = line.split("|", -1);
//			System.out.println("there are " + properties. + " properties");
//			System.out.println("68" + properties[1]);
//			System.out.println("The first name is: " + properties[0]);
			String[] properties = this.split(line,"|");
//			for (String property:properties)
//			{
//				System.out.print(property + "\t");
//			}
			User user = new User(properties[0], properties[1], properties[2], properties[3], properties[4], properties[5], properties[6]);
			//System.out.println("first name is: " + user.getFirstName());
			
			users.add(user);
		}
		
		scan.close();
//		FileReader read = new FileReader(filename);
//		while(read.read()!=-1)
//			{
//				while ((char)read.read()!='\n')
//				{
//					String line = "";
//					line = line + (char)read.read();
//					lines.add(line);
//				}	
//			}
//		System.out.println(lines);
//		read.close();
		
		return users;
	}
	
	private String[] split(String s, String pattern) throws IOException
	{
		System.out.println("running split");
		int numOccurances = 0;
		int ind=0;
		while (ind!=-1)
		{
			//System.out.println("106 Index is: " + ind);
			numOccurances++;
			ind = s.indexOf(pattern, ind+1);
			//System.out.println("109 Index is: " + ind);
		}
		String[] items = new String[numOccurances];
		ind = 0;
		//System.out.println("113 Index is: " + ind);
		for (int i = 0; i < numOccurances; i++)
		{
			int lastField = s.indexOf(pattern, ind+1);
			if (lastField==-1) items[i] = s.substring(ind);
			else items[i] = s.substring(ind, s.indexOf(pattern, ind+1));
			//System.out.print(i + ": " + items[i] + "\t");
			//System.out.println("118 Index is: " + ind);
			ind = s.indexOf(pattern, ind)+1;
			//System.out.println("120 Index is: " + ind);
		}
		//System.out.println();
		return items;
	}
	
}
