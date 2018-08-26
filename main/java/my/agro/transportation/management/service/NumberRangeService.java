package my.agro.transportation.management.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.agro.transportation.management.dao.entity.NumberRange;
import my.agro.transportation.management.dao.entity.NumberRangePK;
import my.agro.transportation.management.dao.repository.NumberRangeRepository;

@Service
public class NumberRangeService {
	@Autowired
	private NumberRangeRepository numberRangeRepository;
	
	private static Map<NumberRangePK, NumberRange> numberRanges = new HashMap<NumberRangePK, NumberRange>();  
	
	@PostConstruct
	public void onPostConstruct() {
		numberRangeRepository.findAll().stream().forEach(i -> numberRanges.put(i.getNumberRangePK(), i));
	}
	
	public static synchronized String getNextNumber(String numberRangeKey, String numberRangeSubKey) {
		NumberRange numberRange = numberRanges.get(new NumberRangePK(numberRangeKey, numberRangeSubKey)); 
		if (numberRange == null) {
			throw new RuntimeException("NumberRange '" + numberRangeKey + "' / '" + numberRangeSubKey + "'" + " not found");
		}
		String newLastNumber = incrementString(numberRange.getLastNumber());
		numberRange.setLastNumber( newLastNumber );
		return newLastNumber;
	}
	
	private static String incrementString(String str) {
		int lastDigit = Character.getNumericValue(str.charAt(str.length() - 1));
		if (lastDigit < 9) {
			lastDigit++;
			return str.substring(0, str.length() - 1) + Character.forDigit(lastDigit, 10);
		} else {
			return incrementString(str.substring(0, str.length() - 1)) + '0';
		}
	}

	public void setNumberRanges(Map<NumberRangePK, NumberRange> numberRanges) {
		this.numberRanges = numberRanges;
	} 
}
