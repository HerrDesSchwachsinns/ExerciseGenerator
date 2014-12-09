package numeral_systems.arithmetic;

import java.util.ArrayList;
import java.util.List;

import static numeral_systems.util.Pair.make_pair;

import numeral_systems.numeral.Numeral;
import numeral_systems.util.Pair;

public class LongDivision extends NumeralDivision {

	public static void main(String[] args) {
		Numeral n1 = new Numeral("1011011");
		Numeral n2 = new Numeral("110");

		LongDivision div = new LongDivision(n1, n2, 2);
		System.out.println(div.dividend() + " / " + div.divisor() + " = "
				+ div.quotient());
		div.partialResults().forEach(System.out::println);
	}

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
	protected void doDivision() {
		partialResults = new ArrayList<Pair<Numeral, Integer>>();
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
		int start = dividend.maxPos() + dividend.minPos(); //start at zero pos
		for (int i = start; i < partialResults.size(); ++i) {
			if (partialResults.get(i).first.equals(sub)) {
				recurring_index = i;
				return true;
			}
		}
		return false;
	}

	private List<Pair<Numeral, Integer>>	partialResults;
	private int								recurring_index	= 0;
}
