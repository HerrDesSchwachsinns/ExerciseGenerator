package numeral_systems.printer.conversion;

import numeral_systems.conversion.FastConversion;
import numeral_systems.numeral.Numeral;
import numeral_systems.printer.GenericPrinter;

public abstract class FastConversionPrinter extends GenericPrinter {
	public FastConversionPrinter(FastConversion conv,
			String exerciseTemplateFile, String solutionTemplateFile) {
		super(exerciseTemplateFile, solutionTemplateFile,
				DEFAULT_EXERCISE_TEMPLATE, DEFAULT_SOLUTION_TEMPLATE);
		add(ENCODED, conv.encoded().toString());
		add(DECODED, conv.decoded().toString());
		add(PARTITIONED_ENCODED,
				numeralPartition(conv.encoded(), conv.baseRatio().first));
		add(PARTITION_DECODED,
				numeralPartition(conv.decoded(), conv.baseRatio().second));
		add(ENCODED_BASE, Integer.toString(conv.encodedBase()));
		add(DECODED_BASE, Integer.toString(conv.decodedBase()));
	}
	protected abstract String numeralPartition(Numeral n, int partSize);

	protected final static String	ENCODED						= "[%ENCODED%]";
	protected final static String	DECODED						= "[%DECODED%]";
	protected final static String	PARTITIONED_ENCODED			= "[%PARTITIONED_ENCODED%]";
	protected final static String	PARTITION_DECODED			= "[%PARTITIONED_DECODED%]";
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
																		+ PARTITIONED_ENCODED
																		+ ")_"
																		+ ENCODED_BASE
																		+ " = "
																		+ PARTITION_DECODED
																		+ "_"
																		+ DECODED_BASE;
}
