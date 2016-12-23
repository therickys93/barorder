package it.therickys93.barorder.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;

public class GetProducts extends ServerResource {
	
	@Get
	public List<String> getProducts() throws IOException {
		
		List<String> response = new ArrayList<String>();
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			response = database.allProducts();
			database.close();
		} catch(Exception e) {
			response = null;
		}
		getLogger().info(response.toString());
		return response;
	}
	
}

