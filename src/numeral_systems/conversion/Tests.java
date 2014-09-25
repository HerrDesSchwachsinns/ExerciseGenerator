package numeral_systems.conversion;

import numeral_systems.numeral.Numeral;

public class Tests {
	public static void main(String[] args) {
		int encBase = 16;
		int decBase = 2;
		Numeral n = new Numeral("f4f6f");
		BaseConversion conv = new HornerConversion(decBase, encBase, n);
		System.out.println(conv);
		conv = new FastConversion(decBase, encBase, n);
		System.out.println(conv);
	}
}
