package numeral_systems.arithmetic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdditionTest.class, SubtractionTest.class,
		MultiplicationTest.class, DivisionTest.class })
public class ArithmeticTests {}
