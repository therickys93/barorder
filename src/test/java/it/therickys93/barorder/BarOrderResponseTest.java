package it.therickys93.barorder;

import static org.junit.Assert.*;

import org.junit.Test;

import it.therickys93.barorder.utils.BarOrderResponse;

public class BarOrderResponseTest {

	@Test
	public void testOne() {
		assertFalse(BarOrderResponse.bad().get(BarOrderResponse.SUCCESS));
		assertTrue(BarOrderResponse.ok().get(BarOrderResponse.SUCCESS));
	}
		
}
