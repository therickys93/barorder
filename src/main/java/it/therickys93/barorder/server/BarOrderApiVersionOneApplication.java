package it.therickys93.barorder.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class BarOrderApiVersionOneApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		// POST /v1/test
		router.attach("/test", TestResource.class);
		getLogger().info("Started " + TestResource.class.toString() + " @ /v1/test");
		
		// POST /v1/newOrder
		router.attach("/insertNewOrder", InsertNewOrder.class);
		getLogger().info("Started " + InsertNewOrder.class.toString() + " @ /v1/insertNewOrder");
		
		// POST /v1/updateOrder
		router.attach("/updateOrder", UpdateOrder.class);
		getLogger().info("Started " + UpdateOrder.class.toString() + " @ /v1/updateOrder");
		
		// GET /v1/products
		router.attach("/products", ProductsResource.class);
		getLogger().info("Started " + ProductsResource.class.toString() + " @ /v1/products");
		
		// POST /v1/completeOrder
		router.attach("/completeOrder", CompleteOrder.class);
		getLogger().info("Started " + CompleteOrder.class.toString() + " @ /v1/completeOrder");
		
		// POST /v1/deleteOrder
		router.attach("/deleteOrder", DeleteOrder.class);
		getLogger().info("Started " + DeleteOrder.class.toString() + " @ /v1/deleteOrder");
		
		return router;
	}
	
}
