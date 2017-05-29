package it.therickys93.barorder.server;

import java.io.IOException;
import java.util.Map;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.database.DatabaseIntegration;
import it.therickys93.barorder.utils.BarOrderResponse;

public class Status extends ServerResource{

	@Get
	public Map<String, Object> status() throws IOException {
		
		try {
			DatabaseIntegration database = new DatabaseIntegration();
			database.open();
			database.checkDatabaseStatus();
			database.close();
			getLogger().info(BarOrderResponse.status(true, Configurations.version(), true).toString());
			return BarOrderResponse.status(true, Configurations.version(), true);
		} catch(Exception e){
			getLogger().info(e.getMessage());
			getLogger().info(BarOrderResponse.status(true, Configurations.version(), false).toString());
			return BarOrderResponse.status(true, Configurations.version(), false);
		}
	}
	
}
