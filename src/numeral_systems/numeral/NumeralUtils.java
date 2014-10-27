package numeral_systems.numeral;

public class NumeralUtils {
	/**
	 * return true if n is a valid numeral for base that is every digit is less
	 * than base
	 * 
	 * @param n
	 *            a Numeral
	 * @param base
	 * @return true if n is valid in base and false otherwise
	 */
	public static boolean isValid(Numeral n, int base) {
		for (int pos = n.minPos(); pos <= n.maxPos(); ++pos) {
			if (n.get(pos) >= base) return false;
		}
		return true;
	}
}
