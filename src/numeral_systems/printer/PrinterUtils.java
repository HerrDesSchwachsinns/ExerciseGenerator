package numeral_systems.printer;

import numeral_systems.numeral.Numeral;

public class PrinterUtils {
	public static String recurringNumeral(Numeral n, int recurringIndex) {
		if (recurringIndex >= 0) return n.toString();
		StringBuilder b = new StringBuilder();
		b.append(n.integer());
		b.append(".");
		int pos;
		for (pos = -1; pos > recurringIndex; --pos) {
			b.append(n.get(pos));
		}
		b.append("|");
		for (/*pos=recurringIndex*/; pos >= n.minPos(); --pos) {
			b.append(n.get(pos));
		}
		return b.toString();
	}
}
