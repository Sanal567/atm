package com.fsoft.atm.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

	@Test
	void testExpectedException() {

		Assertions.assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("One");
		});
	}

	@Test
	void testExpectedExceptionWithSuperType() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Integer.parseInt("One");
		});
	}

//	@Test
//	void testExpectedExceptionFail() {
//
//		Assertions.assertThrows(IllegalArgumentException.class, () -> {
//			Integer.parseInt("1");
//		});
//	}
}
