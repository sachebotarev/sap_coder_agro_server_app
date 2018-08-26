package my.agro.transportation.management.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.dao.entity.TransportationAssignment;
import my.agro.transportation.management.dao.entity.Truck;
import my.agro.transportation.management.service.TransportationService;
import my.agro.transportation.management.service.TruckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImportParameter;

import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.HttpMethod;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.ReturnType;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.ReturnType.Type;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImportParameter;

@Controller
@Configurable // (dependencyCheck=true)
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
public class ODataFunctionImportController {
	@Autowired
	private TransportationService transportationService;
	
	@Autowired
	private TruckService truckService;

	public ODataFunctionImportController() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	@EdmFunctionImport(name = "SetWorkflowInstanceId", entitySet = "Transportations", 
			returnType = @ReturnType(type = ReturnType.Type.ENTITY, isCollection = false), 
			httpMethod = EdmFunctionImport.HttpMethod.POST)
	public Transportation setWorkflowInstanceId(
			@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "WorkflowInstanceId") String workflowInstanceId) {
		return transportationService.processSetWorkflowInstanceId(transportationNum, workflowInstanceId);
	}
	@EdmFunctionImport(name = "ProcessStatusChange", entitySet = "Transportations", 
			returnType = @ReturnType(type = ReturnType.Type.ENTITY, isCollection = false), 
			httpMethod = EdmFunctionImport.HttpMethod.POST)
	public Transportation processStatusChange(
			@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "NewStatus") String newStatus) {
		return transportationService.processStatusChange(transportationNum, newStatus);
	}
	@EdmFunctionImport(name = "ProcessShipToDetermination", entitySet = "ShippingLocations", 
			returnType = @ReturnType(type = ReturnType.Type.ENTITY, isCollection = false), 
			httpMethod = EdmFunctionImport.HttpMethod.POST)
	public ShippingLocation processShipToDetermination(
			@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum) {
		return transportationService.processShipToDetermination(transportationNum).getShipToProperty();
	}
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "ProcessTrucksSearch", entitySet = "Transportations", 
			returnType = @ReturnType(type = ReturnType.Type.ENTITY, isCollection = false), 
			httpMethod = EdmFunctionImport.HttpMethod.POST)
	public Transportation processTrucksSearch(
			@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum) {
		return transportationService.processTrucksSearch(transportationNum);
	}
	@EdmFunctionImport(name = "ReleaseTransportation", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String releaseTransportation(
			@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.releaseTransportation(transportationNum);
		return "";
	}

	@EdmFunctionImport(name = "CancelTransportation", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String cancelTransportation(
			@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum) {
		transportationService.cancelTransportation(transportationNum);
		return "";
	}

	@EdmFunctionImport(name = "SendRequests", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String sendRequests(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processSendRequests(transportationNum);
		return "";
	}

	@EdmFunctionImport(name = "AcceptTransportation", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String acceptTransportation(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "TruckNum") String truckNum)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processTransportationAcceptance(transportationNum, truckNum);
		return "";
	}
	
	@EdmFunctionImport(name = "AcceptTransportationByDriver", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String acceptTransportationByDriver(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "Driver") String driver)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processTransportationAcceptanceByDriver(transportationNum, driver);
		return "";
	}
	
	@EdmFunctionImport(name = "ConfirmReceiveTransportationRequest", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String confirmReceiveTransportationRequest(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "Driver") String driver)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processAssignmentStatusChange(transportationNum, driver, TransportationAssignment.STATUS_REQUEST_DELIVERED);
		return "";
	}
	
	@EdmFunctionImport(name = "ConfirmReadTransportationRequest", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String confirmReadTransportationRequest(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "Driver") String driver)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processAssignmentStatusChange(transportationNum, driver, TransportationAssignment.STATUS_REQUEST_READ);
		return "";
	}
	
	@EdmFunctionImport(name = "RejectTransportationRequest", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String rejectTransportationRequest(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "Driver") String driver)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processAssignmentStatusChange(transportationNum, driver, TransportationAssignment.STATUS_REJECTED);
		return "";
	}
	
	@EdmFunctionImport(name = "RemoveTransportationAssignment", entitySet = "Transportations", returnType = @EdmFunctionImport.ReturnType(type = EdmFunctionImport.ReturnType.Type.SIMPLE, isCollection = false), httpMethod = EdmFunctionImport.HttpMethod.POST)
	public String removeTransportationAssignment(@EdmFunctionImportParameter(name = "TransportationNum") String transportationNum,
			@EdmFunctionImportParameter(name = "TruckNum") String truckNum)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportationService.processAssignmentRemoval(transportationNum, truckNum);
		return "";
	}
	
	@EdmFunctionImport(name = "ProcessTruckStatusChange", entitySet = "Trucks", 
			returnType = @ReturnType(type = ReturnType.Type.ENTITY, isCollection = false), 
			httpMethod = EdmFunctionImport.HttpMethod.POST)
	public Truck processTruckStatusChange(
			@EdmFunctionImportParameter(name = "TruckNum") String truckNum,
			@EdmFunctionImportParameter(name = "NewStatus") String newStatus) {
		return truckService.processStatusChange(truckNum, newStatus);
	}
}
