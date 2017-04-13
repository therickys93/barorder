package it.therickys93.barorder.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Order {

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
		/*
		try {
			JSONParser parser = new JSONParser();
			JSONObject order = (JSONObject)parser.parse(json);
			Long id = (Long)order.get("id");
			this.id = id.intValue();
			Long table = (Long)order.get("table");
			this.table = table.intValue();
			this.done = (boolean)order.get("done");
			JSONArray products = (JSONArray)order.get("products");
			Product[] prods = new Product[products.size()];
			for(int i = 0; i < products.size(); i++) {
				JSONObject json_product = (JSONObject)products.get(i);
				Product product = new Product(json_product.toJSONString());
				prods[i] = product;
			}
			this.products = prods;
			this.ok = true;
		} catch(Exception e){
			this.ok = false;
		}
		*/
		try {
			JsonParser parser = new JsonParser();
			JsonObject order = parser.parse(json).getAsJsonObject();
			this.id = order.get("id").getAsInt();
			this.table = order.get("table").getAsInt();
			this.done = order.get("done").getAsBoolean();
			JsonArray products = order.get("products").getAsJsonArray();
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
		/*
		int results = 0;
		try {
			JSONParser parser = new JSONParser();
			JSONObject complete = (JSONObject)parser.parse(json);
			Long id = (Long)complete.get("id");
			results = id.intValue();
		} catch (Exception e) {
			results = 0;
		}
		return results;
		*/
		int results = 0;
		try {
			JsonParser parser = new JsonParser();
			JsonObject complete = parser.parse(json).getAsJsonObject();
			results = complete.get("id").getAsInt();
		} catch(Exception e){
			results = 0;
		}
		return results;
	}

	public boolean ok() {
		return this.ok;
	}
	
}
