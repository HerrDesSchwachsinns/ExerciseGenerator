package numeral_systems.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import static numeral_systems.util.DigitUtils.*;

public class DigitUtilsTest {

	@Test
	public void testDigits_for_base() {
		for (int b = 2; b <= 16; ++b)
			testDigits_for_base(b, 1000);
	}
	private void testDigits_for_base(int base, int max) {
		int expDigits = 1;
		for (int x = 0; x <= max; ++x) {
			if (x >= Math.pow(base, expDigits)) ++expDigits;
			//			System.out.println("b:" + base + "\tx:" + x + "\td:" + digits);
			int actDigits = digits_for_base(base, x);
			String message = "digits(" + x + "," + base + "): ";
			assertEquals(message, expDigits, actDigits);
		}
	}

	@Test
	public void testInt2base() {
		final int limit = 1000;
		for (int b = 2; b <= 16; ++b)
			testInt2baseB(b, limit);
	}
	@Test
	public void testBase2int() {
		final int limit = 1000;
		for (int b = 2; b <= 16; ++b)
			testBase2intB(b, limit);
	}
	private void testInt2baseB(final int B, final int limit) {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(0);
		for (int x = 0; x <= limit; ++x) {
			assertArrayEquals(ArrayUtils.unboxIntArray(expected
					.toArray(new Integer[0])), int2base(B, x));
			incrementList(expected, B);
			//			System.out.println(expected + "_" + B);
		}
	}

	private void testBase2intB(final int B, final int limit) {
		ArrayList<Integer> x = new ArrayList<>();
		x.add(0);
		for (int expected = 0; expected <= limit; ++expected) {
			assertEquals(
					"Base: " + B,
					expected,
					base2int(B, ArrayUtils.unboxIntArray(x
							.toArray(new Integer[0]))));
			incrementList(x, B);
			//			System.out.println(expected + "_" + B);
		}
	}
	private void incrementList(ArrayList<Integer> a, int B) {
		a.set(0, a.get(0) + 1);
		int i = 0;
		while (a.get(i) >= B) {
			a.set(i, 0);
			if (i < a.size() - 1) {
				a.set(i + 1, a.get(i + 1) + 1);
			} else {
				a.add(1);
			}
			++i;
		}
	}
}
