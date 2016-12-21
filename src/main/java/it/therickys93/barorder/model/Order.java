package it.therickys93.barorder.model;

import it.therickys93.barorder.parser.JSONArray;
import it.therickys93.barorder.parser.JSONObject;
import it.therickys93.barorder.parser.JSONParser;

public class Order {

	private int id;
	private int table;
	private boolean done;
	private Product[] products;
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
			JSONParser parser = new JSONParser();
			JSONObject complete = (JSONObject)parser.parse(json);
			Long id = (Long)complete.get("id");
			results = id.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public boolean ok() {
		return this.ok;
	}
	
}
