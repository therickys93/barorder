package it.therickys93.barorder.utils;

import java.util.HashMap;
import java.util.Map;

public class BarOrderResponse {
	
	public static final String SUCCESS = "success";

	public static Map<String, Boolean> bad() {
		return createResponseWithSuccess(false);
	}

	public static Map<String, Boolean> ok() {
		return createResponseWithSuccess(true);
	}
	
	private static Map<String, Boolean> createResponseWithSuccess(Boolean success) {
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put(SUCCESS, success);
		return response;
	}

}
