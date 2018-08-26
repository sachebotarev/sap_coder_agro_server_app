package my.agro.transportation.management.dao.dto;

import java.util.Date;

public class DateLocationInterval {
	private Date dateFrom;
	private Date dateTo;
	private Integer durationMinutes;
	private String locationFrom;
	private String locationTo;
	
	public DateLocationInterval() {
		super();
	}
	public DateLocationInterval(Date from, Date to) {
		super();
		this.dateFrom = from;
		this.dateTo = to;
	}
	public DateLocationInterval(Date from, Integer durationMinutes) {
		super();
		this.dateFrom = from;
		this.durationMinutes = durationMinutes;
	}
	public DateLocationInterval(Date dateFrom, Date dateTo, String locationFrom, String locationTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.locationFrom = locationFrom;
		this.locationTo = locationTo;
	}
	public DateLocationInterval(Date dateFrom, Integer durationMinutes, String locationFrom, String locationTo) {
		super();
		this.dateFrom = dateFrom;
		this.durationMinutes = durationMinutes;
		this.locationFrom = locationFrom;
		this.locationTo = locationTo;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date from) {
		this.dateFrom = from;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date to) {
		this.dateTo = to;
	}
	public String getLocationFrom() {
		return locationFrom;
	}
	public void setLocationFrom(String locationFrom) {
		this.locationFrom = locationFrom;
	}
	public String getLocationTo() {
		return locationTo;
	}
	public void setLocationTo(String locationTo) {
		this.locationTo = locationTo;
	}
	public Integer getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
}
