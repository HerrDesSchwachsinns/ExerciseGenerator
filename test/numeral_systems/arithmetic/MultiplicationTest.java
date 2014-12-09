package numeral_systems.arithmetic;

import static org.junit.Assert.*;
import numeral_systems.numeral.Numeral;

import org.junit.Test;

public class MultiplicationTest {

	@Test
	public void practiceSheetTests() {
		test("232", "15", 6, "4404");
		test("121", "13", 4, "2233");
		test("103", "21", 4, "2223");
	}
	public void test(String multiplicand, String multiplier, int base,
			String result) {
		LongMultiplication mult = new LongMultiplication(new Numeral(
				multiplicand), new Numeral(multiplier), base);
		assertEquals(mult.product.toString(), result);
	}

}
