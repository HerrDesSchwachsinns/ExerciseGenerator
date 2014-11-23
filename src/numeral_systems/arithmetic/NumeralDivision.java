package numeral_systems.arithmetic;

import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.util.DigitUtils;

/**
 * NumeralDivision represents multiplication by hand
 * 
 * @author vogl
 *
 */
public abstract class NumeralDivision {
	public NumeralDivision(Numeral dividend, Numeral divisor, int base) {
		this.dividend = dividend;
		this.divisor = divisor;
		this.quotient = new Numeral();
		this.base = base;
		testParameter();
		doDivision();
	}
	public Numeral dividend() {
		return dividend;
	}
	public Numeral divisor() {
		return divisor;
	}
	public Numeral quotient() {
		return quotient;
	}
	public int base() {
		return base;
	}

	protected Numeral	dividend;
	protected Numeral	divisor;
	protected Numeral	quotient;
	protected int		base;

	/**
	 * it is guaranteed that dividend and divisor are the same (valid) base and
	 * quotient is initialized to zero
	 */
	protected abstract void doDivision();
	protected void init() {}

	private void testParameter() {
		if (!DigitUtils.isValidBase(base)) throw new IllegalArgumentException(
				"base is not valid");
		if (!NumeralUtils.isValid(dividend, base)) throw new IllegalArgumentException(
				"dividend is not valid in base " + base);
		if (!NumeralUtils.isValid(divisor, base)) throw new IllegalArgumentException(
				"divisor is not valid in base " + base);
	}
}
