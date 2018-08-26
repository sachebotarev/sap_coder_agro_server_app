package my.agro.transportation.management.dao.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TransportationAssignmentPK implements Serializable {
	private String transportation;
	private String step;
	private String truck;
	
	public TransportationAssignmentPK(String transportation, String step, String truck) {
		super();
		this.transportation = transportation;
		this.step = step;
		this.truck = truck;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((step == null) ? 0 : step.hashCode());
		result = prime * result + ((transportation == null) ? 0 : transportation.hashCode());
		result = prime * result + ((truck == null) ? 0 : truck.hashCode());
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
		TransportationAssignmentPK other = (TransportationAssignmentPK) obj;
		if (step == null) {
			if (other.step != null)
				return false;
		} else if (!step.equals(other.step))
			return false;
		if (transportation == null) {
			if (other.transportation != null)
				return false;
		} else if (!transportation.equals(other.transportation))
			return false;
		if (truck == null) {
			if (other.truck != null)
				return false;
		} else if (!truck.equals(other.truck))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransportationAssignmentPK [transportation=" + transportation + ", step=" + step + ", truck=" + truck
				+ "]";
	}
}
