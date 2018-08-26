package my.agro.transportation.management.web.odata.v2.jpa;

import com.google.gson.Gson;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.processor.ODataErrorCallback;
import org.apache.olingo.odata2.api.processor.ODataErrorContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;

public class MyODataErrorCallback implements ODataErrorCallback {

    @Override
    public ODataResponse handleError(final ODataErrorContext context) throws ODataApplicationException {
        Gson gson = new Gson();
        context.setInnerError( gson.toJson(context.getException()) );
        return EntityProvider.writeErrorDocument(context);
    }
}
