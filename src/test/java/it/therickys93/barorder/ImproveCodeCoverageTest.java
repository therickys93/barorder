package it.therickys93.barorder;

import org.junit.Test;

import it.therickys93.barorder.utils.BarOrderInfo;
import it.therickys93.barorder.utils.BarOrderResponse;

public class ImproveCodeCoverageTest {

	@Test
	public void testOne(){
		BarOrderResponse response = new BarOrderResponse();
		response.toString();
	}
	
	@Test
	public void testTwo() {
		BarOrderInfo info = new BarOrderInfo();
		info.toString();
	}
}
