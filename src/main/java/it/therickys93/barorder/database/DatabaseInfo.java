package it.therickys93.barorder.database;

public class DatabaseInfo {
	private final DatabaseIntegration database;
	
	public DatabaseInfo() {
		database = new DatabaseIntegration();
	}
	
	public void info() {
		System.out.println("Database URL: " + database.url());
		System.out.println("Database Username: " + database.username());
		System.out.println("Database Password: " + database.password());
		System.out.println("Database Port: " + database.port());
		System.out.println("Database Name: " + database.database());
	}
	
	public static void main(String[] args) {
		DatabaseInfo database = new DatabaseInfo();
		database.info();
	}

}
