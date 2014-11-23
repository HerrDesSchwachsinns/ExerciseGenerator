package numeral_systems.printer.arithmetic;

import numeral_systems.arithmetic.AustrianSubtraction;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.printer.GenericPrinter;

public class AustrianSubtractionPrinter extends GenericPrinter {
	public AustrianSubtractionPrinter(AustrianSubtraction subtraction) {
		super("AustrianSubtractionExercise.template",
				"AustrianSubtractionSolution.template",
				DEFAULT_EXERCISE_TEMPLATE, DEFAULT_SOLUTION_TEMPLATE);
		int alignment = subtraction.difference().maxPos() + 1;
		add(MINUEND, subtraction.minuend().toString());
		add(SUBTRAHEND, subtraction.subtrahend().toString());
		add(CARRY, subtraction.carry().toString());
		add(DIFFERENCE, subtraction.difference().toString());
		add(ALIGNED_MINUEND, NumeralUtils.toAlignedString(
				subtraction.minuend(), alignment, ' '));
		add(ALIGNED_SUBTRAHEND, NumeralUtils.toAlignedString(
				subtraction.subtrahend(), alignment, ' '));
		add(ALIGNED_CARRY, NumeralUtils.toAlignedString(subtraction.carry(),
				alignment, ' '));
		add(ALIGNED_DIFFERENCE, NumeralUtils.toAlignedString(
				subtraction.difference(), alignment, ' '));
		add(BASE, String.valueOf(subtraction.base()));
	}
	protected final static String	MINUEND						= "[%MINUEND%]";
	protected final static String	SUBTRAHEND					= "[%SUBTRAHEND%]";
	protected final static String	CARRY						= "[%CARRY%]";
	protected final static String	DIFFERENCE					= "[%DIFFERENCE%]";
	protected final static String	ALIGNED_MINUEND				= "[%ALIGNED_MINUEND%]";
	protected final static String	ALIGNED_SUBTRAHEND			= "[%ALIGNED_SUBTRAHEND%]";
	protected final static String	ALIGNED_CARRY				= "[%ALIGNED_CARRY%]";
	protected final static String	ALIGNED_DIFFERENCE			= "[%ALIGNED_DIFFERENCE%]";
	protected final static String	BASE						= "[%BASE%]";

	private final static String		DEFAULT_EXERCISE_TEMPLATE	= "("
																		+ MINUEND
																		+ ")_"
																		+ BASE
																		+ " + "
																		+ "("
																		+ SUBTRAHEND
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "?";
	private final static String		DEFAULT_SOLUTION_TEMPLATE	= "("
																		+ MINUEND
																		+ ")_"
																		+ BASE
																		+ " + "
																		+ "("
																		+ SUBTRAHEND
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "("
																		+ DIFFERENCE
																		+ ")_"
																		+ BASE
																		+ " c "
																		+ CARRY;

}
