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

public class NewOrder extends ServerResource{

	@Post
	public Map<String, Boolean> newOrder(Representation data) throws IOException {
		
		String request = data.getText();
		getLogger().info(request);
		
		Order order = new Order(request);
		if(!order.ok()) {
			getLogger().warning("bad request");
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "bad request");
			return null;
		}
		getLogger().info(order.toString());
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("success", false);
		
		try {
			Connection conn = DriverManager.getConnection(Configurations.url(), Configurations.user(), Configurations.password());
			CallableStatement callStatement = conn.prepareCall("{ CALL insertNewOrder(?, ?, ?, ?)}");
			callStatement.setInt(1, order.id());
			callStatement.setInt(2, order.table());
			callStatement.setString(3, order.products()[0].name());
			callStatement.setInt(4, order.products()[0].quantity());
			callStatement.execute();
			callStatement = conn.prepareCall("{ CALL updateOrder(?, ?, ?)}");
			callStatement.setInt(1, order.id());
			for(int i = 1; i < order.products().length; i++) {
				callStatement.setString(2, order.products()[i].name());
				callStatement.setInt(3, order.products()[i].quantity());
				callStatement.execute();
			}
			callStatement.close();
			conn.close();
			response.remove("success");
			response.put("success", true);
		} catch (Exception e){
			getLogger().warning("Error from the database: " + e.getMessage());
		}
		return response;
	}
	
}
