package numeral_systems.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ArrayUtils {

	/**
	 * creates a new array formed from the concatination of a and b
	 * 
	 * @param a
	 * @param b
	 * @return [a1,...,an,a1,...,bn]
	 */
	public static int[] join(int[] a, int[] b) {
		int[] r = new int[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}

	/**
	 * creates an array of arrays, each being a partition of a
	 * 
	 * @param a
	 *            an array
	 * @param fillDefault
	 *            if true the last element of the new array is exactly the same
	 *            size as all other even if the partition is not exactly
	 *            divisible by {@link partSize}. Also all remaining elements are
	 *            filled with zeros.
	 * @param partSize
	 *            the partition size
	 * @return [[a1,..,a{size},...,[a{i*size},...,an]]
	 */
	public static int[][] partition(int[] a, int partSize, boolean fillDefault) {
		final int size = a.length / partSize
				+ (a.length % partSize != 0 ? 1 : 0);
		int[][] part = new int[size][];
		/*
		 * [1 2 3 4 5 6 7] ->
		 * [[1 2] [3 4] [5 6] [7]]
		 */
		for (int i = 0; i < size; ++i) {
			final int start = i * partSize;
			int end = (i + 1) * partSize;
			if (!fillDefault) end = Math.min(end, a.length);
			part[i] = Arrays.copyOfRange(a, start, end);
		}
		return part;
	}
	/**
	 * reverses a in-place
	 * 
	 * @param a
	 */
	public static void reverse(int[] a) {
		for (int i = 0; i < a.length / 2; ++i)
			swap(i, a.length - 1 - i, a);
	}
	/**
	 * Augmented version of{@link Arrays#copyOfRange(boolean[], int, int)} with
	 * the addition of <code>to < from</code> handling. In such cases the values
	 * of the original array are inserted in reverted order.
	 * <p>
	 * The effect is as if the indices were inverted nad the result reversed
	 * 
	 * <pre>
	 * {@code
	 * sublist(a,x,y)
	 *     | x <= y = Arrays.copyOfRange(a,x,y)
	 *     | else   = reverse(Arrays.copyOfRange(a,y,x))
	 * 
	 * a                = [1 2 3 4]
	 * reverse(a)       = [4 3 2 1]
	 * copyOfRange(0,4) = [1 2 3 4]
	 * copyOfRange(4,0) = [4 3 2 1]
	 * }
	 * </pre>
	 * 
	 * example: <code>copyOfRange({1,2,3,4},3,1)={4,3,2}</code> .
	 * </p>
	 * 
	 * @param original
	 * @param from
	 * @param to
	 * @return
	 */
	public static int[] copyOfRange(int[] original, int from, int to) {
		if (from <= to) return Arrays.copyOfRange(original, from, to);
		int newLength = from - to;
		int[] copy = new int[newLength];
		for (int i = from - 1, j = 0; i >= to; --i, ++j) {
			copy[j] = original[i];
		}
		return copy;
	}
	/**
	 * create a copy of a with every element mapped to char with mapping
	 * function
	 * 
	 * @param a
	 * @return
	 */
	public static char[] asCharArray(int[] a,
			Function<Integer, Character> mapping) {
		char[] ca = new char[a.length];
		for (int i = 0; i < a.length; ++i)
			ca[i] = mapping.apply(a[i]);
		return ca;
	}
	/**
	 * swaps two elements in a
	 * 
	 * @param i
	 *            index of first element
	 * @param j
	 *            index of second element
	 * @param a
	 *            an array
	 */
	public static void swap(int i, int j, int[] a) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static Iterator<Integer> iterator(int[] a) {
		return new Iterator<Integer>() {
			private int	i	= 0;
			@Override
			public boolean hasNext() {
				return i < a.length;
			}

			@Override
			public Integer next() {
				if (!hasNext()) throw new NoSuchElementException();
				return a[i++];
			}
		};
	}
	public static <T> Iterator<T> iterator(T[] a) {
		return new Iterator<T>() {
			private int	i	= 0;
			@Override
			public boolean hasNext() {
				return i < a.length;
			}
			@Override
			public T next() {
				if (!hasNext()) throw new NoSuchElementException();
				return a[i++];
			}
		};
	}

	public static Iterator<Integer> reverseIterator(int[] a) {
		return new Iterator<Integer>() {
			private int	i	= a.length;
			@Override
			public boolean hasNext() {
				return i > 0;
			}

			@Override
			public Integer next() {
				if (!hasNext()) {
					throw new NoSuchElementException("at index " + i);
				}
				++i;
				return a[i];
			}
		};
	}

	public static int[] unboxIntArray(Integer[] a) {
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; ++i)
			b[i] = a[i];
		return b;
	}
	public static Integer[] boxIntArray(int[] a) {
		Integer[] b = new Integer[a.length];
		for (int i = 0; i < a.length; ++i)
			b[i] = a[i];
		return b;
	}
}
