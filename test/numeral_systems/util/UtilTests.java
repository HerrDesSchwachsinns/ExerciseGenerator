package numeral_systems.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ numeral_systems.util.MathUtilsTest.class,
		numeral_systems.util.DigitUtilsTest.class,
		numeral_systems.util.ArrayUtilsTest.class })
public class UtilTests {}
