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
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::TransportationItem\"")
@IdClass(TransportationItemPK.class)
public class TransportationItem extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"transportation\"", length = 10)
	private String transportation = "";
	@Id
	@Column(name = "\"itemNum\"", length = 5)
	private String itemNum = "";
	@Column(name = "\"material\"")
	private String material = "";
	@Column(name = "\"description\"")
	private String description = "";
	@Column(name = "\"quantity\"", precision = 13, scale = 2)
	private BigDecimal quantity;
	@Column(name = "\"quantityUom\"")
	private String quantityUom = "";
	@Column(name = "\"weight\"", precision = 13, scale = 2)
	private BigDecimal weight;
	@Column(name = "\"weightUom\"")
	private String weightUom = "";
	@Column(name = "\"volume\"", precision = 13, scale = 2)
	private BigDecimal volume;
	@Column(name = "\"volumeUom\"")
	private String volumeUom = "";
	
	@JoinColumns({
        @JoinColumn(name = "\"transportation\"", referencedColumnName = "\"transportationNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	@Expose(serialize = false, deserialize = false)
	private Transportation transportationProperty;
	
	public TransportationItem() {
		super();
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getQuantityUom() {
		return quantityUom;
	}
	public void setQuantityUom(String quantityUom) {
		this.quantityUom = quantityUom;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getWeightUom() {
		return weightUom;
	}
	public void setWeightUom(String weightUom) {
		this.weightUom = weightUom;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public String getVolumeUom() {
		return volumeUom;
	}
	public void setVolumeUom(String volumeUom) {
		this.volumeUom = volumeUom;
	}
	public Transportation getTransportationProperty() {
		return transportationProperty;
	}
	public void setTransportationProperty(Transportation transportationProperty) {
		this.transportationProperty = transportationProperty;
	}
}