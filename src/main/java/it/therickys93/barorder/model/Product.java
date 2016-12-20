package it.therickys93.barorder.model;

import it.therickys93.barorder.parser.JSONObject;
import it.therickys93.barorder.parser.JSONParser;

public class Product {

	private String name;
	private int quantity;
	
	public Product(String name, int quantity){
		this.name = name;
		this.quantity = quantity;
	}
	
	public Product(String json){
		try {
			JSONParser parser = new JSONParser();
			JSONObject product = (JSONObject)parser.parse(json);
			this.name = (String)product.get("name");
			Long quantity = (Long)product.get("quantity");
			this.quantity = quantity.intValue();
		} catch(Exception e){
			e.printStackTrace();
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
	
}
