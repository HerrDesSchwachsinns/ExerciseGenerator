package numeral_systems.printer.arithmetic;

import numeral_systems.arithmetic.AustrianAddition;
import numeral_systems.numeral.NumeralUtils;
import numeral_systems.printer.GenericPrinter;

public class AustrianAdditionPrinter extends GenericPrinter {
	public AustrianAdditionPrinter(AustrianAddition addition) {
		super("AustrianAdditionExercise.template",
				"AustrianAdditionSolution.template", DEFAULT_EXERCISE_TEMPLATE,
				DEFAULT_SOLUTION_TEMPLATE);
		int alignment = addition.sum().maxPos() + 1;
		add(AUGEND, addition.augend().toString());
		add(ADDEND, addition.addend().toString());
		add(CARRY, addition.carry().toString());
		add(SUM, addition.sum().toString());
		add(ALIGNED_AUGEND,
				NumeralUtils.toAlignedString(addition.augend(), alignment, ' '));
		add(ALIGNED_ADDEND,
				NumeralUtils.toAlignedString(addition.addend(), alignment, ' '));
		add(ALIGNED_CARRY,
				NumeralUtils.toAlignedString(addition.carry(), alignment, ' '));
		add(ALIGNED_SUM,
				NumeralUtils.toAlignedString(addition.sum(), alignment, ' '));
		add(BASE, String.valueOf(addition.base()));
	}
	protected final static String	AUGEND						= "[%AUGEND%]";
	protected final static String	ADDEND						= "[%ADDEND%]";
	protected final static String	CARRY						= "[%CARRY%]";
	protected final static String	SUM							= "[%SUM%]";
	protected final static String	ALIGNED_AUGEND				= "[%ALIGNED_AUGEND%]";
	protected final static String	ALIGNED_ADDEND				= "[%ALIGNED_ADDEND%]";
	protected final static String	ALIGNED_CARRY				= "[%ALIGNED_CARRY%]";
	protected final static String	ALIGNED_SUM					= "[%ALIGNED_SUM%]";
	protected final static String	BASE						= "[%BASE%]";

	private final static String		DEFAULT_EXERCISE_TEMPLATE	= "("
																		+ AUGEND
																		+ ")_"
																		+ BASE
																		+ " + "
																		+ "("
																		+ ADDEND
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "?";
	private final static String		DEFAULT_SOLUTION_TEMPLATE	= "("
																		+ AUGEND
																		+ ")_"
																		+ BASE
																		+ " + "
																		+ "("
																		+ ADDEND
																		+ ")_"
																		+ BASE
																		+ " = "
																		+ "("
																		+ SUM
																		+ ")_"
																		+ BASE
																		+ " c "
																		+ CARRY;

}
