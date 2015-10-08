package com.athome.nadine.verbalise.impl;

import java.text.DecimalFormat;

/**
 * This class converts a number to British English words.
 * <p>
 * The conversion to English words is mostly the same for the millions digits (m), 
 * the thousands digits (t) and the ones (o):
 * <p>
 * 		mmmtttooo
 * <p>
 * The inner class <code>Under1000</code> performs this common handling.
 */
public class NumberFormatImpl_en_GB {

	protected static final String[] multiplesOfTen = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	protected static final String[] under20 = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	protected static final String HUNDRED = " hundred";
	protected static final String HUNDRED_AND = " hundred and";
	protected static final String THOUSAND = " thousand";
	protected static final String MILLION = " million";
	protected static final String ZERO = "zero";
	protected static final String AND = " and";
	
	public NumberFormatImpl_en_GB () {
	}
	
	public String format(int number) {
		
		if (number == 0)
			return ZERO;
			
		// convert to string and pad with 0's
		String mask = "000000000";
        DecimalFormat df = new DecimalFormat(mask);
        String tempString = df.format(number);
        
        // partition 
        // common handling of 3 digit groupings
        Under1000 millions = new Under1000(tempString.substring(0, 3), MILLION);
        Under1000 thousands = new Under1000(tempString.substring(3, 6), THOUSAND);
        Under1000 ones = new Under1000(tempString.substring(6, 9));
        
        // handle special case
        // if we have millions or thousands and the last group of ones is below 99, insert an "and"
        String specialOnes = ((!millions.isZero() || !thousands.isZero()) && (ones.lessThan99())) ? AND+ones.getWords() : ones.getWords();
        String words = 	millions.getWords() +
		        		thousands.getWords() + 
		        		specialOnes;
			
        // remove whitespace 
		return words.trim();
	}
	
	private class Under1000 {
		
		private int hundreds, tens, units;
		private int number;
		char[] digits;
		String groupLabel = "";

		public Under1000(String stringNumber) {
			digits = stringNumber.toCharArray();
			number = Integer.parseInt(stringNumber);
			hundreds = (int) digits[0] & 0xF;
			tens = (int) digits[1] & 0xF;
			units = (int) digits[2] & 0xF;
		}

		public Under1000(String stringNumber, String groupLabel) {
			this(stringNumber);
			this.groupLabel = groupLabel;
		}

		public boolean isZero() {
			return (number == 0);
		}

		public boolean lessThan99() {
			return (hundreds == 0);
		}

		public String getWords() {

			if (isZero())
				return "";
			
			StringBuffer words = new StringBuffer();

			// if we have a digit in the hundreds column
			if (hundreds > 0) {
				words.append(under20[hundreds]);

				// if we have any tens and units
				if ((tens != 0) || (units != 0)) {
					words.append(HUNDRED_AND);
				} else {
					words.append(HUNDRED);
				}
			}

			// if we are under 20
			if ((tens > 0) && (tens < 2)) {
				words.append(under20[tens]);

				// else cater for 20 - 99
			} else {
				words.append(multiplesOfTen[tens]);
				words.append(under20[units]);
			}
			words.append(groupLabel);
			return words.toString();
		}
	}
}
