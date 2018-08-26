package my.agro.transportation.management.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Set;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.service.SchedulingService;
import my.agro.transportation.management.service.ShippingLocationService;
import my.agro.transportation.management.utils.DateUtils;
import my.agro.transportation.management.utils.MyRuntimeException;

public class ShippingLocationServiceTest {
	ShippingLocationService shippingLocationService = new ShippingLocationService();

	public ShippingLocationServiceTest() {
		shippingLocationService.setDateIntervalService(new SchedulingService());
	}

	@Test
	public void testQueueTimeCalculation() throws FileNotFoundException {
		// test locations with transportations queue
		assertEquals(shippingLocationService.estimateQueueTimeMinutes(TestDataLoader.readShippingLocationByKey("1000A"),
				DateUtils.dateOf("2018-01-01 00:15"), 10), 15);
		assertEquals(shippingLocationService.estimateQueueTimeMinutes(TestDataLoader.readShippingLocationByKey("2000A"),
				DateUtils.dateOf("2018-01-01 00:00"), 10), 0);
		assertEquals(shippingLocationService.estimateQueueTimeMinutes(TestDataLoader.readShippingLocationByKey("2000A"),
				DateUtils.dateOf("2018-01-01 00:55"), 10), 25);
		assertEquals(shippingLocationService.estimateQueueTimeMinutes(TestDataLoader.readShippingLocationByKey("2000A"),
				DateUtils.dateOf("2018-01-01 01:35"), 10), 5);
		// test location without transportations
		assertEquals(shippingLocationService.estimateQueueTimeMinutes(TestDataLoader.readShippingLocationByKey("1000B"),
				DateUtils.dateOf("2018-01-01 00:00"), 10), 0);
		
		
	}

	public ShippingLocationService getShippingLocationService() {
		return shippingLocationService;
	}
}
