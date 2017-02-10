package it.therickys93.barorder.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class IndexResource extends ServerResource{

	@Get
	public StringRepresentation hello() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("src/main/resources/index.html"));
		String html = scanner.useDelimiter("\\Z").next();
		scanner.close();
		StringRepresentation output = new StringRepresentation(html);
		output.setMediaType(MediaType.TEXT_HTML);
		return output;
	}
	
}
