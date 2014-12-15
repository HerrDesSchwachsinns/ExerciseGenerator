package numeral_systems.util;

public class StringUtils {
	public static String repeat(char c, int length) {
		StringBuilder b = new StringBuilder(length);
		for (int i = 0; i < length; ++i)
			b.append(c);
		return b.toString();
	}
}
