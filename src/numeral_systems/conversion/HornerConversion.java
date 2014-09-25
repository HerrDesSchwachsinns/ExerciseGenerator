package numeral_systems.conversion;

import static numeral_systems.util.Pair.make_pair;
import numeral_systems.numeral.Numeral;
import numeral_systems.util.DigitUtils;
import numeral_systems.util.Pair;

/**
 * <code>HornerConversion</code> is the standard conversion algorithm taught
 * here. The Algorithm works with arbitrary bases.
 * 
 * @author Alexander Vogelgsang
 */
public class HornerConversion extends BaseConversion {

	public HornerConversion(int decBase, int encBase, Numeral encoded) {
		super(decBase, encBase, encoded);
	}

	protected void doAlgorithm() {
		convertInteger();
		//		convertFraction();
	}

	/*
	 * works by repeated division by decoded base
	 * the reminders are the digits of the new base
	 */
	private void convertInteger() {
		Numeral numer = encoded().clone();
		Numeral denom = new Numeral(decodedBase(), encodedBase());

		int pos = 0;
		while (!numer.isZero()) {
			Pair<Numeral, Integer> divRem = divideWithRemainder(numer, denom,
					encodedBase());
			numer = divRem.first;
			decoded.set(pos++, divRem.second);
		}
	}

	private Pair<Numeral, Integer> divideWithRemainder(Numeral numer,
			Numeral denom, int base) {
		Numeral count = new Numeral();
		while (numer.compareTo(denom) >= 0) {
			numer.sub(denom, base);
			count.addOne(base);
		}
		return make_pair(count, DigitUtils.base2int(encodedBase(), numer
				.toIntArray().integer()));
	}

	private void convertFraction() {
		// TODO implement pay attention to repeating sequences
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
