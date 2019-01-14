package com.edu.tuiguang.utils;

import java.util.Random;

public class RandomUtils {
	public static String getNumCode(int num) {
		StringBuilder stringBuilder = new StringBuilder();
		long ts = System.currentTimeMillis();
		Random random = new Random(ts);

		for (int i = 0; i < 6; i++) {
			int rand = random.nextInt(10);
			stringBuilder.append(rand);
		}
		return stringBuilder.toString();
	}
}
