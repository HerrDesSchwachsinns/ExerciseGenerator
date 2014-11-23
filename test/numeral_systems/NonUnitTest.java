package numeral_systems;

import numeral_systems.arithmetic.LongDivision;
import numeral_systems.numeral.Numeral;
import numeral_systems.printer.arithmetic.LongDivisionPrinter;

public class NonUnitTest {
	public static void main(String[] args) {
		Numeral n1 = new Numeral("27");
		Numeral n2 = new Numeral("2");
		int base = 10;
		LongDivision div = new LongDivision(n1, n2, base);
		LongDivisionPrinter printer = new LongDivisionPrinter(div);
		System.out.println(printer.solution());
	}
}
