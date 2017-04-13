package it.therickys93.barorder.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Order {

	private static final String PRODUCTS = "products";
	private static final String DONE = "done";
	private static final String TABLE = "table";
	private static final String ORDER_ID = "id";
	public int id;
	public int table;
	public boolean done;
	public Product[] products;
	private boolean ok;
	
	public Order(int id, int table, boolean done, Product[] products) {
		this.id = id;
		this.table = table;
		this.done = done;
		this.products = products;
		this.ok = true;
	}
	
	public Order(String json){
		try {
			JsonParser parser = new JsonParser();
			JsonObject order = parser.parse(json).getAsJsonObject();
			this.id = order.get(ORDER_ID).getAsInt();
			this.table = order.get(TABLE).getAsInt();
			this.done = order.get(DONE).getAsBoolean();
			JsonArray products = order.get(PRODUCTS).getAsJsonArray();
			Product[] prods = new Product[products.size()];
			for(int i = 0; i < products.size(); i++){
				JsonObject prod = products.get(i).getAsJsonObject();
				Product product = new Product(prod.toString());
				prods[i] = product;
			}
			this.products = prods;
			this.ok = true;
		} catch(Exception e){
			this.ok = false;
		}
	}

	public int id() {
		return this.id;
	}

	public int table() {
		return this.table;
	}

	public boolean done() {
		return this.done;
	}

	public Product[] products() {
		return this.products;
	}

	public String toString() {
		String response = "";
		response += "Order={id=" + this.id + ", table=" + this.table + ", done=" + this.done + ", products=[";
		for(int i = 0; i < this.products.length; i++){
			response += this.products[i].toString();
			if(i == this.products.length - 1) {
				
			} else {
				response += ", ";
			}
		}
		response += "]}";
		return response;
	}

	public String toJson() {
		String response = "";
		response += "{\"id\":" + this.id + ",\"table\":" + this.table + ",\"done\":" + this.done + ",\"products\":[";
		for(int i = 0; i < this.products.length; i++){
			response += this.products[i].toJson();
			if(i == this.products.length - 1) {
				
			} else {
				response += ",";
			}
		}
		response += "]}";
		return response;
	}

	public static int parseComplete(String json) {
		int results = 0;
		try {
			JsonParser parser = new JsonParser();
			JsonObject complete = parser.parse(json).getAsJsonObject();
			results = complete.get(ORDER_ID).getAsInt();
		} catch(Exception e){
			results = 0;
		}
		return results;
	}

	public boolean ok() {
		return this.ok;
	}
	
}
