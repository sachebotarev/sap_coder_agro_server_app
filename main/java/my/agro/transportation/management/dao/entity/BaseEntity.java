package my.agro.transportation.management.dao.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@Column(name = "\"uuid\"")
	private String uuid = UUID.randomUUID().toString();
	
	public BaseEntity() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int hashCode() {
		return Objects.hash(uuid);
	}
	
	public boolean equals(Object that) {
		return this == that || that instanceof BaseEntity 
				&& Objects.equals(uuid, ((BaseEntity) that).uuid); 
	}
}
