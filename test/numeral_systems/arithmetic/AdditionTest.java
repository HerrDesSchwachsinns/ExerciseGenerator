package numeral_systems.arithmetic;

import static org.junit.Assert.*;
import numeral_systems.numeral.Numeral;

import org.junit.Test;

public class AdditionTest {

	@Test
	public void test() {
		test("99.999", "0.001", 10, "100");
		test("1011", "1011", 2, "10110");
	}

	private void test(String augend, String addend, int base, String result) {
		assertEquals(result, new AustrianAddition(new Numeral(augend),
				new Numeral(addend), base).sum().toString());
	}
}
