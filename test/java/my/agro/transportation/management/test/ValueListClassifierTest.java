package my.agro.transportation.management.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.Assert.assertEquals;

import my.agro.transportation.management.utils.ValueListClasifier;


public class ValueListClassifierTest {
	@Test
	public void TestClassifier() {
		/*Gson gson = new GsonBuilder().setPrettyPrinting()
				.addSerializationExclusionStrategy(new NoModuleExclusionStrategy(false))
				.addDeserializationExclusionStrategy(new NoModuleExclusionStrategy(true)).create();*/

		List<Integer> limits = ValueListClasifier
				.calculateLimits(Arrays.asList(new Integer[] { 10, 11, 12, 13, 14, 15, 16, 17, 80, 90, 100, 150 }), 5);

		assertEquals(ValueListClasifier.classify(limits, 40), 0);
		assertEquals(ValueListClasifier.classify(limits, 70), 1);
		assertEquals(ValueListClasifier.classify(limits, 90), 2);
		assertEquals(ValueListClasifier.classify(limits, 100), 3);
		assertEquals(ValueListClasifier.classify(limits, 130), 4);
		
		//List<Integer> limits2 = ValueListClasifier
		//		.calculateLimits(Arrays.asList(new Integer[] { 10, 11 }), 3);
	}
}
