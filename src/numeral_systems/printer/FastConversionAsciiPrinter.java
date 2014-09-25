package numeral_systems.printer;

import numeral_systems.util.DigitUtils;
import numeral_systems.numeral.Numeral;
import numeral_systems.conversion.FastConversion;

public class FastConversionAsciiPrinter extends FastConversionPrinter {

	public FastConversionAsciiPrinter(FastConversion conv,
			String exerciseTemplateFile, String solutionTemplateFile) {
		super(conv, exerciseTemplateFile, solutionTemplateFile);
	}
	public FastConversionAsciiPrinter(FastConversion conv) {
		this(conv, "FastConversionAsciiExercise.template",
				"FastConversionAsciiSolution.template");
	}
	@Override
	protected String numeralPartition(Numeral n, int partSize) {
		StringBuilder b = new StringBuilder();
		for (int i = n.maxPos(); i >= n.minPos(); --i) {
			b.append(DigitUtils.d2c(n.get(i)));
			if (i != n.minPos()) {
				if (i % partSize == 0) b.append("|");
				if (i == 0) b.append(".");
			}
		}
		return b.toString();
	}
}
