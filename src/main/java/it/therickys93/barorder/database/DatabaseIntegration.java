package it.therickys93.barorder.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import it.therickys93.barorder.model.Order;

public class DatabaseIntegration {

	public static final String COMPLETE_ORDER_QUERY = "{ CALL completeOrder(?)}";
	public static final int COMPLETE_ORDER_ID = 1;
	
	private Connection connection;
	private String url;
	private String username;
	private String password;
	
	public DatabaseIntegration(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public DatabaseMetaData databaseMetadata() throws SQLException {
		return this.connection.getMetaData();
	}
	
	public void insertNewOrder(Order order) throws SQLException {
		// TODO: implement insertNewOrder()
	}
	
	public void updateOrder(Order order) throws SQLException {
		// TODO: implement updateOrder();
	}
	
	public List<String> getAllProducts() throws SQLException {
		// TODO: implement getAllProducts();
		return null;
	}
	
	public void completeOrderWithId(int id) throws SQLException {
		CallableStatement callableStatement = this.connection.prepareCall(COMPLETE_ORDER_QUERY);
		callableStatement.setInt(COMPLETE_ORDER_ID, id);
		callableStatement.execute();
		callableStatement.close();
	}
	
	public void open() throws SQLException {
		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	public void close() throws SQLException {
		this.connection.close();
	}
	
	public String url() {
		return this.url;
	}
	
	public String username() {
		return this.username;
	}
	
	public String password() {
		return this.password;
	}
	
}
