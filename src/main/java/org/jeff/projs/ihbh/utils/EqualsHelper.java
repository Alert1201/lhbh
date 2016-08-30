package org.jeff.projs.ihbh.utils;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


public class EqualsHelper {

	/**
	 * Compares the two given objects and returns true, if they are equal and
	 * false, if they are not.
	 * 
	 * @param a
	 *            one of the two objects to compare
	 * @param b
	 *            the other one of the two objects to compare
	 * @return if the two given lists are equal.
	 */
	public static boolean areObjectsEqual(Object o1, Object o2) {

		return o1 == o2 || (o1 != null && o1.equals(o2));
	}

	public static boolean areDatesEqual(Date a, Date b) {
		if (a == b) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.getTime() == b.getTime();
	}
	
	public static boolean areStringsEquals(String s1, String s2){
		return StringUtils.equals(s1, s2);
	}
	
	public static boolean areArraysEquals(byte[] b1, byte[] b2){
		return Arrays.equals(b1, b2);
	}
}
