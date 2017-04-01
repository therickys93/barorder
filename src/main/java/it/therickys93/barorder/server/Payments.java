package it.therickys93.barorder.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.model.Order;

public class Payments extends ServerResource{

	@Get
	public List<Order> payments() throws IOException {
		List<Order> orders = new ArrayList<Order>();
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			orders = database.allPayments();
			database.close();
		} catch(Exception e){
			getLogger().warning("Error in the database: " + e.getMessage());
		}
		return orders;
	}
}
