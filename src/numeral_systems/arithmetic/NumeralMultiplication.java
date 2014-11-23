package numeral_systems.arithmetic;

import java.util.ArrayList;
import java.util.List;

import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.util.DigitUtils;
import numeral_systems.util.Pair;

/**
 * NumeralMultiplication represents multiplication by hand
 * 
 * @author vogl
 *
 */
public abstract class NumeralMultiplication {
	public NumeralMultiplication(Numeral multiplicant, Numeral multiplier,
			int base) {
		this.multiplicand = multiplicant;
		this.multiplier = multiplier;
		this.product = new Numeral();
		this.base = base;
		this.partialResults = new ArrayList<Numeral>();
		testParameter();
		doMultiplication();
	}
	public Numeral multiplicand() {
		return multiplicand;
	}
	public Numeral multiplier() {
		return multiplier;
	}
	public Numeral product() {
		return product;
	}
	public List<Numeral> partialResults() {
		return partialResults;
	}
	public int base() {
		return base;
	}

	protected Numeral		multiplicand;
	protected Numeral		multiplier;
	protected Numeral		product;
	protected List<Numeral>	partialResults;
	protected int			base;

	/**
	 * it is guaranteed that multiplicand and multiplier are the same (valid)
	 * base, product is initialized to zero and partialResults is empty
	 */
	protected abstract void doMultiplication();

	private void testParameter() {
		if (!DigitUtils.isValidBase(base)) throw new IllegalArgumentException(
				"base is not valid");
		if (!NumeralUtils.isValid(multiplicand, base)) throw new IllegalArgumentException(
				"multiplicant is not valid in base " + base);
		if (!NumeralUtils.isValid(multiplier, base)) throw new IllegalArgumentException(
				"multiplier is not valid in base " + base);
	}
}