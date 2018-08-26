package my.agro.transportation.management.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.FileNotFoundException;

import org.junit.Test;

import my.agro.transportation.management.dao.repository.ShippingLocationRepository;
import my.agro.transportation.management.service.GeoServices;
import my.agro.transportation.management.service.SchedulingService;
import my.agro.transportation.management.service.TruckService;
import my.agro.transportation.management.utils.DateUtils;

import static org.mockito.Mockito.*;

public class TruckServiceTest {
	TruckService truckService = new TruckService();

	public TruckServiceTest() {
		GeoServices mockGeoServices = mock(GeoServices.class);
		truckService.setSchedulingService(new SchedulingService());
		truckService.setGeoServices(mockGeoServices);
		truckService.getSchedulingService().setGeoServices(mockGeoServices);

		// A--{10m}--B-----{20m}-----C
		when(mockGeoServices.getTravelTimeMinutes("A", "A")).thenReturn(0);
		when(mockGeoServices.getTravelTimeMinutes("B", "B")).thenReturn(0);
		when(mockGeoServices.getTravelTimeMinutes("C", "C")).thenReturn(0);
		when(mockGeoServices.getTravelTimeMinutes("A", "B")).thenReturn(10);
		when(mockGeoServices.getTravelTimeMinutes("B", "A")).thenReturn(10);
		when(mockGeoServices.getTravelTimeMinutes("B", "C")).thenReturn(20);
		when(mockGeoServices.getTravelTimeMinutes("C", "B")).thenReturn(20);
		when(mockGeoServices.getTravelTimeMinutes("A", "C")).thenReturn(30);
		when(mockGeoServices.getTravelTimeMinutes("C", "A")).thenReturn(30);
	}

	@Test
	public void estimateArrivalTimeMinutesTest() {
		// try to squeeze between A->B and B->C transportations
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000C"),
				TestDataLoader.readShippingLocationByKey("1000B"), TestDataLoader.readShippingLocationByKey("2000C"),
				DateUtils.dateOf("2018-01-01 00:00"), 40), 60*2); // positive
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000C"),
				TestDataLoader.readShippingLocationByKey("1000B"), TestDataLoader.readShippingLocationByKey("2000C"),
				DateUtils.dateOf("2018-01-01 00:00"), 41), 60*4 + 20); // negative

		// try to get before A->B transportation
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000C"),
				TestDataLoader.readShippingLocationByKey("1000A"), TestDataLoader.readShippingLocationByKey("2000B"),
				DateUtils.dateOf("2018-01-01 00:00"), 20), 30); // positive
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000C"),
				TestDataLoader.readShippingLocationByKey("1000A"), TestDataLoader.readShippingLocationByKey("2000B"),
				DateUtils.dateOf("2018-01-01 00:00"), 21), 60*2 + 10); // negative
		
		
		// long transportation which will happen after A->B and B->C transportations
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000C"),
				TestDataLoader.readShippingLocationByKey("1000A"), TestDataLoader.readShippingLocationByKey("2000B"),
				DateUtils.dateOf("2018-01-01 00:00"), 51), 60*4 + 30); // positive
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000C"),
				TestDataLoader.readShippingLocationByKey("1000A"), TestDataLoader.readShippingLocationByKey("2000B"),
				DateUtils.dateOf("2018-01-01 00:00"), 50), 60*2 + 10); // negative
		
		// test truck that does not have transportation queue
		assertEquals(truckService.estimateArrivalTimeMinutes(TestDataLoader.readTruckByNumber("1000B"),
				TestDataLoader.readShippingLocationByKey("1000A"), TestDataLoader.readShippingLocationByKey("2000B"),
				DateUtils.dateOf("2018-01-01 00:00"), 60), 10);
	}

	public TruckService getTruckService() {
		return truckService;
	}
}
