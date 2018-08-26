package my.agro.transportation.management.dao.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::MediaResource\"")
//@EntityListeners(TransportationListener.class)
public class MediaResource extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"mediaResourceUuid\"")
	private String mediaResourceUuid = "";
	@Column(name = "\"mimeType\"")
	private String mimeType;
	@Column(name = "\"sourceURI\"")
	private String sourceURI;
	
	public MediaResource() {
		super();
	}
	public String getMediaResourceUuid() {
		return mediaResourceUuid;
	}
	public void setMediaResourceUuid(String mediaResourceUuid) {
		this.mediaResourceUuid = mediaResourceUuid;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getSourceURI() {
		return sourceURI;
	}
	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}
}
