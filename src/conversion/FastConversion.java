package conversion;

import static java.lang.Math.max;
import static util.ArrayUtils.partition;
import static util.ArrayUtils.reverse;
import static util.DigitUtils.base2base;
import static util.DigitUtils.digits_for_base;
import static util.IterableUtils.asIterable;
import static util.IterableUtils.counter;
import static util.IterableUtils.zip;
import static util.MathUtils.isExponentiationOf;
import static java.util.Arrays.copyOf;
import numeral.IntFrac;
import numeral.Numeral;
import util.Pair;

//TODO combine all implementation parts to one more generic
/**
 * <code>FastConversion</code> is a conversion algorithm that can be used only
 * if one base is an exponentiation of the other.
 * <p>
 * It uses the fact that if that connection is true one can convert discrete
 * digit groups between each other. For example if one base is 2 and the other
 * 16 four digits of base 2 correspond exactly to one digit of base 16.
 * </p>
 * 
 * @author Alexander Vogelgsang
 */
public class FastConversion extends BaseConversion {
	public FastConversion(int decBase, int encBase, Numeral encoded) {
		super(decBase, encBase, encoded);
	}
	public Pair<Integer, Integer> baseRatio() {
		return baseRatio;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(toString(encoded(), baseRatio().first, encodedBase()));
		b.append(" = ");
		b.append(toString(decoded(), baseRatio().second, decodedBase()));
		return b.toString();
	}
	private String toString(Numeral n, int partition, int base) {
		StringBuilder b = new StringBuilder();
		b.append("(");
		for (int i = n.maxPos(); i >= n.minPos(); --i) {
			b.append(n.get(i));
			if (i != n.minPos()) {
				if (i % partition == 0) b.append('|');
				if (i == 0) b.append('.');
			}
		}
		b.append(")_");
		b.append(base);
		return b.toString();
	}

	@Override
	protected void testParamConstraints() {
		if (!isExponentiationOf(encBase, decBase)
				&& !isExponentiationOf(decBase, encBase)) throw new IllegalArgumentException(
				"bases " + encBase + " and " + decBase
						+ " are not compatible for fast conversion");
	}
	@Override
	protected void doAlgorithm() {
		final int digitsForDigit = max(
				digits_for_base(encodedBase(), decodedBase()) - 1, 1);
		final int digitForDigits = max(
				digits_for_base(decodedBase(), encodedBase()) - 1, 1);
		baseRatio = new Pair<>(digitsForDigit, digitForDigits);
		IntFrac intFrac = encoded.toIntArray();
		convertInteger(intFrac.integer());
		convertFraction(intFrac.fraction());
	}

	/*
	 * converts integer part of input
	 * digits are written in increasing order
	 * examples:
	 * (10111)_2 = (?)_16
	 * integer:    [11101]
	 * partition:  [1110, 1]
	 * zipped:     [ [1110, 0], [1, 1] ]
	 * all ds:     [7], [1]
	 * after copy: [7], [1]
	 * (10111)_2 = (17)_16
	 * 
	 * (35)_16 = (?)_4
	 * integer:    [53]
	 * partition:  [5, 3]
	 * zipped:     [ [5, 0], [3, 2] ]
	 * all ds:     [11], [3]
	 * after copy: [11], [30]
	 * (35)_16 = (311)_4
	 */
	private void convertInteger(int[] integer) {
		int[][] digitGroups = partition(integer, baseRatio.first, true);
		for (Pair<int[], Integer> p : zip(asIterable(digitGroups),
				counter(0, baseRatio.second))) {
			int[] ds = base2base(encBase, decBase, p.first);
			ds = copyOf(ds, baseRatio.second);
			for (int i = 0; i < baseRatio.second; ++i)
				decoded.set(p.second + i, ds[i]);
		}
	}
	/*
	 * converts fraction part of input
	 * as integer but reverse all partitions
	 * digit groups are written in some kind of inverted little endian mode
	 */
	private void convertFraction(int[] fraction) {
		int[][] digitGroups = partition(fraction, baseRatio.first, true);
		for (Pair<int[], Integer> p : zip(asIterable(digitGroups),
				counter(-1, -baseRatio.second))) {
			reverse(p.first);//now ascending
			int[] ds = base2base(encBase, decBase, p.first);
			ds = copyOf(ds, baseRatio.second);
			for (int i = 0; i < baseRatio.second; ++i)
				decoded.set(p.second - baseRatio.second + 1 + i, ds[i]);
		}
	}
	private Pair<Integer, Integer>	baseRatio;
}
