package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;

public class ProductsWithPrice extends ServerResource {

	@Get
	public Map<String, Double> getProducts() throws IOException {
		
		Map<String, Double> response = new HashMap<String, Double>();
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			response = database.allProductsWithPrice();
			database.close();
		} catch(Exception e) {
			getLogger().info("error in the database : " + e.getMessage());
			return null;
		}
		getLogger().info(response.toString());
		return response;
	}
	
}
