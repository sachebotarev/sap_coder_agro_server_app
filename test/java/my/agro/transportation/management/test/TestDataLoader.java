package my.agro.transportation.management.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.dao.entity.Truck;
import my.agro.transportation.management.utils.DateUtils;
import my.agro.transportation.management.utils.MyRuntimeException;

public class TestDataLoader {
	public static Gson getGson() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm").setPrettyPrinting().create();
	}
	public static Set<Truck> readTrucks(String fileName){
		Type REVIEW_TYPE = new TypeToken<Set<Truck>>() {
		}.getType();
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(TestDataLoader.class.getResource(fileName ).getPath().substring(1)));
		} catch (FileNotFoundException e) {
			throw new MyRuntimeException(e);
		}
		return getGson().fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
	}
	
	public static Set<ShippingLocation> readShippingLocations(String fileName)  {
		Type REVIEW_TYPE = new TypeToken<Set<ShippingLocation>>() {
		}.getType();
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(TestDataLoader.class.getResource(fileName).getPath().substring(1)));
		} catch (FileNotFoundException e) {
			throw new MyRuntimeException(e);
		}
		return getGson().fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
	}
	
	public static Set<Transportation> readTransportations(String fileName) {
		Type REVIEW_TYPE = new TypeToken<Set<Transportation>>() {
		}.getType();
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(TestDataLoader.class.getResource(fileName ).getPath().substring(1)));
		} catch (FileNotFoundException e) {
			throw new MyRuntimeException(e);
		}
		return getGson().fromJson(reader, REVIEW_TYPE); // contains the whole reviews list
	}
	
	public static ShippingLocation readShippingLocationByKey(String key ) {
		return readShippingLocations("ShippingLocations.js").stream()
		.filter((location) -> location.getShippingLocationKey().equals(key))
		.findFirst().orElseThrow(() -> new MyRuntimeException("failed"));
	}
	
	public static Truck readTruckByNumber(String truckNumber) {
		return readTrucks("Trucks.js").stream()
		.filter((truck) -> truck.getTruckNum().equals(truckNumber))
		.findFirst().orElseThrow(() -> new MyRuntimeException("failed"));
	}
	
	public static Transportation readTransportationByNumber(String number) {
		return readTransportations("Transportations.js").stream()
		.filter((transporation) -> transporation.getTransportationNum().equals(number))
		.findFirst().orElseThrow(() -> new MyRuntimeException("failed"));
	}
}
