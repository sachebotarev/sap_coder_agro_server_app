package my.agro.transportation.management.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Comparators;

import my.agro.transportation.management.dao.dto.TransportationProcessingTimeline;
import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.dao.entity.TransportationAssignment;
import my.agro.transportation.management.dao.entity.TransportationLocationAssignment;
import my.agro.transportation.management.dao.entity.TransportationMessageLog;
import my.agro.transportation.management.dao.entity.Truck;
import my.agro.transportation.management.dao.repository.ShippingLocationRepository;
import my.agro.transportation.management.dao.repository.TransportationRepository;
import my.agro.transportation.management.dao.repository.TruckRepository;
import my.agro.transportation.management.utils.DateUtils;
import my.agro.transportation.management.utils.MyRuntimeException;
import my.agro.transportation.management.utils.ValueListClasifier;

@Service
public class TransportationService {
	@Autowired
	private GeoServices geoServices;
	@Autowired
	private ShippingLocationService shippingLocationService;
	@Autowired
	private TruckService truckService;
	@Autowired
	private ShippingLocationRepository shippingLocationRepository;
	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private TransportationRepository transportationRepository;

	public Transportation processStatusChange(String transporationNum, String newStatus) {
		return processStatusChange(transportationRepository.findOne(transporationNum), newStatus);
	}

	@Transactional
	public Transportation processAssignmentRemoval(String transporationNum, String truckNum) {
		Transportation transportation = transportationRepository.findOne(transporationNum);
		
		Iterator<TransportationAssignment> assignmentIterator = transportation.getAssignments().iterator();	
		while(assignmentIterator.hasNext()) {
			TransportationAssignment assignment = assignmentIterator.next();
			if (assignment.getTruck().equals(truckNum)) {
				assignmentIterator.remove();
			}
		}
	
		//transportation.getAssignments().stream().filter(assignment -> assignment.getTruck().equals(truckNum))
		//		.forEach(assignment -> transportation.getAssignments().remove(assignment));
		transportationRepository.save(transportation);
		return transportation;
	}

	@Transactional
	public Transportation processAssignmentStatusChange(String transporationNum, String driver, String newStatus) {
		Transportation transportation = transportationRepository.findOne(transporationNum);
		transportation.getAssignments().stream()
				.filter(assignment -> assignment.getTruckProperty().getDriver().equals(driver))
				.forEach(assignment -> assignment.setStatus(newStatus));
		transportationRepository.save(transportation);
		return transportation;
	}

	@Transactional
	public Transportation processStatusChange(Transportation transporation, String newStatus) {
		transporation.setStatus(newStatus);
		if (newStatus.equals(Transportation.STATUS_ARRIVED_FOR_LOAD)) {
			transporation.setArrivalTimeActualMinutes(transporation.getArrivalTimeMinutes() + 10);
			transporation.setStartActualDateTime(DateUtils.addMinutesToDate(
					transporation.getTruckAssignedActualDateTime(), 
					transporation.getArrivalTimeActualMinutes()));
		} else if (newStatus.equals(Transportation.STATUS_LOADED)) {
			transporation.setLoadQueueTimeActualMinutes(transporation.getLoadQueueTimeMinutes() + 10);
			transporation.setLoadTimeActualMinutes(transporation.getLoadTimeMinutes() + 10);
			transporation.setLoadStartActualDateTime(DateUtils.addMinutesToDate(
					transporation.getStartActualDateTime(), 
					transporation.getLoadQueueTimeActualMinutes()));
			transporation.setTravelStartActualDateTime(DateUtils.addMinutesToDate(
					transporation.getLoadStartActualDateTime(), 
					transporation.getLoadTimeActualMinutes()));
		} else if (newStatus.equals(Transportation.STATUS_ARRIVED_FOR_UNLOAD)) {
			transporation.setTravelTimeActualMinutes(transporation.getTravelTimeMinutes() + 10);
			transporation.setUnloadQueueStartActualDateTime(DateUtils.addMinutesToDate(
					transporation.getTravelStartActualDateTime(), 
					transporation.getTravelTimeActualMinutes()));
		} else if (newStatus.equals(Transportation.STATUS_CLOSED)) {
			transporation.setUnloadQueueTimeActualMinutes(transporation.getUnloadQueueTimeMinutes() + 10);
			transporation.setUnloadTimeActualMinutes(transporation.getUnloadTimeMinutes() + 10);
			transporation.setUnloadStartActualDateTime(DateUtils.addMinutesToDate(
					transporation.getUnloadQueueStartActualDateTime(), 
					transporation.getUnloadQueueTimeActualMinutes()));
			transporation.setEndActualDateTime(DateUtils.addMinutesToDate(
					transporation.getUnloadStartActualDateTime(), 
					transporation.getUnloadTimeActualMinutes()));
		}
		transportationRepository.save(transporation);
		return transporation;
	}

	public Transportation processSetWorkflowInstanceId(String transporationNum, String workflowInstanceId) {
		return processSetWorkflowInstanceId(transportationRepository.findOne(transporationNum), workflowInstanceId);
	}

	@Transactional
	public Transportation processSetWorkflowInstanceId(Transportation transporation, String workflowInstanceId) {
		transporation.setWorkflowInstanceId(workflowInstanceId);
		transportationRepository.save(transporation);
		return transporation;
	}

	public Transportation processShipToDetermination(String transporationNum) {
		return processShipToDetermination(transportationRepository.findOne(transporationNum));
	}

	public Transportation processTrucksSearch(String transporationNum) {
		return processTrucksSearch(transportationRepository.findOne(transporationNum));
	}

	public void releaseTransportation(String transporationNum)
			throws ClientProtocolException, URISyntaxException, IOException {
		releaseTransportation(transportationRepository.findOne(transporationNum));
	}

	@Transactional
	public void releaseTransportation(Transportation transportation)
			throws ClientProtocolException, URISyntaxException, IOException {
		transportation.setStatus(Transportation.STATUS_RELEASED);
		transportationRepository.save(transportation);
		processShipToDetermination(transportation);
		processTrucksSearch(transportation);
	}

	public void cancelTransportation(String transporationNum) {
		cancelTransportation(transportationRepository.findOne(transporationNum));
	}

	@Transactional
	public void cancelTransportation(Transportation transportation) {
		if (!transportation.getShipTo().equals("")) {
			shippingLocationService.removeTransportationForShipTo(transportation.getShipToProperty(), transportation);
		}
		transportation.setShipTo("");
		transportation.setShipToProperty(null);

		if (!transportation.getTruck().equals("")) {
			truckService.removeTransportation(transportation.getTruckProperty(), transportation);
		}
		transportation.setTruck("");
		transportation.setTruckProperty(null);

		clearTimelineFields(transportation);

		transportation.getAssignments().clear();
		transportation.getLocationAssignments().clear();
		transportation.setStatus(Transportation.STATUS_DRAFT);
		transportationRepository.save(transportation);
	}

	@Transactional
	public Transportation processShipToDetermination(Transportation transportation) {
		int minimumDeliveryTime = Integer.MAX_VALUE;
		boolean isShipToAssigned = false;

		for (ShippingLocation shipTo : shippingLocationRepository.findByLocationType(ShippingLocation.TYPE_STORAGE)) {
			if (shipTo.getRegion().equals(transportation.getShipFromProperty().getRegion())) {
				TransportationLocationAssignment locationAssignment = new TransportationLocationAssignment();
				locationAssignment.setTransportation(transportation.getTransportationNum());
				locationAssignment.setTransportationProperty(transportation);
				locationAssignment.setShipToLocation(shipTo.getShippingLocationKey());
				locationAssignment.setShipToLocationProperty(shipTo);
				shippingLocationService.addTransportationForShipTo(shipTo, transportation);
				copyFields(locationAssignment, prepareTransporationProcessingTimeline(transportation, shipTo, null));
				locationAssignment.setProcessingTimeMinutes(locationAssignment.getTravelTimeMinutes()
						+ locationAssignment.getUnloadQueueTimeMinutes() + locationAssignment.getUnloadTimeMinutes());
				if (minimumDeliveryTime > estimateTotalDeliveryTime(transportation, shipTo)) {
					minimumDeliveryTime = estimateTotalDeliveryTime(transportation, shipTo);
					transportation.setShipTo(shipTo.getShippingLocationKey());
					transportation.setShipToProperty(shipTo);
					isShipToAssigned = true;
				}
				transportation.getLocationAssignments().add(locationAssignment);
			}
		}

		transportation.getLocationAssignments().forEach(assignment -> assignment.setProcessingTimeScore(
				ValueListClasifier.classify(assignment.getProcessingTimeMinutes(), 10, 30, 60, 120)));

		transportation.getLocationAssignments().stream()
				.sorted((a, b) -> a.getProcessingTimeMinutes().compareTo(b.getProcessingTimeMinutes()))
				.reduce(null, (previousAssignment, currentAssignment) -> {
					if (previousAssignment == null) {
						currentAssignment.setAssignmentIndex(1);
					} else {
						currentAssignment.setAssignmentIndex(previousAssignment.getAssignmentIndex() + 1);
					}
					return currentAssignment;
				});

		if (isShipToAssigned) {
			copyFields(transportation,
					prepareTransporationProcessingTimeline(transportation, transportation.getShipToProperty(), null));

			transportation.setStatus(Transportation.STATUS_SHIP_TO_DETERMINED);
			transportation.getLocationAssignments().stream()
					.filter(assignment -> assignment.getShipToLocation().equals(transportation.getShipTo())).findFirst()
					.orElseThrow(() -> new RuntimeException("Failed to determine ship to location"))
					.setStatus(TransportationLocationAssignment.STATUS_ACCEPTED);
			transportation.getLocationAssignments().stream().filter(
					lcoationAssignment -> !lcoationAssignment.getShipToLocation().equals(transportation.getShipTo()))
					.forEach(lcoationAssignment -> lcoationAssignment
							.setStatus(TransportationAssignment.STATUS_INACTIVE));
		} else {
			// put error messaging handling here
		}

		transportationRepository.save(transportation);

		return transportation;
	}

	public TransportationProcessingTimeline prepareTransporationProcessingTimeline(Transportation transportation,
			ShippingLocation shipTo, Truck truck) {
		Date executionStartDate = (transportation.getKickOffDate() == null) ? DateUtils.getCurrentDate()
				: transportation.getKickOffDate();
		TransportationProcessingTimeline timeline = new TransportationProcessingTimeline();

		timeline.setAssignmentTimeMinutes(10);
		timeline.setTruckAssignedDateTime(
				DateUtils.addMinutesToDate(executionStartDate, timeline.getAssignmentTimeMinutes()));

		if (truck != null) {
			timeline.setArrivalTimeMinutes(
					truckService.estimateArrivalTimeMinutes(truck, transportation.getShipFromProperty(),
							transportation.getShipToProperty(), timeline.getTruckAssignedDateTime(), 60));
		} else {
			timeline.setArrivalTimeMinutes(20);
		}

		timeline.setStartDateTime(
				DateUtils.addMinutesToDate(timeline.getTruckAssignedDateTime(), timeline.getArrivalTimeMinutes()));

		timeline.setLoadTimeMinutes(shippingLocationService.estimateLoadTimeMinutes(transportation));

		timeline.setLoadQueueTimeMinutes(shippingLocationService.estimateQueueTimeMinutes(
				transportation.getShipFromProperty(), timeline.getStartDateTime(), timeline.getLoadTimeMinutes()));

		timeline.setLoadStartDateTime(
				DateUtils.addMinutesToDate(timeline.getStartDateTime(), timeline.getLoadQueueTimeMinutes()));

		timeline.setTravelStartDateTime(
				DateUtils.addMinutesToDate(timeline.getLoadStartDateTime(), timeline.getLoadTimeMinutes()));

		timeline.setTravelTimeMinutes(
				truckService.estimateTravelTimeMinutes(truck, transportation.getShipFromProperty().getGeoLocation(),
						shipTo.getGeoLocation(), timeline.getTravelStartDateTime()));

		timeline.setUnloadQueueStartDateTime(
				DateUtils.addMinutesToDate(timeline.getTravelStartDateTime(), timeline.getTravelTimeMinutes()));

		timeline.setUnloadTimeMinutes(shippingLocationService.estimateUnloadTimeMinutest(transportation));

		timeline.setUnloadQueueTimeMinutes(shippingLocationService.estimateQueueTimeMinutes(shipTo,
				timeline.getUnloadQueueStartDateTime(), timeline.getUnloadTimeMinutes()));

		timeline.setUnloadStartDateTime(DateUtils.addMinutesToDate(timeline.getUnloadQueueStartDateTime(),
				timeline.getUnloadQueueTimeMinutes()));

		timeline.setEndDateTime(
				DateUtils.addMinutesToDate(timeline.getUnloadQueueStartDateTime(), timeline.getUnloadTimeMinutes()));

		timeline.setTravelMileageKm(geoServices.getDistanceKm(transportation.getShipFromProperty().getGeoLocation(),
				shipTo.getGeoLocation()));
		if (truck != null) {
			timeline.setArrivalMileageKm(geoServices.getDistanceKm(truck.getGeoLocation(),
					transportation.getShipFromProperty().getGeoLocation()));
		}

		return timeline;
	}

	public int estimateTotalDeliveryTime(Transportation transportation, ShippingLocation shipTo) {
		TransportationProcessingTimeline timeline = prepareTransporationProcessingTimeline(transportation, shipTo,
				null);
		return timeline.getLoadQueueTimeMinutes() + timeline.getLoadTimeMinutes() + timeline.getTravelTimeMinutes()
				+ timeline.getUnloadQueueTimeMinutes() + timeline.getUnloadTimeMinutes();
	}

	@Transactional
	public Transportation processTrucksSearch(Transportation transportation) {
		List<Truck> trucks = truckRepository.findAll();
		boolean isTrucksAssigned = false;
		for (Truck truck : trucks) {
			if ((	truck.getMaxWeight() != null && 
					transportation.getTotalWeight() != null && 
					truck.getMaxWeight().compareTo(transportation.getTotalWeight()) >= 0 &&
					truck.getMaxVolume() != null &&
					transportation.getTotalVolume() != null &&
					truck.getMaxVolume().compareTo(transportation.getTotalVolume()) >= 0
				) || (
					truck.getMaxWeight() == null ||
					transportation.getTotalWeight() == null ||
					truck.getMaxVolume() == null ||
					transportation.getTotalVolume() == null
				) ) {
				
				TransportationAssignment newAssignment = new TransportationAssignment();
				newAssignment.setTransportation(transportation.getTransportationNum());
				newAssignment.setTransportationProperty(transportation);
				newAssignment.setStep(TransportationAssignment.STEP_010);
				newAssignment.setPreferred(truck.getCarrierProperty().getRegions().stream()
						.anyMatch(r -> r.getRegion().equals(transportation.getShipFromProperty().getRegion())));
				newAssignment.setTruck(truck.getTruckNum());
				newAssignment.setTruckProperty(truck);
				newAssignment.setProposedPrice(calculateTransporationPriceRub(transportation, truck));
				newAssignment.setReputationIndex(truck.getCarrierProperty().getReputationIndex());
				copyFields(newAssignment, prepareTransporationProcessingTimeline(transportation,
						transportation.getShipToProperty(), truck));
				if (newAssignment.getArrivalTimeMinutes().compareTo(180) <= 0) {
					transportation.getAssignments().add(newAssignment);
					isTrucksAssigned = true;
				}
			}
		}

		transportation.getAssignments().forEach(assignment -> assignment
				.setArrivalTimeScore(ValueListClasifier.classify(assignment.getArrivalTimeMinutes(), 10, 30, 60, 120)));

		transportation.getAssignments().stream()
				.sorted((a, b) -> a.getArrivalTimeMinutes().compareTo(b.getArrivalTimeMinutes()))
				.reduce(null, (previousAssignment, currentAssignment) -> {
					if (previousAssignment == null) {
						currentAssignment.setAssignmentIndex(1);
					} else {
						currentAssignment.setAssignmentIndex(previousAssignment.getAssignmentIndex() + 1);
					}
					return currentAssignment;
				});

		if (isTrucksAssigned) {
			transportation.setStatus(Transportation.STATUS_TRUCKS_FOUND);
		} else {
			transportation.setStatus(Transportation.STATUS_TRUCKS_NOT_FOUND);
		}
		transportationRepository.save(transportation);
		return transportation;
	}

	private void clearTimelineFields(Transportation transportation) {
		transportation.setAssignmentTimeMinutes(null);
		transportation.setArrivalTimeMinutes(null);
		transportation.setLoadQueueTimeMinutes(null);
		transportation.setLoadTimeMinutes(null);
		transportation.setTravelTimeMinutes(null);
		transportation.setUnloadQueueTimeMinutes(null);
		transportation.setUnloadTimeMinutes(null);

		transportation.setAssignmentTimeActualMinutes(null);
		transportation.setArrivalTimeActualMinutes(null);
		transportation.setLoadQueueTimeActualMinutes(null);
		transportation.setLoadTimeActualMinutes(null);
		transportation.setTravelTimeActualMinutes(null);
		transportation.setUnloadQueueTimeActualMinutes(null);
		transportation.setUnloadTimeActualMinutes(null);

		transportation.setTruckAssignedDateTime(null);
		transportation.setStartDateTime(null);
		transportation.setLoadStartDateTime(null);
		transportation.setTravelStartDateTime(null);
		transportation.setUnloadQueueStartDateTime(null);
		transportation.setUnloadStartDateTime(null);
		transportation.setEndDateTime(null);

		transportation.setTruckAssignedActualDateTime(null);
		transportation.setStartActualDateTime(null);
		transportation.setLoadStartActualDateTime(null);
		transportation.setTravelStartActualDateTime(null);
		transportation.setUnloadQueueStartActualDateTime(null);
		transportation.setUnloadStartActualDateTime(null);
		transportation.setEndActualDateTime(null);

		transportation.setTravelMileageKm(null);
		transportation.setArrivalMileageKm(null);
	}

	private void copyFields(TransportationAssignment to, TransportationProcessingTimeline from) {
		to.setAssignmentTimeMinutes(from.getAssignmentTimeMinutes());
		to.setArrivalTimeMinutes(from.getArrivalTimeMinutes());
		to.setLoadQueueTimeMinutes(from.getLoadQueueTimeMinutes());
		to.setLoadTimeMinutes(from.getLoadTimeMinutes());
		to.setTravelTimeMinutes(from.getTravelTimeMinutes());
		to.setUnloadQueueTimeMinutes(from.getUnloadQueueTimeMinutes());
		to.setUnloadTimeMinutes(from.getUnloadTimeMinutes());

		to.setTruckAssignedDateTime(from.getTruckAssignedDateTime());
		to.setStartDateTime(from.getStartDateTime());
		to.setLoadStartDateTime(from.getLoadStartDateTime());
		to.setTravelStartDateTime(from.getTravelStartDateTime());
		to.setUnloadQueueStartDateTime(from.getUnloadQueueStartDateTime());
		to.setUnloadStartDateTime(from.getUnloadStartDateTime());
		to.setEndDateTime(from.getEndDateTime());

		to.setTravelMileageKm(from.getTravelMileageKm());
		to.setArrivalMileageKm(from.getArrivalMileageKm());
	}

	private void copyFields(Transportation to, TransportationProcessingTimeline from) {
		to.setAssignmentTimeMinutes(from.getAssignmentTimeMinutes());
		to.setArrivalTimeMinutes(from.getArrivalTimeMinutes());
		to.setLoadQueueTimeMinutes(from.getLoadQueueTimeMinutes());
		to.setLoadTimeMinutes(from.getLoadTimeMinutes());
		to.setTravelTimeMinutes(from.getTravelTimeMinutes());
		to.setUnloadQueueTimeMinutes(from.getUnloadQueueTimeMinutes());
		to.setUnloadTimeMinutes(from.getUnloadTimeMinutes());

		to.setTruckAssignedDateTime(from.getTruckAssignedDateTime());
		to.setStartDateTime(from.getStartDateTime());
		to.setLoadStartDateTime(from.getLoadStartDateTime());
		to.setTravelStartDateTime(from.getTravelStartDateTime());
		to.setUnloadQueueStartDateTime(from.getUnloadQueueStartDateTime());
		to.setUnloadStartDateTime(from.getUnloadStartDateTime());
		to.setEndDateTime(from.getEndDateTime());

		to.setTravelMileageKm(from.getTravelMileageKm());
		to.setArrivalMileageKm(from.getArrivalMileageKm());
	}

	private void copyFields(TransportationLocationAssignment to, TransportationProcessingTimeline from) {
		// to.setAssignmentTimeMinutes(from.getAssignmentTimeMinutes());
		// to.setArrivalTimeMinutes(from.getArrivalTimeMinutes());
		// to.setLoadQueueTimeMinutes(from.getLoadQueueTimeMinutes());
		// to.setLoadTimeMinutes(from.getLoadTimeMinutes());
		to.setTravelTimeMinutes(from.getTravelTimeMinutes());
		to.setUnloadQueueTimeMinutes(from.getUnloadQueueTimeMinutes());
		to.setUnloadTimeMinutes(from.getUnloadTimeMinutes());

		to.setTravelMileageKm(from.getTravelMileageKm());
	}

	public BigDecimal calculateTransporationPriceRub(Transportation transportation, Truck truck) {
		return BigDecimal.valueOf(100);
	}

	public void processSendRequests(String transportation) {
		processSendRequests(transportationRepository.findOne(transportation));
	}

	@Transactional
	public void processSendRequests(Transportation transportation) {
		transportation.getAssignments().stream()
				.forEach(assignment -> assignment.setStatus(TransportationAssignment.STATUS_REQUEST_SEND));
		transportation.setStatus(Transportation.STATUS_REQUESTS_SENT);
		transportationRepository.save(transportation);
	}

	public void processTransportationAcceptance(String transportation, String truck) {
		processTransportationAcceptance(transportationRepository.findOne(transportation),
				truckRepository.findOne(truck));
	}

	public void processTransportationAcceptanceByDriver(String transportation, String driver) {
		processTransportationAcceptance(transportationRepository.findOne(transportation),
				truckRepository.findByDriver(driver).stream().findFirst()
						.orElseThrow(() -> new MyRuntimeException("Failed find truck for driver: '" + driver + "'")));
	}

	@Transactional
	public void processTransportationAcceptance(Transportation transportation, Truck truck) {
		TransportationAssignment assignment = transportation.getAssignments().stream()
				.filter(a -> a.getTruck().equals(truck.getTruckNum())).findFirst()
				.orElseThrow(() -> new MyRuntimeException("Failed to accept transportation, truck '"
						+ truck.getTruckNum() + "' not found in assignments."));

		assignment.setStatus(TransportationAssignment.STATUS_ACCEPTED);
		transportation.getAssignments().stream().filter(assignment2 -> !assignment2.equals(assignment))
				.forEach(assignment2 -> assignment2.setStatus(TransportationAssignment.STATUS_INACTIVE));
		transportation.getLocationAssignments().stream().filter(
				lcoationAssignment -> !lcoationAssignment.getShipToLocation().equals(transportation.getShipTo()))
				.forEach(lcoationAssignment -> lcoationAssignment.setStatus(TransportationAssignment.STATUS_INACTIVE));

		copyFields(transportation,
				prepareTransporationProcessingTimeline(transportation, transportation.getShipToProperty(), truck));
		transportation.setTruck(truck.getTruckNum());
		transportation.setTruckProperty(truck);
		transportation.setAssignmentTimeActualMinutes(transportation.getAssignmentTimeMinutes() + 10);
		transportation.setTruckAssignedActualDateTime(DateUtils.addMinutesToDate(transportation.getKickOffDate(),
				transportation.getAssignmentTimeActualMinutes()));
		truckService.addTransportation(truck, transportation);
		transportation.setStatus(Transportation.STATUS_ASSIGNED);
		transportationRepository.save(transportation);
	}

	public GeoServices getGeoServices() {
		return geoServices;
	}

	public void setGeoServices(GeoServices geoServices) {
		this.geoServices = geoServices;
	}

	public ShippingLocationService getShippingLocationService() {
		return shippingLocationService;
	}

	public void setShippingLocationService(ShippingLocationService shippingLocationService) {
		this.shippingLocationService = shippingLocationService;
	}

	public ShippingLocationRepository getShippingLocationRepository() {
		return shippingLocationRepository;
	}

	public void setShippingLocationRepository(ShippingLocationRepository shippingLocationRepository) {
		this.shippingLocationRepository = shippingLocationRepository;
	}

	public TruckRepository getTruckRepository() {
		return truckRepository;
	}

	public void setTruckRepository(TruckRepository truckRepository) {
		this.truckRepository = truckRepository;
	}

	public TransportationRepository getTransportationRepository() {
		return transportationRepository;
	}

	public void setTransportationRepository(TransportationRepository transportationRepository) {
		this.transportationRepository = transportationRepository;
	}

	public TruckService getTruckService() {
		return truckService;
	}

	public void setTruckService(TruckService truckService) {
		this.truckService = truckService;
	}
}
/*
 * @Transactional public void processTrucksByCarrierRegionSearch(Transportation
 * transportation) throws ClientProtocolException, URISyntaxException,
 * IOException { Set<Truck> trucks =
 * truckRepository.findTrucksByRegion(transportation.getShipFromProperty().
 * getRegion()); boolean isTrucksAssigned = false; int sequentialNumber = 1; for
 * (Truck truck : trucks) { TransportationAssignment newAssignment = new
 * TransportationAssignment();
 * newAssignment.setTransportation(transportation.getTransportationNum());
 * newAssignment.setTransportationProperty(transportation);
 * newAssignment.setStep(TransportationAssignment.STEP_010);
 * newAssignment.setAssignmentIndex(sequentialNumber);
 * newAssignment.setTruck(truck.getTruckNum());
 * newAssignment.setTruckProperty(truck);
 * newAssignment.setProposedPrice(calculateTransporationPriceRub(transportation,
 * truck)); //
 * newAssignment.setArrivalTimeMinutes(geoServices.getArrivalTimeMinutes(truck.
 * getGeoLocation(), // transportation.getShipFromProperty().getGeoLocation()));
 * copyFields(newAssignment,
 * prepareTransporationProcessingTimeline(transportation,
 * transportation.getShipToProperty(), truck));
 * newAssignment.setReputationIndex(truck.getCarrierProperty().
 * getReputationIndex()); transportation.getAssignments().add(newAssignment);
 * isTrucksAssigned = true; sequentialNumber++; }
 * 
 * transportation.getAssignments().forEach(assignment -> assignment
 * .setArrivalTimeScore(ValueListClasifier.classify(assignment.
 * getArrivalTimeMinutes(), 10, 30, 60, 120)));
 * 
 * if (isTrucksAssigned) {
 * transportation.setStatus(Transportation.STATUS_TRUCKS_FOUND); } else {
 * transportation.setStatus(Transportation.STATUS_TRUCKS_NOT_FOUND); }
 * transportationRepository.save(transportation); }
 */

/*
 * public void processTransportationAssignment(Transportation transportation)
 * throws ClientProtocolException, URISyntaxException, IOException {
 * Set<TransportationMessageLog> messageLog = new
 * HashSet<TransportationMessageLog>();
 * 
 * switch (transportation.getStatus()) { case Transportation.STATUS_RELEASED:
 * processShipToLocationDetermination(transportation); break; case
 * Transportation.STATUS_SHIP_TO_DETERMINED:
 * processTrucksSearch(transportation); break; case
 * Transportation.STATUS_TRUCKS_FOUND: processSendNotifications(transportation);
 * break; default: throw new
 * RuntimeException("Unsupported transportation status '" +
 * transportation.getStatus() + "'"); } }
 */