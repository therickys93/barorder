package it.therickys93.barorder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.restlet.representation.Representation;

import it.therickys93.barorder.endpoints.CompleteOrder;
import it.therickys93.barorder.endpoints.DeleteOrder;
import it.therickys93.barorder.endpoints.DeleteProduct;
import it.therickys93.barorder.endpoints.DeleteProductAll;
import it.therickys93.barorder.endpoints.InsertOrder;
import it.therickys93.barorder.endpoints.InsertProduct;
import it.therickys93.barorder.endpoints.OrderWithId;
import it.therickys93.barorder.endpoints.PayOrder;
import it.therickys93.barorder.endpoints.UpdateOrder;
import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.model.Product;
import it.therickys93.barorder.utils.BarOrderInfo;
import it.therickys93.barorder.utils.BarOrderResponse;

public class ImproveCodeCoverageTest {

	@Test
	public void testOne(){
		BarOrderResponse response = new BarOrderResponse();
		response.toString();
	}
	
	@Test
	public void testTwo() {
		BarOrderInfo info = new BarOrderInfo();
		info.toString();
	}
	
	@Test
	public void testThree() throws IOException {
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		UpdateOrder updateOrder = new UpdateOrder();
		updateOrder.updateOrder(representation);
	}
	
	@Test
	public void testFour() throws IOException {
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		DeleteOrder order = new DeleteOrder();
		order.deleteOrder(representation);
	}
	
	@Test
	public void testFive() throws IOException {
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		InsertOrder order = new InsertOrder();
		order.newOrder(representation);
	}
	
	@Test
	public void testSix() throws IOException {
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		CompleteOrder order = new CompleteOrder();
		order.completeOrder(representation);
	}
	
	@Test
	public void testSeven() throws IOException {
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		PayOrder order = new PayOrder();
		order.payOrder(representation);
	}
	
	@Test
	public void testEight() throws IOException {
		OrderWithId orderId = mock(OrderWithId.class);
		when(orderId.getAttribute("id")).thenReturn("102");
		when(orderId.orderWithId()).thenCallRealMethod();
		when(orderId.getLogger()).thenCallRealMethod();
		
		orderId.orderWithId();
	}
	
	@Test
	public void testNine() throws IOException {
		OrderWithId order = new OrderWithId();
		order.toString();
	}
	
	@Test
	public void testTen() throws IOException {
		DeleteProductAll all = new DeleteProductAll();
		all.deleteProductAll();
	}
	
	@Test
	public void testEleven() throws IOException {
		InsertProduct insert = new InsertProduct();
		insert.toString();
	}
	
	@Test
	public void test12() throws IOException {
		InsertProduct insert = mock(InsertProduct.class);
		when(insert.getAttribute("product")).thenReturn("cioccolata%20con%20panna");
		when(insert.getLogger()).thenCallRealMethod();
		when(insert.insertProduct()).thenCallRealMethod();
		insert.insertProduct();
	}
	
	@Test
	public void test13() throws IOException {
		DeleteProduct product = new DeleteProduct();
		product.toString();
	}
	
	@Test
	public void test14() throws IOException {
		DeleteProduct product = mock(DeleteProduct.class);
		when(product.getAttribute("product")).thenReturn("cioccolata%20con%20panna");
		when(product.getLogger()).thenCallRealMethod();
		when(product.deleteProduct()).thenCallRealMethod();
		
		product.deleteProduct();
	}
	
	private Order createOrderOk() {
		int id = 102;
		int table = 20;
		boolean done = false;
		Product[] products = {new Product("cappuccino", 2), new Product("brioches", 2)};
		return new Order(id, table, done, products);
	}
}
