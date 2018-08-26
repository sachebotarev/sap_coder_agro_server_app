package my.agro.transportation.management.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::TransportationLocationAssignment\"")
@IdClass(TransportationLocationAssignmentPK.class)
public class TransportationLocationAssignment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_CREATED 					= "CREATED";
	public static final String STATUS_ACCEPTED 					= "ACCEPTED";
	public static final String STATUS_INACTIVE 					= "INACTIVE";
	
	@Id
	@Column(name = "\"transportation\"", length = 10)
	private String transportation = "";
	@Id
	@Column(name = "\"shipToLocation\"", length = 10)
	private String shipToLocation = "";
	@Column(name = "\"assignmentIndex\"")
	private Integer assignmentIndex = 0;
	@Column(name = "\"selected\"")
	private Boolean selected = false;
	@Column(name = "\"status\"", length = 30)
	private String status = STATUS_CREATED;
	@Column(name = "\"travelTimeMinutes\"")
	private Integer travelTimeMinutes = 0;
	@Column(name = "\"unloadQueueTimeMinutes\"")
	private Integer unloadQueueTimeMinutes = 0;
	@Column(name = "\"unloadTimeMinutes\"")
	private Integer unloadTimeMinutes = 0;
	@Column(name = "\"processingTimeMinutes\"")
	private Integer processingTimeMinutes = 0;
	@Column(name = "\"processingTimeScore\"")
	private Integer processingTimeScore = 0;
	
	@Column(name = "\"travelMileageKm\"")
	private Integer travelMileageKm;
	
	@JoinColumns({
        @JoinColumn(name = "\"transportation\"", referencedColumnName = "\"transportationNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	@Expose(serialize = false, deserialize = false)
	private Transportation transportationProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"shipToLocation\"", referencedColumnName = "\"shippingLocationKey\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private ShippingLocation shipToLocationProperty;

	public TransportationLocationAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getShipToLocation() {
		return shipToLocation;
	}
	public void setShipToLocation(String shipToLocation) {
		this.shipToLocation = shipToLocation;
	}
	public Integer getAssignmentIndex() {
		return assignmentIndex;
	}
	public void setAssignmentIndex(Integer assignmentIndex) {
		this.assignmentIndex = assignmentIndex;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTravelTimeMinutes() {
		return travelTimeMinutes;
	}
	public void setTravelTimeMinutes(Integer travelTimeMinutes) {
		this.travelTimeMinutes = travelTimeMinutes;
	}
	public Integer getUnloadQueueTimeMinutes() {
		return unloadQueueTimeMinutes;
	}
	public void setUnloadQueueTimeMinutes(Integer queueTimeMinutes) {
		this.unloadQueueTimeMinutes = queueTimeMinutes;
	}
	public Integer getUnloadTimeMinutes() {
		return unloadTimeMinutes;
	}
	public void setUnloadTimeMinutes(Integer unloadTimeMinutes) {
		this.unloadTimeMinutes = unloadTimeMinutes;
	}
	public Integer getProcessingTimeMinutes() {
		return processingTimeMinutes;
	}
	public void setProcessingTimeMinutes(Integer totalTimeMunutes) {
		this.processingTimeMinutes = totalTimeMunutes;
	}
	public Transportation getTransportationProperty() {
		return transportationProperty;
	}
	public void setTransportationProperty(Transportation transportationProperty) {
		this.transportationProperty = transportationProperty;
	}
	public ShippingLocation getShipToLocationProperty() {
		return shipToLocationProperty;
	}
	public void setShipToLocationProperty(ShippingLocation shipToLocationProperty) {
		this.shipToLocationProperty = shipToLocationProperty;
	}
	public Integer getProcessingTimeScore() {
		return processingTimeScore;
	}
	public void setProcessingTimeScore(Integer processingTimeScore) {
		this.processingTimeScore = processingTimeScore;
	}
	public Integer getTravelMileageKm() {
		return travelMileageKm;
	}
	public void setTravelMileageKm(Integer travelMileageKm) {
		this.travelMileageKm = travelMileageKm;
	}
}
