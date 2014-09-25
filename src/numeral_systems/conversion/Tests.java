package numeral_systems.conversion;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import numeral_systems.numeral.Numeral;
import numeral_systems.printer.ExercisePrinter;
import numeral_systems.printer.FastConversionAsciiPrinter;
import numeral_systems.printer.FastConversionLaTeXPrinter;

public class Tests {
	public static void main(String[] args) {
		FastConversion conv = new FastConversion(16, 2, new Numeral(
				"10110101.01"));
		ExercisePrinter printer;
		//		printer = new FastConversionLaTeXPrinter(conv);
		printer = new FastConversionAsciiPrinter(conv);
		try (BufferedWriter writer = Files
				.newBufferedWriter(Paths
						.get("C:\\Users\\vogl\\workspace\\Uni\\Praktikum\\Latex Tests\\template.tex"))) {
			//			System.out.println(printer.exercise());
			//			writer.write(printer.exercise());
			System.out.println(printer.solution());
			writer.write(printer.solution());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
