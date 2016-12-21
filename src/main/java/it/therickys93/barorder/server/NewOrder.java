package it.therickys93.barorder.server;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.model.Order;

public class NewOrder extends ServerResource{

	@Post
	public String newOrder(Representation data) throws IOException {
		
		getLogger().info(data.getText());
		
		Order order = new Order(data.getText());
		if(!order.ok()) {
			getLogger().warning("bad request");
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "bad request");
			return null;
		}
		getLogger().info(order.toString());
		
		return order.toString();
	}
	
}
