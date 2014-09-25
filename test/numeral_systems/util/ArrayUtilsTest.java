package numeral_systems.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import static numeral_systems.util.ArrayUtils.*;

public class ArrayUtilsTest {

	@Test
	public void testCopyOfRange() {
		int[] a = new int[] { 0, 1, 2, 3 };
		for (int from = 0; from <= a.length; ++from) {
			for (int to = 0; to <= a.length; ++to) {
				testCopyOfRange(a, from, to);
			}

		}
		//		assertArrayEquals(new int[] { 0 }, copyOfRange(a, 0, 1));
		//		assertArrayEquals(new int[] { 0, 1 }, copyOfRange(a, 0, 2));
		//		assertArrayEquals(new int[] { 0, 1, 2 }, copyOfRange(a, 0, 3));
		//		assertArrayEquals(new int[] { 0, 1, 2, 3 }, copyOfRange(a, 0, 4));
		//
		//		assertArrayEquals(new int[] { 0 }, copyOfRange(a, 1, 0));
		//		assertArrayEquals(new int[] {}, copyOfRange(a, 1, 1));
		//		assertArrayEquals(new int[] { 1 }, copyOfRange(a, 1, 2));
		//		assertArrayEquals(new int[] { 1, 2 }, copyOfRange(a, 1, 3));
		//		assertArrayEquals(new int[] { 1, 2, 3 }, copyOfRange(a, 1, 4));
		//
		//		assertArrayEquals(new int[] { 1, 0 }, copyOfRange(a, 2, 0));
		//		assertArrayEquals(new int[] { 1 }, copyOfRange(a, 2, 1));
		//		assertArrayEquals(new int[] {}, copyOfRange(a, 2, 2));
		//		assertArrayEquals(new int[] { 2 }, copyOfRange(a, 2, 3));
		//		assertArrayEquals(new int[] { 2, 3 }, copyOfRange(a, 2, 4));
		//
		//		assertArrayEquals(new int[] { 2, 1, 0 }, copyOfRange(a, 3, 0));
		//		assertArrayEquals(new int[] { 2, 1 }, copyOfRange(a, 3, 1));
		//		assertArrayEquals(new int[] { 2 }, copyOfRange(a, 3, 2));
		//		assertArrayEquals(new int[] {}, copyOfRange(a, 3, 3));
		//		assertArrayEquals(new int[] { 3 }, copyOfRange(a, 3, 4));
		//
		//		assertArrayEquals(new int[] { 3, 2, 1, 0 }, copyOfRange(a, 4, 0));
		//		assertArrayEquals(new int[] { 3, 2, 1 }, copyOfRange(a, 4, 1));
		//		assertArrayEquals(new int[] { 3, 2 }, copyOfRange(a, 4, 2));
		//		assertArrayEquals(new int[] { 3 }, copyOfRange(a, 4, 3));
		//		assertArrayEquals(new int[] {}, copyOfRange(a, 4, 4));

	}

	private void testCopyOfRange(int[] a, int from, int to) {
		String message = "from " + from + " to " + to;
		if (from <= to) {
			assertArrayEquals(message, Arrays.copyOfRange(a, from, to),
					copyOfRange(a, from, to));
		} else {
			int[] b = Arrays.copyOfRange(a, to, from);
			reverse(b);
			assertArrayEquals(message, b, copyOfRange(a, from, to));
		}
	}
}
