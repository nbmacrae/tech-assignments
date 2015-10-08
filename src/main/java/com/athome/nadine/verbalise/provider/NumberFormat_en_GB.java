package com.athome.nadine.verbalise.provider;

import com.athome.nadine.verbalise.NumberFormat;
import com.athome.nadine.verbalise.impl.NumberFormatImpl_en_GB;

/**
 * Specialisation of NumberFormat for British English.
 * 
 * @see com.athome.nadine.verbalise.NumberFormat
 *
 */

public class NumberFormat_en_GB extends NumberFormat {
	
    /**
     * Specialization of format.
     */
	@Override
	public String format(int number) throws IllegalArgumentException {
		
		// check number is in expected range
		if ((number < 0) || (number > 999999999)) {
			throw new IllegalArgumentException("Number is invalid: out of range");
		}
		
		NumberFormatImpl_en_GB impl = new NumberFormatImpl_en_GB();
		return impl.format(number);
		
	}
	

}
