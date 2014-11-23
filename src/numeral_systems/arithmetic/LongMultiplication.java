package numeral_systems.arithmetic;

import numeral_systems.numeral.Numeral;

public class LongMultiplication extends NumeralMultiplication {
	public LongMultiplication(Numeral multiplicant, Numeral multiplier, int base) {
		super(multiplicant, multiplier, base);
	}
	@Override
	protected void doMultiplication() {
		for (int i = multiplier.minPos(); i <= multiplier.maxPos(); ++i) {
			Numeral n = new Numeral(multiplicand);
			n.mult(multiplier.get(i), base).shift(i);
			partialResults.add(n);
		}
		for (Numeral n : partialResults) {
			product.add(n, base);
		}
	}
}
