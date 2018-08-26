package my.agro.transportation.management.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import my.agro.transportation.management.dao.entity.NumberRange;
import my.agro.transportation.management.dao.entity.NumberRangePK;
import my.agro.transportation.management.service.NumberRangeService;



public class NumberRangeServiceTest {
	NumberRangeService numberRangeService = new NumberRangeService();
	
	public NumberRangeServiceTest() {
		Map<NumberRangePK, NumberRange> numberRanges = new HashMap<NumberRangePK, NumberRange>();  
		numberRanges.put(new NumberRangePK("AA", ""), new NumberRange("AA", "", "AA0000", "AA9999", "AA0000"));
		numberRanges.put(new NumberRangePK("BB", ""), new NumberRange("BB", "", "BB0000", "BB9999", "BB0999"));
		numberRanges.put(new NumberRangePK("BB", "XX"), new NumberRange("BB", "XX", "BBXX00", "BBXX99", "BBXX09"));
		
		numberRangeService.setNumberRanges(numberRanges);
	}
	
	@Test
	public void getNextNumberTest() {

		
		assertEquals(numberRangeService.getNextNumber("AA", ""), "AA0001");
		assertEquals(numberRangeService.getNextNumber("AA", ""), "AA0002");
		assertEquals(numberRangeService.getNextNumber("AA", ""), "AA0003");
		
		assertEquals(numberRangeService.getNextNumber("BB", ""), "BB1000");
		assertEquals(numberRangeService.getNextNumber("BB", ""), "BB1001");
		assertEquals(numberRangeService.getNextNumber("BB", ""), "BB1002");
		
		
		assertEquals(numberRangeService.getNextNumber("BB", "XX"), "BBXX10");
		assertEquals(numberRangeService.getNextNumber("BB", "XX"), "BBXX11");
		assertEquals(numberRangeService.getNextNumber("BB", "XX"), "BBXX12");
	}
	
	@Test(expected = RuntimeException.class)
	public void getNextNumberExceptionTest() {
		numberRangeService.getNextNumber("ZZ", "");
	}
}
