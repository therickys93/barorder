package it.therickys93.barorder.model;

public class ProductWithPrice {

	public String name;
	public double price;
	
	public ProductWithPrice(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String name() {
		return this.name;
	}

	public double price() {
		return this.price;
	}

}
