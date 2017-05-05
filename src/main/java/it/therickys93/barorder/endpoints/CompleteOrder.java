package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.util.Map;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseUtils;
import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.utils.BarOrderResponse;

public class CompleteOrder extends ServerResource {

	@Post
	public Map<String, Boolean> completeOrder(Representation data) throws IOException {
		
		String request = data.getText();
		getLogger().info(request);
		
		int id = Order.parseComplete(request);
		if(id == 0){
			getLogger().info("bad request");
			getLogger().warning(BarOrderResponse.bad().toString());
			return BarOrderResponse.bad();
		}
		getLogger().info("" + id);
				
		try {
			DatabaseUtils.performCall(DatabaseUtils.COMPLETE, id);
		} catch (Exception e){
			getLogger().warning("Error with the database" + e.getMessage());
			return BarOrderResponse.bad();
		}
		getLogger().info(BarOrderResponse.ok().toString());
		return BarOrderResponse.ok();
	}
	
}
