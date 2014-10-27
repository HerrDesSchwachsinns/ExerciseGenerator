package numeral_systems.conversion;

import numeral_systems.numeral.Numeral;

public class Tests {
	public static void main(String[] args) {
		Numeral n = new Numeral("99.9");
		n.add(new Numeral("999.99"), 10);
		System.out.println(n);
	}
}
