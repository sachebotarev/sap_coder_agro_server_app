package my.agro.transportation.management.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::TransportationAssignment\"")
@IdClass(TransportationAssignmentPK.class)
public class TransportationAssignment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String STEP_010 = "010";
	public static final String STEP_020 = "020";
	
	public static final String STATUS_CREATED 					= "CREATED";
	public static final String STATUS_REQUEST_SEND 				= "REQUEST_SEND";
	public static final String STATUS_REQUEST_DELIVERED 		= "REQUEST_DELIVERED";
	public static final String STATUS_REQUEST_READ 				= "REQUEST_READ";
	public static final String STATUS_ACCEPTED 					= "ACCEPTED";
	public static final String STATUS_REJECTED 					= "REJECTED";
	public static final String STATUS_INACTIVE 					= "INACTIVE";
	
	@Id
	@Column(name = "\"transportation\"", length = 10)
	private String transportation = "";
	@Id
	@Column(name = "\"step\"", length = 10)
	private String step = "";
	@Id
	@Column(name = "\"truck\"", length = 10)
	private String truck = "";
	@Column(name = "\"status\"", length = 30)
	private String status = STATUS_CREATED;
	@Column(name = "\"proposedPrice\"", precision = 13, scale = 2)
	private BigDecimal proposedPrice = new BigDecimal(0);;
	@Column(name = "\"reputationIndex\"")
	private Integer reputationIndex = 0;
	@Column(name = "\"assignmentIndex\"")
	private Integer assignmentIndex = 0;
	@Column(name = "\"preferred\"")
	private Boolean preferred = false;
	@Column(name = "\"selected\"")
	private Boolean selected = false;
	
	
	@Column(name = "\"assignmentTimeMinutes\"")
	private Integer assignmentTimeMinutes = 0;
	@Column(name = "\"arrivalTimeMinutes\"")
	private Integer arrivalTimeMinutes = 0;
	@Column(name = "\"loadQueueTimeMinutes\"")
	private Integer loadQueueTimeMinutes = 0;
	@Column(name = "\"loadTimeMinutes\"")
	private Integer loadTimeMinutes = 0;
	@Column(name = "\"travelTimeMinutes\"")
	private Integer travelTimeMinutes = 0;
	@Column(name = "\"unloadQueueTimeMinutes\"")
	private Integer unloadQueueTimeMinutes = 0;
	@Column(name = "\"unloadTimeMinutes\"")
	private Integer unloadTimeMinutes = 0;
	@Column(name = "\"arrivalTimeScore\"")
	private Integer arrivalTimeScore = 0;
	
	@Column(name = "\"truckAssignedDateTime\"")
	private Date truckAssignedDateTime;
	@Column(name = "\"startDateTime\"")
	private Date startDateTime;
	@Column(name = "\"loadStartDateTime\"")
	private Date loadStartDateTime;
	@Column(name = "\"travelStartDateTime\"")
	private Date travelStartDateTime;
	@Column(name = "\"unloadQueueStartDateTime\"")
	private Date unloadQueueStartDateTime;
	@Column(name = "\"unloadStartDateTime\"")
	private Date unloadStartDateTime;
	@Column(name = "\"endDateTime\"")
	private Date endDateTime; 
	
	@Column(name = "\"travelMileageKm\"")
	private Integer travelMileageKm;
	@Column(name = "\"arrivalMileageKm\"")
	private Integer arrivalMileageKm;
	
	@JoinColumns({
        @JoinColumn(name = "\"transportation\"", referencedColumnName = "\"transportationNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	@Expose(serialize = false, deserialize = false)
	private Transportation transportationProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"truck\"", referencedColumnName = "\"truckNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Truck truckProperty;
	
	public TransportationAssignment() {
		super();
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getTruck() {
		return truck;
	}
	public void setTruck(String truck) {
		this.truck = truck;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getArrivalTimeMinutes() {
		return arrivalTimeMinutes;
	}
	public void setArrivalTimeMinutes(Integer arrivalTimeMinutes) {
		this.arrivalTimeMinutes = arrivalTimeMinutes;
	}
	public BigDecimal getProposedPrice() {
		return proposedPrice;
	}
	public void setProposedPrice(BigDecimal proposedPrice) {
		this.proposedPrice = proposedPrice;
	}
	public Integer getReputationIndex() {
		return reputationIndex;
	}
	public void setReputationIndex(Integer reputationIndex) {
		this.reputationIndex = reputationIndex;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Transportation getTransportationProperty() {
		return transportationProperty;
	}
	public void setTransportationProperty(Transportation transporationProperty) {
		this.transportationProperty = transporationProperty;
	}
	public Truck getTruckProperty() {
		return truckProperty;
	}
	public void setTruckProperty(Truck truckProperty) {
		this.truckProperty = truckProperty;
	}
	public Integer getAssignmentIndex() {
		return assignmentIndex;
	}
	public void setAssignmentIndex(Integer assignmentNum) {
		this.assignmentIndex = assignmentNum;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Integer getAssignmentTimeMinutes() {
		return assignmentTimeMinutes;
	}
	public void setAssignmentTimeMinutes(Integer assignmentTimeMinutes) {
		this.assignmentTimeMinutes = assignmentTimeMinutes;
	}
	public Integer getLoadQueueTimeMinutes() {
		return loadQueueTimeMinutes;
	}
	public void setLoadQueueTimeMinutes(Integer loadQueueTimeMinutes) {
		this.loadQueueTimeMinutes = loadQueueTimeMinutes;
	}
	public Integer getLoadTimeMinutes() {
		return loadTimeMinutes;
	}
	public void setLoadTimeMinutes(Integer loadTimeMinutes) {
		this.loadTimeMinutes = loadTimeMinutes;
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
	public void setUnloadQueueTimeMinutes(Integer unloadQueueTimeMinutes) {
		this.unloadQueueTimeMinutes = unloadQueueTimeMinutes;
	}
	public Integer getUnloadTimeMinutes() {
		return unloadTimeMinutes;
	}
	public void setUnloadTimeMinutes(Integer unloadTimeMinutes) {
		this.unloadTimeMinutes = unloadTimeMinutes;
	}
	public Integer getArrivalTimeScore() {
		return arrivalTimeScore;
	}
	public void setArrivalTimeScore(Integer arrivalTimeScore) {
		this.arrivalTimeScore = arrivalTimeScore;
	}
	public Boolean getPreferred() {
		return preferred;
	}
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	public Date getTruckAssignedDateTime() {
		return truckAssignedDateTime;
	}
	public void setTruckAssignedDateTime(Date truckAssignedDateTime) {
		this.truckAssignedDateTime = truckAssignedDateTime;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
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
	public void setUnloadStartDateTime(Date unloadStartDateTime) {
		this.unloadStartDateTime = unloadStartDateTime;
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
