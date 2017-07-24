package it.therickys93.barorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import it.therickys93.barorder.server.Configurations;

public class ConfigTest {

	@Rule
	public final EnvironmentVariables environmentVariables = new EnvironmentVariables();
	
	@Test
	public void testOne() {
		assertEquals(Integer.parseInt("8080"), Configurations.port());
		assertEquals("localhost", Configurations.host());
		assertEquals("root", Configurations.user());
		assertEquals("password", Configurations.password());
		assertEquals("barorder", Configurations.database());
		assertNull(Configurations.version());
	}
	
	@Test
	public void testTwo() {
		environmentVariables.set("BARORDER_PORT", "80");
		environmentVariables.set("BARORDER_HOST", "mysql");
		environmentVariables.set("BARORDER_USER", "bar");
		environmentVariables.set("BARORDER_PASSWORD", "pass1234");
		environmentVariables.set("BARORDER_DATABASE", "bardata");
		assertEquals(Integer.parseInt("80"), Configurations.port());
		assertEquals("mysql", Configurations.host());
		assertEquals("bar", Configurations.user());
		assertEquals("pass1234", Configurations.password());
		assertEquals("bardata", Configurations.database());
	}
	
	@Test
	public void testThree() {
		assertEquals("jdbc:mysql://localhost:3306/barorder", Configurations.url());
	}
	
	@Test
	public void testFour() {
		environmentVariables.set("BARORDER_PORT", "80");
		environmentVariables.set("BARORDER_HOST", "mysql");
		environmentVariables.set("BARORDER_USER", "bar");
		environmentVariables.set("BARORDER_PASSWORD", "pass1234");
		environmentVariables.set("BARORDER_DATABASE", "bardata");
		assertEquals("jdbc:mysql://mysql:3306/bardata", Configurations.url());
	}
	
}
