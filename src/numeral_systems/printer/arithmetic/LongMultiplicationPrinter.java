package numeral_systems.printer.arithmetic;

import java.util.List;
import java.util.stream.Collectors;

import numeral_systems.arithmetic.LongMultiplication;
import numeral_systems.numeral.Numeral;
import numeral_systems.numeral.NumeralUtils;
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
		add(PARTIAL_RESULTS, multiplication.partialResults().toString());
		add(PARTIAL_RESULTS_ALIGNED,
				alignPartialResults(multiplication.partialResults(),
						multiplication.product().maxPos()));
	}
	protected final static String	MULTIPLICAND				= "[%MULTIPLICAND%]";
	protected final static String	MULTIPLIER					= "[%MULTIPLIER%]";
	protected final static String	PRODUCT						= "[%PRODUCT%]";
	protected final static String	BASE						= "[%BASE%]";
	protected final static String	PARTIAL_RESULTS				= "[%PARTIAL_RESULTS%]";
	protected final static String	PARTIAL_RESULTS_ALIGNED		= "[%PARTIAL_RESULTS_ALIGNED%]";

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
	private String alignPartialResults(List<Numeral> partialResults,
			int alignment) {
		return partialResults.stream().filter(n -> !n.isZero())
				.map(n -> NumeralUtils.toAlignedString(n, alignment, ' '))
				.collect(Collectors.joining("\n"));
	}
}
