package numeral_systems.arithmetic;

import numeral_systems.numeral.Numeral;

public class AustrianAddition extends NumeralAddition {
	public AustrianAddition(Numeral augend, Numeral addend, int base) {
		super(augend, addend, base);
	}

	protected void doAddition() {
		int minPos = Math.min(augend.minPos(), addend.minPos());
		int maxPos = Math.max(augend.maxPos(), addend.maxPos());

		for (int pos = minPos; pos <= maxPos + 1; ++pos) {
			int s = augend.get(pos) + addend.get(pos) + carry.get(pos);
			if (s >= base) { //overflow -> carry
				s -= base;
				carry.set(pos + 1, 1);
			}
			sum.set(pos, s);
		}
	}

}
