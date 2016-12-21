package it.therickys93.barorder.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectingDatabase {

	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection(Configurations.url(), Configurations.user(), Configurations.password());
			Statement statement = conn.createStatement();
			statement.execute("SELECT * FROM product");
			ResultSet resultSet = statement.getResultSet();
			while(resultSet.next()) {
				System.out.println("product: " + resultSet.getString(1));
			}
			resultSet.close();
			statement.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
