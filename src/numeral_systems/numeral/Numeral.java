package numeral_systems.numeral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import numeral_systems.util.ArrayUtils;
import numeral_systems.util.DigitUtils;

public class Numeral implements Comparable<Numeral> {
	/**
	 * default ctor. Creates a new Numeral with value zero
	 */
	public Numeral() {
		digits = new int[1];
		zeroIndex = 0;
		minIndex = 0;
		maxIndex = 0;
	}
	/**
	 * deep copy constructor
	 * 
	 * @param that
	 */
	public Numeral(Numeral that) {
		this.digits = Arrays.copyOf(that.digits, that.digits.length);
		this.zeroIndex = that.zeroIndex;
		this.minIndex = that.zeroIndex;
		this.maxIndex = that.maxIndex;
	}
	/**
	 * string conversion ctor. Creates a new Numeral with value specified in str
	 * 
	 * @param str
	 *            number of form &lt;digits&gt;+[.&lt;digits&gt;+]
	 * @throws IllegalArgumentException
	 *             if str couldn't be parsed
	 */
	public Numeral(String str) {
		//not the most efficient one but who cares...
		//delete trailing/ending zeros
		int begin = 0, end = str.length() - 1;
		while (begin < str.length() - 1 && str.charAt(begin + 1) != '.'
				&& str.charAt(begin) == '0')
			++begin;
		if (str.indexOf('.') != -1) while (end > 0
				&& str.charAt(end - 1) != '.' && str.charAt(end) == '0')
			--end;
		if (end > 0 && str.charAt(end - 1) == '.' && str.charAt(end) == '0') end -= 2; // fractional part consists only of zeros -> cap them all
		str = str.substring(begin, end + 1);
		int decPointIndex = str.indexOf('.');
		if (decPointIndex != -1) {
			str = str.replace(".", "");
			zeroIndex = str.length() - decPointIndex;
		} else {
			zeroIndex = 0;
		}
		minIndex = 0;
		maxIndex = str.length() - 1;
		digits = new int[str.length()];
		for (int i = str.length() - 1; i >= 0; --i) {
			digits[digits.length - 1 - i] = DigitUtils.c2d(str.charAt(i));
		}
	}
	public Numeral(int x, int base) {
		if (!DigitUtils.isValidBase(base)) throw new IllegalArgumentException(
				"not valid base");
		digits = new int[DigitUtils.digits_for_base(base, x)];
		int i = 0;
		while (x != 0) {
			digits[i] = x % base;
			x /= base;
			++i;
		}
		zeroIndex = 0;
		minIndex = 0;
		maxIndex = digits.length - 1;

	}
	public Numeral(double x, int base) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	/**
	 * get digit at position pos. As Numeral is a infinite sequence not assigned
	 * positions yield zero (that is positions greater/less than
	 * minPos()/maxPos())
	 * 
	 * @param pos
	 * @return digit at pos
	 */
	public int get(int pos) {
		if (out_of_range(pos)) return 0;
		return digits[pos2index(pos)];
	}
	public void set(int pos, int value) {
		if (out_of_range(pos)) reserve(pos);
		digits[pos2index(pos)] = value;
		rearrange_indices(pos2index(pos));
	}
	public int minPos() {
		return index2pos(minIndex);
	}
	public int maxPos() {
		return index2pos(maxIndex);
	}

	//================ AXIOMS ================//

	public boolean isZero() {
		return minPos() == 0 && maxPos() == 0 && get(0) == 0;
	}
	/**
	 * add one to this in base
	 * 
	 * @param base
	 * @return this
	 */
	public Numeral addOne(int base) {
		int pos = 0;
		while (get(pos) + 1 >= base) {
			set(pos, 0);
			++pos;
		}
		set(pos, get(pos) + 1);
		return this;
	}

	//================ DIGIT ================//

	/**
	 * add digit d in base. Assumes 0 <= d < base (d is valid) and base is valid
	 * 
	 * @param d
	 * @param base
	 * @return this
	 */
	public Numeral add(int d, int base) {
		int pos = 0;
		int carry = add(pos, d, 0, base);
		while (carry != 0) {
			pos++;
			carry = add(pos, 0, carry, base);
		}
		return this;
	}
	private int add(int pos, int d, int carry, int base) {
		int y = get(pos) + d + carry;
		carry = y >= base ? 1 : 0;
		y = y >= 0 ? y - base : y;
		set(pos, y);
		return carry;
	}
	/**
	 * sub digit d in base. Assumes 0 <= d < base (d is valid) and d <= this and
	 * base is valid
	 * 
	 * @param d
	 * @param base
	 * @return this
	 */
	public Numeral sub(int d, int base) {
		int pos = 0;
		int carry = sub(pos, d, 0, base);
		while (carry != 0) {
			pos++;
			carry = sub(pos, d, carry, base);
		}
		return this;
	}
	private int sub(int pos, int d, int carry, int base) {
		int y = get(pos) - d - carry;
		carry = y < 0 ? 1 : 0;
		if (y < 0) y += base;
		set(pos, y);
		return carry;
	}
	/**
	 * mult this with d. Assumes 0 <= d < base (d is valid) and base is valid
	 * 
	 * @param d
	 * @param base
	 * @return this
	 */
	public Numeral mult(int d, int base) {
		int minPos = minPos();
		int maxPos = maxPos();
		int carry = 0;
		int pos;
		for (pos = minPos; pos <= maxPos; ++pos) {
			int y = get(pos) * d + carry;
			carry = y / base;
			y = y % base;
			set(pos, y);
		}
		set(pos + 1, carry);
		return this;
	}

	//================ NUMERAL ================//

	/**
	 * add that to this in base
	 * 
	 * @param that
	 * @param base
	 * @return this
	 */
	public Numeral add(Numeral that, int base) {
		int minPos = Math.min(this.minPos(), that.minPos());
		int maxPos = Math.max(this.maxPos(), that.maxPos());
		Numeral carry = new Numeral();

		for (int pos = minPos; pos <= maxPos + 1; ++pos) {
			int s = this.get(pos) + that.get(pos) + carry.get(pos);
			if (s >= base) { //overflow -> carry
				s -= base;
				carry.set(pos + 1, 1);
			}
			this.set(pos, s);
		}
		return this;
	}
	/**
	 * sub that to this in base. Assumes that 'that' is not greater than this
	 * and base is valid
	 * 
	 * @param that
	 * @param base
	 * @return this
	 */
	public Numeral sub(Numeral that, int base) {
		int maxPos = Math.max(this.maxPos(), that.maxPos());
		int minPos = Math.min(this.minPos(), that.minPos());

		int nextCarry = 0;
		for (int pos = minPos; pos <= maxPos + 1; ++pos) {
			int x = this.get(pos) - that.get(pos) - nextCarry;
			if (x < 0) {
				x += base;
				nextCarry = 1;
			} else {
				nextCarry = 0;
			}
			this.set(pos, x);
		}
		return this;
	}
	/**
	 * mult this with that in base
	 * 
	 * @param that
	 * @param base
	 * @return this
	 */
	public Numeral mult(Numeral that, int base) {
		List<Numeral> partMult = new ArrayList<>();
		//TODO implement
		throw new NotImplementedException();
	}
	/**
	 * shift this amount digits to left/right. A positive amount means shifting
	 * to the 'right' or greater positions, a negative amount shifting to the
	 * 'left' or smaller positions.
	 * 
	 * @param amount
	 * @return this
	 */
	public Numeral shift(int amount) {
		reserve(amount > 0 ? minPos() - amount : maxPos() - amount);//TODO reserve more efficiently
		zeroIndex -= amount;
		minIndex = Math.min(minIndex, zeroIndex);
		maxIndex = Math.max(maxIndex, zeroIndex);
		return this;
	}
	/**
	 * 
	 * @param base
	 * @return string representation of this numeral, appending base
	 */
	public String toString(int base) {
		return "(" + this.toString() + ")_" + base;
	}
	@Override
	public Numeral clone() {
		Numeral clone = new Numeral();
		clone.digits = Arrays.copyOf(digits, digits.length);
		clone.maxIndex = maxIndex;
		clone.minIndex = minIndex;
		clone.zeroIndex = zeroIndex;
		return clone;
	}
	public IntFrac toIntArray() {
		return new IntFrac(ArrayUtils.copyOfRange(digits, zeroIndex,
				maxIndex + 1), ArrayUtils.copyOfRange(digits, zeroIndex,
				minIndex));
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int pos = maxPos(); pos >= 0; --pos)
			b.append(DigitUtils.d2c(get(pos))); // integer part
		if (minPos() < 0) b.append('.'); //has fractional part
		for (int pos = -1; pos >= minPos(); --pos)
			b.append(DigitUtils.d2c(get(pos))); // fractional part

		return b.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Numeral) {
			Numeral that = (Numeral) obj;
			int minPos = Math.min(this.minPos(), that.minPos());
			int maxPos = Math.max(this.maxPos(), that.maxPos());
			for (int pos = maxPos; pos >= minPos; --pos)
				if (this.get(pos) != that.get(pos)) return false;
			return true;
		} else if (obj instanceof String) {
			try {
				return equals(new Numeral((String) obj)); //Not efficient
			} catch (Exception ex) {
				return false;
			}
		} else return false;
	}
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	@Override
	public int compareTo(Numeral that) {
		int diff = this.maxPos() - that.maxPos();
		if (diff != 0) return diff;
		for (int pos = maxPos(); pos >= Math.min(this.minPos(), that.minPos()); --pos) {
			diff = this.get(pos) - that.get(pos);
			if (diff != 0) return diff;
		}
		return 0;
	}

	/*
	 * value: 1 2 . 3 4 5
	 *                min       z max
	 *           |     |        |  |  |
	 * digits: [ 0  0  5  4  3  2  1  0 ]
	 *  index:   0  1  2  3  4  5  6  7
	 *    pos:        -3 -2 -1  0  1
	 */
	private int[]	digits;	// char would be sufficient but is tedious to write
	private int		zeroIndex;
	private int		minIndex;	//never greater than zeroIndex
	private int		maxIndex;	//never less than zeroIndex

	//transform pos to index into buffer
	private int pos2index(int pos) {
		return pos + zeroIndex;
	}
	//transform buffer index to pos
	private int index2pos(int index) {
		return index - zeroIndex;
	}
	//is pos valid?
	private boolean out_of_range(int pos) {
		return pos < minPos() || pos > maxPos();
	}
	//reserve as much space as is needed for storing a digit at pos
	private void reserve(int pos) {
		int index = pos2index(pos);
		if (index < 0) { //-> reserve space at left
			int[] newDigits = new int[digits.length + (-index)];
			System.arraycopy(digits, 0, newDigits, -index, digits.length);
			//rearrange indices
			minIndex += -index;
			maxIndex += -index;
			zeroIndex += -index;
			digits = newDigits;
		} else if (index >= digits.length) { //-> reserve space at right
			int[] newDigits = new int[digits.length
					+ (index - (digits.length - 1))];
			System.arraycopy(digits, 0, newDigits, 0, digits.length);
			digits = newDigits;
		} else {} // nothing to do
	}
	//rearrange maxIndex and minIndex after set(pos) operation
	private void rearrange_indices(int index) {
		minIndex = Math.min(minIndex, index);
		maxIndex = Math.max(maxIndex, index);
		while (minIndex < zeroIndex && digits[minIndex] == 0)
			++minIndex;
		while (maxIndex > zeroIndex && digits[maxIndex] == 0)
			--maxIndex;
	}
}
