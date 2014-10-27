package numeral_systems.arithmetic;

import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.util.DigitUtils;

/*
 *   minuend
 * -subtrahend
 * c carry
 * -------
 * =difference
 */
/**
 * NumeralSubtraction represents subtraction by hand
 * 
 * @author vogl
 *
 */
public abstract class NumeralSubtraction {
	public NumeralSubtraction(Numeral minuend, Numeral subtrahend, int base) {
		this.minuend = minuend;
		this.subtrahend = subtrahend;
		this.difference = new Numeral();
		this.carry = new Numeral();
		this.base = base;
		testParameter();
		doSubtraction();
	}
	public Numeral minuend() {
		return minuend;
	}
	public Numeral subtrahend() {
		return subtrahend;
	}
	public Numeral difference() {
		return difference;
	}
	public Numeral carry() {
		return carry;
	}
	public int base() {
		return base;
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(minuend.toString(base));
		b.append(" - ");
		b.append(subtrahend.toString(base));
		b.append(" = ");
		b.append(difference.toString(base));
		b.append(" c ");
		b.append(carry.toString());
		return b.toString();
	}

	protected Numeral	minuend;
	protected Numeral	subtrahend;
	protected Numeral	difference;
	protected Numeral	carry;
	protected int		base;

	/**
	 * it is guaranteed that minuend and subtrahend are the same (valid) base,
	 * minuend is greater or equal to subtrahend and difference and carry are
	 * initialized to zero
	 */
	protected abstract void doSubtraction();

	private void testParameter() {
		if (!DigitUtils.isValidBase(base)) throw new IllegalArgumentException(
				"base is not valid");
		if (!NumeralUtils.isValid(minuend, base)) throw new IllegalArgumentException(
				"minuend is not valid in base " + base);
		if (!NumeralUtils.isValid(subtrahend, base)) throw new IllegalArgumentException(
				"subtrahend is not valid in base " + base);
		if (minuend.compareTo(subtrahend) < 0) throw new IllegalArgumentException(
				"minuend is less than subtrahend");
	}
}
