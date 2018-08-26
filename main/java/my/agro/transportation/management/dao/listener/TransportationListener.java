package my.agro.transportation.management.dao.listener;

import javax.persistence.PrePersist;

import my.agro.transportation.management.dao.entity.Transportation;
import my.agro.transportation.management.service.NumberRangeService;



public class TransportationListener {
	@PrePersist 
	private void transportationPrePersist(Transportation transportation) {
		if (transportation.getTransportationNum().equals("")) {
			transportation.setTransportationNum( NumberRangeService.getNextNumber("TRANSPORTATION","") );
		}
	}
}