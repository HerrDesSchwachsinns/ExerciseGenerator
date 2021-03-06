package numeral_systems.printer.conversion;

import numeral_systems.conversion.HornerConversion;
import numeral_systems.numeral.Numeral;
import numeral_systems.printer.GenericPrinter;
import numeral_systems.util.ArrayUtils;
import numeral_systems.util.DigitUtils;
import numeral_systems.util.Pair;

public class HornerConversionPrinter extends GenericPrinter {
	public HornerConversionPrinter(HornerConversion conv) {
		super("HornerConversionExercise.template",
				"HornerConversionSolution.template", DEFAULT_EXERCISE_TEMPLATE,
				DEFAULT_SOLUTION_TEMPLATE);
		add(ENCODED, conv.encoded().toString());
		add(DECODED, conv.decoded().toString());
		add(ENCODED_BASE, Integer.toString(conv.encodedBase()));
		add(DECODED_BASE, Integer.toString(conv.decodedBase()));
		add(PARTIAL_FRACTION_RESULTS, partialFractionResults(conv));
		add(PARTIAL_INTEGER_RESULTS, partialIntegerResults(conv));
	}
	protected final static String	ENCODED						= "[%ENCODED%]";
	protected final static String	DECODED						= "[%DECODED%]";
	protected final static String	ENCODED_BASE				= "[%ENCODED_BASE%]";
	protected final static String	DECODED_BASE				= "[%DECODED_BASE%]";
	protected final static String	PARTIAL_FRACTION_RESULTS	= "[%PARTIAL_FRACTION_RESULTS%]";
	protected final static String	PARTIAL_INTEGER_RESULTS		= "[%PARTIAL_INTEGER_RESULTS%]";

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
	private String partialIntegerResults(HornerConversion conv) {
		StringBuilder b = new StringBuilder();
		int pos = conv.decoded().maxPos();

		String decBaseInEncBase = DigitUtils.int2baseStr(conv.encodedBase(),
				conv.decodedBase());

		for (int i = 0; i < conv.partialIntegerResults().size(); ++i) {
			Pair<Numeral, Numeral> p = conv.partialIntegerResults().get(i);
			b.append(p.first);
			b.append(" / ");
			b.append(decBaseInEncBase);
			b.append(" = ");
			b.append(p.second);
			b.append(" R ");
			int d = conv.decoded().get(pos--);
			b.append(DigitUtils.int2baseStr(conv.encodedBase(), d));
			b.append(" -> ");
			b.append(DigitUtils.d2c(d));
			b.append('\n');
		}
		return b.toString();
	}
	private String partialFractionResults(HornerConversion conv) {
		StringBuilder b = new StringBuilder();
		int pos = -1;

		String decBaseInEncBase = DigitUtils.int2baseStr(conv.encodedBase(),
				conv.decodedBase());

		for (Pair<Numeral, Numeral> p : conv.partialFractionResults()) {
			b.append(p.first);
			b.append(" * ");
			b.append(decBaseInEncBase);
			b.append(" = ");
			b.append(p.second); //TODO correct output format (base)
			b.append(" -> ");
			b.append(DigitUtils.d2c(conv.decoded().get(pos--)));
			b.append('\n');
		}
		return b.toString();
	}
}
