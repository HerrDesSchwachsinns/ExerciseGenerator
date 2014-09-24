package numeral;

import util.Pair;

public class IntFrac extends Pair<int[], int[]> {

	public IntFrac(int[] integer, int[] fractional) {
		super(integer, fractional);
	}
	public int[] integer() {
		return first;
	}
	public int[] fraction() {
		return second;
	}
}
