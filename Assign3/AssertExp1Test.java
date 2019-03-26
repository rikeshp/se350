package homework3;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AssertExp1Test {
	
	@Test
	void testMinValue() {
		try {
			AssertExp1.minValue(null);
			fail();
		} catch(AssertionError e) {};

		try {
			AssertExp1.minValue(new double[] {});
			fail();
		} catch(AssertionError e) {};
		assertEquals(-7, AssertExp1.minValue(new double[] {-7}));
		assertEquals(-7, AssertExp1.minValue(new double[] {1, -4,-7, 7, 8, 11}));
		assertEquals(-13, AssertExp1.minValue(new double[] {-13, -4, -7, 7, 8, 11}));
		assertEquals(-9, AssertExp1.minValue(new double[] {1, -4, -7, 7, 8, 11, -9}));
	}
	
	@Test
	void testMinPosition() {
		try {
			AssertExp1.minPosition(null);
			fail();
		} catch(AssertionError e) {};

		try {
			AssertExp1.minPosition(new double[] {});
			fail();
		} catch(AssertionError e) {};
		assertEquals(0, AssertExp1.minPosition(new double[] {-7}));
		assertEquals(2, AssertExp1.minPosition(new double[] {1, -4, -7, 7, 8, 11}));
		assertEquals(0, AssertExp1.minPosition(new double[] {-13, -4, -7, 7, 8, 11}));
		assertEquals(6, AssertExp1.minPosition(new double[] {1, -4, -7, 7, 8, 11, -9}));
	}
	
	@Test
	void testNumUnique() {
		try {
			AssertExp1.numUnique(null);
			fail();
		} catch(AssertionError e) {};

		assertEquals(0, AssertExp1.numUnique(new double[] {}));
		assertEquals(1, AssertExp1.numUnique(new double[] {11}));
		assertEquals(1, AssertExp1.numUnique(new double[] {11, 11, 11, 11}));
		assertEquals(8, AssertExp1.numUnique(new double[] {11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88}));
		assertEquals(8, AssertExp1.numUnique(new double[] {11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88}));
		
	}
	
	@Test
	void removeDuplicates() {
		try {
			AssertExp1.removeDuplicates(null);
			fail();
		} catch(AssertionError e) {};
		
		assertArrayEquals(new double[] {}, AssertExp1.removeDuplicates(new double[] {}));
		assertArrayEquals(new double[] {11}, AssertExp1.removeDuplicates(new double[] {11}));
		assertArrayEquals(new double[] {11}, AssertExp1.removeDuplicates(new double[] {11, 11, 11, 11}));
		assertArrayEquals(new double[] {11, 22, 33, 44, 55, 66, 77, 88}, AssertExp1.removeDuplicates(new double[] {11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88}));
		assertArrayEquals(new double[] {11, 22, 33, 44, 55, 66, 77, 88}, AssertExp1.removeDuplicates(new double[] {11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88}));
		
	}

}
