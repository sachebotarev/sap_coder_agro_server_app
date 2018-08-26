package my.agro.transportation.management.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransportationItemPK implements Serializable {
	private String transportation;
	private String itemNum;
	
	public TransportationItemPK(String transportation, String itemNum) {
		super();
		this.transportation = transportation;
		this.itemNum = itemNum;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemNum == null) ? 0 : itemNum.hashCode());
		result = prime * result + ((transportation == null) ? 0 : transportation.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransportationItemPK other = (TransportationItemPK) obj;
		if (itemNum == null) {
			if (other.itemNum != null)
				return false;
		} else if (!itemNum.equals(other.itemNum))
			return false;
		if (transportation == null) {
			if (other.transportation != null)
				return false;
		} else if (!transportation.equals(other.transportation))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TransportationItemPK [transportation=" + transportation + ", itemNum=" + itemNum + "]";
	}
}
