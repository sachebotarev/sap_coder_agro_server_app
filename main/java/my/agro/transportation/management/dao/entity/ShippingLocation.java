package my.agro.transportation.management.dao.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::ShippingLocation\"")
public class ShippingLocation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_PRODUCING = "PRODUCING";
	public static final String TYPE_STORAGE = "STORAGE";
	
	@Id
	@Column(name = "\"shippingLocationKey\"", length = 20)
	private String shippingLocationKey = "";
	@Column(name = "\"description\"", length = 40)
	private String description = "";
	@Column(name = "\"geoLocation\"", length = 30)
	private String geoLocation = "";
	@Column(name = "\"locationType\"", length = 30)
	private String locationType = "";
	@Column(name = "\"region\"", length = 20)
	private String region = "";
	
	@Column(name = "\"regionName\"")
	private String regionName = "";
	@Column(name = "\"city\"")
	private String city = "";
	@Column(name = "\"street\"")
	private String street = "";
	@Column(name = "\"addressLine\"")
	private String addressLine = "";
	
	@OneToMany(mappedBy = "shipToProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@Expose(serialize = false, deserialize = false)
	private Set<Transportation> shipToTransportations;
	@OneToMany(mappedBy = "shipFromProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@Expose(serialize = false, deserialize = false)
	private Set<Transportation> shipFromTransportations;
	@OneToMany(mappedBy = "shippingLocationProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@Expose(serialize = false, deserialize = false)
	private Set<RoadEvent> roadEvents;
		
	public ShippingLocation() {
		super();
	}
	
	public ShippingLocation(String shippingLocationKey, String description, String geoLocation, String locationType) {
		super();
		this.shippingLocationKey = shippingLocationKey;
		this.description = description;
		this.geoLocation = geoLocation;
		this.locationType = locationType;
	}

	public String getShippingLocationKey() {
		return shippingLocationKey;
	}
	public void setShippingLocationKey(String shippingLocationKey) {
		this.shippingLocationKey = shippingLocationKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public Set<Transportation> getShipToTransportations() {
		return shipToTransportations;
	}

	public void setShipToTransportations(Set<Transportation> shipToTransportations) {
		this.shipToTransportations = shipToTransportations;
	}

	public Set<Transportation> getShipFromTransportations() {
		return shipFromTransportations;
	}

	public void setShipFromTransportations(Set<Transportation> shipFromTransportations) {
		this.shipFromTransportations = shipFromTransportations;
	}

	public Set<RoadEvent> getRoadEvents() {
		return roadEvents;
	}

	public void setRoadEvents(Set<RoadEvent> roadEvents) {
		this.roadEvents = roadEvents;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
}
