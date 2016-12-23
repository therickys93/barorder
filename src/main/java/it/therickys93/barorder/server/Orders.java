package it.therickys93.barorder.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.model.Order;

public class Orders extends ServerResource {

	@Get
	public List<String> orders() throws IOException {
		List<String> orders = new ArrayList<String>();
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			orders = database.allOrders();
			database.close();
		} catch(Exception e){
			getLogger().warning("Error in the database: " + e.getMessage());
		}
		return orders;
	}
	
}
