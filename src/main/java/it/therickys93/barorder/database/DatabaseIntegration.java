package it.therickys93.barorder.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.model.Product;
import it.therickys93.barorder.server.Configurations;

public class DatabaseIntegration {

	private static final String SELECT_ALL_PAYMENTS = "select o.id from barorder.order as o where done = 1 and pay = 0";
	private static final String SELECT_ALL_ORDERS = "select o.id from barorder.order as o where done = 0 and pay = 0";
	public static final String COMPLETE_ORDER_QUERY = "{ CALL completeOrder(?)}";
	public static final int COMPLETE_ORDER_ID = 1;
	public static final String PAY_ORDER_QUERY = "{ CALL payOrder(?)}";
	public static final int PAY_ORDER_ID = 1;
	public static final String INSERT_NEW_ORDER_QUERY = "{ CALL insertNewOrder(?, ?, ?, ?)}";
	public static final int INSERT_NEW_ORDER_ID = 1;
	public static final int INSERT_NEW_ORDER_TABLE = 2;
	public static final int INSERT_NEW_ORDER_NAME = 3;
	public static final int INSERT_NEW_ORDER_QUANTITY = 4;
	public static final String UPDATE_ORDER_QUERY = "{ CALL updateOrder(?, ?, ?)}";
	public static final int UPDATE_ORDER_ID = 1;
	public static final int UPDATE_ORDER_NAME = 2;
	public static final int UPDATE_ORDER_QUANTITY = 3;
	public static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM product";
	public static final int PRODUCT_NAME_COLUMN = 1; 
	public static final String DELETE_ORDER_QUERY = "{ CALL deleteOrder(?)}";
	public static final int DELETE_ORDER_ID = 1;
	
	private Connection connection;
	private String url;
	private String username;
	private String password;
	private String port;
	private String database;
	
	public DatabaseIntegration(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		String[] tokens = url.split(":");
		String portAndDatabase = tokens[3];
		this.port = portAndDatabase.split("/")[0];
		this.database = portAndDatabase.split("/")[1];
	}
	
	public String port() {
		return this.port;
	}
	
	public String database() {
		return this.database;
	}
	
	public DatabaseIntegration() {
		this(Configurations.url(), Configurations.user(), Configurations.password());
	}
	
	public DatabaseMetaData databaseMetadata() throws SQLException {
		return this.connection.getMetaData();
	}
	
	public void insertNewOrder(Order order) throws SQLException {
		CallableStatement callableStatement = this.connection.prepareCall(INSERT_NEW_ORDER_QUERY);
		callableStatement.setInt(INSERT_NEW_ORDER_ID, order.id());
		callableStatement.setInt(INSERT_NEW_ORDER_TABLE, order.table());
		callableStatement.setString(INSERT_NEW_ORDER_NAME, order.products()[0].name());
		callableStatement.setInt(INSERT_NEW_ORDER_QUANTITY, order.products()[0].quantity());
		callableStatement.execute();
		callableStatement = this.connection.prepareCall(UPDATE_ORDER_QUERY);
		callableStatement.setInt(UPDATE_ORDER_ID, order.id());
		for(int i = 1; i < order.products().length; i++) {
			callableStatement.setString(UPDATE_ORDER_NAME, order.products()[i].name());
			callableStatement.setInt(UPDATE_ORDER_QUANTITY, order.products()[i].quantity());
			callableStatement.execute();
		}
		callableStatement.close();
	}
	
	public void updateOrder(Order order) throws SQLException {
		deleteOrder(order.id());
		insertNewOrder(order);
	}
		
	public List<String> allProducts() throws SQLException {
		List<String> response = new ArrayList<String>();
		Statement statement = this.connection.createStatement();
		statement.execute(GET_ALL_PRODUCTS_QUERY);
		ResultSet resultSet = statement.getResultSet();
		while(resultSet.next()) {
			response.add(resultSet.getString(PRODUCT_NAME_COLUMN));
		}
		resultSet.close();
		statement.close();
		return response;
	}
	
	public List<Order> allOrders() throws SQLException {
		return all(SELECT_ALL_ORDERS);
	}
	
	public List<Order> allPayments() throws SQLException {
		return all(SELECT_ALL_PAYMENTS);
	}
	
	private List<Order> all(String query) throws SQLException {
		List<Order> orders = new ArrayList<Order>();
		Statement statement = this.connection.createStatement();
		statement.execute(query);
		ResultSet resultSet = statement.getResultSet();
		while(resultSet.next()) {
			orders.add(orderWithId(resultSet.getInt(1)));
		}
		return orders;
	}
	
	public List<Product> productsWithId(int id) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		PreparedStatement preparedStatement = this.connection.prepareStatement("select name, quantity from barorder.has_products where id = ?");
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			Product product = new Product(resultSet.getString(1), resultSet.getInt(2));
			products.add(product);
		}
		return products;
	}
	
	public Order orderWithId(int id) throws SQLException {
		Order order = null;
		PreparedStatement statement = this.connection.prepareStatement("select o.id, o.table, o.done from barorder.order as o where id = ?;");
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			order = new Order(resultSet.getInt(1), resultSet.getInt(2), resultSet.getBoolean(3), productsWithId(id).toArray(new Product[0]));
		}
		resultSet.close();
		statement.close();
		return order;
	}
	
	public void completeOrderWithId(int id) throws SQLException {
		performCallWithQueryIdAndOrderId(COMPLETE_ORDER_QUERY, COMPLETE_ORDER_ID, id);
	}
	
	public void payOrderWithId(int id) throws SQLException {
		performCallWithQueryIdAndOrderId(PAY_ORDER_QUERY, PAY_ORDER_ID, id);
	}
	
	public void deleteOrder(int id) throws SQLException {
		performCallWithQueryIdAndOrderId(DELETE_ORDER_QUERY, DELETE_ORDER_ID, id);
	}
	
	private void performCallWithQueryIdAndOrderId(String query, int id, int orderId) throws SQLException{
		CallableStatement callableStatement = this.connection.prepareCall(query);
		callableStatement.setInt(id, orderId);
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
