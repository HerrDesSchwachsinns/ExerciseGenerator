package numeral_systems.printer.conversion;

import numeral_systems.util.DigitUtils;
import numeral_systems.numeral.Numeral;
import numeral_systems.conversion.FastConversion;

public class FastConversionLaTeXPrinter extends FastConversionPrinter {

	public FastConversionLaTeXPrinter(FastConversion conv,
			String exerciseTemplateFile, String solutionTemplateFile) {
		super(conv, exerciseTemplateFile, solutionTemplateFile);
	}
	public FastConversionLaTeXPrinter(FastConversion conv) {
		this(conv, "FastConversionLaTeXExercise.template",
				"FastConversionLaTeXSolution.template");
	}
	@Override
	protected String numeralPartition(Numeral n, int partSize) {
		StringBuilder b = new StringBuilder();
		b.append("\\underbrace{");
		for (int i = n.maxPos(); i >= n.minPos(); --i) {
			b.append(DigitUtils.d2c(n.get(i)));
			if (i != n.minPos()) {
				if (i % partSize == 0) b.append("}\\underbrace{");
				if (i == 0) b.append(".");
			}
		}
		b.append("}");
		return b.toString();
	}
}
