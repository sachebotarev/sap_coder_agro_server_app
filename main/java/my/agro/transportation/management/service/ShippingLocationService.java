package my.agro.transportation.management.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javafx.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.agro.transportation.management.dao.entity.ShippingLocation;
import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.dao.repository.ShippingLocationRepository;
import my.agro.transportation.management.utils.DateUtils;
import my.agro.transportation.management.utils.MyRuntimeException;
import my.agro.transportation.management.dao.dto.DateLocationInterval;

@Service
public class ShippingLocationService {
	@Autowired
	private SchedulingService schedulingService;
	//@Autowired
	//private ShippingLocationRepository shippingLocationRepository;

	public void addTransportationForShipFrom(ShippingLocation shipFromLocation, Transportation transportation) {
		shipFromLocation.getShipFromTransportations().add(transportation);
	}
	
	public void addTransportationForShipTo(ShippingLocation shipToLocation, Transportation transportation) {
		shipToLocation.getShipToTransportations().add(transportation);
	}
	
	public void removeTransportationForShipFrom(ShippingLocation shipFromLocation, Transportation transportation) {
		shipFromLocation.getShipFromTransportations().remove(transportation);
	}
	
	public void removeTransportationForShipTo(ShippingLocation shipToLocation, Transportation transportation) {
		shipToLocation.getShipToTransportations().remove(transportation);
	}
	
	public int estimateQueueTimeMinutes(ShippingLocation location, Date plannedArrivalTime, Integer loadTimeMinutes) {
		return DateUtils.minutesBetween(plannedArrivalTime,
				schedulingService.findFirstIntervalToAddAfter(getLoadingIntervals(location),
						new DateLocationInterval(plannedArrivalTime, loadTimeMinutes)).getDateTo());
	}

	private List<DateLocationInterval> getLoadingIntervals(ShippingLocation location) {
		if (location.getLocationType().equals(ShippingLocation.TYPE_PRODUCING)) {
			return location.getShipFromTransportations().stream()
					.filter(transportation -> Arrays.asList(Transportation.STATUSES_EXECUTION).contains(transportation.getStatus()) )
					.map(transportation -> new DateLocationInterval(transportation.getLoadStartDateTime(),
							transportation.getTravelStartDateTime()))
					.collect(Collectors.toList());
		} else if (location.getLocationType().equals(ShippingLocation.TYPE_STORAGE)) {
			return location.getShipToTransportations().stream()
					.filter(transportation -> Arrays.asList(Transportation.STATUSES_EXECUTION).contains(transportation.getStatus()) )
					.map(transportation -> new DateLocationInterval(transportation.getUnloadStartDateTime(),
							transportation.getEndDateTime()))
					.collect(Collectors.toList());
		} else {
			throw new MyRuntimeException("Uncknown ShippingLocation type'" + location.getLocationType() + "'");
		}
	}

	public int estimateLoadTimeMinutes(Transportation transportation) {
		return 10;
	}

	public int estimateUnloadTimeMinutest(Transportation transportation) {
		return 10;
	}

	public SchedulingService getDateIntervalService() {
		return schedulingService;
	}

	public void setDateIntervalService(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}
}
