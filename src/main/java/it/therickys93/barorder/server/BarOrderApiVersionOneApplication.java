package it.therickys93.barorder.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import it.therickys93.barorder.endpoints.CompleteOrder;
import it.therickys93.barorder.endpoints.DeleteProduct;
import it.therickys93.barorder.endpoints.DeleteProductAll;
import it.therickys93.barorder.endpoints.GetProducts;
import it.therickys93.barorder.endpoints.InsertOrder;
import it.therickys93.barorder.endpoints.InsertProduct;
import it.therickys93.barorder.endpoints.InsertProductWithPrice;
import it.therickys93.barorder.endpoints.Orders;
import it.therickys93.barorder.endpoints.PayOrder;
import it.therickys93.barorder.endpoints.Payments;
import it.therickys93.barorder.endpoints.ProductsWithPrice;
import it.therickys93.barorder.utils.BarOrderInfo;

public class BarOrderApiVersionOneApplication extends Application {
	
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
				
		// POST /v1/insertOrder
		router.attach(BarOrderInfo.insertOrderPath(), InsertOrder.class);
		getLogger().info(BarOrderInfo.insertOrderPathInfo());
		
		// POST /v1/updateOrder
		/*
		router.attach(BarOrderInfo.updateOrderPath(), UpdateOrder.class);
		getLogger().info(BarOrderInfo.updateOrderPathInfo());
		*/
		
		// GET /v1/products
		router.attach(BarOrderInfo.productsPath(), GetProducts.class);
		getLogger().info(BarOrderInfo.productsPathInfo());
		
		// GET /v1/productsWithPrice
		router.attach(BarOrderInfo.productsWithPricePath(), ProductsWithPrice.class);
		getLogger().info(BarOrderInfo.productsWithPricePathInfo());
		
		// POST /v1/completeOrder
		router.attach(BarOrderInfo.completeOrderPath(), CompleteOrder.class);
		getLogger().info(BarOrderInfo.completeOrderPathInfo());
		
		// POST /v1/deleteOrder
		/*
		router.attach(BarOrderInfo.deleteOrderPath(), DeleteOrder.class);
		getLogger().info(BarOrderInfo.deleteOrderPathInfo());
		*/
		
		/*
		// GET /v1/order/{id}
		router.attach(BarOrderInfo.orderWithIdPath(), OrderWithId.class);
		getLogger().info(BarOrderInfo.orderWithIdPathInfo());
		*/
		
		// GET /v1/orders
		router.attach(BarOrderInfo.ordersPath(), Orders.class);
		getLogger().info(BarOrderInfo.ordersPathInfo());
		
		// GET /v1/payments --> prende tutti gli ordini fatti ma non ancora pagati
		router.attach(BarOrderInfo.paymentsPath(), Payments.class);
		getLogger().info(BarOrderInfo.paymentsPathInfo());
		
		// POST /v1/payOrder
		router.attach(BarOrderInfo.payOrderPath(), PayOrder.class);
		getLogger().info(BarOrderInfo.payOrderPathInfo());
		
		// GET /v1/status
		router.attach(BarOrderInfo.statusPath(), Status.class);
		getLogger().info(BarOrderInfo.statusPathInfo());
		
		// POST /v1/deleteProductAll
		router.attach(BarOrderInfo.deleteProductsAllPath(), DeleteProductAll.class);
		getLogger().info(BarOrderInfo.deleteProductsAllPathInfo());
		
		// POST /v1/deleteProduct/{product}
		router.attach(BarOrderInfo.deleteProductPath(), DeleteProduct.class);
		getLogger().info(BarOrderInfo.deleteProductPathInfo());
		
		// POST /v1/insertProduct/{product}
		router.attach(BarOrderInfo.insertProductPath(), InsertProduct.class);
		getLogger().info(BarOrderInfo.insertProductPathInfo());
		
		// POST /v1/insertProduct/{product}/{price}
		router.attach(BarOrderInfo.insertProductWithPricePath(), InsertProductWithPrice.class);
		getLogger().info(BarOrderInfo.insertProductWithPricePathInfo());
		
		return router;
	}
	
}
