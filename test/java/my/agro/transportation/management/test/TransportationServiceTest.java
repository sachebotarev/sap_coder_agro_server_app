package my.agro.transportation.management.test;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.dao.entity.Truck;
import my.agro.transportation.management.dao.repository.ShippingLocationRepository;
import my.agro.transportation.management.dao.repository.TransportationRepository;
import my.agro.transportation.management.dao.repository.TruckRepository;
import my.agro.transportation.management.service.GeoServices;
import my.agro.transportation.management.service.ShippingLocationService;
import my.agro.transportation.management.service.TransportationService;
import my.agro.transportation.management.service.TruckService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.lang.reflect.Type;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;

public class TransportationServiceTest {
	TransportationService transportationService = new TransportationService();

	public TransportationServiceTest() throws FileNotFoundException {
		ShippingLocationServiceTest shippingLocationServiceTest = new ShippingLocationServiceTest();
		TruckServiceTest truckServiceTest = new TruckServiceTest();

		// Mockito overview: https://habr.com/post/72617/
		ShippingLocationRepository shippingLocationRepository = mock(ShippingLocationRepository.class);
		TransportationRepository transportationRepository = mock(TransportationRepository.class);
		TruckRepository truckRepository = mock(TruckRepository.class);

		// shippingLocationRepository.get

		when(shippingLocationRepository.findByLocationType(ShippingLocation.TYPE_STORAGE))
				.thenReturn(TestDataLoader.readShippingLocations("ShippingLocations.js").stream()
						.filter((location) -> location.getLocationType().equals(ShippingLocation.TYPE_STORAGE))
						.collect(Collectors.toSet()));
		when(truckRepository.findTrucksByRegion("50")).thenReturn(TestDataLoader.readTrucks("Trucks.js"));
		when(truckRepository.findAll()).thenReturn(TestDataLoader.readTrucks("Trucks.js").stream().collect(Collectors.toList()));

		transportationService.setGeoServices(truckServiceTest.getTruckService().getGeoServices());
		transportationService.setShippingLocationRepository(shippingLocationRepository);
		transportationService.setShippingLocationService(shippingLocationServiceTest.getShippingLocationService());
		transportationService.setTruckService(truckServiceTest.getTruckService());
		transportationService.setTransportationRepository(transportationRepository);
		transportationService.setTruckRepository(truckRepository);
	}

	@Test
	public void testReleaseAndCancel() throws ClientProtocolException, URISyntaxException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.addSerializationExclusionStrategy(new NoModuleExclusionStrategy(false))
				.addDeserializationExclusionStrategy(new NoModuleExclusionStrategy(true)).create();

		Transportation transportation = TestDataLoader.readTransportationByNumber("1000");

		//System.out.println("INPUT:");
		// System.out.println(gson.toJson(transportation));

		for (int i = 0; i < 3; i++) {
			//System.out.println("STEP 1, Release:");
			transportationService.releaseTransportation(transportation);
			transportationService.processSendRequests(transportation);
			transportationService.processTransportationAcceptance(transportation, TestDataLoader.readTruckByNumber("1000C"));
			//System.out.println(gson.toJson(transportation));

			// System.out.println("STEP 2, Cancel:");
			transportationService.cancelTransportation(transportation);
			// System.out.println(gson.toJson(transportation));
			
			Transportation transportation2 = TestDataLoader.readTransportationByNumber("1010");
			transportationService.releaseTransportation(transportation2);
			transportationService.cancelTransportation(transportation2);
		}

		
	}

	@Test
	public void testAutomatedAssignment() throws ClientProtocolException, URISyntaxException, IOException {
		/*
		 * Gson gson = new GsonBuilder() .setPrettyPrinting()
		 * .addSerializationExclusionStrategy(new NoModuleExclusionStrategy(false))
		 * .addDeserializationExclusionStrategy(new NoModuleExclusionStrategy(true))
		 * .create(); Transportation transportation =
		 * readTransportations("Transportations.js").stream().findFirst().get();
		 * 
		 * System.out.println("INPUT:");
		 * System.out.println(gson.toJson(transportation));
		 * 
		 * System.out.println("STEP 1:");
		 * transportationService.processTransportationAssignment(transportation);
		 * System.out.println(gson.toJson(transportation));
		 * 
		 * System.out.println("STEP 2:");
		 * transportationService.processTransportationAssignment(transportation);
		 * System.out.println(gson.toJson(transportation));
		 * 
		 * System.out.println("STEP 3:");
		 * transportationService.processTransportationAssignment(transportation);
		 * System.out.println(gson.toJson(transportation)); //System.out.println(
		 * gson.toJson( readShippingLocations("ShippingLocations.js") ) );
		 * //System.out.println( gson.toJson( readTrucks("Trucks.js") ) );
		 * 
		 * // data.toScreen(); // prints to screen some values
		 */
	}

	/*
	 * private <T> Set<T> readSet(String fileName, T param) throws
	 * FileNotFoundException { Type REVIEW_TYPE = new TypeToken<Set<T>>() {
	 * }.getType(); Gson gson = new GsonBuilder().setPrettyPrinting().create();
	 * System.out.println( getClass().getResource(fileName).getPath().substring(1)
	 * ); JsonReader reader = new JsonReader(new
	 * FileReader(getClass().getResource(fileName).getPath().substring(1))); Set<T>
	 * result = gson.fromJson(reader, REVIEW_TYPE); return result; // contains the
	 * whole reviews list }
	 */
}
