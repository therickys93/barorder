package it.therickys93.barorder.database;

import java.sql.*;

import it.therickys93.barorder.model.Order;

public class DatabaseIntegration {

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
		// TODO: implementarlo insertNewOrder()
	}
	
	public void completeOrderWithId(int id) throws SQLException {
		CallableStatement callableStatement = this.connection.prepareCall("{ CALL completeOrder(?)}");
		callableStatement.setInt(1, id);
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
