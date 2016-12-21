package it.therickys93.barorder.server;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.model.Order;

public class UpdateOrder extends ServerResource {

	@Post
	public String updateOrder(Representation data) throws IOException {
		
		getLogger().info(data.getText());
		
		Order order = new Order(data.getText());
		getLogger().info(order.toString());
		
		return order.toString();
	}
	
}
