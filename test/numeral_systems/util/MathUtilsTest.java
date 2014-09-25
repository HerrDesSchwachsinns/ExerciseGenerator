package numeral_systems.util;

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
		assertEquals(true, MathUtils.isPowerOf(2, 2));
		assertEquals(false, MathUtils.isPowerOf(3, 2));
		assertEquals(true, MathUtils.isPowerOf(4, 2));
		assertEquals(false, MathUtils.isPowerOf(5, 2));
		assertEquals(false, MathUtils.isPowerOf(6, 2));
		assertEquals(false, MathUtils.isPowerOf(7, 2));
		assertEquals(true, MathUtils.isPowerOf(8, 2));
		assertEquals(false, MathUtils.isPowerOf(9, 2));
		assertEquals(false, MathUtils.isPowerOf(10, 2));
		assertEquals(false, MathUtils.isPowerOf(11, 2));
		assertEquals(false, MathUtils.isPowerOf(12, 2));
		assertEquals(false, MathUtils.isPowerOf(13, 2));
		assertEquals(false, MathUtils.isPowerOf(14, 2));
		assertEquals(false, MathUtils.isPowerOf(15, 2));
		assertEquals(true, MathUtils.isPowerOf(16, 2));
	}
}
