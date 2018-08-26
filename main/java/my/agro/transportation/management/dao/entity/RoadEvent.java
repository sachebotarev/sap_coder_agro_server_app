package my.agro.transportation.management.dao.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::RoadEvent\"")
//@EntityListeners(TransportationListener.class)
public class RoadEvent extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_DRAFT = 					"DRAFT";
	public static final String STATUS_CREATED = 				"CREATED";
	public static final String STATUS_CONFIRMED = 				"CONFIRMED";
	public static final String STATUS_REJECTED = 				"REJECTED";
	public static final String STATUS_HISTORICAL = 				"HISTORICAL";
	
	@Id
	@Column(name = "\"roadEventUuid\"")
	private String roadEventUuid = "";
	@Column(name = "\"status\"")
	private String status = STATUS_DRAFT;
	@Column(name = "\"shippingLocation\"")
	private String shippingLocation;
	@Column(name = "\"transportation\"")
	private String transportation;
	@Column(name = "\"truck\"")
	private String truck;
	@Column(name = "\"driver\"")
	private String driver;
	@Column(name = "\"geoLocation\"")
	private String geoLocation;
	@Column(name = "\"eventDateTime\"")
	private Date eventDateTime;
	@Column(name = "\"eventType\"")
	private String eventType;
	@Column(name = "\"description\"")
	private String description;
	@Column(name = "\"mimeType\"")
	private String mimeType;
	@Column(name = "\"sourceURI\"")
	private String sourceURI;
	@Column(name = "\"mediaResource\"")
	private String mediaResource;
	
	@JoinColumns({
        @JoinColumn(name = "\"shippingLocation\"", referencedColumnName = "\"shippingLocationKey\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private ShippingLocation shippingLocationProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"transportation\"", referencedColumnName = "\"transportationNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Transportation transportationProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"truck\"", referencedColumnName = "\"truckNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Truck truckProperty;
	
	@JoinColumns({
        @JoinColumn(name = "\"mediaResource\"", referencedColumnName = "\"mediaResourceUuid\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private MediaResource mediaResourceProperty;
	
	public RoadEvent() {
		super();
	}
	public String getRoadEventUuid() {
		return roadEventUuid;
	}
	public void setRoadEventUuid(String roadEventUuid) {
		this.roadEventUuid = roadEventUuid;
	}
	public String getShippingLocation() {
		return shippingLocation;
	}
	public void setShippingLocation(String shippingLocation) {
		this.shippingLocation = shippingLocation;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getSourceURI() {
		return sourceURI;
	}
	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}
	public ShippingLocation getShippingLocationProperty() {
		return shippingLocationProperty;
	}
	public void setShippingLocationProperty(ShippingLocation shippingLocationProperty) {
		this.shippingLocationProperty = shippingLocationProperty;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public Transportation getTransportationProperty() {
		return transportationProperty;
	}
	public void setTransportationProperty(Transportation transportationProperty) {
		this.transportationProperty = transportationProperty;
	}
	public String getTruck() {
		return truck;
	}
	public void setTruck(String truck) {
		this.truck = truck;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Truck getTruckProperty() {
		return truckProperty;
	}
	public void setTruckProperty(Truck truckProperty) {
		this.truckProperty = truckProperty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMediaResource() {
		return mediaResource;
	}
	public void setMediaResource(String mediaResource) {
		this.mediaResource = mediaResource;
	}
	public MediaResource getMediaResourceProperty() {
		return mediaResourceProperty;
	}
	public void setMediaResourceProperty(MediaResource mediaResourceProperty) {
		this.mediaResourceProperty = mediaResourceProperty;
	}
	
}
