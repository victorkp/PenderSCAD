package com.kaiser.pendergrast.scad.objects;

import java.util.Locale;

import com.kaiser.pendergrast.scad.util.Debug;

public abstract class ScadObject {

	private static final String GENERIC_SCAD = 
		"translate([%s,%s,%s]) {\n" + 
		"\trotation([%s,%s,%s]) {\n" +
		"\t\t%s\n" + 
		"\t}\n" + 
		"}\n";

	private static final String NO_TRANSLATION_SCAD = 
		"rotation([%s,%s,%s]) {\n" +
		"\t%s\n" + 
		"}\n";

	private static final String NO_ROTATION_SCAD = 
		"translate([%s,%s,%s]) {\n" +
		"\t%s\n" + 
		"}\n";

	private boolean mCenter = false;

	private double mTranslationX;
	private double mTranslationY;
	private double mTranslationZ;

	private double mRotationX;
	private double mRotationY;
	private double mRotationZ;

	public void setCentered(boolean centered) {
		mCenter = centered;
	}

	public boolean isCentered() {
		return mCenter;
	}

	public void setTranslation(double x, double y, double z) {
		mTranslationX = x;
		mTranslationY = y;
		mTranslationZ = z;
	}

	public double[] getTranslation() {
		return new double[]{mTranslationX, mTranslationY, mTranslationZ};
	}

	public void setTranslation(double[] translation) {
		if(translation.length != 3){
			throw new IllegalArgumentException("Translation array must be three doubles");
		}

		setTranslation(translation[0], translation[1], translation[2]);
	}

	public void setTranslationX(double x) {
		mTranslationX = x;
	}

	public void setTranslationY(double x) {
		mTranslationY = x;
	}

	public void setTranslationZ(double x) {
		mTranslationZ = x;
	}

	public double getTranslationX() {
		return mTranslationX;
	}

	public double getTranslationY() {
		return mTranslationY;
	}

	public double getTranslationZ() {
		return mTranslationZ;
	}

	public void setRotation(double x, double y, double z) {
		mRotationX = x;
		mRotationY = y;
		mRotationZ = z;
	}

	public void setRotation(double[] rotation) {
		if(rotation.length != 3){
			throw new IllegalArgumentException("Rotation array must be three doubles");
		}

		setRotation(rotation[0], rotation[1], rotation[2]);
	}

	public double[] getRotation() {
		return new double[]{mRotationX, mRotationY, mRotationZ};
	}

	public void setRotationX(double x) {
		mRotationX = x;
	}

	public void setRotationY(double x) {
		mRotationY = x;
	}

	public void setRotationZ(double x) {
		mRotationZ = x;
	}

	public double getRotationX() {
		return mRotationX;
	}

	public double getRotationY() {
		return mRotationY;
	}

	public double getRotationZ() {
		return mRotationZ;
	}

	/**
	 * @return if this ScadObject has a non-zero translation
	 */
	public boolean hasTranslation() {
		return (mTranslationX != 0 || mTranslationY != 0 || mTranslationZ != 0);
	}

	/**
	 * @return if this ScadObject has a non-zero rotation 
	 */
	public boolean hasRotation() {
		return (mRotationX != 0 || mRotationY != 0 || mRotationZ != 0);
	}

	/**
	 * Get this ScadObject's object dependent code
	 * Example: cube(size[1,2,3], center=true);
	 */
	public abstract String getObjectScad();

	/**
	 * Construct and return a copy of this ScadObject
	 */
	public abstract ScadObject copy();

	/**
	 * Translate this ScadObject into proper OpenSCAD code
	 */
	public String toScad() {
		boolean hasTranslation = hasTranslation();
		boolean hasRotation = hasRotation();

		if(hasTranslation && hasRotation) {
			return String.format(Locale.ENGLISH, GENERIC_SCAD,
					"" + mTranslationX, "" + mTranslationY, "" + mTranslationZ,
					"" + mRotationX, "" + mRotationY, "" + mRotationZ, getObjectScad());
		} else if (hasTranslation) {
			return String.format(Locale.ENGLISH, NO_ROTATION_SCAD,
					"" + mTranslationX, "" + mTranslationY, "" + mTranslationZ, getObjectScad());
		} else if (hasRotation) {
			return String.format(Locale.ENGLISH, NO_TRANSLATION_SCAD,
					"" + mRotationX, "" + mRotationY, "" + mRotationZ, getObjectScad());
		} else {
			return getObjectScad() + "\n";
		}
	}

	@Override
	public String toString() {
		return toScad();
	}

}
