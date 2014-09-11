package com.kaiser.pendergrast.scad.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import com.kaiser.pendergrast.scad.objects.ScadObject;


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
			System.out.println(tabFormat(message));
		}
	}

	/**
	 * Debug to the console with a DEFAULT debug level
	 */
	public static void write(String message) {
		write(message, DEFAULT);
	}

	/**
	 * Debug to the console with at a certain debug level
	 * (VERBOSE, DEFAULT, or ERROR)
	 */
	public static void write(ScadObject object, int debugLevel) {
		write("" + object, debugLevel);
	}

	/**
	 * Debug to the console with a DEFAULT debug level
	 */
	public static void write(ScadObject object) {
		write("" + object);
	}

	/**
	 * Set the lowest level of debug messages allowed
	 * (VERBOSE, DEFAULT, ERROR, or NONE)
	 */
	public static void setDebugLevel(int debugLevel) {
		mDebugLevel = debugLevel;
	}

	public static String tabFormat(String scad) {
		String formatted = "";

		StringReader sReader = new StringReader(scad);
		BufferedReader reader = new BufferedReader(sReader);

		try {
			int tabNumber = 0;
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				if(line.contains("}")) {
					tabNumber--;
				}

				for(int i = 0; i < tabNumber; i++) {
					formatted += "\t";
				}

				formatted += line.replace("\t", "") + "\n";

				if(line.contains("{")) {
					tabNumber++;
				}
			}
		} catch (IOException e){ 
			Debug.write(e.getMessage(), Debug.ERROR);
		}


		return formatted;
	}


}
