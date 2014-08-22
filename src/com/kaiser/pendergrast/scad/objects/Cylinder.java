package com.kaiser.pendergrast.scad.objects;

/**
 * Implementation of OpenSCAD's cylinder
 */
public class Cylinder extends ScadObject{

	private static final String SPHERE_SCAD = "sphere(r=%s, center=%s);";

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
	 * Construct a Cylider with two different radii and a height
	 * (note: one radius of 0 will produce a cone)
	 */
	public Cylinder(double radius1, double radius2, double height) {
		super();
		mRadius1 = radius1;
		mRadius2 = radius2;
		mHeight = height;
	}

	/**
	 * Construct a Cylider with two different radii, height,
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
	 * Return a copy of this Cylider object
	 */
	@Override
	public ScadObject copy() { 
		Cylinder copy = new Cylinder(mRadius1, mRadius2, mHeight, isCentered());
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}


}
