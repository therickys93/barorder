package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.util.Map;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.utils.BarOrderResponse;

public class DeleteProductAll extends ServerResource {

	@Post
	public Map<String, Boolean> deleteProductAll() throws IOException {
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			database.deleteProductAll();
			database.close();
		} catch(Exception e){
			e.printStackTrace();
			getLogger().info(BarOrderResponse.bad().toString());
			return BarOrderResponse.bad();
		}
		getLogger().info(BarOrderResponse.ok().toString());
		return BarOrderResponse.ok();
	}
	
}
