package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {
	private final static int	MAX_BASE	= 300;
	@Test
	public void testPowZero() {
		for (int b = 1; b <= MAX_BASE; ++b)
			assertEquals(1, MathUtils.pow(b, 0));
	}
	@Test
	public void testPowOne() {
		for (int b = 1; b <= MAX_BASE; ++b)
			assertEquals(b, MathUtils.pow(b, 1));
	}
	@Test
	public void testPowTwo() {
		for (int b = 1; b <= MAX_BASE; ++b) {
			assertEquals(b * b, MathUtils.pow(b, 2));
		}
	}
	@Test
	public void testPowerThree() {
		for (int b = 1; b <= MAX_BASE; ++b) {
			assertEquals(b * b * b, MathUtils.pow(b, 3));
		}
	}
	@Test
	public void testisExponentiationOf2() {
		assertEquals(true, MathUtils.isExponentiationOf(2, 2));
		assertEquals(false, MathUtils.isExponentiationOf(3, 2));
		assertEquals(true, MathUtils.isExponentiationOf(4, 2));
		assertEquals(false, MathUtils.isExponentiationOf(5, 2));
		assertEquals(false, MathUtils.isExponentiationOf(6, 2));
		assertEquals(false, MathUtils.isExponentiationOf(7, 2));
		assertEquals(true, MathUtils.isExponentiationOf(8, 2));
		assertEquals(false, MathUtils.isExponentiationOf(9, 2));
		assertEquals(false, MathUtils.isExponentiationOf(10, 2));
		assertEquals(false, MathUtils.isExponentiationOf(11, 2));
		assertEquals(false, MathUtils.isExponentiationOf(12, 2));
		assertEquals(false, MathUtils.isExponentiationOf(13, 2));
		assertEquals(false, MathUtils.isExponentiationOf(14, 2));
		assertEquals(false, MathUtils.isExponentiationOf(15, 2));
		assertEquals(true, MathUtils.isExponentiationOf(16, 2));
	}
}
