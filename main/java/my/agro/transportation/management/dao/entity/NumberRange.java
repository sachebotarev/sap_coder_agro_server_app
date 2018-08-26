package my.agro.transportation.management.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::NumberRange\"")
@IdClass(NumberRangePK.class)
public class NumberRange {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"numberRangeKey\"", length = 30)
	private String numberRangeKey = "";
	@Id
	@Column(name = "\"numberRangeSubKey\"", length = 50)
	private String numberRangeSubKey = "";
	@Column(name = "\"startNumber\"", length = 30)
	private String startNumber = "";
	@Column(name = "\"endNumber\"", length = 30)
	private String endNumber = "";
	@Column(name = "\"lastNumber\"", length = 30)
	private String lastNumber = "";
	
	public NumberRange() {
		super();
	}
	
	public NumberRange(String numberRangeKey, String numberRangeSubKey) {
		super();
		this.numberRangeKey = numberRangeKey;
		this.numberRangeSubKey = numberRangeSubKey;
	}

	public NumberRange(String numberRangeKey, String numberRangeSubKey, String startNumber, String endNumber,
			String lastNumber) {
		super();
		this.numberRangeKey = numberRangeKey;
		this.numberRangeSubKey = numberRangeSubKey;
		this.startNumber = startNumber;
		this.endNumber = endNumber;
		this.lastNumber = lastNumber;
	}

	public NumberRangePK getNumberRangePK() {
		return new NumberRangePK(numberRangeKey, numberRangeSubKey);
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
	public String getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}
	public String getEndNumber() {
		return endNumber;
	}
	public void setEndNumber(String endNumber) {
		this.endNumber = endNumber;
	}
	public String getLastNumber() {
		return lastNumber;
	}
	public void setLastNumber(String lastNumber) {
		this.lastNumber = lastNumber;
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
		NumberRange other = (NumberRange) obj;
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
		return "NumberRange [numberRangeKey=" + numberRangeKey + ", numberRangeSubKey=" + numberRangeSubKey + "]";
	}
}
