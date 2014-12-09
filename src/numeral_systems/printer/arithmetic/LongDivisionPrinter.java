package numeral_systems.printer.arithmetic;

import numeral_systems.arithmetic.LongDivision;
import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.printer.GenericPrinter;
import numeral_systems.printer.PrinterUtils;

public class LongDivisionPrinter extends GenericPrinter {

	public LongDivisionPrinter(LongDivision division) {
		super("LongDivisionExercise.template", "LongDivisionSolution.template",
				DEFAULT_EXERCISE_TEMPLATE, DEFAULT_SOLUTION_TEMPLATE);
		add(DIVIDEND, division.dividend().toString());
		add(DIVISOR, division.divisor().toString());
		add(QUOTIENT, division.quotient().toString());
		add(BASE, String.valueOf(division.base()));
		add(PARTIAL_RESULTS, division.partialResults().toString());
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
}
