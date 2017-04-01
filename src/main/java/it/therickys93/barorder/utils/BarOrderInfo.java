package it.therickys93.barorder.utils;

import it.therickys93.barorder.server.CompleteOrder;
import it.therickys93.barorder.server.Configurations;
import it.therickys93.barorder.server.DeleteOrder;
import it.therickys93.barorder.server.GetProducts;
import it.therickys93.barorder.server.IndexResource;
import it.therickys93.barorder.server.InsertOrder;
import it.therickys93.barorder.server.OrderWithId;
import it.therickys93.barorder.server.Orders;
import it.therickys93.barorder.server.Payments;
import it.therickys93.barorder.server.TestResource;
import it.therickys93.barorder.server.UpdateOrder;

public class BarOrderInfo {

	private static final String PAYMENTS = "payments";
	private static String DEFAULT_PATH = "/";
	private static String API_VERSION = "v1";
	private static String TEST = "test";
	private static String INSERT_ORDER = "insertOrder";
	private static String UPDATE_ORDER = "updateOrder";
	private static String COMPLETE_ORDER = "completeOrder";
	private static String DELETE_ORDER = "deleteOrder";
	private static String PRODUCTS = "products";
	private static String ORDERS = "orders";
	private static String ORDER_WITH_ID = "order/{id}";
	
	public static String apiPath() {
		return "/" + API_VERSION;
	}
	
	public static String testPathInfo() {
		return "Started " + TestResource.class.toString() + " @ " + apiPath() + testPath();
	}
	
	public static String insertOrderPathInfo() {
		return "Started " + InsertOrder.class.toString() + " @ " + apiPath() + insertOrderPath();
	}
	
	public static String updateOrderPathInfo() {
		return "Started " + UpdateOrder.class.toString() + " @ " + apiPath() + updateOrderPath();
	}
	
	public static String completeOrderPathInfo() {
		return "Started " + CompleteOrder.class.toString() + " @ " + apiPath() + completeOrderPath();
	}
	
	public static String deleteOrderPathInfo() {
		return "Started " + DeleteOrder.class.toString() + " @ " + apiPath() + deleteOrderPath();
	}
	
	public static String productsPathInfo() {
		return "Started " + GetProducts.class.toString() + " @ " + apiPath() + productsPath();
	}
	
	public static String serverInfo() {
		return "Server started @ http://localhost:" + Configurations.port();
	}
	
	public static String testPath() {
		return "/" + TEST;
	}
	
	public static String insertOrderPath() {
		return "/" + INSERT_ORDER;
	}
	
	public static String updateOrderPath() {
		return "/" + UPDATE_ORDER;
	}
	
	public static String completeOrderPath() {
		return "/" + COMPLETE_ORDER;
	}
	
	public static String deleteOrderPath() {
		return "/" + DELETE_ORDER;
	}
	
	public static String productsPath() {
		return "/" + PRODUCTS;
	}

	public static String ordersPath() {
		return "/" + ORDERS;
	}

	public static String orderWithIdPath() {
		return "/" + ORDER_WITH_ID;
	}

	public static String ordersPathInfo() {
		return "Started " + Orders.class.toString() + " @ " + apiPath() + ordersPath();
	}

	public static String orderWithIdPathInfo() {
		return "Started " + OrderWithId.class.toString() + " @ " + apiPath() + orderWithIdPath();
	}

	public static String defaultPath() {
		return DEFAULT_PATH;
	}

	public static String indexPath() {
		return "";
	}

	public static String indexPathInfo() {
		return "Started "+ IndexResource.class.toString() +" @ " + defaultPath() + indexPath();
	}

	public static String paymentsPath() {
		return "/" + PAYMENTS;
	}

	public static String paymentsPathInfo() {
		return "Started " + Payments.class.toString() + " @ " + apiPath() + paymentsPath();
	}

}
