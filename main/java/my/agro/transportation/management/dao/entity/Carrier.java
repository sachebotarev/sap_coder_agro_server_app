package my.agro.transportation.management.dao.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::Carrier\"")
//@EntityListeners(TransportationListener.class)
public class Carrier extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"carrierKey\"", length = 10)
	private String carrierKey = "";
	@Column(name = "\"name\"", length = 40)
	private String name = "";
	@Column(name = "\"reputationIndex\"")
	private Integer reputationIndex = 0;
	
	@OneToMany(mappedBy = "carrierProperty", fetch = javax.persistence.FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<CarrierRegion> regions;
	
	public Carrier() {
		super();
	}
	public String getCarrierKey() {
		return carrierKey;
	}
	public void setCarrierKey(String carrierKey) {
		this.carrierKey = carrierKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getReputationIndex() {
		return reputationIndex;
	}
	public void setReputationIndex(Integer reputationIndex) {
		this.reputationIndex = reputationIndex;
	}
	public Set<CarrierRegion> getRegions() {
		return regions;
	}
	public void setRegions(Set<CarrierRegion> regions) {
		this.regions = regions;
	}
}
