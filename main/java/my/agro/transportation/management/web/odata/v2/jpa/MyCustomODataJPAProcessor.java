package my.agro.transportation.management.web.odata.v2.jpa;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.GetMediaResourceUriInfo;
import org.apache.olingo.odata2.api.uri.info.PutMergePatchUriInfo;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.ref.util.CustomODataJPAProcessor;

public class MyCustomODataJPAProcessor extends CustomODataJPAProcessor {
	static Map<String, byte[]> mediaResources = new HashMap<>();

	public MyCustomODataJPAProcessor(ODataJPAContext oDataJPAContext) {
		super(oDataJPAContext);
	}

	@Override
	public ODataResponse readEntityMedia(GetMediaResourceUriInfo uriInfo, String contentType) throws ODataException {
		// return super.readEntityMedia(uriInfo, contentType);
		String id = getKeyValue(uriInfo.getKeyPredicates().get(0));
		String mimeType = "image/jpeg";
		if (id.contains("pdf")) {
			mimeType = "application/pdf";
		}
		byte [] binaryData = mediaResources.get(getKeyValue(uriInfo.getKeyPredicates().get(0)));
		return ODataResponse.fromResponse(EntityProvider.writeBinary(mimeType, binaryData)).build();
	}

	@Override
	public ODataResponse updateEntityMedia(PutMergePatchUriInfo uriInfo, InputStream content, String requestContentType,
			String contentType) throws ODataException {
		try {
			mediaResources.put(getKeyValue(uriInfo.getKeyPredicates().get(0)), IOUtils.toByteArray(content));
			//image = IOUtils.toByteArray(content);
		} catch (IOException e) {
			throw (new RuntimeException(e));
		}
		return ODataResponse.status(HttpStatusCodes.NO_CONTENT).build();
	}

	private String getKeyValue(KeyPredicate key) throws ODataException {
		EdmProperty property = key.getProperty();
		EdmSimpleType type = (EdmSimpleType) property.getType();
		return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), String.class);
	}
}
