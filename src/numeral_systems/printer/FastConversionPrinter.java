package numeral_systems.printer;

import static numeral_systems.util.Pair.make_pair;

import java.util.ArrayList;
import java.util.List;

import numeral_systems.numeral.Numeral;
import numeral_systems.util.Pair;
import numeral_systems.conversion.FastConversion;

public abstract class FastConversionPrinter extends ExercisePrinter {
	public FastConversionPrinter(FastConversion conv,
			String exerciseTemplateFile, String solutionTemplateFile) {
		this.exerciseTemplateFile = exerciseTemplateFile;
		this.solutionTemplateFile = solutionTemplateFile;
		variables = new ArrayList<>();
		variables.add(make_pair(ENCODED, conv.encoded().toString()));
		variables.add(make_pair(DECODED, conv.decoded().toString()));
		variables.add(make_pair(PARTITIONED_ENCODED,
				numeralPartition(conv.encoded(), conv.baseRatio().first)));
		variables.add(make_pair(PARTITION_DECODED,
				numeralPartition(conv.decoded(), conv.baseRatio().second)));
		variables.add(make_pair(ENCODED_BASE,
				Integer.toString(conv.encodedBase())));
		variables.add(make_pair(DECODED_BASE,
				Integer.toString(conv.decodedBase())));
	}
	protected abstract String numeralPartition(Numeral n, int partSize);

	@Override
	protected String exerciseTemplateFile() {
		return exerciseTemplateFile;
	}
	@Override
	protected String solutionTemplateFile() {
		return solutionTemplateFile;
	}
	@Override
	protected List<Pair<String, String>> placeholders() {
		return variables;
	}
	@Override
	protected String defaultExerciseTemplate() {
		return DEFAULT_EXERCISE_TEMPLATE;
	}
	@Override
	protected String defaultSolutionTemplate() {
		return DEFAULT_SOLUTION_TEMPLATE;
	}
	private String						exerciseTemplateFile;
	private String						solutionTemplateFile;
	private List<Pair<String, String>>	variables;

	protected final static String		ENCODED						= "[%ENCODED%]";
	protected final static String		DECODED						= "[%DECODED%]";
	protected final static String		PARTITIONED_ENCODED			= "[%PARTITIONED_ENCODED%]";
	protected final static String		PARTITION_DECODED			= "[%PARTITIONED_DECODED%]";
	protected final static String		ENCODED_BASE				= "[%ENCODED_BASE%]";
	protected final static String		DECODED_BASE				= "[%DECODED_BASE%]";

	private final static String			DEFAULT_EXERCISE_TEMPLATE	= "("
																			+ ENCODED
																			+ ")_"
																			+ ENCODED_BASE
																			+ " = "
																			+ "("
																			+ "?"
																			+ ")_"
																			+ DECODED_BASE;
	private final static String			DEFAULT_SOLUTION_TEMPLATE	= "("
																			+ PARTITIONED_ENCODED
																			+ ")_"
																			+ ENCODED_BASE
																			+ " = "
																			+ PARTITION_DECODED
																			+ "_"
																			+ DECODED_BASE;
}
