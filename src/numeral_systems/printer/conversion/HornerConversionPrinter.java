package numeral_systems.printer.conversion;

import numeral_systems.conversion.HornerConversion;
import numeral_systems.printer.GenericPrinter;

public class HornerConversionPrinter extends GenericPrinter {
	public HornerConversionPrinter(HornerConversion conv) {
		super("HornerConversionExercise.template",
				"HornerConversionSolution.template", DEFAULT_EXERCISE_TEMPLATE,
				DEFAULT_SOLUTION_TEMPLATE);
		add(ENCODED, conv.encoded().toString());
		add(DECODED, conv.decoded().toString());
		add(ENCODED_BASE, Integer.toString(conv.encodedBase()));
		add(DECODED_BASE, Integer.toString(conv.decodedBase()));
	}

	protected final static String	ENCODED						= "[%ENCODED%]";
	protected final static String	DECODED						= "[%DECODED%]";
	protected final static String	ENCODED_BASE				= "[%ENCODED_BASE%]";
	protected final static String	DECODED_BASE				= "[%DECODED_BASE%]";

	private final static String		DEFAULT_EXERCISE_TEMPLATE	= "("
																		+ ENCODED
																		+ ")_"
																		+ ENCODED_BASE
																		+ " = "
																		+ "("
																		+ "?"
																		+ ")_"
																		+ DECODED_BASE;
	private final static String		DEFAULT_SOLUTION_TEMPLATE	= "("
																		+ ENCODED
																		+ ")_"
																		+ ENCODED_BASE
																		+ " = "
																		+ DECODED
																		+ "_"
																		+ DECODED_BASE;
}
