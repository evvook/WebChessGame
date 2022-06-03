package utils;

public class CharCalculater {
	public static Character increase(char c) {
		int ascii = (int)c + 1;
		return (char) ascii;
	}
	public static Character decrease(char c) {
		int ascii = (int)c - 1;
		return (char) ascii;
	}
}
