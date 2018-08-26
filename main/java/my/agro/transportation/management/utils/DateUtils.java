package my.agro.transportation.management.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	static final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	public static Date addMinutesToDate(final Date date, int minutesCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutesCount);
		return calendar.getTime();
	}

	public static int minutesBetween(Date begin, Date end) {
		return (int) TimeUnit.MILLISECONDS.toMinutes(end.getTime() - begin.getTime());
	}

	public static Date dateOf(String dateString) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			throw new MyRuntimeException("Failed to parse date string '" + dateString + "'", e);
		}
	}

}
