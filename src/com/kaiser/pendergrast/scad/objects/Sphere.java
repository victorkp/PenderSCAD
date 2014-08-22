package com.kaiser.pendergrast.scad.objects;

/**
 * Implementation of OpenSCAD's sphere
 */
public class Sphere extends ScadObject{

	private static final String SPHERE_SCAD = "sphere(r=%s, center=%s);";

	private double mRadius;

	/**
	 * Construct a Sphere with radius 0
	 */
	public Sphere() {
		super();
	}

	/**
	 * Construct a Sphere with a specified radius
	 */
	public Sphere(double radius){
		super();
		mRadius = radius;
	}

	/**
	 * Construct a Sphere with a specified radius
	 * and specify if it is centered
	 */
	public Sphere(double radius, boolean center){
		this(radius);
		setCentered(center);	
	}

	@Override
	public String getObjectScad() {
		return String.format(SPHERE_SCAD,
				"" + mRadius,
				"" + isCentered());
	}

	/**
	 * Return a copy of this Cylider object
	 */
	@Override
	public ScadObject copy() { 
		Sphere copy = new Sphere(mRadius, isCentered());
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}

}
