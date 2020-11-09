/*
 * Class to represent a product
 * Creator: Arin
 */

package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class Product 
{
	//Fields for name and description are Strings, 
	//and have size constraints of 5-15 and 5-50 chars, respectively
	@Size(min=5, max=15, message="You must have between 5 and 15 characters")
	String prodName = "";
	@Size(min=5, max=50, message="You must have between 5 and 50 characters")
	String desc = "";
	
	//The price is a double variable, and must be at least 1 cent
	@DecimalMin("0.01")
	double price = 0;
	
	//The stock is an integer quantity, and cannot be less than 0
	@Min(0)
	int stock = 0;
	
	//The photo location is held by a string value, and may be blank
	String photo = "";
	
	//Default constructor
	public Product()
	{
		prodName = "";
		desc = "";
		price = 0;
		stock = 0;
		photo = "";
	}


	//Returns the product name
	public String getProdName() {
		return prodName;
	}


	//Sets the product name to specified value
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	//Returns the product description
	public String getDesc() {
		return desc;
	}


	//Sets the product description to specified value
	public void setDesc(String desc) {
		this.desc = desc;
	}


	//Returns the product price
	public double getPrice() {
		return price;
	}

	//Sets the product price to specified value
	public void setPrice(double price) {
		this.price = price;
	}

	//Returns the product stock
	public int getStock() {
		return stock;
	}

	//Sets the product stock to specified value
	public void setStock(int stock) {
		this.stock = stock;
	}

	//Returns the product photo location
	public String getPhoto() {
		return photo;
	}

	//Sets the product photo location to specified value
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
