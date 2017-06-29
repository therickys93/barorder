package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.model.ProductWithPrice;

public class ProductsWithPrice extends ServerResource {

	@Get
	public List<ProductWithPrice> getProducts() throws IOException {
		
		List<ProductWithPrice> response = new ArrayList<ProductWithPrice>();
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
