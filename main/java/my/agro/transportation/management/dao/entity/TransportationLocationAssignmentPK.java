package my.agro.transportation.management.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransportationLocationAssignmentPK implements Serializable {
	private String transportation;
	private String shipToLocation;
	
	public TransportationLocationAssignmentPK() {
		super();
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
	public void setShipToLocation(String location) {
		this.shipToLocation = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shipToLocation == null) ? 0 : shipToLocation.hashCode());
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
		TransportationLocationAssignmentPK other = (TransportationLocationAssignmentPK) obj;
		if (shipToLocation == null) {
			if (other.shipToLocation != null)
				return false;
		} else if (!shipToLocation.equals(other.shipToLocation))
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
		return "TransportationLocationAssignmentPK [transportation=" + transportation + ", shipToLocation=" + shipToLocation + "]";
	}
}
