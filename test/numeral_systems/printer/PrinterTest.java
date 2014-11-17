package numeral_systems.printer;

import numeral_systems.arithmetic.AustrianAddition;
import numeral_systems.arithmetic.LongMultiplication;
import numeral_systems.conversion.FastConversion;
import numeral_systems.numeral.Numeral;
import numeral_systems.printer.arithmetic.AustrianAdditionPrinter;
import numeral_systems.printer.arithmetic.LongMultiplicationPrinter;
import numeral_systems.printer.conversion.FastConversionAsciiPrinter;
import numeral_systems.printer.conversion.FastConversionPrinter;

import org.junit.Test;

public class PrinterTest {

	//	@Test
	//	public void addition() {
	//		AustrianAddition add = new AustrianAddition(new Numeral("12345.00107"),
	//				new Numeral("987.12603"), 10);
	//		AustrianAdditionPrinter printer = new AustrianAdditionPrinter(add);
	//		System.out.println(printer.exercise());
	//		System.out.println(printer.solution());
	//	}
	@Test
	public void multiplication() {
		LongMultiplication mult = new LongMultiplication(new Numeral("232"),
				new Numeral("15"), 6);
		LongMultiplicationPrinter printer = new LongMultiplicationPrinter(mult);
		//		System.out.println(printer.exercise());
		System.out.println(printer.solution());
	}
	//	@Test
	//	public void fastConversion() {
	//		FastConversion conv = new FastConversion(16, 2, new Numeral("f10"));
	//		FastConversionPrinter printer = new FastConversionAsciiPrinter(conv);
	//		System.out.println(printer.exercise());
	//		System.out.println(printer.solution());
	//	}

}
