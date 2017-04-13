package it.therickys93.barorder.endpoints;

import java.io.IOException;
import java.util.Map;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseUtils;
import it.therickys93.barorder.model.Order;
import it.therickys93.barorder.utils.BarOrderResponse;

public class PayOrder extends ServerResource{

	@Post
	public Map<String, Boolean> payOrder(Representation data) throws IOException {
		String request = data.getText();
		getLogger().info(request);
		
		int id = Order.parsePayed(request);
		if(id == 0){
			getLogger().warning("bad request");
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "bad request");
			return null;
		}
		getLogger().info("" + id);
				
		try {
			DatabaseUtils.performCall(DatabaseUtils.PAY, id);
		} catch (Exception e){
			getLogger().warning("Error with the database" + e.getMessage());
			return BarOrderResponse.bad();
		}
		
		return BarOrderResponse.ok();
	}
	
}
