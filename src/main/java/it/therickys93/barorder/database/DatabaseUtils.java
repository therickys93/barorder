package it.therickys93.barorder.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.therickys93.barorder.model.Order;

public class DatabaseUtils {

	public static final String UPDATE = "update";
	public static final String INSERT = "insert";
	public static final String PAY = "pay";
	public static final String DELETE = "delete";
	public static final String COMPLETE = "complete";
	public static final String PAYMENTS = "payments";
	public static final String ORDERS = "orders";

	public static List<Order> get(String name) throws SQLException {
		List<Order> orders = new ArrayList<Order>();
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			if(name.equals(ORDERS)){
				orders = database.allOrders();
			} else if(name.equals(PAYMENTS)) {
				orders = database.allPayments();
			}
			database.close();
		} catch(Exception e){
			throw e;
		}
		return orders;
	}
	
	public static void performCall(String name, int id) throws SQLException {
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		if(name.equals(DELETE)){
			database.deleteOrder(id);
		} else if(name.equals(COMPLETE)){
			database.completeOrderWithId(id);
		} else if(name.equals(PAY)){
			database.payOrderWithId(id);
		}
		database.close();
	}
	
	public static void performCall(String name, Order order) throws SQLException {
		DatabaseIntegration database = new DatabaseIntegration();
		database.open();
		if(name.equals(INSERT)){
			database.insertNewOrder(order);
		} else if(name.equals(UPDATE)){
			database.updateOrder(order);
		}
		database.close();
	}
	
}
