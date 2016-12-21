package it.therickys93.barorder.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ProductsResource extends ServerResource {

	public static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM product";
	public static final int PRODUCT_NAME_COLUMN = 1; 
	
	@Get
	public List<String> getProducts() throws IOException {
		
		List<String> response = new ArrayList<String>();
		try {
			Connection conn = DriverManager.getConnection(Configurations.url(), Configurations.user(), Configurations.password());
			Statement statement = conn.createStatement();
			statement.execute(GET_ALL_PRODUCTS_QUERY);
			ResultSet resultSet = statement.getResultSet();
			while(resultSet.next()) {
				response.add(resultSet.getString(PRODUCT_NAME_COLUMN));
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch(Exception e) {
			response = null;
		}
		getLogger().info(response.toString());
		return response;
	}
	
}

