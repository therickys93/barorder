package it.therickys93.barorder.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import it.therickys93.barorder.utils.BarOrderInfo;

public class BarOrderApiVersionOneApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		// POST /v1/test
		router.attach(BarOrderInfo.testPath(), TestResource.class);
		getLogger().info(BarOrderInfo.testPathInfo());
		
		// POST /v1/insertOrder
		router.attach(BarOrderInfo.insertOrderPath(), InsertOrder.class);
		getLogger().info(BarOrderInfo.insertOrderPathInfo());
		
		// POST /v1/updateOrder
		router.attach(BarOrderInfo.updateOrderPath(), UpdateOrder.class);
		getLogger().info(BarOrderInfo.updateOrderPathInfo());
		
		// GET /v1/products
		router.attach(BarOrderInfo.productsPath(), GetProducts.class);
		getLogger().info(BarOrderInfo.productsPathInfo());
		
		// POST /v1/completeOrder
		router.attach(BarOrderInfo.completeOrderPath(), CompleteOrder.class);
		getLogger().info(BarOrderInfo.completeOrderPathInfo());
		
		// POST /v1/deleteOrder
		router.attach(BarOrderInfo.deleteOrderPath(), DeleteOrder.class);
		getLogger().info(BarOrderInfo.deleteOrderPathInfo());
		
		// GET /v1/order/{id}
		router.attach(BarOrderInfo.orderWithIdPath(), OrderWithId.class);
		getLogger().info(BarOrderInfo.orderWithIdPathInfo());
		
		// GET /v1/orders
		router.attach(BarOrderInfo.ordersPath(), Orders.class);
		getLogger().info(BarOrderInfo.ordersPathInfo());
		
		return router;
	}
	
}
