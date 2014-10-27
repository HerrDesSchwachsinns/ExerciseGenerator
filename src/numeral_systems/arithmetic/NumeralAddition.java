package numeral_systems.arithmetic;

import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.util.DigitUtils;

/*
 *  augend
 * +addend
 * c carry
 * -------
 * =   sum
 */
/**
 * NumeralAddition represents addition by hand
 * 
 * @author vogl
 *
 */
public abstract class NumeralAddition {
	public NumeralAddition(Numeral augend, Numeral addend, int base) {
		this.augend = augend;
		this.addend = addend;
		this.sum = new Numeral();
		this.carry = new Numeral();
		this.base = base;
		testParameter();
		doAddition();
	}
	public Numeral augend() {
		return augend;
	}
	public Numeral addend() {
		return addend;
	}
	public Numeral sum() {
		return sum;
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
		b.append(augend.toString(base));
		b.append(" + ");
		b.append(addend.toString(base));
		b.append(" = ");
		b.append(sum.toString(base));
		b.append(" c ");
		b.append(carry.toString());
		return b.toString();
	}

	protected Numeral	augend;
	protected Numeral	addend;
	protected Numeral	sum;
	protected Numeral	carry;
	protected int		base;

	/**
	 * it is guaranteed that augend and addend are the same (valid) base and sum and
	 * carry are initialized to zero
	 */
	protected abstract void doAddition();

	private void testParameter() {
		if (!DigitUtils.isValidBase(base)) throw new IllegalArgumentException(
				"base is not valid");
		if (!NumeralUtils.isValid(augend, base)) throw new IllegalArgumentException(
				"augend is not valid in base " + base);
		if (!NumeralUtils.isValid(addend, base)) throw new IllegalArgumentException(
				"addend is not valid in base " + base);
	}
}