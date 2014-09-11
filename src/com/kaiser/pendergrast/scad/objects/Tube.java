package com.kaiser.pendergrast.scad.objects;

/**
 * Implementation of OpenSCAD's cylinder
 */
public class Tube extends ScadObject{

	private static final String TUBE_SCAD =
						"difference() {\n" + 
						"\tcylinder(r=%s, h=%s, center=%s);\n" +
						"\tcylinder(r=%s, h=%s, center=%s);\n" +
						"}";

	private double mRadiusOuter;

	private double mWallThickness;

	private double mLength;

	/**
	 * Construct a Tube with radius and length 0
	 */
	public Tube() {
		super();
	}

	/**
	 * Construct a Tube with inner and outer radii, and a length
	 */
	public Tube(double radiusOuter, double wallThickness, double length) {
		super();
		mRadiusOuter = radiusOuter;
		mWallThickness = wallThickness;
		mLength = length;
	}

	/**
	 * Construct a Tube with inner and outer radii, length,
	 * and specify if if is centered
	 */
	public Tube(double radiusOuter, double wallThickness, double length, boolean center) {
		this(radiusOuter, wallThickness, length);
		setCentered(center);
	}

	public void setOuterRadius(double radius) {
		mRadiusOuter = radius;
	}

	public void setWallThickness(double thickness) {
		mWallThickness = thickness;
	}

	public void setLength(double length){
		mLength = length;
	}

	public double getOuterRadius() {
		return mRadiusOuter;
	}

	public double getWallThickness() {
		return mWallThickness;
	}

	public double getLength() {
		return mLength;
	}

	@Override
	public String getObjectScad() {
		return String.format(TUBE_SCAD,
				"" + mRadiusOuter,
				"" + mLength,
				"" + isCentered(),
				"" + (mRadiusOuter - 2 * mWallThickness),
				"" + mLength,
				"" + isCentered());
	}

	/**
	 * Return a copy of this Cylider object
	 */
	@Override
	public ScadObject copy() { 
		Tube copy = new Tube(mRadiusOuter, mWallThickness, mLength, isCentered());
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}

}
