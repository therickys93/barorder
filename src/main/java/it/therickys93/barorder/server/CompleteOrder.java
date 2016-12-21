package it.therickys93.barorder.server;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.model.Order;

public class CompleteOrder extends ServerResource {

	@Post
	public String completeOrder(Representation data) throws IOException {
		
		String request = data.getText();
		getLogger().info(request);
		
		int id = Order.parseComplete(request);
		getLogger().info("" + id);
		
		return "" + id;
	}
	
}
