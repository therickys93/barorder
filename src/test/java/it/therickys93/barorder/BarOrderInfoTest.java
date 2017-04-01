package it.therickys93.barorder;
import static org.junit.Assert.*;

import org.junit.Test;

import it.therickys93.barorder.utils.BarOrderInfo;

public class BarOrderInfoTest {

	@Test
	public void testApiVersionPathAndOutput() {
		assertEquals("/v1", BarOrderInfo.apiPath());
		assertEquals("Server started @ http://localhost:8080", BarOrderInfo.serverInfo());
	}
	
	@Test
	public void testPaths() {
		assertEquals("/test", BarOrderInfo.testPath());
		assertEquals("/insertOrder", BarOrderInfo.insertOrderPath());
		assertEquals("/updateOrder", BarOrderInfo.updateOrderPath());
		assertEquals("/completeOrder", BarOrderInfo.completeOrderPath());
		assertEquals("/products", BarOrderInfo.productsPath());
		assertEquals("/deleteOrder", BarOrderInfo.deleteOrderPath());
		assertEquals("/orders", BarOrderInfo.ordersPath());
		assertEquals("/order/{id}", BarOrderInfo.orderWithIdPath());
		assertEquals("/payments", BarOrderInfo.paymentsPath());
	}
	
	@Test
	public void testPathOutput() {
		assertEquals("Started class it.therickys93.barorder.server.TestResource @ /v1/test", BarOrderInfo.testPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.InsertOrder @ /v1/insertOrder", BarOrderInfo.insertOrderPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.UpdateOrder @ /v1/updateOrder", BarOrderInfo.updateOrderPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.GetProducts @ /v1/products", BarOrderInfo.productsPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.CompleteOrder @ /v1/completeOrder", BarOrderInfo.completeOrderPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.DeleteOrder @ /v1/deleteOrder", BarOrderInfo.deleteOrderPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.Orders @ /v1/orders", BarOrderInfo.ordersPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.OrderWithId @ /v1/order/{id}", BarOrderInfo.orderWithIdPathInfo());
		assertEquals("Started class it.therickys93.barorder.server.Payments @ /v1/payments", BarOrderInfo.paymentsPathInfo());
	}
	
	@Test
	public void testApplication() {
		assertEquals("/", BarOrderInfo.defaultPath());
		assertEquals("", BarOrderInfo.indexPath());
		assertEquals("Started class it.therickys93.barorder.server.IndexResource @ /", BarOrderInfo.indexPathInfo());
	}
	
}
