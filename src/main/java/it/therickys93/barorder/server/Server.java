package it.therickys93.barorder.server;

import org.restlet.Component;
import org.restlet.data.Protocol;

import it.therickys93.barorder.database.DatabaseInfo;
import it.therickys93.barorder.utils.BarOrderInfo;

public class Server {
	private final Component component;
	
	public Server(){
		component = new Component();
		component.getServers().add(Protocol.HTTP, Configurations.port());
		component.getDefaultHost().attach(BarOrderInfo.defaultPath(), new BarOrderApplication());
		component.getDefaultHost().attach(BarOrderInfo.apiPath(), new BarOrderApiVersionOneApplication());
		component.getLogService().setResponseLogFormat(Configurations.logFormat());
		component.getLogger().info(BarOrderInfo.serverInfo());
	}
	
	public void start() throws Exception {
		component.start();
	}
		
	public void stop() throws Exception {
		component.stop();
	}
	
	public static void main(String[] args) throws Exception{
		final DatabaseInfo database = new DatabaseInfo();
		database.info();
		final Server server = new Server();
		server.start();
	}
}
