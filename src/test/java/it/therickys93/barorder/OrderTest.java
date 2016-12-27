package it.therickys93.barorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.model.Product;

public class OrderTest {
	
	@Test
	public void testOne() {
		Order order = new Order(102, 20, false, products());
		assertEquals(102, order.id());
		assertEquals(20, order.table());
		assertFalse(order.done());
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
	
	@Test
	public void testSix() {
		int id = Order.parseComplete("{\"id\": 102}");
		assertEquals(102, id);
		int newId = Order.parseComplete("{\"id\": \"102\"}");
		assertEquals(0, newId);
	}
	
	@Test
	public void testSeven() {
		Order order = new Order("{\"id\":102, \"table\": 20, \"done\": false, \"products\": [{\"name\": \"Cioccolata con panna\", \"quantity\": 2}, {\"name\": \"Cigni\", \"quantity\": 2}]}");
		assertTrue(order.ok());
		Order neworder = new Order("{\"id\":102, \"table\": \"20\", \"done\": false, \"products\": [{\"name\": \"Cioccolata con panna\", \"quantity\": 2}, {\"name\": \"Cigni\", \"quantity\": 2}]}");
		assertFalse(neworder.ok());
	}
	
	@Test
	public void jacksonTest() {
		Order order = new Order(0, 0, false, products());
		ObjectMapper objectWriter = new ObjectMapper();
		objectWriter.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		try {
			String s = objectWriter.writeValueAsString(order);
			assertEquals("{\"id\":0,\"table\":0,\"done\":false,\"products\":[{\"name\":\"Cioccolata con panna\",\"quantity\":2,\"ok\":true}],\"ok\":true}", s);
		} catch(JsonGenerationException e) {
			e.printStackTrace();
		} catch(JsonMappingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
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
