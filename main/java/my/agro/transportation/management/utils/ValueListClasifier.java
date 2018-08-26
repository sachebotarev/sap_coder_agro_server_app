package my.agro.transportation.management.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// https://www.programcreek.com/java-api-examples/index.php?source_dir=openjump-core-rels-master/src/org/openjump/core/attributeoperations/Classifier1D.java
public class ValueListClasifier {
	public static int classify(int value, int limit1, int limit2, int limit3, int limit4) {
		if (value <= limit1) {
			return 1;
		} else if (value <= limit2) {
			return 2;
		} else if (value <= limit3) {
			return 3;
		} else if (value <= limit4) {
			return 4;
		} else {
			return 5;
		}
	}
	public static int classify(List<Integer> limits, Integer value) {
		int classNumber = 0;;
		for (Integer limit: limits) {
			if (value.compareTo(limit) <= 0) {
				return classNumber; 
			} 
			classNumber++;
		}
		return classNumber;
	}

	public static List<Integer> calculateLimits(List<Integer> data, int numberClasses) {
		return Arrays
				.stream(classifyNaturalBreaks(data.stream().mapToDouble(Integer::doubleValue).toArray(), numberClasses))
				.boxed().map((val) -> Integer.valueOf(val.intValue())).collect(Collectors.toList());
	}

	public static double[] classifyNaturalBreaks(double[] data, int numberClasses) {
		double[] limits = new double[numberClasses - 1];

		if (limits.length == 0)
			return limits;

		// double[] orderedItems = DoubleArray.sort(data);
		double[] orderedItems = data.clone();
		Arrays.sort(orderedItems);

		int numData = data.length;
		if (numData == 0)
			return limits;

		double[][] mat1 = new double[numData + 1][numberClasses + 1];
		double[][] mat2 = new double[numData + 1][numberClasses + 1];

		for (int i = 1; i <= numberClasses; i++) {
			mat1[1][i] = 1;
			mat2[1][i] = 0;
			for (int j = 2; j <= numData; j++)
				mat2[j][i] = Double.MAX_VALUE;
		}
		double v = 0;

		for (int l = 2; l <= numData; l++) {
			double s1 = 0;
			double s2 = 0;
			double w = 0;
			for (int m = 1; m <= l; m++) {
				int i3 = l - m + 1;
				double val = orderedItems[i3 - 1];

				s2 += val * val;
				s1 += val;

				w++;
				v = s2 - (s1 * s1) / w;
				int i4 = i3 - 1;
				if (i4 != 0) {
					for (int j = 2; j <= numberClasses; j++) {
						if (mat2[l][j] >= (v + mat2[i4][j - 1])) {
							mat1[l][j] = i3;
							mat2[l][j] = v + mat2[i4][j - 1];
						}
						;
					}
					;
				}
				;
			}
			;
			mat1[l][1] = 1;
			mat2[l][1] = v;
		}
		;
		int k = numData;

		for (int j = numberClasses; j >= 2; j--) {
			int id = (int) (mat1[k][j]) - 2;
			// -- [sstein] modified version from Hisaji,
			// otherwise breaks will be "on" one item
			// limits[j - 2] = orderedItems[id];
			// -- new
			double limit = 0.5 * (orderedItems[id] + orderedItems[id + 1]);
			limits[j - 2] = limit;

			k = (int) mat1[k][j] - 1;
		}
		;

		return limits;
	}
}
