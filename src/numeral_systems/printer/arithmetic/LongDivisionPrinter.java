package numeral_systems.printer.arithmetic;

import numeral_systems.arithmetic.LongDivision;
import numeral_systems.printer.GenericPrinter;

public class LongDivisionPrinter extends GenericPrinter {

	public LongDivisionPrinter(LongDivision division) {
		super("LongDivisionExercise.template", "LongDivisionSolution.template",
				DEFAULT_EXERCISE_TEMPLATE, DEFAULT_SOLUTION_TEMPLATE);
		add(DIVIDEND, division.dividend().toString());
		add(DIVISOR, division.divisor().toString());
		add(QUOTIENT, division.quotient().toString());
		add(BASE, String.valueOf(division.base()));
		add(PARTIAL_RESULTS, division.partialResults().toString());
	}
	protected final static String	DIVIDEND					= "[%DIVIDEND%]";
	protected final static String	DIVISOR						= "[%DIVISOR%]";
	protected final static String	QUOTIENT					= "[%QUOTIENT%]";
	protected final static String	BASE						= "[%BASE%]";
	protected final static String	PARTIAL_RESULTS				= "[%PARTIAL_RESULTS%]";

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
