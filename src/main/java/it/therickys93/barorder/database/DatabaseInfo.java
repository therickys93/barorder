package it.therickys93.barorder.database;

public class DatabaseInfo {

	public static void main(String[] args) {
		DatabaseIntegration database = new DatabaseIntegration();
		System.out.println("Database URL: " + database.url());
		System.out.println("Database Username: " + database.username());
		System.out.println("Database Password: " + database.password());
		System.out.println("Database Port: " + database.port());
		System.out.println("Database Name: " + database.database());
	}

}
