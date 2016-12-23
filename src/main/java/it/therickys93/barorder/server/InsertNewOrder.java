package it.therickys93.barorder.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.model.Order;

public class InsertNewOrder extends ServerResource{

	@Post
	public Map<String, Boolean> newOrder(Representation data) throws IOException {
		
		String request = data.getText();
		getLogger().info(request);
		
		Order order = new Order(request);
		if(!order.ok()) {
			getLogger().warning("bad request");
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "bad request");
			return null;
		}
		getLogger().info(order.toString());
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("success", false);
		
		try {
			DatabaseIntegration database = new DatabaseIntegration(Configurations.url(), Configurations.user(), Configurations.password());
			database.open();
			database.insertNewOrder(order);
			database.close();
			response.remove("success");
			response.put("success", true);
		} catch (Exception e){
			getLogger().warning("Error from the database: " + e.getMessage());
		}
		return response;
	}
	
}
