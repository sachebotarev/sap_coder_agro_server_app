package my.agro.transportation.management.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.agro.transportation.management.dao.dto.DateLocationInterval;
import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.dao.entity.Truck;
import my.agro.transportation.management.dao.repository.TruckRepository;
import my.agro.transportation.management.utils.DateUtils;

@Service
public class TruckService {
	// getTrucksByRegion() {
	//
	// }
	@Autowired
	GeoServices geoServices;
	@Autowired
	private SchedulingService schedulingService;
	
	@Autowired
	private TruckRepository truckRepository;

	@Transactional 
	public Truck processStatusChange(String truckNum, String newStatus) {
		Truck truck = truckRepository.findOne(truckNum);
		truck.setStatus(newStatus);
		truckRepository.save(truck);
		return truck;
	}
	public void addTransportation(Truck truck, Transportation transportation) {
		truck.getTransportations().add(transportation);
	}
	
	public void removeTransportation(Truck truck, Transportation transportation) {
		truck.getTransportations().remove(transportation);
	}
	
	public Set<Truck> getTrucksByShippingPoints(String shipFrom, String shipTo) {
		return new HashSet<Truck>();
	}

	public int estimateTravelTimeMinutes(Truck truck, String geoLocationFrom, String geoLocationTo,
			Date plannedStartTimes) {
		return geoServices.getTravelTimeMinutes(geoLocationFrom, geoLocationTo);
	}

	public int estimateArrivalTimeMinutes(Truck truck, ShippingLocation from, ShippingLocation to,
			Date plannedStartTime, int transporationDurationMinutes) {
		DateLocationInterval previousInterval = schedulingService
				.findFirstIntervalToAddAfter(
						getBusyIntervals(truck), new DateLocationInterval(plannedStartTime,
								transporationDurationMinutes, from.getGeoLocation(), to.getGeoLocation()),
						truck.getGeoLocation());

		return DateUtils.minutesBetween(plannedStartTime, previousInterval.getDateTo())
				+ geoServices.getTravelTimeMinutes(previousInterval.getLocationTo(), from.getGeoLocation());
	}

	/*
	 * private List<DateLocationInterval>
	 * addInitialLocationToBusyIntervals(List<DateLocationInterval> intervals, Truck
	 * truck, Date plannedStartTime) { if
	 * (!schedulingService.getIntervalByDateTime(intervals,
	 * plannedStartTime).isPresent() &&
	 * !schedulingService.getPreviousIntervalByDateTime(intervals,
	 * plannedStartTime).isPresent()) { intervals.add(new
	 * DateLocationInterval(plannedStartTime, plannedStartTime,
	 * truck.getGeoLocation(), truck.getGeoLocation())); } return intervals; }
	 */

	private List<DateLocationInterval> getBusyIntervals(Truck truck) {
		return truck.getTransportations().stream()
				.filter(transportation -> Arrays.asList(Transportation.STATUSES_EXECUTION).contains(transportation.getStatus()) )
				.map(transportation -> new DateLocationInterval(transportation.getStartDateTime(),
						transportation.getEndDateTime(), transportation.getShipFromProperty().getGeoLocation(),
						transportation.getShipToProperty().getGeoLocation()))
				.collect(Collectors.toList());
	}

	public GeoServices getGeoServices() {
		return geoServices;
	}

	public void setGeoServices(GeoServices geoServices) {
		this.geoServices = geoServices;
	}

	public SchedulingService getSchedulingService() {
		return schedulingService;
	}

	public void setSchedulingService(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}
}
