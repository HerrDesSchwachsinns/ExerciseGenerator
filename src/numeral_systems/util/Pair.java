package numeral_systems.util;

public class Pair<S, T> {
	public final S	first;
	public final T	second;
	public Pair(S first, T second) {
		this.first = first;
		this.second = second;
	}
	public static <S, T> Pair<S, T> make_pair(S first, T second) {
		return new Pair<S, T>(first, second);
	}
	@Override
	public boolean equals(Object that) {
		if (that == null) return false;
		if (this == that) return true;
		if (this.getClass() != that.getClass()) return false;
		@SuppressWarnings("unchecked")
		Pair<S, T> otherPair = (Pair<S, T>) that;
		return ((this.first == otherPair.first || (this.first != null
				&& otherPair.first != null && this.first
					.equals(otherPair.first))) && (this.second == otherPair.second || (this.second != null
				&& otherPair.second != null && this.second
					.equals(otherPair.second))));
	}

	@Override
	public int hashCode() {
		int hashFirst = first != null ? first.hashCode() : 0;
		int hashSecond = second != null ? second.hashCode() : 0;

		return (hashFirst + hashSecond) * hashSecond + hashFirst;
	}

	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
}
