package com.kaiser.pendergrast.scad.objects;

/**
 * Implementation of OpenSCAD's cylinder
 */
public class SquareTube extends ScadObject{

	private static final String TUBE_CENTERED_SCAD =
						"difference() {\n" + 
						"\tcube(size=[%s,%s,%s], center=true);\n" +
						"\tcube(size=[%s,%s,%s], center=true);\n" +
						"}";

	private static final String TUBE_NOT_CENTERED_SCAD =
						"difference() {\n" + 
						"\tcube(size=[%s,%s,%s], center=true);\n" +
						"\ttranslate([%s,%s,0]) {\n" +
						"\tcube(size=[%s,%s,%s], center=true);\n" +
						"\t}\n" +
						"}";

	private double mTubeWidth;

	private double mWallThickness;

	private double mLength;

	/**
	 * Construct a SquareTube with radius and length 0
	 */
	public SquareTube() {
		super();
	}

	/**
	 * Construct a SquareTube with inner and outer radii, and a length
	 */
	public SquareTube(double tubeWidth, double wallThickness, double length) {
		super();
		mTubeWidth = tubeWidth;
		mWallThickness = wallThickness;
		mLength = length;
	}

	/**
	 * Construct a SquareTube with inner and outer radii, length,
	 * and specify if if is centered
	 */
	public SquareTube(double tubeWidth, double wallThickness, double length, boolean center) {
		this(tubeWidth, wallThickness, length);
		setCentered(center);
	}

	public void setTubeWidth(double width) {
		mTubeWidth = width;
	}

	public void setWallThickness(double thickness) {
		mWallThickness = thickness;
	}

	public void setLength(double length){
		mLength = length;
	}

	public double getTubeWidth() {
		return mTubeWidth;
	}

	public double getWallThickness() {
		return mWallThickness;
	}

	public double getLength() {
		return mLength;
	}

	@Override
	public String getObjectScad() {
		if(isCentered()) {
			return String.format(TUBE_CENTERED_SCAD,
					"" + mTubeWidth,
					"" + mTubeWidth,
					"" + mLength,
					"" + (mTubeWidth -  2 * mWallThickness),
					"" + (mTubeWidth -  2 * mWallThickness),
					"" + mLength);
		} else {
			// Need to use offsets to get inner cylinder centered in outer cylinder
			return String.format(TUBE_NOT_CENTERED_SCAD,
					"" + mTubeWidth,
					"" + mTubeWidth,
					"" + mLength,
					"" + mWallThickness,
					"" + mWallThickness,
					"" + (mTubeWidth -  2 * mWallThickness),
					"" + (mTubeWidth -  2 * mWallThickness),
					"" + mLength);
		}
	}

	/**
	 * Return a copy of this Cylider object
	 */
	@Override
	public ScadObject copy() { 
		SquareTube copy = new SquareTube(mTubeWidth, mWallThickness, mLength, isCentered());
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}

}
