package com.kaiser.pendergrast.scad.util;


/**
 * Handles writing debug messages to Systemm.out
 */
public class Debug {

	public static int VERBOSE = 0;
	public static int DEFAULT = 1;
	public static int ERROR = 2;
	public static int NONE = 3;

	private static int mDebugLevel = VERBOSE;

	/**
	 * Debug to the console with at a certain debug level
	 * (VERBOSE, DEFAULT, or ERROR)
	 */
	public static void write(String message, int debugLevel) {
		if(debugLevel >= mDebugLevel) {
			System.out.println(message);
		}
	}

	/**
	 * Debug to the console with a DEFAULT debug level
	 */
	public static void write(String message) {
		write(message, DEFAULT);
	}

	/**
	 * Set the lowest level of debug messages allowed
	 * (VERBOSE, DEFAULT, ERROR, or NONE)
	 */
	public static void setDebugLevel(int debugLevel) {

	}


}
