package it.therickys93.barorder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.therickys93.barorder.database.DatabaseInfo;
import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.database.DatabaseUtils;
import it.therickys93.barorder.server.Configurations;

public class DatabaseTest {
	
	@Test
	public void testDatabaseIntegrationClass() {
		DatabaseIntegration database = new DatabaseIntegration(Configurations.url(), Configurations.user(), Configurations.password());
		assertEquals("jdbc:mysql://localhost:3306/barorder", database.url());
		assertEquals("root", database.username());
		assertEquals("password", database.password());
		assertEquals("3306", database.port());
		assertEquals("barorder", database.database());
	}
	
	@Test
	public void testDatabaseIntegrationClassWithNoArguments() {
		DatabaseIntegration database = new DatabaseIntegration();
		assertEquals("jdbc:mysql://localhost:3306/barorder", database.url());
		assertEquals("root", database.username());
		assertEquals("password", database.password());
		assertEquals("3306", database.port());
		assertEquals("barorder", database.database());
	}
	
	@Test
	public void testCompleteOrderConstants() {
		assertEquals("{ CALL completeOrder(?)}", DatabaseIntegration.COMPLETE_ORDER_QUERY);
		assertEquals(1, DatabaseIntegration.COMPLETE_ORDER_ID);
	}
	
	@Test
	public void testInsertNewOrderConstants() {
		assertEquals("{ CALL insertNewOrder(?, ?, ?, ?)}", DatabaseIntegration.INSERT_NEW_ORDER_QUERY);
		assertEquals(1, DatabaseIntegration.INSERT_NEW_ORDER_ID);
		assertEquals(2, DatabaseIntegration.INSERT_NEW_ORDER_TABLE);
		assertEquals(3, DatabaseIntegration.INSERT_NEW_ORDER_NAME);
		assertEquals(4, DatabaseIntegration.INSERT_NEW_ORDER_QUANTITY);
	}
	
	@Test
	public void testUpdateOrderConstants() {
		assertEquals("{ CALL updateOrder(?, ?, ?)}", DatabaseIntegration.UPDATE_ORDER_QUERY);
		assertEquals(1, DatabaseIntegration.UPDATE_ORDER_ID);
		assertEquals(2, DatabaseIntegration.UPDATE_ORDER_NAME);
		assertEquals(3, DatabaseIntegration.UPDATE_ORDER_QUANTITY);
	}
	
	@Test
	public void testDeleteOrderConstants() {
		assertEquals("{ CALL deleteOrder(?)}", DatabaseIntegration.DELETE_ORDER_QUERY);
		assertEquals(1, DatabaseIntegration.DELETE_ORDER_ID);
	}
	
	@Test
	public void testGetAllProducts() {
		assertEquals("SELECT * FROM product", DatabaseIntegration.GET_ALL_PRODUCTS_QUERY);
		assertEquals(1, DatabaseIntegration.PRODUCT_NAME_COLUMN);
	}
	
	@Test
	public void testDatabaseInfo() {
		DatabaseInfo database = new DatabaseInfo();
		database.toString();
		database.info();
	}
	
	@Test
	public void testDatabaseUtils() {
		DatabaseUtils database = new DatabaseUtils();
		database.toString();
	}	
}
