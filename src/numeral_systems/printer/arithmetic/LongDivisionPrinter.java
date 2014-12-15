package numeral_systems.printer.arithmetic;

import java.util.List;

import numeral_systems.arithmetic.LongDivision;
import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.printer.GenericPrinter;
import numeral_systems.printer.PrinterUtils;
import numeral_systems.util.Pair;
import numeral_systems.util.StringUtils;

public class LongDivisionPrinter extends GenericPrinter {

	public LongDivisionPrinter(LongDivision division) {
		super("LongDivisionExercise.template", "LongDivisionSolution.template",
				DEFAULT_EXERCISE_TEMPLATE, DEFAULT_SOLUTION_TEMPLATE);
		add(DIVIDEND, division.dividend().toString());
		add(DIVISOR, division.divisor().toString());
		add(QUOTIENT, division.quotient().toString());
		add(BASE, String.valueOf(division.base()));
		add(PARTIAL_RESULTS,
				partialResults(division.partialResults(), division.divisor(),
						division.base()));
		add(QUOTIENT_RECURRING,
				PrinterUtils.recurringNumeral(division.quotient(),
						division.recurringIndex()));
	}
	protected final static String	DIVIDEND					= "[%DIVIDEND%]";
	protected final static String	DIVISOR						= "[%DIVISOR%]";
	protected final static String	QUOTIENT					= "[%QUOTIENT%]";
	protected final static String	BASE						= "[%BASE%]";
	protected final static String	PARTIAL_RESULTS				= "[%PARTIAL_RESULTS%]";
	protected final static String	QUOTIENT_RECURRING			= "[%QUOTIENT_RECURRING%]";

	private final static String		DEFAULT_EXERCISE_TEMPLATE	= "("
																		+ DIVIDEND
																		+ ")_"
																		+ BASE
																		+ " / "
																		+ "("
																		+ DIVISOR
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "?";
	private final static String		DEFAULT_SOLUTION_TEMPLATE	= "("
																		+ DIVIDEND
																		+ ")_"
																		+ BASE
																		+ " / "
																		+ "("
																		+ DIVISOR
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "("
																		+ QUOTIENT
																		+ ")_"
																		+ BASE;
	private String partialResults(List<Pair<Numeral, Integer>> partialResults,
			Numeral divisor, int base) {
		int maxPartResSize = partialResults
				.stream()
				.mapToInt(
						p -> new Numeral(p.first).mult(p.second, base).maxPos())
				.max().orElse(0) + 1;

		String delimLine = StringUtils.repeat('-', maxPartResSize
				+ partialResults.size() + divisor.maxPos());

		StringBuilder b = new StringBuilder();
		for (int i = 0; i < partialResults.size(); ++i) {
			Pair<Numeral, Integer> pair = partialResults.get(i);
			Numeral mult = new Numeral(divisor).mult(pair.second, base);
//			int alignment = Math.max(pair.first.maxPos(), mult.maxPos());
			int alignment = maxPartResSize;
			String padding = StringUtils.repeat(' ', i);
			b.append(padding + "  "
					+ NumeralUtils.toAlignedString(pair.first, alignment, ' '));
			b.append("\n");
			b.append(padding + "- ");
			b.append(NumeralUtils.toAlignedString(mult, alignment, ' '));

			b.append("\n");
			b.append(delimLine);
			b.append("\n");
		}
		return b.toString();
	}
}
