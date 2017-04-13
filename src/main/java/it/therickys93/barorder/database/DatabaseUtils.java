package it.therickys93.barorder.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.therickys93.barorder.model.Order;

public class DatabaseUtils {

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
	
}
