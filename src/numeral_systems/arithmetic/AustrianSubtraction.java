package numeral_systems.arithmetic;

import numeral_systems.numeral.Numeral;

public class AustrianSubtraction extends NumeralSubtraction {

	public AustrianSubtraction(Numeral minuend, Numeral subtrahend, int base) {
		super(minuend, subtrahend, base);
	}

	@Override
	protected void doSubtraction() {
		int minPos = Math.min(minuend().minPos(), subtrahend().minPos());
		int maxPos = Math.max(minuend().maxPos(), subtrahend().maxPos());

		for (int pos = minPos; pos <= maxPos; ++pos) {
			int s = minuend().get(pos) - subtrahend().get(pos) - carry.get(pos);
			if (s < 0) { //underflow -> carry
				s += base;
				carry.set(pos + 1, 1);
			}
			difference.set(pos, s);
		}

	}

}
