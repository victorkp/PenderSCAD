package com.kaiser.pendergrast.scad.objects;

import java.util.Locale;

import com.kaiser.pendergrast.scad.util.Debug;

public abstract class ScadObject {

	private static final String GENERIC_SCAD = 
		"translation([%s,%s,%s]) {\n" + 
		"\trotation([%s,%s,%s]) {\n" +
		"\t\t%s" + 
		"\t}" + 
		"}";

	private double mTranslationX;
	private double mTranslationY;
	private double mTranslationZ;

	private double mRotationX;
	private double mRotationY;
	private double mRotationZ;

	public void setTranslation(double x, double y, double z) {
		mTranslationX = x;
		mTranslationY = y;
		mTranslationZ = z;
	}

	public double[] getTranslation() {
		return new double[]{mTranslationX, mTranslationY, mTranslationZ};
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

	public abstract String getObjectScad();

	public String toScad() {
		return String.format(Locale.ENGLISH, GENERIC_SCAD,
			       	"" + mTranslationX, "" + mTranslationY, "" + mTranslationZ,
				"" + mRotationX, "" + mRotationY, "" + mRotationZ, getObjectScad());
					
	}


}
