package it.therickys93.barorder.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import it.therickys93.barorder.utils.BarOrderInfo;

public class BarOrderApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		// GET / --> return index page
		router.attach(BarOrderInfo.indexPath(), IndexResource.class);
		getLogger().info(BarOrderInfo.indexPathInfo());
		
//		// GET /about --> return about page
//		router.attach("about", IndexResource.class);
		
		return router;
	}
	
}
