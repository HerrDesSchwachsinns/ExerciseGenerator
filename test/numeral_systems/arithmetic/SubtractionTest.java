package numeral_systems.arithmetic;

import static org.junit.Assert.*;
import numeral_systems.numeral.Numeral;

import org.junit.Test;

public class SubtractionTest {
	@Test
	public void test() {
		test("25.74","2.535",10,"23.205");
	}
	private void test(String minuend, String subtrahend, int base, String result) {
		assertEquals(result, new AustrianSubtraction(new Numeral(minuend),
				new Numeral(subtrahend), base).difference().toString());
	}
}
