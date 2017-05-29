package it.therickys93.barorder.server;

public class Configurations {

	public static int port() {
		if(System.getenv("BARORDER_PORT") != null) {
			return Integer.parseInt(System.getenv("BARORDER_PORT"));
		} else {
			return 8080;
		}
	}
	
	public static String host() {
		if(System.getenv("BARORDER_HOST") != null) {
			return System.getenv("BARORDER_HOST");
		} else {
			return "localhost";
		}
	}
	
	public static String user() {
		if(System.getenv("BARORDER_USER") != null) {
			return System.getenv("BARORDER_USER");
		} else {
			return "root";
		}
	}
	
	public static String password() {
		if(System.getenv("BARORDER_PASSWORD") != null) {
			return System.getenv("BARORDER_PASSWORD");
		} else {
			return "password";
		}
	}
	
	public static String database() {
		if(System.getenv("BARORDER_DATABASE") != null) {
			return System.getenv("BARORDER_DATABASE");
		} else {
			return "barorder";
		}
	}
	
	public static String url() {
		String url = "";
		url += "jdbc:mysql://" + host() + ":3306/" + database();
		return url;
	}

	public static String version() {
		return "1.0.0";
	}
	
}
