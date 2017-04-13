package it.therickys93.barorder.server;

import java.io.IOException;
import java.util.Map;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import it.therickys93.barorder.utils.BarOrderResponse;

public class Status extends ServerResource{

	@Get
	public Map<String, Boolean> status() throws IOException {
		return BarOrderResponse.ok();
	}
	
}
