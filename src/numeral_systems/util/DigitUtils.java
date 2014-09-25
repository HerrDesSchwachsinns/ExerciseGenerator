package numeral_systems.util;

import static numeral_systems.util.MathUtils.*;
import static java.lang.Math.log;

import java.util.HashMap;

public class DigitUtils {
	/**
	 * 
	 * @param x
	 *            value, shall be non-negative
	 * @param b
	 *            base, shall be valid
	 * @return amount of digits one need to represent x in base b
	 */
	public static int digits_for_base(int b, int x) {
		if (x == 0) return 1;
		int d = (int) (log(x) / log(b)) + 1;//d=floor(log_{base}(x)) + 1
		return pow(b, d) > x ? d : d + 1;//inverse calculation check and adjusting
	}
	/**
	 * converts a int value to specified base representation
	 * 
	 * @param B
	 *            base, shall be valid
	 * @param x
	 *            value
	 * @return x in base B representation
	 */
	public static int[] int2base(final int B, int x) {
		final int N = digits_for_base(B, x);

		int[] y = new int[N];
		for (int i = 0; i < N; ++i) {
			y[i] = x % B;
			x = x / B;
		}
		return y;
	}
	/**
	 * converts a base representation to an int value
	 * 
	 * @param B
	 *            base, shall be valid
	 * @param x
	 *            representation in base B, shall be representable by an int
	 *            value
	 * @return (x)_base as int value
	 */
	public static int base2int(final int B, int[] x) {
		int y = 0;
		for (int i = 0; i < x.length; ++i) {
			y += x[i] * pow(B, i);
		}
		return y;
	}

	/**
	 * convert from base B1 to base B2
	 * 
	 * <p>
	 * This method is implemented in terms of base2int and int2base. So it is
	 * limited by capacity of the int type. It should only be used for values
	 * representable by int.
	 * </p>
	 * 
	 * @param B1
	 *            input base, shall be valid
	 * @param B2
	 *            output base, shall be valid
	 * @param x
	 *            representation in base B1
	 * @return x as B1 converted to B2
	 */
	public static int[] base2base(final int B1, final int B2, int[] x) {
		return int2base(B2, base2int(B1, x));
	}
	/***********************
	 * digit char mappings *
	 ***********************/

	/**
	 * c2d (char to digit) returns the mapping from a specific
	 * char(representation) to its value(digit)
	 * <p>
	 * the following condition holds <code>d2c(c2d(c)) = c</code>
	 * </p>
	 * 
	 * @param c
	 *            a character
	 * @return digit mapped to c
	 * @throws IllegalArgumentException
	 *             if there is no mapping
	 */
	public static int c2d(char c) {
		Integer x = char2intMap.get(Character.toLowerCase(c));
		if (x == null) throw new IllegalArgumentException(c + " has no mapping");
		return x;
	}
	/**
	 * d2c (digit to char) returns the mapping from a specific digit(value) to
	 * its representation(char)
	 * <p>
	 * the following condition holds <code>c2d(d2c(d)) = d</code>
	 * </p>
	 * 
	 * @param x
	 *            a digit
	 * 
	 * @return character mapped to x
	 * @throws IllegalArgumentException
	 *             if there is no mapping
	 */
	public static char d2c(int x) {
		Character c = int2charMap.get(x);
		if (c == null) throw new IllegalArgumentException(x + " has no mapping");
		return c;
	}
	/**
	 * minimal supported base
	 */
	public static final int	MIN_BASE;
	/**
	 * maximal supported base
	 */
	public static final int	MAX_BASE;

	/**
	 * 
	 * @param B
	 *            a base
	 * @return true if base is valid false otherwise
	 */
	public static boolean isValidBase(final int B) {
		return B >= MIN_BASE && B <= MAX_BASE;
	}

	private static HashMap<Character, Integer>	char2intMap	= new HashMap<>();
	private static HashMap<Integer, Character>	int2charMap	= new HashMap<>();
	private static void insertPair(char c, int x) {
		char2intMap.put(c, x);
		int2charMap.put(x, c);
	}
	static {
		MIN_BASE = 2;
		MAX_BASE = 16;
		insertPair('0', 0);
		insertPair('1', 1);
		insertPair('2', 2);
		insertPair('3', 3);
		insertPair('4', 4);
		insertPair('5', 5);
		insertPair('6', 6);
		insertPair('7', 7);
		insertPair('8', 8);
		insertPair('9', 9);
		insertPair('a', 10);
		insertPair('b', 11);
		insertPair('c', 12);
		insertPair('d', 13);
		insertPair('e', 14);
		insertPair('f', 15);
	}
}
