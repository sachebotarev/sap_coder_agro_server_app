package my.agro.transportation.management.dao.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class TransportationProcessingTimeline implements Serializable {
	int assignmentTimeMinutes = 0;
	int arrivalTimeMinutes = 0;
	int loadQueueTimeMinutes = 0;
	int loadTimeMinutes = 0;
	int travelTimeMinutes = 0;
	int unloadQueueTimeMinutes = 0;
	int unloadTimeMinutes = 0;
	
	Date truckAssignedDateTime;
	Date startDateTime;
	Date loadStartDateTime;
	Date travelStartDateTime;
	Date unloadQueueStartDateTime;
	Date unloadStartDateTime;
	Date endDateTime; 
	
	private Integer travelMileageKm;
	private Integer arrivalMileageKm;
	
	public TransportationProcessingTimeline() {
		super();
	}
	public Date getTruckAssignedDateTime() {
		return truckAssignedDateTime;
	}
	public void setTruckAssignedDateTime(Date truckAssignedDateTime) {
		this.truckAssignedDateTime = truckAssignedDateTime;
	}
	public int getAssignmentTimeMinutes() {
		return assignmentTimeMinutes;
	}
	public void setAssignmentTimeMinutes(int assignmentTimeMinutes) {
		this.assignmentTimeMinutes = assignmentTimeMinutes;
	}
	public int getArrivalTimeMinutes() {
		return arrivalTimeMinutes;
	}
	public void setArrivalTimeMinutes(int arrivalTimeMinutes) {
		this.arrivalTimeMinutes = arrivalTimeMinutes;
	}
	public int getLoadQueueTimeMinutes() {
		return loadQueueTimeMinutes;
	}
	public void setLoadQueueTimeMinutes(int loadQueueTimeMinutes) {
		this.loadQueueTimeMinutes = loadQueueTimeMinutes;
	}
	public int getLoadTimeMinutes() {
		return loadTimeMinutes;
	}
	public void setLoadTimeMinutes(int loadTimeMinutes) {
		this.loadTimeMinutes = loadTimeMinutes;
	}
	public int getTravelTimeMinutes() {
		return travelTimeMinutes;
	}
	public void setTravelTimeMinutes(int travelTimeMinutes) {
		this.travelTimeMinutes = travelTimeMinutes;
	}
	public int getUnloadQueueTimeMinutes() {
		return unloadQueueTimeMinutes;
	}
	public void setUnloadQueueTimeMinutes(int unLoadQueueTimeMinutes) {
		this.unloadQueueTimeMinutes = unLoadQueueTimeMinutes;
	}
	public int getUnloadTimeMinutes() {
		return unloadTimeMinutes;
	}
	public void setUnloadTimeMinutes(int unLoadTimeMinutes) {
		this.unloadTimeMinutes = unLoadTimeMinutes;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	/*public Date getLoadQueueStartDateTime() {
		return loadQueueStartDateTime;
	}
	public void setLoadQueueStartDateTime(Date loadQueueStartDateTime) {
		this.loadQueueStartDateTime = loadQueueStartDateTime;
	}*/
	public Date getLoadStartDateTime() {
		return loadStartDateTime;
	}
	public void setLoadStartDateTime(Date loadStartDateTime) {
		this.loadStartDateTime = loadStartDateTime;
	}
	public Date getTravelStartDateTime() {
		return travelStartDateTime;
	}
	public void setTravelStartDateTime(Date travelStartDateTime) {
		this.travelStartDateTime = travelStartDateTime;
	}
	public Date getUnloadQueueStartDateTime() {
		return unloadQueueStartDateTime;
	}
	public void setUnloadQueueStartDateTime(Date unloadQueueStartDateTime) {
		this.unloadQueueStartDateTime = unloadQueueStartDateTime;
	}
	public Date getUnloadStartDateTime() {
		return unloadStartDateTime;
	}
	public void setUnloadStartDateTime(Date i) {
		this.unloadStartDateTime = i;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public Integer getTravelMileageKm() {
		return travelMileageKm;
	}
	public void setTravelMileageKm(Integer travelMileageKm) {
		this.travelMileageKm = travelMileageKm;
	}
	public Integer getArrivalMileageKm() {
		return arrivalMileageKm;
	}
	public void setArrivalMileageKm(Integer arrivalMileageKm) {
		this.arrivalMileageKm = arrivalMileageKm;
	}
}
