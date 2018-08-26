package my.agro.transportation.management.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"AGRO_TRANSPORTATION_MANAGEMENT\".\"my.agro_transportation_management::TransportationMessageLog\"")
@IdClass(TransportationMessageLogPK.class)
public class TransportationMessageLog extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "\"transportation\"", length = 10)
	private String transportation = "";
	@Id
	@Column(name = "\"action\"", length = 30)
	private String action = "";
	@Id
	@Column(name = "\"messageCode\"", length = 30)
	private String messageCode = "";
	@Id
	@Column(name = "\"timestamp\"")
	private Date timestamp;
	@Column(name = "\"messageType\"", length = 10)
	private String messageType = "";
	@Column(name = "\"messageText\"", length = 2048)
	private String messageText = "";
	
	@JoinColumns({
        @JoinColumn(name = "\"transportation\"", referencedColumnName = "\"transportationNum\"", insertable = false, updatable = false)
    })
    @ManyToOne(optional = true, fetch = javax.persistence.FetchType.LAZY)
	private Transportation transportationProperty;
	
	public TransportationMessageLog() {
		super();
	}
	public TransportationMessageLog(String transportation, String action, String messageCode, String messageType,
			String messageText) {
		super();
		this.transportation = transportation;
		this.action = action;
		this.messageCode = messageCode;
		this.messageType = messageType;
		this.messageText = messageText;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Transportation getTransportationProperty() {
		return transportationProperty;
	}
	public void setTransportationProperty(Transportation transporationProperty) {
		this.transportationProperty = transporationProperty;
	}
}
