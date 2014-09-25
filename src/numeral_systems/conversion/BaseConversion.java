package numeral_systems.conversion;

import numeral_systems.util.DigitUtils;
import numeral_systems.numeral.Numeral;

/**
 * BaseConversion is the base class of all conversion algorithms. In order to
 * implement a new base conversion algorithm one must inherit from this class
 * and override the {@link #doAlgorithm()} method.
 * 
 * <p>
 * This class creates a skeleton implementation of a base conversion algorithm
 * as it contains minimal parameter constraint checking every implementation
 * must respect. The result of the operation shall be saved to {@link #decoded}.
 * </p>
 * <p>
 * Every Conversion consists at least of the following parameters:
 * </p>
 * <ul>
 * <li>{@link #encBase} encoded/input base</li>
 * <li>{@link #decBase} decoded/output base</li>
 * <li>{@link #encoded} encoded/input value</li>
 * </ul>
 * <p>
 * An inheriting class can be sure that all these values a valid for a generic
 * conversion(bases are valid, encoded is in the specified base...). An
 * implementation may add additional constraints by overriding {@lin
 * #testParamConstraints()}.
 * </p>
 * 
 * @author Alexander Vogelgsang
 */
public abstract class BaseConversion {
	public BaseConversion(int decBase, int encBase, Numeral encoded) {
		this.decBase = decBase;
		this.encBase = encBase;
		this.encoded = encoded;
		this.decoded = new Numeral();
		testParamDefaultConstraints();
		doAlgorithm();
	}
	/**
	 * @return base in which encoded is represented
	 */
	public int encodedBase() {
		return encBase;
	}
	/**
	 * @return base in which decoded is represented
	 */
	public int decodedBase() {
		return decBase;
	}
	/**
	 * @return encoded Numeral in base {@link #encodedBase()}
	 */
	public Numeral encoded() {
		return encoded;
	}
	/**
	 * @return decoded Numeral in base {@link #decodedBase()}
	 */
	public Numeral decoded() {
		return decoded;
	}
	@Override
	public String toString() {
		return encoded().toString(encodedBase()) + "="
				+ decoded().toString(decodedBase());
	}
	/**
	 * implement this method for the actual work to be done. This method is
	 * called in the constructor after {@link #testParamConstraints()}
	 */
	protected abstract void doAlgorithm();
	/**
	 * override if you want to provide additional parameter constraints to your
	 * implementation. If a constraint is violated throw an
	 * {@link java.lang.IllegalArgumentException}. This Method is called in the
	 * constructor.
	 */
	protected void testParamConstraints() {}

	protected final int		encBase;
	protected final int		decBase;
	protected final Numeral	encoded;
	protected Numeral		decoded;

	private void testParamDefaultConstraints() throws IllegalArgumentException {
		if (!DigitUtils.isValidBase(encBase)) throw new IllegalArgumentException(
				"encoded base is not valid(" + encBase + ")");
		if (!DigitUtils.isValidBase(decBase)) throw new IllegalArgumentException(
				"encoded base is not valid(" + encBase + ")");
		//
		if (false/*TODO here: test if encoded is in specified base*/) throw new IllegalArgumentException(
				"encoded " + encoded + " is not in specified base(" + encBase
						+ ")");
		testParamConstraints();
	}
}
