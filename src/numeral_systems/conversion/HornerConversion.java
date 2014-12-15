package numeral_systems.conversion;

import static numeral_systems.util.Pair.make_pair;

import java.util.ArrayList;
import java.util.List;

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

	public List<Pair<Numeral, Numeral>> partialFractionResults() {
		return partialFractionResults;
	}
	public List<Pair<Numeral, Numeral>> partialIntegerResults() {
		return partialIntegerResults;
	}

	protected void init() {
		partialIntegerResults = new ArrayList<>();
		partialFractionResults = new ArrayList<>();
		recurring_index = 0;
	}

	protected void doAlgorithm() {
		convertInteger();
		convertFraction();
	}

	/*
	 * works by repeated division by decoded base
	 * the reminders are the digits of the new base
	 */
	private void convertInteger() {
		Numeral numer = encoded().integer();
		Numeral denom = new Numeral(decodedBase(), encodedBase());

		int pos = 0;
		while (!numer.isZero()) {
			Numeral tmp = new Numeral(numer);
			Pair<Numeral, Integer> divRem = divideWithRemainder(numer, denom,
					encodedBase());
			partialIntegerResults
					.add(make_pair(tmp, new Numeral(divRem.first)));
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

	/*
	 * works by repeated multiplication by decoded base
	 * the reminders are the digits of the new base
	 */
	private void convertFraction() {
		Numeral one = new Numeral().addOne(encBase);
		Numeral numer = encoded().fraction();
		int pos = -1;
		while (!numer.isZero() && !is_recurring(numer)) {
			Numeral tmp = new Numeral(numer);
			numer.mult(decBase, encBase);
			partialFractionResults.add(make_pair(tmp, new Numeral(numer)));
			decoded.set(pos--, DigitUtils.base2int(encodedBase(), numer
					.toIntArray().integer()));
			if (numer.compareTo(one) >= 0) {
				numer = numer.fraction();
			}
		}
	}
	private boolean is_recurring(Numeral numer) {
		for (int i = 0; i < partialFractionResults.size(); ++i) {
			if (partialFractionResults.get(i).first.equals(numer)) {
				recurring_index = -1 - i;
				return true;
			}
		}
		return false;
	}
	private List<Pair<Numeral, Numeral>>	partialIntegerResults;
	private List<Pair<Numeral, Numeral>>	partialFractionResults;
	private int								recurring_index;
}
