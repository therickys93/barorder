package it.therickys93.barorder.utils;

import it.therickys93.barorder.endpoints.CompleteOrder;
import it.therickys93.barorder.endpoints.DeleteOrder;
import it.therickys93.barorder.endpoints.DeleteProduct;
import it.therickys93.barorder.endpoints.DeleteProductAll;
import it.therickys93.barorder.endpoints.GetProducts;
import it.therickys93.barorder.endpoints.InsertOrder;
import it.therickys93.barorder.endpoints.InsertProduct;
import it.therickys93.barorder.endpoints.InsertProductWithPrice;
import it.therickys93.barorder.endpoints.OrderWithId;
import it.therickys93.barorder.endpoints.Orders;
import it.therickys93.barorder.endpoints.PayOrder;
import it.therickys93.barorder.endpoints.Payments;
import it.therickys93.barorder.endpoints.ProductsWithPrice;
import it.therickys93.barorder.endpoints.UpdateOrder;
import it.therickys93.barorder.server.Configurations;
import it.therickys93.barorder.server.IndexResource;
import it.therickys93.barorder.server.Status;

public class BarOrderInfo {

	private static final String PRODUCTS_WITH_PRICE = "productsWithPrice";
	private static final String INSERT_PRODUCT_WITH_PRICE = "insertProduct/{product}/{price}";
	private static final String INSERT_PRODUCT = "insertProduct/{product}";
	private static final String DELETE_PRODUCT = "deleteProduct/{product}";
	private static final String DELETE_PRODUCT_ALL = "deleteProductAll";
	private static final String STATUS = "status";
	private static final String PAY_ORDER = "payOrder";
	private static final String PAYMENTS = "payments";
	private static String DEFAULT_PATH = "/";
	private static String API_VERSION = "v1";
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

	public static String payOrderPath() {
		return "/" + PAY_ORDER;
	}

	public static String payOrderPathInfo() {
		return "Started " + PayOrder.class.toString() + " @ " + apiPath() + payOrderPath();
	}

	public static String statusPath() {
		return "/" + STATUS;
	}

	public static String statusPathInfo() {
		return "Started " + Status.class.toString() + " @ " + apiPath() + statusPath();
	}

	public static String deleteProductsAllPath() {
		return "/" + DELETE_PRODUCT_ALL;
	}

	public static String deleteProductPath() {
		return "/" + DELETE_PRODUCT;
	}

	public static String insertProductPath() {
		return "/" + INSERT_PRODUCT;
	}

	public static String deleteProductsAllPathInfo() {
		return "Started " + DeleteProductAll.class.toString() + " @ " + apiPath() + deleteProductsAllPath();
	}

	public static String deleteProductPathInfo() {
		return "Started " + DeleteProduct.class.toString() + " @ " + apiPath() + deleteProductPath();
	}

	public static String insertProductPathInfo() {
		return "Started " + InsertProduct.class.toString() + " @ " + apiPath() + insertProductPath();
	}

	public static String insertProductWithPricePath() {
		return "/" + INSERT_PRODUCT_WITH_PRICE;
	}

	public static String insertProductWithPricePathInfo() {
		return "Started " + InsertProductWithPrice.class.toString() + " @ " + apiPath() + insertProductWithPricePath();
	}

<<<<<<< HEAD
	public static String productsWithPricePath() {
		return "/" + PRODUCTS_WITH_PRICE;
	}

	public static String productsWithPricePathInfo() {
		return "Started " + ProductsWithPrice.class.toString() + " @ " + apiPath() + productsWithPricePath();
	}

=======
>>>>>>> aggiunto endpoint inserimento prodotto con prezzo
}
