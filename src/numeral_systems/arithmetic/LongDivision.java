package numeral_systems.arithmetic;

import java.util.ArrayList;
import java.util.List;

import static numeral_systems.util.Pair.make_pair;
import numeral_systems.numeral.Numeral;
import numeral_systems.util.Pair;

public class LongDivision extends NumeralDivision {

	public LongDivision(Numeral dividend, Numeral divisor, int base) {
		super(dividend, divisor, base);
	}

	public List<Pair<Numeral, Integer>> partialResults() {
		return partialResults;
	}
	public boolean isRecurring() {
		return recurring_index != 0;
	}
	public int recurringIndex() {
		return recurring_index;
	}

	@Override
	public String toString() { //override for debugging purpose
		StringBuilder b = new StringBuilder();
		b.append("(" + dividend.toString() + " / " + divisor.toString() + ")_"
				+ base + "\n");
		b.append("quotient: " + quotient.toString() + "\n");
		b.append("recurring_index:" + recurring_index + "\n");
		b.append("partial results:" + partialResults.toString());
		return b.toString();
	}

	@Override
	protected void init() {
		recurring_index = 0;
		partialResults = new ArrayList<Pair<Numeral, Integer>>();
	}

	@Override
	protected void doDivision() {
		Numeral sub = new Numeral();
		for (int pos = dividend.maxPos(); pos >= dividend.minPos()
				|| !sub.isZero(); --pos) {
			sub.set(0, dividend.get(pos));
			if (pos < dividend.minPos() && is_Recurring(sub)) break;
			Numeral tmp_sub = new Numeral(sub);
			int sub_count = sub_divisor(sub);
			partialResults.add(make_pair(tmp_sub, sub_count));
			//			System.out.println(partialResults.get(partialResults.size() - 1));
			quotient.set(pos, sub_count);
			sub.shift(1);
		}
	}

	private int sub_divisor(Numeral sub) {
		int count = 0;
		while (sub.compareTo(divisor) >= 0) {
			sub.sub(divisor, base);
			++count;
		}
		return count;
	}
	private boolean is_Recurring(Numeral sub) {
		int start = dividend.maxPos() + dividend.minPos() + 1; //start at zero-1 pos
		for (int i = start; i < partialResults.size(); ++i) {
			if (partialResults.get(i).first.equals(sub)) {
				recurring_index = i - start - 1;
				return true;
			}
		}
		return false;
	}

	private List<Pair<Numeral, Integer>>	partialResults;
	private int								recurring_index;
}
