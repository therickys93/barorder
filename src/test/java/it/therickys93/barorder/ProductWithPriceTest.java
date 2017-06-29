package it.therickys93.barorder;

import static org.junit.Assert.*;

import org.junit.Test;

import it.therickys93.barorder.model.ProductWithPrice;

public class ProductWithPriceTest {

	private static final double DELTA = 1e-15;
	
	@Test
	public void testOne() {
		ProductWithPrice pwp = new ProductWithPrice("cioccolata con panna", 2.5);
		assertEquals("cioccolata con panna", pwp.name());
		assertEquals(2.5, pwp.price(), DELTA);
	}
	
}
