package it.therickys93.barorder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.restlet.representation.Representation;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.StatementResultSetHandler;
import com.mockrunner.mock.jdbc.MockConnection;
import com.mockrunner.mock.jdbc.MockResultSet;

import it.therickys93.barorder.endpoints.CompleteOrder;
import it.therickys93.barorder.endpoints.DeleteOrder;
import it.therickys93.barorder.endpoints.DeleteProductAll;
import it.therickys93.barorder.endpoints.GetProducts;
import it.therickys93.barorder.endpoints.InsertOrder;
import it.therickys93.barorder.endpoints.Orders;
import it.therickys93.barorder.endpoints.PayOrder;
import it.therickys93.barorder.endpoints.Payments;
import it.therickys93.barorder.endpoints.UpdateOrder;
import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.model.Product;

public class EndpointsTest extends BasicJDBCTestCaseAdapter {

	private void prepareEmptyResultSet() {
        MockConnection connection = 
            getJDBCMockObjectFactory().getMockConnection();
        StatementResultSetHandler statementHandler = 
            connection.getStatementResultSetHandler();
        MockResultSet result = statementHandler.createResultSet();
        statementHandler.prepareGlobalResultSet(result);
    }
	
	private void teardown() {
        verifyNotCommitted();
        verifyAllResultSetsClosed();
        verifyAllStatementsClosed();
        verifyConnectionClosed();
	}
	
	@Test
	public void getProductsWithDatabase() throws IOException {
		prepareEmptyResultSet();
		GetProducts products = new GetProducts();
		products.getProducts();
		teardown();
	}
	
	@Test
	public void getProductsWithoutDatabase() throws IOException {
		GetProducts products = new GetProducts();
		products.getProducts();
	}
	
	@Test
	public void ordersWithDatabase() throws IOException {
		prepareEmptyResultSet();
		Orders orders = new Orders();
		orders.orders();
		teardown();
	}
	
	@Test
	public void paymentsWithDatabase() throws IOException {
		prepareEmptyResultSet();
		Payments payments = new Payments();
		payments.payments();
		teardown();
	}
	
	@Test
	public void deleteProductsAllWithDatabase() throws IOException {
		prepareEmptyResultSet();
		DeleteProductAll deleteProductsAll = new DeleteProductAll();
		deleteProductsAll.deleteProductAll();
		teardown();
	}
	
	@Test
	public void payOrder() throws Exception {
		prepareEmptyResultSet();
		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn("{\"id\":102}");
		
		PayOrder payOrder = new PayOrder();
		payOrder.payOrder(representation);
		
		teardown();
	}
	
	@Test
	public void payOrderError() throws Exception {		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn("{\"id\":ciao}");
		
		PayOrder payOrder = new PayOrder();
		payOrder.payOrder(representation);
	}
	
	@Test
	public void completeOrder() throws Exception {
		prepareEmptyResultSet();
		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn("{\"id\":102}");
		
		CompleteOrder completeOrder = new CompleteOrder();
		completeOrder.completeOrder(representation);
		
		teardown();
	}
	
	@Test
	public void completeOrderError() throws Exception {		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn("{\"id\":ciao}");
		
		CompleteOrder completeOrder = new CompleteOrder();
		completeOrder.completeOrder(representation);
	}
	
	@Test
	public void deleteOrder() throws Exception {
		prepareEmptyResultSet();
		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn("{\"id\":102}");
		
		DeleteOrder deleteOrder = new DeleteOrder();
		deleteOrder.deleteOrder(representation);
		
		teardown();
	}
	
	@Test
	public void deleteOrderError() throws Exception {		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn("{\"id\":ciao}");
		
		DeleteOrder deleteOrder = new DeleteOrder();
		deleteOrder.deleteOrder(representation);
	}
	
	@Test
	public void insertOrder() throws Exception {
		prepareEmptyResultSet();
		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		InsertOrder insertOrder = new InsertOrder();
		insertOrder.newOrder(representation);
		
		teardown();
	}
	
	@Test
	public void insertOrderError() throws Exception {		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderNotOk());
		
		InsertOrder insertOrder = new InsertOrder();
		insertOrder.newOrder(representation);
	}
	
	@Test
	public void updateOrder() throws Exception {
		prepareEmptyResultSet();
		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderOk().toJson());
		
		UpdateOrder updateOrder = new UpdateOrder();
		updateOrder.updateOrder(representation);
		
		teardown();
	}
	
	@Test
	public void updateOrderError() throws Exception {		
		Representation representation = mock(Representation.class);
		when(representation.getText()).thenReturn(createOrderNotOk());
		
		UpdateOrder updateOrder = new UpdateOrder();
		updateOrder.updateOrder(representation);
	}
	
	private String createOrderNotOk() {
		return "ordine non in json";
	}
	
	private Order createOrderOk() {
		int id = 102;
		int table = 20;
		boolean done = false;
		Product[] products = {new Product("cappuccino", 2), new Product("brioches", 2)};
		return new Order(id, table, done, products);
	}
}
