package numeral_systems.printer.arithmetic;

import numeral_systems.arithmetic.LongMultiplication;
import numeral_systems.printer.GenericPrinter;

public class LongMultiplicationPrinter extends GenericPrinter {

	public LongMultiplicationPrinter(LongMultiplication multiplication) {
		super("LongMultiplicationExercise.template",
				"LongMultiplicationSolution.template",
				DEFAULT_EXERCISE_TEMPLATE, DEFAULT_SOLUTION_TEMPLATE);
		add(MULTIPLICAND, multiplication.multiplicand().toString());
		add(MULTIPLIER, multiplication.multiplier().toString());
		add(PRODUCT, multiplication.product().toString());
		add(BASE, String.valueOf(multiplication.base()));
		add(PARTIAL_RESULTS, multiplication.partialResults().toString()); //TODO prettify output
	}
	protected final static String	MULTIPLICAND				= "[%MULTIPLICAND%]";
	protected final static String	MULTIPLIER					= "[%MULTIPLIER%]";
	protected final static String	PRODUCT						= "[%PRODUCT%]";
	protected final static String	BASE						= "[%BASE%]";
	protected final static String	PARTIAL_RESULTS				= "[%PARTIAL_RESULTS%]";

	private final static String		DEFAULT_EXERCISE_TEMPLATE	= "("
																		+ MULTIPLICAND
																		+ ")_"
																		+ BASE
																		+ " * "
																		+ "("
																		+ MULTIPLIER
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "?";
	private final static String		DEFAULT_SOLUTION_TEMPLATE	= "("
																		+ MULTIPLICAND
																		+ ")_"
																		+ BASE
																		+ " * "
																		+ "("
																		+ MULTIPLIER
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "("
																		+ PRODUCT
																		+ ")_"
																		+ BASE;
}
