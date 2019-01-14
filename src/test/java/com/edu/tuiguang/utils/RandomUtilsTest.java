package com.edu.tuiguang.utils;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RandomUtilsTest {

	@Test
	public void getNumCode() {
		StringBuilder stringBuilder = new StringBuilder();
		long ts = System.currentTimeMillis();
		Random random = new Random(ts);

		for (int i = 0; i < 6; i++) {
			int rand = random.nextInt(10);
			stringBuilder.append(rand);
		}
		System.out.println(stringBuilder);
	}
}