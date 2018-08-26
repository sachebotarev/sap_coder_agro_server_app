package my.agro.transportation.management.web.odata.v2.jpa;

import java.io.InputStream;

import org.apache.olingo.odata2.api.edm.provider.Mapping;
import org.apache.olingo.odata2.jpa.processor.core.model.JPAEdmMappingImpl;
import org.apache.olingo.odata2.api.edm.provider.Schema;
import org.apache.olingo.odata2.jpa.processor.api.model.*;

import my.agro.transportation.management.controller.ODataFunctionImportController;

public class MyProcessingExtension implements JPAEdmExtension {
	@Override
	public void extendJPAEdmSchema(JPAEdmSchemaView view) {
		view.registerOperations(ODataFunctionImportController.class, null);

		view.getEdmSchema().getEntityTypes().stream().filter(entityType -> entityType.getName().equals("MediaResource"))
				.findFirst().orElseThrow(() -> new RuntimeException("Entity MediaResource not found"))
				.setHasStream(true).getMapping().setMediaResourceMimeTypeKey("MimeType");

		/*view.getEdmSchema().getEntityTypes().stream().filter(entityType -> entityType.getName().equals("RoadEvent"))
				.findFirst().orElseThrow(() -> new RuntimeException("Entity RoadEvent not found")).setHasStream(true)
				.getMapping().setMediaResourceMimeTypeKey("MimeType");*/
	}

	@Override
	public void extendWithOperation(JPAEdmSchemaView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream getJPAEdmMappingModelStream() {
		// TODO Auto-generated method stub
		return null;
	}

}
