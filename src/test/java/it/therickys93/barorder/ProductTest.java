package it.therickys93.barorder;

import it.therickys93.barorder.model.Product;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

	@Test
	public void testOne() {
		Product product = new Product("Cioccolata con panna", 2);
		assertEquals("Cioccolata con panna", product.name());
		assertEquals(2, product.quantity());
	}
	
	@Test
	public void testTwo() {
		Product product = new Product("Cioccolata con panna", 2);
		assertEquals("{\"name\":\"Cioccolata con panna\",\"quantity\":2}", product.toJson());
		assertEquals("Product={name=Cioccolata con panna, quantity=2}", product.toString());
	}
	
	@Test
	public void testThree() {
		Product product = new Product("{\"name\":\"Cioccolata con panna\", \"quantity\": 2}");
		assertEquals("Cioccolata con panna", product.name());
		assertEquals(2, product.quantity());
		assertEquals("{\"name\":\"Cioccolata con panna\",\"quantity\":2}", product.toJson());
		assertEquals("Product={name=Cioccolata con panna, quantity=2}", product.toString());
	}
	
	@Test
	public void testFour() {
		Product product = new Product("{\"name\":\"Cioccolata con panna\", \"quantity\": 2}");
		assertTrue(product.ok());
		Product newproduct = new Product("{\"name\":\"Cioccolata con panna\", \"quantity\": \"due\"}");
		assertFalse(newproduct.ok());
	}
	
}
