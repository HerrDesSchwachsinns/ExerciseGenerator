package numeral_systems;

import numeral_systems.arithmetic.ArithmeticTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ numeral_systems.util.UtilTests.class, ArithmeticTests.class })
public class AllTests {}