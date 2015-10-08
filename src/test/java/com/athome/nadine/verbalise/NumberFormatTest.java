package com.athome.nadine.verbalise;

import static org.junit.Assert.*;
import java.util.Locale;

import org.junit.Test;

@SuppressWarnings("unused")
public class NumberFormatTest {

	@Test
	public void testGetInstance_default() {
		NumberFormat format = NumberFormat.getInstance();
		assertNotNull(format);
	}

	@Test
	public void testGetInstance_en_GB_ManualConstruction() {
		try {
			NumberFormat format = NumberFormat.getInstance(new Locale("en", "GB"));
			assertNotNull(format);
		} catch (UnsupportedLocaleException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testGetInstance_en_GB_Predefined() {
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.UK);
			assertNotNull(format);
		} catch (UnsupportedLocaleException e) {
			fail("Not expecting an exception");
		}
	}
	
	@Test
	public void testGetInstance_en_GB_Cambridgeshire() {
		try {
			NumberFormat format = NumberFormat.getInstance(new Locale("en", "GB", "Cambrideshire"));
			assertNotNull(format);
		} catch (UnsupportedLocaleException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testGetInstance_en_US() {
		try {
			NumberFormat format = NumberFormat.getInstance(new Locale("en", "US"));
			fail("Expecting an exception");
		} catch (UnsupportedLocaleException e) {}
	}

	@Test
	public void testGetInstance_fr_GB() {
		try {
			NumberFormat format = NumberFormat.getInstance(new Locale("fr", "GB"));
			fail("Expecting an exception");
		} catch (UnsupportedLocaleException e) {}
	}
	
	@Test
	// assumes you have British English set on local machine
	public void testGetInstance_SystemDefaultLocale() {
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			assertNotNull(format);
		} catch (UnsupportedLocaleException e) {
			fail("Not expecting an exception");
		}
	}
	
	@Test
	public void testGetInstance_en_GB_() {
		try {
			NumberFormat format = NumberFormat.getInstance(new Locale("en", "GB"));
			assertNotNull(format);
		} catch (UnsupportedLocaleException e) {
			fail("Not expecting an exception");
		}
	}

	@Test
	public void testGetInstance_NullLocale() {
		try {
			NumberFormat format = NumberFormat.getInstance(null);
			fail("Expecting an exception");
		} catch (NullPointerException e) {
		} catch (UnsupportedLocaleException e) {
			fail("Not expecting this exception");
		}
	}

	@Test
	public void testGetInstance_UnsupportedLocale() {
		try {
			NumberFormat format = NumberFormat.getInstance(new Locale("some", "nonsense"));
			fail("Expecting an exception");
		} catch (UnsupportedLocaleException e) {}
	}
}
