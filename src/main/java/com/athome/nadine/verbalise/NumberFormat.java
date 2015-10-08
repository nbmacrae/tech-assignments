package com.athome.nadine.verbalise;

import java.util.Locale;

import com.athome.nadine.verbalise.provider.NumberFormat_en_GB;


/**
 * <h2>Worldpay programming task.</h2>
 * <p> 
 * <code>NumberFormat</code> is the abstract base class for all number
 * formats. This class provides the interface for returning numbers as words.
 * <p>
 * In this example, only one locale is supported: British English. 
 * If this were a server-side library, it is likely that more that one locale would need to be 
 * supported. I have chosen to implement behavior similar to standard Java Format classes.
 * <p>
 * To format a number for the current Locale, use one of the factory
 * class methods:
 * <blockquote>
 * <pre>{@code
 * myString = NumberFormat.getInstance().format(myNumber);
 * }</pre>
 * </blockquote>
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 *
 * <p>
 * As taken from <code>java.text.NumberFormat</code>, number formats are generally not synchronized.
 * It is recommended to create separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized
 * externally.
 * 
 * @see java.text.NumberFormat
 * @author Nadine Macrae
 */
abstract public class NumberFormat {

	// For purpose of this example, British English is default
	public static NumberFormat getInstance() {
		return new NumberFormat_en_GB();
	}

	/**
	 * Returns a number format for the specified locale.
	 * 
	 * @param myLocale	the desired locale
	 * @return the instance to use for verbalising a number
	 * @throws NullPointerException			if locale is null
	 * @throws UnsupportedLocaleException	if there is no implementation for the given locale
	 */
	public static NumberFormat getInstance(Locale myLocale) throws UnsupportedLocaleException {
		
		// no locale specified
		// handle in same as as Java Format classes
		if (myLocale == null)
			throw new NullPointerException();
		
		NumberFormat format = null;
		
		// only interested in country and language
		String country = myLocale.getCountry();
		String language = myLocale.getLanguage();
		String localeName = language+"_"+country;
		
		switch (localeName) {
		
		case "en_GB":
			format = new NumberFormat_en_GB();
			break;
			
		// for purpose of this example, if locale has been specified that is not supported
		// throw exception
		default:
			throw new UnsupportedLocaleException("Unable to verbalise number for this locale");
		
		}
		return format;
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	 /**
     * Formats the number into words. Supports numbers from 0 to 999999999.
     *
     * @param number     the number to format
     * @return the formatted string
	 * @throws IllegalArgumentException number is outside supported range.
     */	
	abstract public String format(int number)throws IllegalArgumentException;
}
