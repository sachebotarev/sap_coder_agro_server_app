package my.agro.transportation.management.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import my.agro.transportation.management.service.GeoServices;

public class GeoServicesTest {
	private GeoServices geoServices = new GeoServices();
	
	@Test
	public void getTravelTimeMinutes() {
		assertEquals(geoServices.getTravelTimeMinutes("51.733031, 36.556652", "51.788646, 36.504636"), 11);
		assertEquals(geoServices.getTravelTimeMinutes("51.733031, 36.556652", "51.788646, 36.504636"), 11);
		assertEquals(geoServices.getTravelTimeMinutes("51.733031, 36.556652", "51.788646, 36.504636"), 11);
		
	}
	
	@Test
	public void getTravelDistanceKm() {
		assertEquals(geoServices.getDistanceKm("51.733031, 36.556652", "51.788646, 36.504636"), 9);
	}
	
}
