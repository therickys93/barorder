package it.therickys93.barorder.server;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.service.LogService;

import it.therickys93.barorder.utils.BarOrderInfo;

public class Server {
	private final Component component;
	
	public Server(){
		component = new Component();
		component.getServers().add(Protocol.HTTP, Configurations.port());
		component.getDefaultHost().attach(BarOrderInfo.defaultPath(), new BarOrderApplication());
		component.getDefaultHost().attach(BarOrderInfo.apiPath(), new BarOrderApiVersionOneApplication());
		component.setLogService(new LogService(false));
		component.getLogger().info(BarOrderInfo.serverInfo());
	}
	
	public void start() throws Exception {
		component.start();
	}
		
	public void stop() throws Exception {
		component.stop();
	}
	
	public static void main(String[] args) throws Exception{
		final Server server = new Server();
		server.start();
	}
}
