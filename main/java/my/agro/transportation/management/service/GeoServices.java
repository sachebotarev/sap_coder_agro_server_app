package my.agro.transportation.management.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.helpers.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.naming.Context;
import javax.naming.InitialContext;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.utils.HcpHttpDestination;
import my.agro.transportation.management.utils.MyRuntimeException;

@Service
public class GeoServices {
	private static Map<String, Integer> cachedRequests = new HashMap<String, Integer>();

	public int getDistanceKm(String geoLocationFrom, String geoLocationTo) {
		if (geoLocationFrom.equals(geoLocationTo)) {
			return 0;
		}
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("maps.googleapis.com")
					.setPath("/maps/api/distancematrix/json").setParameter("units", "metric")
					.setParameter("origins", geoLocationFrom).setParameter("destinations", geoLocationTo)
					// .setParameter("key", "AIzaSyD6RqhnxU5olOb3vUUssoojf0Vypf3bwCo")
					.build();

			HcpHttpDestination httpDestination = new HcpHttpDestination();
			JsonObject result = (JsonObject) new JsonParser().parse(httpDestination.executeGet(uri.toURL()));


			return result.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements").get(0)
					.getAsJsonObject().getAsJsonObject("distance").getAsJsonPrimitive("value").getAsInt() / 1000;
		} catch (Throwable e) {
			throw new MyRuntimeException("Google Maps API distancematrix request failed", e);
		}
	}

	public int getTravelTimeMinutes(String geoLocationFrom, String geoLocationTo) {
		String cacheKey = geoLocationFrom + " - " + geoLocationTo;
		if (!cachedRequests.containsKey(cacheKey)) {
			cachedRequests.put(cacheKey, requestTravelTimeFromGoogle(geoLocationFrom, geoLocationTo));
		}
		return cachedRequests.get(cacheKey);
	}

	public int requestTravelTimeFromGoogle(String geoLocationFrom, String geoLocationTo) {

		if (geoLocationFrom.equals(geoLocationTo)) {
			return 0;
		}
		// CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("maps.googleapis.com")
					.setPath("/maps/api/distancematrix/json").setParameter("units", "metric")
					.setParameter("origins", geoLocationFrom).setParameter("destinations", geoLocationTo)
					// .setParameter("key", "AIzaSyD6RqhnxU5olOb3vUUssoojf0Vypf3bwCo")
					.build();

			HcpHttpDestination httpDestination = new HcpHttpDestination();
			JsonObject result = (JsonObject) new JsonParser().parse(httpDestination.executeGet(uri.toURL()));

			/*
			 * HttpGet httpget = new HttpGet(uri); CloseableHttpResponse response =
			 * httpclient.execute(httpget); HttpEntity responseEntity =
			 * response.getEntity(); JsonObject result = (JsonObject) new
			 * JsonParser().parse(EntityUtils.toString(responseEntity));
			 * 
			 * System.out.println(result);
			 */

			return result.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements").get(0)
					.getAsJsonObject().getAsJsonObject("duration").getAsJsonPrimitive("value").getAsInt() / 60;
		} catch (Throwable e) {
			throw new MyRuntimeException("Google Maps API distancematrix request failed", e);
		}
	}

}
