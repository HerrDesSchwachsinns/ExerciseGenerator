package numeral_systems;

import numeral_systems.arithmetic.AustrianAddition;
import numeral_systems.arithmetic.AustrianSubtraction;
import numeral_systems.arithmetic.LongDivision;
import numeral_systems.arithmetic.LongMultiplication;
import numeral_systems.conversion.FastConversion;
import numeral_systems.conversion.HornerConversion;
import numeral_systems.numeral.Numeral;
import numeral_systems.printer.ExercisePrinter;
import numeral_systems.printer.arithmetic.AustrianAdditionPrinter;
import numeral_systems.printer.arithmetic.AustrianSubtractionPrinter;
import numeral_systems.printer.arithmetic.LongDivisionPrinter;
import numeral_systems.printer.arithmetic.LongMultiplicationPrinter;
import numeral_systems.printer.conversion.FastConversionAsciiPrinter;
import numeral_systems.printer.conversion.HornerConversionPrinter;

public class CliMain {
	public static void main(String[] args) {
		//		args = new String[] { "h" };
		args = new String[] { "/10", "10", "3" };
		//args = new String[] { "c", "2", "10", "0.3" };
		if (args.length < 1) {
			System.err.println("not enough parameter");
			printHelp();
			System.exit(1);
		}
		try {
			switch (args[0].charAt(0)) {
			case '+':
			case '-':
			case '*':
			case '/':
				arithmetic(args);
				break;
			case 'c':
			case 'f':
				conversion(args);
				break;
			case 'h':
				printHelp();
				System.exit(0);
				break;
			default:
				System.err.println("unknown parameter: " + args[0].charAt(0));
				System.exit(2);
			}
		} catch (Exception ex) {
			System.err.println(ex);
			printHelp();
			System.exit(1);
		}
	}
	private static void arithmetic(String[] args) {
		if (args.length != 3) {
			System.err.println("not enough parameter");
			System.exit(1);
		}
		int base = Integer.parseInt(args[0].substring(1));
		Numeral n1 = new Numeral(args[1]);
		Numeral n2 = new Numeral(args[2]);
		ExercisePrinter printer = null;
		switch (args[0].charAt(0)) {
		case '+':
			printer = new AustrianAdditionPrinter(new AustrianAddition(n1, n2,
					base));
			break;
		case '-':
			printer = new AustrianSubtractionPrinter(new AustrianSubtraction(
					n1, n2, base));
			break;
		case '*':
			printer = new LongMultiplicationPrinter(new LongMultiplication(n1,
					n2, base));
			break;
		case '/':
			printer = new LongDivisionPrinter(new LongDivision(n1, n2, base));
			break;
		default:
			assert (false);
		}
		System.out.println(printer.solution());
	}
	private static void conversion(String[] args) {
		if (args.length != 4) {
			System.err.println("not enough parameter");
			System.exit(1);
		}
		int decodedBase = Integer.parseInt(args[1]);
		int encodededBase = Integer.parseInt(args[2]);
		Numeral n = new Numeral(args[3]);
		ExercisePrinter printer = null;
		switch (args[0].charAt(0)) {
		case 'f':
			printer = new FastConversionAsciiPrinter(new FastConversion(
					decodedBase, encodededBase, n));
			break;
		case 'c':
			printer = new HornerConversionPrinter(new HornerConversion(
					decodedBase, encodededBase, n));
			break;
		default:
			assert (false);
		}
		System.out.println(printer.solution());
	}
	private static void printHelp() {
		System.out.println("numeral cli usage:");
		System.out.println("arithmetic:");
		System.out.println("\t[+-*/]<base> <numeral1> <numeral1>");
		System.out.println();
		System.out.println("conversion:");
		System.out.println("\t[cf] <decBase> <encBase> <numeral>");
		System.out.println();
		System.out.println("+\t addition");
		System.out.println("-\t subtraction");
		System.out.println("*\t multiplication");
		System.out.println("/\t division");
		System.out.println("c\t horner conversion");
		System.out.println("f\t fast conversion");
	}
}
