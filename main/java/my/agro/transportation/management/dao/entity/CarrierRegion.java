package my.agro.transportation.management.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::CarrierRegion\"")
public class CarrierRegion extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"carrier\"", length = 10)
	private String carrier = "";
	@Id
	@Column(name = "\"region\"", length = 20)
	private String region = "";
	
	@JoinColumns({
        @JoinColumn(name = "\"carrier\"", referencedColumnName = "\"carrierKey\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Carrier carrierProperty;

	public CarrierRegion() {
		super();
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String carrierRegion) {
		this.region = carrierRegion;
	}

	public Carrier getCarrierProperty() {
		return carrierProperty;
	}

	public void setCarrierProperty(Carrier carrierProperty) {
		this.carrierProperty = carrierProperty;
	}
}
