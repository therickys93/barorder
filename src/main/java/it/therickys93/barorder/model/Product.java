package it.therickys93.barorder.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Product {

	private static final String QUANTITY = "quantity";
	private static final String NAME = "name";
	public String name;
	public int quantity;
	private boolean ok;
	
	public Product(String name, int quantity){
		this.name = name;
		this.quantity = quantity;
		this.ok = true;
	}
	
	public Product(String json){
		try{
			JsonParser parser = new JsonParser();
			JsonObject product = parser.parse(json).getAsJsonObject();
			this.name = product.get(NAME).getAsString();
			this.quantity = product.get(QUANTITY).getAsInt();
			this.ok = true;
		} catch (Exception e){
			this.ok = false;
		}
	}

	public String name() {
		return this.name;
	}
	
	public String toString() {
		return "Product={name="+this.name+", quantity="+this.quantity+"}";
	}

	public int quantity() {
		return this.quantity;
	}

	public String toJson() {
		return "{\"name\":\""+this.name+"\",\"quantity\":"+this.quantity+"}";
	}

	public boolean ok() {
		return this.ok;
	}
	
}
