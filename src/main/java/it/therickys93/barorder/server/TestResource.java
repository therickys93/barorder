package it.therickys93.barorder.server;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class TestResource extends ServerResource {

	@Post
	public String update(Representation data) throws IOException {
		return data.getText();
	}
	
}
