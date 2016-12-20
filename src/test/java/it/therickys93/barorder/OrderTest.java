package it.therickys93.barorder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.model.Product;

public class OrderTest {
	
	@Test
	public void testOne() {
		Order order = new Order(102, 20, false, products());
		assertEquals(102, order.id());
		assertEquals(20, order.table());
		assertEquals("Cioccolata con panna", order.products()[0].name());
		assertEquals(2, order.products()[0].quantity());
	}
	
	@Test
	public void testTwo() {
		Order order = new Order(102, 20, false, products());
		assertEquals("Order={id=102, table=20, done=false, products=[Product={name=Cioccolata con panna, quantity=2}]}", order.toString());
		assertEquals("{\"id\":102,\"table\":20,\"done\":false,\"products\":[{\"name\":\"Cioccolata con panna\",\"quantity\":2}]}", order.toJson());
	}
	
	@Test
	public void testThree() {
		Order order = new Order(102, 20, false, moreThanOne());
		assertEquals("{\"id\":102,\"table\":20,\"done\":false,\"products\":[{\"name\":\"Cioccolata con panna\",\"quantity\":2},{\"name\":\"Cigni\",\"quantity\":2}]}", order.toJson());
		assertEquals("Order={id=102, table=20, done=false, products=[Product={name=Cioccolata con panna, quantity=2}, Product={name=Cigni, quantity=2}]}", order.toString());
	}
	
	@Test
	public void testFour() {
		Order order = new Order("{\"id\":102, \"table\": 20, \"done\": false, \"products\": [{\"name\": \"Cioccolata con panna\", \"quantity\": 2}]}");
		assertEquals("Order={id=102, table=20, done=false, products=[Product={name=Cioccolata con panna, quantity=2}]}", order.toString());
		assertEquals("{\"id\":102,\"table\":20,\"done\":false,\"products\":[{\"name\":\"Cioccolata con panna\",\"quantity\":2}]}", order.toJson());
	}
	
	@Test
	public void testFive(){
		Order order = new Order("{\"id\":102, \"table\": 20, \"done\": false, \"products\": [{\"name\": \"Cioccolata con panna\", \"quantity\": 2}, {\"name\": \"Cigni\", \"quantity\": 2}]}");
		assertEquals("{\"id\":102,\"table\":20,\"done\":false,\"products\":[{\"name\":\"Cioccolata con panna\",\"quantity\":2},{\"name\":\"Cigni\",\"quantity\":2}]}", order.toJson());
		assertEquals("Order={id=102, table=20, done=false, products=[Product={name=Cioccolata con panna, quantity=2}, Product={name=Cigni, quantity=2}]}", order.toString());
	}
	
	private Product[] products() {
		Product[] products = new Product[1];
		products[0] = new Product("Cioccolata con panna", 2);
		return products;
	}
	
	private Product[] moreThanOne() {
		Product[] products = new Product[2];
		products[0] = new Product("Cioccolata con panna", 2);
		products[1] = new Product("Cigni", 2);
		return products;
	}
}
