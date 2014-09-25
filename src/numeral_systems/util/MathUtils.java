package numeral_systems.util;

import java.util.Random;

import static java.lang.Math.log;

public class MathUtils {
	/**
	 * non-negative integer (natural) power function defined for non-negative
	 * numbers x, and e not defined for x and e both 0
	 * 
	 * @param x
	 *            the base, shall be non-negative
	 * @param e
	 *            the exponent, shall be non-negative
	 * @return {@code x}<sup>{@code e}</sup>
	 */
	public static int pow(int x, int e) {
		int res = 1;
		for (int i = 0; i < e; ++i) {
			res *= x;
		}
		return res;
	}
	/**
	 * logarithm function using natural logarithm for calculation
	 * 
	 * @param b
	 *            logarithm base
	 * @param x
	 *            value
	 * @return log_b(x)
	 */
	public static double logb(int b, double x) {
		return log(x) / log(b);
	}
	/**
	 * y=base^e with e in integer
	 * 
	 * @param y
	 * @param x
	 * @return true if y is a exponentiation of x false otherwise
	 */
	public static boolean isExponentiationOf(int y, int x) {
		int e = (int) (logb(x, y));
		return pow(x, e) == y;
	}
	/**
	 * 
	 * @param min
	 * @param max
	 * @param r
	 * @return int value between min and max inclusive
	 */
	public static int random(int min, int max, Random r) {
		return r.nextInt(max - min + 1) + min;
	}
	public static int random(int min, int max) {
		return random(min, max, new Random());
	}
}
