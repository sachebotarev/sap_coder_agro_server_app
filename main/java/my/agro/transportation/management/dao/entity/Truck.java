package my.agro.transportation.management.dao.entity;

import java.math.BigDecimal;
import java.util.Set;

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

import com.google.gson.annotations.Expose;

import my.agro.transportation.management.dao.listener.TransportationListener;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::Truck\"")
//@EntityListeners(TransportationListener.class)
public class Truck extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_UNKNOWN = "UNKNOWN";
	public static final String STATUS_READY = "READY";
	public static final String STATUS_STOPPED = "STOPPED";
	public static final String STATUS_BUSY = "BUSY";
	
	@Id
	@Column(name = "\"truckNum\"", length = 10)
	private String truckNum = "";
	@Column(name = "\"description\"", length = 40)
	private String description = "";
	@Column(name = "\"status\"", length = 20)
	private String status = STATUS_UNKNOWN;
	@Column(name = "\"driver\"", length = 20)
	private String driver = "";
	@Column(name = "\"driverName\"", length = 50)
	private String driverName = "";
	@Column(name = "\"geoLocation\"", length = 30)
	private String geoLocation = "";
	@Column(name = "\"licensePlateNum\"", length = 20)
	private String licensePlateNum = "";
	@Column(name = "\"carrier\"", length = 10)
	private String carrier = "";
	@Column(name = "\"maxWeight\"", precision = 13, scale = 2)
	private BigDecimal maxWeight = new BigDecimal(0);
	@Column(name = "\"weightUom\"", length = 3)
	private String weightUom = "";
	@Column(name = "\"maxVolume\"", precision = 13, scale = 2)
	private BigDecimal maxVolume = new BigDecimal(0);
	@Column(name = "\"volumeUom\"", length = 3)
	private String volumeUom = "";
	
	@JoinColumns({
        @JoinColumn(name = "\"carrier\"", referencedColumnName = "\"carrierKey\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Carrier carrierProperty;
	
	@OneToMany(mappedBy = "truckProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@Expose(serialize = false, deserialize = false)
	private Set<Transportation> transportations;
	
	public Truck() {
		super();
	}
	
	public String getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(String truckKey) {
		this.truckNum = truckKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	public String getLicensePlateNum() {
		return licensePlateNum;
	}
	public void setLicensePlateNum(String licensePlateNum) {
		this.licensePlateNum = licensePlateNum;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public BigDecimal getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(BigDecimal maxWeight) {
		this.maxWeight = maxWeight;
	}
	public String getWeightUom() {
		return weightUom;
	}
	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}
	public BigDecimal getMaxVolume() {
		return maxVolume;
	}
	public void setMaxVolume(BigDecimal maxVolume) {
		this.maxVolume = maxVolume;
	}
	public String getVolumeUom() {
		return volumeUom;
	}
	public void setVolumeUom(String volumeUom) {
		this.volumeUom = volumeUom;
	}
	public Carrier getCarrierProperty() {
		return carrierProperty;
	}
	public void setCarrierProperty(Carrier carrierProperty) {
		this.carrierProperty = carrierProperty;
	}
	public Set<Transportation> getTransportations() {
		return transportations;
	}
	public void setTransportations(Set<Transportation> transportations) {
		this.transportations = transportations;
	}
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
}
