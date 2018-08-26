package my.agro.transportation.management.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

public class HcpHttpDestination {
	public String executeGet(URL url) {
		try {
			// use the sslcontext for url connection
			URLConnection urlConnection = url.openConnection();

			urlConnection.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			String result = new String();
			while ((inputLine = in.readLine()) != null) {
				result += inputLine;
			}

			in.close();
			return result;
		} catch (Throwable e) {
			throw new MyRuntimeException("Google Maps API distancematrix request failed", e);
		}
	}

	/*public String executeGet(String destination, String path) throws NamingException, IOException {
		// look up the connectivity configuration API "connectivityConfiguration"
		Context ctx = new InitialContext();
		ConnectivityConfiguration configuration = (ConnectivityConfiguration) ctx
				.lookup("java:comp/env/connectivityConfiguration");
		DestinationConfiguration destConfiguration = configuration.getConfiguration(destination);
		// get the destination URL
		String value = destConfiguration.getProperty("URL");
		URL url = new URL(value + path);

		// use the sslcontext for url connection
		URLConnection urlConnection = url.openConnection();

		urlConnection.connect();

		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		String result = new String();
		while ((inputLine = in.readLine()) != null) {
			result += inputLine;
		}

		in.close();
		return result;
	}

	public String executeGet(String uri) throws NamingException, IOException {
		URL url = new URL(uri);

		// use the sslcontext for url connection
		URLConnection urlConnection = url.openConnection();

		urlConnection.connect();

		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		String result = new String();
		while ((inputLine = in.readLine()) != null) {
			result += inputLine;
		}

		in.close();
		return result;
	}*/
}
