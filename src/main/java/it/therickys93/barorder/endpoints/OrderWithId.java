package it.therickys93.barorder.endpoints;

import java.io.IOException;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.model.Order;

public class OrderWithId extends ServerResource {

	@Get
	public Order orderWithId() throws IOException {
		
		final String string_id = getAttribute("id");
		if(string_id.equals(null) || string_id.equals("")) {
			return null;
		}
		final int id = Integer.parseInt(string_id);
		
		Order order = null;
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			order = database.orderWithId(id);
			database.close();
		} catch(Exception e){
			getLogger().warning("Error in the database: " +e.getMessage());
		}
		return order;
	}
	
}
