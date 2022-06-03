package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.CharCalculater;

class testCharCalculater {

	@Test
	void test() {
		System.out.println((char)91);
		System.out.println((int)'a');
		Character a = 'a';
		System.out.println(a.charValue());
		int uniN = Character.getNumericValue('a');
		char[] uniC = Character.toChars(uniN);
		System.out.println(uniC[0]);
	}

}
