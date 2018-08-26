package my.agro.transportation.management.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import my.agro.transportation.management.utils.DateUtils;

public class DateUtilsTest {
	@Test
	public void addMinuteToDateTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
        
		assertEquals(DateUtils.addMinutesToDate(date, 0).toString(), "Sat Jan 01 00:00:00 MSK 2000");
		assertEquals(DateUtils.addMinutesToDate(date, 10).toString(), "Sat Jan 01 00:10:00 MSK 2000");
		assertEquals(DateUtils.addMinutesToDate(date, 24*60).toString(), "Sun Jan 02 00:00:00 MSK 2000");
	}
	
	@Test
	public void dateOfTest() {
		assertEquals(DateUtils.dateOf("2018-01-01 00:00").toString(), "Mon Jan 01 00:00:00 MSK 2018");
		assertEquals(DateUtils.dateOf("2018-01-01 10:10").toString(), "Mon Jan 01 10:10:00 MSK 2018");
		assertEquals(DateUtils.dateOf("2018-01-01 23:59").toString(), "Mon Jan 01 23:59:00 MSK 2018");
	}
	
}
