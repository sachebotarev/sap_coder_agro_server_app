package my.agro.transportation.management.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javafx.util.Pair;
import my.agro.transportation.management.dao.dto.DateLocationInterval;
import my.agro.transportation.management.utils.DateUtils;

@Service
public class SchedulingService {
	@Autowired
	GeoServices geoServices;

	/*@SuppressWarnings("restriction")
	public Date findFirstFreeInterval(List<DateLocationInterval> intervals, Date from, Integer durationMinutes) {
		return intervals.stream().filter(interval -> interval.getDateTo().compareTo(from) > 0)
				.sorted((interval1, interval2) -> interval1.getDateFrom().compareTo(interval2.getDateFrom()))
				.map(interval -> new Pair<>(interval, false))
				.reduce(new Pair<>(new DateLocationInterval(from, from), false),
						(previousIntervalPair, intervalPair) -> {
							if (previousIntervalPair.getValue()) { // if value is true then we found interval after
																	// which we
																	// have enough time
								return previousIntervalPair;
							}
							if (DateUtils.minutesBetween(previousIntervalPair.getKey().getDateTo(),
									intervalPair.getKey().getDateFrom()) >= durationMinutes) {
								return new Pair<>(previousIntervalPair.getKey(), true);
							} else {
								return new Pair<>(intervalPair.getKey(), false);
							}
						})
				.getKey().getDateTo();
	}*/

	public DateLocationInterval findFirstIntervalToAddAfter(List<DateLocationInterval> intervals,
			DateLocationInterval newInterval) {
		return findFirstIntervalToAddAfter(intervals, newInterval, null);
	}

	public DateLocationInterval findFirstIntervalToAddAfter(List<DateLocationInterval> intervals,
			DateLocationInterval newInterval, String lastKnownLocation) {
		String locationAtTheNewIntervalStart = null;
		if (lastKnownLocation != null) {
			locationAtTheNewIntervalStart = getLocationByDateTime(intervals, newInterval.getDateFrom(),
					lastKnownLocation);
		}
		return intervals.stream().filter(interval -> interval.getDateTo().compareTo(newInterval.getDateFrom()) > 0)
				.sorted((interval1, interval2) -> interval1.getDateFrom().compareTo(interval2.getDateFrom()))
				.map(interval -> new Pair<>(interval, false))
				.reduce(new Pair<>(new DateLocationInterval(newInterval.getDateFrom(), newInterval.getDateFrom(),
						locationAtTheNewIntervalStart, locationAtTheNewIntervalStart), false),
						(previousIntervalPair, intervalPair) -> {
							if (previousIntervalPair.getValue1()) {
								return previousIntervalPair;
							}
							int timeSlotMinutes = DateUtils.minutesBetween(previousIntervalPair.getValue0().getDateTo(),
									intervalPair.getValue0().getDateFrom());
							int arrival1Time = 0, arrival2Time = 0;
							if (lastKnownLocation != null) {
								arrival1Time = geoServices.getTravelTimeMinutes(
										previousIntervalPair.getValue0().getLocationTo(),
										newInterval.getLocationFrom());
								arrival2Time = geoServices.getTravelTimeMinutes(newInterval.getLocationTo(),
										intervalPair.getValue0().getLocationFrom());
							}
							if (timeSlotMinutes >= (arrival1Time + newInterval.getDurationMinutes() + arrival2Time)) {
								return new Pair<>(previousIntervalPair.getValue0(), true);
							} else {
								return new Pair<>(intervalPair.getValue0(), false);
							}
						})
				.getValue0();
	}

	public String getLocationByDateTime(List<DateLocationInterval> intervals, Date dateTime, String lastKnownLocation) {
		return getIntervalByDateTime(intervals, dateTime)
				.orElse(getPreviousIntervalByDateTime(intervals, dateTime)
						.orElse(new DateLocationInterval(dateTime, dateTime, lastKnownLocation, lastKnownLocation)))
				.getLocationTo();
	}

	public Optional<DateLocationInterval> getIntervalByDateTime(List<DateLocationInterval> intervals, Date dateTime) {
		return intervals.stream().filter(interval -> interval.getDateFrom().compareTo(dateTime) <= 0
				&& interval.getDateTo().compareTo(dateTime) >= 0).findFirst();
	}

	public Optional<DateLocationInterval> getPreviousIntervalByDateTime(List<DateLocationInterval> intervals,
			Date dateTime) {
		return intervals.stream().filter(interval -> interval.getDateTo().compareTo(dateTime) <= 0)
				.sorted((interval1, interval2) -> interval2.getDateTo().compareTo(interval1.getDateTo())).findFirst();
	}

	public GeoServices getGeoServices() {
		return geoServices;
	}

	public void setGeoServices(GeoServices geoServices) {
		this.geoServices = geoServices;
	}
}
