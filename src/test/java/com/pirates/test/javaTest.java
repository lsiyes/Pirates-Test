package com.pirates.test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class javaTest {

	@Test
	void test() {
		LocalDate date = LocalDate.now();
		DayOfWeek day = date.getDayOfWeek();
		System.out.println(day);
	}

}
