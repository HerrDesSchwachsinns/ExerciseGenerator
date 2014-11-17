package numeral_systems;

import numeral_systems.numeral.Numeral;

public class NonUnitTest {
	public static void main(String[] args) {
		System.out.println(new Numeral("12.345").shift(-3));
		System.out.println(new Numeral("12.345").shift(-2));
		System.out.println(new Numeral("12.345").shift(-1));
		System.out.println(new Numeral("12.345").shift(0));
		System.out.println(new Numeral("12.345").shift(1));
		System.out.println(new Numeral("12.345").shift(2));
		System.out.println(new Numeral("12.345").shift(3));
	}
}
