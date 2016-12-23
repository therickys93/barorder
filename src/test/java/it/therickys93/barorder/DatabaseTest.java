package it.therickys93.barorder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.server.Configurations;
import it.therickys93.barorder.server.ProductsResource;

public class DatabaseTest {
	
	@Test
	public void testDatabaseIntegrationClass() {
		DatabaseIntegration database = new DatabaseIntegration(Configurations.url(), Configurations.user(), Configurations.password());
		assertEquals("jdbc:mysql://localhost:3306/barorder", database.url());
		assertEquals("root", database.username());
		assertEquals("password", database.password());
	}
	
	@Test
	public void testOne() {
		assertEquals("SELECT * FROM product", ProductsResource.GET_ALL_PRODUCTS_QUERY);
		assertEquals(1, ProductsResource.PRODUCT_NAME_COLUMN);
	}

}
