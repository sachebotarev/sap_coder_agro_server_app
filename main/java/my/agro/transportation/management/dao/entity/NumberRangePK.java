package my.agro.transportation.management.dao.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class NumberRangePK implements Serializable {
	private String numberRangeKey;
	private String numberRangeSubKey;
	
	public NumberRangePK() {
		super();
	}
	
	public NumberRangePK(String numberRangeKey, String numberRangeSubKey) {
		super();
		this.numberRangeKey = numberRangeKey;
		this.numberRangeSubKey = numberRangeSubKey;
	}
	
	public String getNumberRangeKey() {
		return numberRangeKey;
	}
	public void setNumberRangeKey(String numberRangeKey) {
		this.numberRangeKey = numberRangeKey;
	}
	public String getNumberRangeSubKey() {
		return numberRangeSubKey;
	}
	public void setNumberRangeSubKey(String numberRangeSubKey) {
		this.numberRangeSubKey = numberRangeSubKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numberRangeKey == null) ? 0 : numberRangeKey.hashCode());
		result = prime * result + ((numberRangeSubKey == null) ? 0 : numberRangeSubKey.hashCode());
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
		NumberRangePK other = (NumberRangePK) obj;
		if (numberRangeKey == null) {
			if (other.numberRangeKey != null)
				return false;
		} else if (!numberRangeKey.equals(other.numberRangeKey))
			return false;
		if (numberRangeSubKey == null) {
			if (other.numberRangeSubKey != null)
				return false;
		} else if (!numberRangeSubKey.equals(other.numberRangeSubKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NumberRangePK [numberRangeKey=" + numberRangeKey + ", numberRangeSubKey=" + numberRangeSubKey + "]";
	}
}
