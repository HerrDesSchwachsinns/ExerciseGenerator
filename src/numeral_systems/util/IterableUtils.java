package numeral_systems.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableUtils {
	/**
	 * creates an unbounded int range with Iterable interface. USeful for
	 * zipping with a collection to obtain index values.
	 * 
	 * @param start
	 * @param step
	 * @return Iterable representing an unbounded integer range
	 *         <code>{x | forall i in N,x = start + i*step}</code>
	 */
	public static Iterable<Integer> counter(int start, int step) {

		return () -> {
			return new Iterator<Integer>() {
				private int	istart	= start;
				private int	istep	= step;
				public boolean hasNext() {
					return true;
				}
				public Integer next() {
					int tmp = istart;
					istart += istep;
					return tmp;
				}
			};
		};
	}
	public static <S, T> Iterable<Pair<S, T>> zip(Iterable<S> first,
			Iterable<T> second) {
		return () -> {
			final Iterator<S> firstIt = first.iterator();
			final Iterator<T> secondIt = second.iterator();
			return new Iterator<Pair<S, T>>() {
				public boolean hasNext() {
					return firstIt.hasNext() && secondIt.hasNext();
				}
				public Pair<S, T> next() {
					if (!hasNext()) throw new NoSuchElementException();
					return new Pair<S, T>(firstIt.next(), secondIt.next());
				}
			};
		};
	}
	public static <T> Iterable<T> asIterable(T[] a) {
		return () -> ArrayUtils.iterator(a);
	}
	public static Iterable<Integer> asIterable(int[] a) {
		return () -> ArrayUtils.iterator(a);
	}
}
