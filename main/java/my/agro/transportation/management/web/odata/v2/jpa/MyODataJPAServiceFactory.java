package my.agro.transportation.management.web.odata.v2.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.olingo.odata2.api.ODataCallback;
import org.apache.olingo.odata2.api.processor.ODataErrorCallback;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAProcessor;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.core.access.data.JPAProcessorImpl;
import org.apache.olingo.odata2.jpa.processor.ref.util.CustomODataJPAProcessor;
import org.eclipse.persistence.config.PersistenceUnitProperties;

public class MyODataJPAServiceFactory extends ODataJPAServiceFactory {
	private EntityManagerFactory emf;
	private String persistenceUnitName = "my.orders.persistance.unit";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MyODataJPAServiceFactory() {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
			Map properties = new HashMap();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			emf = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
			this.setDetailErrors(true);
			
		} catch (NamingException e) {
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ODataSingleProcessor createCustomODataProcessor(ODataJPAContext oDataJPAContext) {
		return new MyCustomODataJPAProcessor(oDataJPAContext);
	}

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext oDataJPAContext = this.getODataJPAContext();
		try {
			oDataJPAContext.setEntityManagerFactory(emf);
			oDataJPAContext.setPersistenceUnitName(persistenceUnitName);
			oDataJPAContext.setJPAEdmExtension((JPAEdmExtension) new MyProcessingExtension());
			return oDataJPAContext;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T extends ODataCallback> T getCallback(Class<T> callbackInterface) {
		if (callbackInterface.isAssignableFrom(ODataErrorCallback.class)) {
			return (T) new MyODataErrorCallback();
		}
		return super.getCallback(callbackInterface);
	}

}
