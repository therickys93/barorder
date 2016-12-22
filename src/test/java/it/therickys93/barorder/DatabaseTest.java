package it.therickys93.barorder;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import it.therickys93.barorder.server.ProductsResource;

public class DatabaseTest {
	
	@Test
	public void testOne() {
		assertEquals("SELECT * FROM product", ProductsResource.GET_ALL_PRODUCTS_QUERY);
		assertEquals(1, ProductsResource.PRODUCT_NAME_COLUMN);
	}

}
