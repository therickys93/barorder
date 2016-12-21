package it.therickys93.barorder.server;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.model.Order;

public class CompleteOrder extends ServerResource {

	@Post
	public Map<String, Boolean> completeOrder(Representation data) throws IOException {
		
		String request = data.getText();
		getLogger().info(request);
		
		int id = Order.parseComplete(request);
		if(id == 0){
			getLogger().warning("bad request");
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "bad request");
			return null;
		}
		getLogger().info("" + id);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("success", false);
		
		try {
			Connection conn = DriverManager.getConnection(Configurations.url(), Configurations.user(), Configurations.password());
			CallableStatement callStatement = conn.prepareCall("{ CALL completeOrder(?)}");
			callStatement.setInt(1, id);
			callStatement.execute();
			callStatement.close();
			conn.close();
			response.remove("success");
			response.put("success", true);
		} catch (Exception e){
			getLogger().warning("Error with the database" + e.getMessage());
		}
		
		return response;
	}
	
}
