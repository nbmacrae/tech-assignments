package com.athome.nadine.verbalise;

import static org.junit.Assert.*;
import org.junit.Test;

public class NumberFormat_en_GBTest {

	@Test
	public void testFormat_InputOutOfRange() {
		
		NumberFormat formatter = NumberFormat.getInstance();
		
		try {
			formatter.format(-1);
			fail("Expecting an exception");
		} catch (IllegalArgumentException e) {}
		
		try {
			formatter.format(1000000000);
			fail("Expecting an exception");
		} catch (IllegalArgumentException e) {}
		
	}

	
	@Test
	public void testFormat_Limits() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String bottom = formatter.format(0);
			assertEquals("zero", bottom);
			String top = formatter.format(999999999);
			assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", top);
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}
	
	@Test
	public void testFormat_MissingHundreds() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(99099099);
			assertEquals("ninety nine million ninety nine thousand and ninety nine", s);
			s = formatter.format(99);
			assertEquals("ninety nine", s);
			
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testFormat_MissingTens() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(909909909);
			assertEquals("nine hundred and nine million nine hundred and nine thousand nine hundred and nine", s);
			s = formatter.format(909);
			assertEquals("nine hundred and nine", s);
			
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testFormat_MissingUnits() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(990990990);
			assertEquals("nine hundred and ninety million nine hundred and ninety thousand nine hundred and ninety", s);
			s = formatter.format(990);
			assertEquals("nine hundred and ninety", s);
			
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}
	
	@Test
	public void testFormat_HundredsOnly() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(900900900);
			assertEquals("nine hundred million nine hundred thousand nine hundred", s);
			s = formatter.format(900);
			assertEquals("nine hundred", s);
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testFormat_TensOnly() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(90090090);
			assertEquals("ninety million ninety thousand and ninety", s);
			s = formatter.format(90);
			assertEquals("ninety", s);
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}
	
	@Test
	public void testFormat_UnitsOnly() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(9009009);
			assertEquals("nine million nine thousand and nine", s);
			s = formatter.format(9);
			assertEquals("nine", s);
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testFormat_TestSampleData() {
		NumberFormat formatter = NumberFormat.getInstance();
		try {
			String s = formatter.format(0);
			assertEquals("zero", s);
			s = formatter.format(1);
			assertEquals("one", s);
			s = formatter.format(21);
			assertEquals("twenty one", s);
			s = formatter.format(105);
			assertEquals("one hundred and five", s);
			s = formatter.format(123);
			assertEquals("one hundred and twenty three", s);
			s = formatter.format(1005);
			assertEquals("one thousand and five", s);
			s = formatter.format(1042);
			assertEquals("one thousand and forty two", s);
			s = formatter.format(1105);
			assertEquals("one thousand one hundred and five", s);
			s = formatter.format(1105);
			assertEquals("one thousand one hundred and five", s);
			s = formatter.format(56945781);
			assertEquals("fifty six million nine hundred and forty five thousand seven hundred and eighty one", s);
			s = formatter.format(999999999);
			assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", s);
		} catch (IllegalArgumentException e) {
			fail("Not expecting an exception");
		}
	}
	
}
