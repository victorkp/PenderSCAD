package com.kaiser.pendergrast.scad.objects;

/**
 * Implementation of OpenSCAD's cylinder
 */
public class Cylinder extends ScadObject{

	private static final String SPHERE_SCAD = "cylinder(r1=%s, r2=%s, h=%s, center=%s);";

	private double mRadius1;
	private double mRadius2;

	private double mHeight;

	/**
	 * Construct a Cylinder with radius and height 0
	 */
	public Cylinder() {
		super();
	}

	/**
	 * Construct a Cylinder with a specified radius and height
	 */
	public Cylinder(double radius, double height) {
		super();
		mRadius1 = mRadius2 = radius;
		mHeight = height;
	}

	/**
	 * Construct a Cylinder with a specified radius and height
	 * and specify if it is centered
	 */
	public Cylinder(double radius, double height, boolean center) {
		this(radius, height);
		setCentered(center);
	}

	/**
	 * Construct a Cylinder with two different radii and a height
	 * (note: one radius of 0 will produce a cone)
	 */
	public Cylinder(double radius1, double radius2, double height) {
		super();
		mRadius1 = radius1;
		mRadius2 = radius2;
		mHeight = height;
	}

	public void setRadius1(double radius) {
		mRadius1 = radius;
	}

	public void setRadius2(double radius) {
		mRadius2 = radius;
	}

	public void setHeight(double height) {
		mHeight = height;
	}

	public double getRadius1() {
		return mRadius1;
	}

	public double getRadius2() {
		return mRadius2;
	}

	public double getHeight() {
		return mHeight;
	}


	/**
	 * Construct a Cylinder with two different radii, height,
	 * and specify if if is centered
	 * (note: one radius of 0 will produce a cone)
	 */
	public Cylinder(double radius1, double radius2, double height, boolean center) {
		this(radius1, radius2, height);
		setCentered(center);
	}

	@Override
	public String getObjectScad() {
		return String.format(SPHERE_SCAD,
				"" + mRadius1,
				"" + mRadius2,
				"" + mHeight,
				"" + isCentered());
	}

	/**
	 * Return a copy of this Cylinder object
	 */
	@Override
	public ScadObject copy() { 
		Cylinder copy = new Cylinder(mRadius1, mRadius2, mHeight, isCentered());
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}


}
