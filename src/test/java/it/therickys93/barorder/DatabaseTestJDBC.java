package it.therickys93.barorder;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.junit.Test;

import com.mockrunner.jdbc.BasicJDBCTestCaseAdapter;
import com.mockrunner.jdbc.StatementResultSetHandler;
import com.mockrunner.mock.jdbc.MockConnection;
import com.mockrunner.mock.jdbc.MockResultSet;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.database.DatabaseUtils;
import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.model.Product;

public class DatabaseTestJDBC extends BasicJDBCTestCaseAdapter {

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
	public void checkDatabaseStatus() throws Exception {
        prepareEmptyResultSet();
        DatabaseIntegration database = new DatabaseIntegration();
        database.open();
        database.checkDatabaseStatus();
        database.close();
        verifySQLStatementExecuted("select 1");
        teardown();
    }
	
	@Test
	public void getOrders() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.allOrders();
		database.close();
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void getPayments() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.allPayments();
		database.close();
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void testDatabaseMetadata() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		DatabaseMetaData meta = database.databaseMetadata();
		meta.toString();
		database.close();
		teardown();
	}
	
	@Test
	public void insertNewOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.insertNewOrder(createOrder());
		database.close();
		verifySQLStatementExecuted("insert");
		teardown();
	}
	
	@Test
	public void updateOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.updateOrder(createOrder());
		database.close();
		verifySQLStatementExecuted("insert");
		teardown();
	}
	
	private Order createOrder() {
		int id = 102;
		int table = 20;
		boolean done = false;
		Product[] products = {new Product("cappuccino", 2), new Product("brioches", 2)};
		return new Order(id, table, done, products);
	}
	
	@Test
	public void allProducts() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.allProducts();
		database.close();
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void productsWithId() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.productsWithId(102);
		database.close();
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void orderWithId() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.orderWithId(102);
		database.close();
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void completeOrderWithId() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.completeOrderWithId(102);
		database.close();
		verifySQLStatementExecuted("call");
		teardown();
	}
	
	@Test
	public void payOrderWithId() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.payOrderWithId(102);
		database.close();
		verifySQLStatementExecuted("call");
		teardown();
	}
	
	@Test
	public void deleteProductAll() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.deleteProductAll();
		database.close();
		verifySQLStatementExecuted("delete");
		teardown();
	}
	
	@Test(expected = SQLException.class)
	public void deleteProduct() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.deleteProduct("cioccolata con panna");
		database.close();
		verifySQLStatementExecuted("delete");
		teardown();
	}
	
	@Test
	public void insertProduct() throws SQLException {
		prepareEmptyResultSet();
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		database.insertProduct("cioccolata con panna");
		database.close();
		verifySQLStatementExecuted("insert");
		teardown();
	}
	
	@Test
	public void utilsGetOrder() throws Exception {
		prepareEmptyResultSet();
		DatabaseUtils.get(DatabaseUtils.ORDERS);
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void utilsGetPayments() throws Exception {
		prepareEmptyResultSet();
		DatabaseUtils.get(DatabaseUtils.PAYMENTS);
		verifySQLStatementExecuted("select");
		teardown();
	}
	
	@Test
	public void utilsGetError() throws Exception {
		prepareEmptyResultSet();
		DatabaseUtils.get("errore");
		teardown();
	}
	
	@Test
	public void utilsInsertOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall(DatabaseUtils.INSERT, createOrder());
		verifySQLStatementExecuted("insert");
		teardown();
	}
	
	@Test
	public void utilsUpdateOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall(DatabaseUtils.UPDATE, createOrder());
		verifySQLStatementExecuted("insert");
		teardown();
	}
	
	@Test
	public void utilsOrderError() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall("errore", createOrder());
		teardown();
	}
	
	@Test
	public void utilsDeleteOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall(DatabaseUtils.DELETE, 102);
		verifySQLStatementExecuted("delete");
		teardown();
	}
	
	@Test
	public void utilsCompleteOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall(DatabaseUtils.COMPLETE, 102);
		verifySQLStatementExecuted("call");
		teardown();
	}
	
	@Test
	public void utilsPayOrder() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall(DatabaseUtils.PAY, 102);
		verifySQLStatementExecuted("call");
		teardown();
	}
	
	@Test
	public void utilsCompleteOrderError() throws SQLException {
		prepareEmptyResultSet();
		DatabaseUtils.performCall("errore", 102);
		teardown();
	}
	
	@Test(expected = NullPointerException.class)
	public void utilsGetErrorCodeCoverage() throws SQLException {
		DatabaseUtils.get("orders");
	}
}
