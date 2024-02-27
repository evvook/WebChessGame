package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testCtoString {

	@Test
	void test() {
		char[] c = {'a','b','c'};
		String s = "abc";
		assertTrue(s.equals(String.valueOf(c)));
	}

}
