/*
 * Class to manage all of the beans and button handlers
 * Creator: Thomas
 */

package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import beans.Product;
import beans.User;

@ManagedBean
@ViewScoped
public class Controller 
{
	//EJB for the users account credentials and info
	@EJB
	public User user;
	
	//Button handler for the registration page
	public String onSubmit() throws IOException
	{
		//Test print statement
		//System.out.println("The users name is :" + user.getFirstName());
		
		//If the user is not a duplicate, register it
		//and naviagate to logged in page
		if (user.registerUser()) 
		{
			
			return "LoggedIn.xhtml";
		}
		
		//if registration fails, return login failed
		return "LoginFailed.xhtml";
	}
	
	//Button handler for the login page
	public String onLogin() throws IOException
	{
		//If the user can be logged in, return success
		if (user.login())
		{
			return "LoggedIn.xhtml";
		}
		
		//else return failure
		return "LoginFailed.xhtml";
	}
	
	//Button handler for the product creation page
	public String onProductAdd()
	{
		//Create a product object from the text field values
		FacesContext context = FacesContext.getCurrentInstance();
		Product product = context.getApplication().evaluateExpressionGet(context, "#{product}", Product.class);
		
		//Send the product to the product created page
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("product", product);
		return "ProductCreated.xhtml";	
	}
	
	//Getter for the user EJB
	public User getUser()
	{
		return this.user;
	}
	
	//Setter for the user EJB	
	public void setUser(User user)
	{
		this.user = user;
	}
}