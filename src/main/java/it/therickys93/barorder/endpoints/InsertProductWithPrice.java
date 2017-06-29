package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.utils.BarOrderResponse;

public class InsertProductWithPrice extends ServerResource {

	@Post
	public Map<String, Boolean> insertProduct() throws IOException {
		String product = getAttribute("product");
		String price = getAttribute("price");
		Double.parseDouble(price);
		product = URLDecoder.decode(product, "UTF-8");
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			database.insertProduct(product, Double.parseDouble(price));
			database.close();
		} catch(Exception e){
			getLogger().info("database error: " + e.getMessage());
			getLogger().info(BarOrderResponse.bad().toString());
			return BarOrderResponse.bad();
		}
		getLogger().info(BarOrderResponse.ok().toString());
		return BarOrderResponse.ok();
	}
	
}
