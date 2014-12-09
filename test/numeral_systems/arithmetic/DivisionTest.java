package numeral_systems.arithmetic;

import static org.junit.Assert.assertEquals;
import numeral_systems.numeral.Numeral;

import org.junit.Test;

public class DivisionTest {

	@Test
	public void practiceSheetTests() {
		testRecurring("11111", "10", 2, "1111.1", 0);
		testRecurring("11111", "11", 2, "1010.01", -1);
		testRecurring("1010110", "100", 2, "10101.1", 0);
		testRecurring("1011011", "110", 2, "1111.001", -2);
		testRecurring("10001", "10", 2, "1000.1", 0);
		testRecurring("11001", "11", 2, "1000.01", -1);
	}
	public void test(String dividend, String divisor, int base, String result) {
		LongDivision div = new LongDivision(new Numeral(dividend), new Numeral(
				divisor), base);
		assertEquals(div.quotient.toString(), result);
	}
	public void testRecurring(String dividend, String divisor, int base,
			String result, int recurringIndex) {
		LongDivision div = new LongDivision(new Numeral(dividend), new Numeral(
				divisor), base);
		assertEquals(result, div.quotient.toString());
		assertEquals(recurringIndex, div.recurringIndex());
	}

}
