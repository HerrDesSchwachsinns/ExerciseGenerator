package conversion;

import numeral.Numeral;

public class Tests {
	public static void main(String[] args) {
		testConvBigger();
		testConvSmaller();
	}
	public static void testConvBigger() {
		FastConversion conv = new FastConversion(16, 2, new Numeral(
				"10111"));
		System.out.println(conv);
		System.out.println(conv.baseRatio());
	}
	public static void testConvSmaller() {
		FastConversion conv = new FastConversion(4, 16, new Numeral(
				"32.123456789abcdef"));
		System.out.println(conv);
		System.out.println(conv.baseRatio());
	}
}
