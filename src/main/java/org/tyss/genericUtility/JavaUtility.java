package org.tyss.genericUtility;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import io.qameta.allure.Step;

/**
 * This class contains java reusable methods
 * @author MOHAN GOWDA
 *
 */
public final class JavaUtility {
	/**
	 * This method is used to generate the Random Number with in limit
	 * @param int
	 */
	public int getRandomNumber(int limit) {
		return	new Random().nextInt(limit);
	}

	/**
	 * This method is used to generate the Random Number with in 1000
	 * @return
	 */
	public int getRandomNumber() {
		return	new Random().nextInt(1000);
	}


	/**
	 * This method is used to convert the String to long data type
	 * @param stringData
	 * @return
	 */
	public long convertStringToLong(String stringData) {
		return Long.parseLong(stringData);
	}

	/**
	 * This method is used to print the values in console
	 * @param value
	 */
	@Step("{0}")
	public void printStatement(String value) {
		System.out.println(value);
		
	}

	/**
	 * This method is used to split the string based on strategy
	 * @param value
	 * @param strategy
	 * @return
	 */
	public String[] splitString(String value, String strategy) {
		return value.split(strategy);
	}

	/**
	 * This method is used to get current data in specified strategy
	 * @param strategy
	 * @return
	 */
	public String getCurrentDate(String strategy) {		
		return new SimpleDateFormat(strategy).format(new Date());
	}


	public String convertFromIntegerToString(int number) {
		return String.valueOf(number);
	}

	public int convertMonthFromStringToInt(String monthName, String strategy) {
		return DateTimeFormatter.ofPattern(strategy)
				.withLocale(Locale.ENGLISH)
				.parse(monthName)
				.get(ChronoField.MONTH_OF_YEAR);
	}


}
