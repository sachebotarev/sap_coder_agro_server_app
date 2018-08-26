package my.agro.transportation.management.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import my.agro.transportation.management.dao.listener.TransportationListener;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::Transportation\"")
@EntityListeners(TransportationListener.class)
public class Transportation extends  BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_DRAFT = 					"010.DRAFT";
	public static final String STATUS_CREATED = 				"020.CREATED";
	public static final String STATUS_RELEASED = 				"030.RELEASED";
	public static final String STATUS_SHIP_TO_DETERMINED =	 	"041.SHIP_TO_DETERMINED";
	public static final String STATUS_TRUCKS_FOUND = 			"042.TRUCKS_FOUND";
	public static final String STATUS_REQUESTS_SENT = 			"043.REQUESTS_SENT";
	public static final String STATUS_TRUCKS_NOT_FOUND = 		"044.TRUCKS_NOT_FOUND";
	public static final String STATUS_ASSIGNED = 				"049.ASSIGNED";
	public static final String STATUS_ARRIVED_FOR_LOAD = 		"050.ARRIVED_FOR_LOAD";
	public static final String STATUS_LOADED = 					"060.LOADED";
	public static final String STATUS_ARRIVED_FOR_UNLOAD = 		"070.ARRIVED_FOR_UNLOAD";
	public static final String STATUS_CLOSED = 					"080.CLOSED";
	public static final String STATUS_COMPLETED = 				"090.COMPLETED";
	
	public static final String[] STATUSES_EXECUTION = {STATUS_ASSIGNED, STATUS_ARRIVED_FOR_LOAD, STATUS_LOADED, STATUS_ARRIVED_FOR_UNLOAD};
	public static final String[] STATUSES_ASSIGNMENT = {STATUS_SHIP_TO_DETERMINED, STATUS_TRUCKS_FOUND, STATUS_REQUESTS_SENT, STATUS_TRUCKS_NOT_FOUND};
	
	@Id
	@Column(name = "\"transportationNum\"", length = 10)
	private String transportationNum = "";
	@Column(name = "\"status\"", length = 30)
	private String status = STATUS_DRAFT;
	@Column(name = "\"shipFrom\"", length = 20)
	private String shipFrom = "";
	@Column(name = "\"shipTo\"", length = 20)
	private String shipTo = "";
	@Column(name = "\"truck\"", length = 10)
	private String truck = "";
	@Column(name = "\"comment\"", length = 50)
	private String comment = "";
	@Column(name = "\"workflowInstanceId\"", length = 36)
	private String workflowInstanceId = "";
	@Column(name = "\"wayBillPdfMediaResource\"", length = 36)
	private String wayBillPdfMediaResource = "";
	@Column(name = "\"totalWeight\"", precision = 13, scale = 2)
	private BigDecimal totalWeight;
	@Column(name = "\"weightUom\"")
	private String weightUom = "";
	@Column(name = "\"totalVolume\"", precision = 13, scale = 2)
	private BigDecimal totalVolume;
	@Column(name = "\"volumeUom\"")
	private String volumeUom = "";
	
	@Column(name="\"kickOffDate\"")
	private Date kickOffDate;
	@Column(name="\"truckAssignedDateTime\"")
	private Date truckAssignedDateTime;
	@Column(name="\"truckAssignedActualDateTime\"")
	private Date truckAssignedActualDateTime;
	@Column(name = "\"startDateTime\"")
	private Date startDateTime;
	@Column(name = "\"startActualDateTime\"")
	private Date startActualDateTime;
	@Column(name = "\"loadStartDateTime\"")
	private Date loadStartDateTime;
	@Column(name = "\"loadStartActualDateTime\"")
	private Date loadStartActualDateTime;
	@Column(name = "\"travelStartDateTime\"")
	private Date travelStartDateTime;
	@Column(name = "\"travelStartActualDateTime\"")
	private Date travelStartActualDateTime;
	@Column(name = "\"unloadQueueStartDateTime\"")
	private Date unloadQueueStartDateTime;
	@Column(name = "\"unloadQueueStartActualDateTime\"")
	private Date unloadQueueStartActualDateTime;
	@Column(name = "\"unloadStartDateTime\"")
	private Date unloadStartDateTime;
	@Column(name = "\"unloadStartActualDateTime\"")
	private Date unloadStartActualDateTime;
	@Column(name = "\"endDateTime\"")
	private Date endDateTime; 
	@Column(name = "\"endActualDateTime\"")
	private Date endActualDateTime; 
	
	@Column(name = "\"assignmentTimeMinutes\"")
	private Integer assignmentTimeMinutes;
	@Column(name = "\"assignmentTimeActualMinutes\"")
	private Integer assignmentTimeActualMinutes;
	@Column(name = "\"arrivalTimeMinutes\"")
	private Integer arrivalTimeMinutes;
	@Column(name = "\"arrivalTimeActualMinutes\"")
	private Integer arrivalTimeActualMinutes;
	@Column(name = "\"loadQueueTimeMinutes\"")
	private Integer loadQueueTimeMinutes;
	@Column(name = "\"loadQueueTimeActualMinutes\"")
	private Integer loadQueueTimeActualMinutes;
	@Column(name = "\"loadTimeMinutes\"")
	private Integer loadTimeMinutes;
	@Column(name = "\"loadTimeActualMinutes\"")
	private Integer loadTimeActualMinutes;
	@Column(name = "\"travelTimeMinutes\"")
	private Integer travelTimeMinutes;
	@Column(name = "\"travelTimeActualMinutes\"")
	private Integer travelTimeActualMinutes;
	@Column(name = "\"unloadQueueTimeMinutes\"")
	private Integer unloadQueueTimeMinutes;
	@Column(name = "\"unloadQueueTimeActualMinutes\"")
	private Integer unloadQueueTimeActualMinutes;
	@Column(name = "\"unloadTimeMinutes\"")
	private Integer unloadTimeMinutes;
	@Column(name = "\"unloadTimeActualMinutes\"")
	private Integer unloadTimeActualMinutes;
	
	@Column(name = "\"totalPriceRub\"", precision = 13, scale = 2)
	private BigDecimal totalPriceRub;
	@Column(name = "\"travelMileageKm\"")
	private Integer travelMileageKm;
	@Column(name = "\"arrivalMileageKm\"")
	private Integer arrivalMileageKm;
	@Column(name = "\"pusherRoomId\"")
	private String pusherRoomId = "";
	@Column(name = "\"ratingMark\"")
	private Integer ratingMark;
	
	
	@JoinColumns({
        @JoinColumn(name = "\"shipFrom\"", referencedColumnName = "\"shippingLocationKey\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private ShippingLocation shipFromProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"shipTo\"", referencedColumnName = "\"shippingLocationKey\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private ShippingLocation shipToProperty;
	
	@OneToMany(mappedBy = "transportationProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<TransportationAssignment> assignments;
	
	@OneToMany(mappedBy = "transportationProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<TransportationLocationAssignment> locationAssignments;
	
	@OneToMany(mappedBy = "transportationProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<TransportationMessageLog> messages;
	
	@OneToMany(mappedBy = "transportationProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<TransportationItem> transportationItems;
	
	@JoinColumns({
        @JoinColumn(name = "\"truck\"", referencedColumnName = "\"truckNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Truck truckProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"wayBillPdfMediaResource\"", referencedColumnName = "\"mediaResourceUuid\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private MediaResource wayBillPdfMediaResourceProperty;
	
	public Transportation() {
		super();
	}
	public Transportation(String transportationNum, String status, String shipFrom, String shipTo, String truck) {
		super();
		this.transportationNum = transportationNum;
		this.status = status;
		this.shipFrom = shipFrom;
		this.shipTo = shipTo;
		this.truck = truck;
	}
	public Date getKickOffDate() {
		return kickOffDate;
	}
	public void setKickOffDate(Date kickOffDate) {
		this.kickOffDate = kickOffDate;
	}
	public String getTransportationNum() {
		return transportationNum;
	}
	public void setTransportationNum(String transportationNum) {
		this.transportationNum = transportationNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShipFrom() {
		return shipFrom;
	}
	public void setShipFrom(String shipFrom) {
		this.shipFrom = shipFrom;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getTruck() {
		return truck;
	}
	public void setTruck(String truck) {
		this.truck = truck;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ShippingLocation getShipFromProperty() {
		return shipFromProperty;
	}
	public void setShipFromProperty(ShippingLocation shipFromProperty) {
		this.shipFromProperty = shipFromProperty;
	}
	public ShippingLocation getShipToProperty() {
		return shipToProperty;
	}
	public void setShipToProperty(ShippingLocation shipToProperty) {
		this.shipToProperty = shipToProperty;
	}
	public Set<TransportationAssignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<TransportationAssignment> assignments) {
		this.assignments = assignments;
	}
	public Set<TransportationMessageLog> getMessages() {
		return messages;
	}
	public void setMessages(Set<TransportationMessageLog> messages) {
		this.messages = messages;
	}
	public Set<TransportationLocationAssignment> getLocationAssignments() {
		return locationAssignments;
	}
	public void setLocationAssignments(Set<TransportationLocationAssignment> locationAssignments) {
		this.locationAssignments = locationAssignments;
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
	public void setUnloadStartDateTime(Date unloadStartDateTime) {
		this.unloadStartDateTime = unloadStartDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public Integer getAssignmentTimeMinutes() {
		return assignmentTimeMinutes;
	}
	public void setAssignmentTimeMinutes(Integer assignmentTimeMinutes) {
		this.assignmentTimeMinutes = assignmentTimeMinutes;
	}
	public Integer getArrivalTimeMinutes() {
		return arrivalTimeMinutes;
	}
	public void setArrivalTimeMinutes(Integer arrivalTimeMinutes) {
		this.arrivalTimeMinutes = arrivalTimeMinutes;
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
	public Truck getTruckProperty() {
		return truckProperty;
	}
	public void setTruckProperty(Truck truckProperty) {
		this.truckProperty = truckProperty;
	}
	public String getWorkflowInstanceId() {
		return workflowInstanceId;
	}
	public void setWorkflowInstanceId(String workflowInstanceId) {
		this.workflowInstanceId = workflowInstanceId;
	}
	public Date getTruckAssignedDateTime() {
		return truckAssignedDateTime;
	}
	public void setTruckAssignedDateTime(Date truckAssignedDateTime) {
		this.truckAssignedDateTime = truckAssignedDateTime;
	}
	public Date getTruckAssignedActualDateTime() {
		return truckAssignedActualDateTime;
	}
	public void setTruckAssignedActualDateTime(Date truckAssignedActualDateTime) {
		this.truckAssignedActualDateTime = truckAssignedActualDateTime;
	}
	public Date getStartActualDateTime() {
		return startActualDateTime;
	}
	public void setStartActualDateTime(Date startActualDateTime) {
		this.startActualDateTime = startActualDateTime;
	}
	public Date getLoadStartActualDateTime() {
		return loadStartActualDateTime;
	}
	public void setLoadStartActualDateTime(Date loadStartActualDateTime) {
		this.loadStartActualDateTime = loadStartActualDateTime;
	}
	public Date getTravelStartActualDateTime() {
		return travelStartActualDateTime;
	}
	public void setTravelStartActualDateTime(Date travelStartActualDateTime) {
		this.travelStartActualDateTime = travelStartActualDateTime;
	}
	public Date getUnloadQueueStartActualDateTime() {
		return unloadQueueStartActualDateTime;
	}
	public void setUnloadQueueStartActualDateTime(Date unloadQueueStartActualDateTime) {
		this.unloadQueueStartActualDateTime = unloadQueueStartActualDateTime;
	}
	public Date getUnloadStartActualDateTime() {
		return unloadStartActualDateTime;
	}
	public void setUnloadStartActualDateTime(Date unloadStartActualDateTime) {
		this.unloadStartActualDateTime = unloadStartActualDateTime;
	}
	public Date getEndActualDateTime() {
		return endActualDateTime;
	}
	public void setEndActualDateTime(Date endActualDateTime) {
		this.endActualDateTime = endActualDateTime;
	}
	public Integer getAssignmentTimeActualMinutes() {
		return assignmentTimeActualMinutes;
	}
	public void setAssignmentTimeActualMinutes(Integer assignmentTimeActualMinutes) {
		this.assignmentTimeActualMinutes = assignmentTimeActualMinutes;
	}
	public Integer getArrivalTimeActualMinutes() {
		return arrivalTimeActualMinutes;
	}
	public void setArrivalTimeActualMinutes(Integer arrivalTimeActualMinutes) {
		this.arrivalTimeActualMinutes = arrivalTimeActualMinutes;
	}
	public Integer getLoadQueueTimeActualMinutes() {
		return loadQueueTimeActualMinutes;
	}
	public void setLoadQueueTimeActualMinutes(Integer loadQueueTimeActualMinutes) {
		this.loadQueueTimeActualMinutes = loadQueueTimeActualMinutes;
	}
	public Integer getLoadTimeActualMinutes() {
		return loadTimeActualMinutes;
	}
	public void setLoadTimeActualMinutes(Integer loadTimeActualMinutes) {
		this.loadTimeActualMinutes = loadTimeActualMinutes;
	}
	public Integer getTravelTimeActualMinutes() {
		return travelTimeActualMinutes;
	}
	public void setTravelTimeActualMinutes(Integer travelTimeActualMinutes) {
		this.travelTimeActualMinutes = travelTimeActualMinutes;
	}
	public Integer getUnloadQueueTimeActualMinutes() {
		return unloadQueueTimeActualMinutes;
	}
	public void setUnloadQueueTimeActualMinutes(Integer unloadQueueTimeActualMinutes) {
		this.unloadQueueTimeActualMinutes = unloadQueueTimeActualMinutes;
	}
	public Integer getUnloadTimeActualMinutes() {
		return unloadTimeActualMinutes;
	}
	public void setUnloadTimeActualMinutes(Integer unloadTimeActualMinutes) {
		this.unloadTimeActualMinutes = unloadTimeActualMinutes;
	}
	public String getWayBillPdfMediaResource() {
		return wayBillPdfMediaResource;
	}
	public void setWayBillPdfMediaResource(String wayBillPdfMediaResource) {
		this.wayBillPdfMediaResource = wayBillPdfMediaResource;
	}
	public MediaResource getWayBillPdfMediaResourceProperty() {
		return wayBillPdfMediaResourceProperty;
	}
	public void setWayBillPdfMediaResourceProperty(MediaResource wayBillPdfMediaResourceProperty) {
		this.wayBillPdfMediaResourceProperty = wayBillPdfMediaResourceProperty;
	}
	public BigDecimal getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getWeightUom() {
		return weightUom;
	}
	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}
	public BigDecimal getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(BigDecimal totalVolume) {
		this.totalVolume = totalVolume;
	}
	public String getVolumeUom() {
		return volumeUom;
	}
	public void setVolumeUom(String volumeUom) {
		this.volumeUom = volumeUom;
	}
	public Set<TransportationItem> getTransportationItems() {
		return transportationItems;
	}
	public void setTransportationItems(Set<TransportationItem> transportationItems) {
		this.transportationItems = transportationItems;
	}
	public BigDecimal getTotalPriceRub() {
		return totalPriceRub;
	}
	public void setTotalPriceRub(BigDecimal totalPriceRub) {
		this.totalPriceRub = totalPriceRub;
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
	public String getPusherRoomId() {
		return pusherRoomId;
	}
	public void setPusherRoomId(String pusherRoomId) {
		this.pusherRoomId = pusherRoomId;
	}
	public Integer getRatingMark() {
		return ratingMark;
	}
	public void setRatingMark(Integer ratingMark) {
		this.ratingMark = ratingMark;
	}
}
