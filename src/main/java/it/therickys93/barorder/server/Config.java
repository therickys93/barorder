package it.therickys93.barorder.server;

public enum Config {
	INSTANCE;

	public final int PORT;

	private Config() {
		PORT = Integer.parseInt(System.getenv("PORT"));
	}
}
