package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseUtils;
import it.therickys93.barorder.model.Order;

public class Orders extends ServerResource {

	@Get
	public List<Order> orders() throws IOException {
		List<Order> orders = new ArrayList<Order>();
		try {
			orders =  DatabaseUtils.get(DatabaseUtils.ORDERS);
		} catch (Exception e) {
			getLogger().warning("error in the database : " + e.getMessage());
			return null;
		}
		getLogger().info(orders.toString());
		return orders;
	}
	
}
