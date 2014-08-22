package com.kaiser.pendergrast.scad.objects;

/**
 * Implementation of OpenSCAD's cube
 */
public class Cube extends ScadObject{

	private static final String CUBE_SCAD = "cube([%s,%s,%s], center=%s);";

	private double mSizeX;
	private double mSizeY;
	private double mSizeZ;

	/**
	 * Construct a Cube with sides of length 0
	 */
	public Cube() {
		super();
	}

	/**
	 * Construct a Cube with all lengths the same
	 */
	public Cube(double size){
		this(size, size, size);
	}

	/**
	 * Construct a Cube with a given size
	 */
	public Cube(double x, double y, double z){
		super();
		mSizeX = x;
		mSizeY = y;
		mSizeZ = z;
	}

	/**
	 * Construct a cube with a given size
	 * and specify if the cube is centered or not
	 */
	public Cube(double x, double y, double z, boolean center){
		this(x, y, z);
		setCentered(center);
	}

	/**
	 * Construct a Cube with a given size
	 */
	public Cube(double[] size) {
		super(); 

		if(size.length != 3){
			throw new IllegalArgumentException("Size must have X, Y, Z lengths");
		}

		mSizeX = size[0];
		mSizeY = size[1];
		mSizeZ = size[2];
	}

	/**
	 * Construct a Cube with a given size
	 * and specify if the cube is centered or not
	 */
	public Cube(double[] size, boolean center) {
		this(size);
		setCentered(center);
	}

	@Override
	public String getObjectScad() {
		return String.format(CUBE_SCAD,
				"" + mSizeX, "" + mSizeY, "" + mSizeZ,
				"" + isCentered());
	}

	/**
	 * Return a copy of this Cube object
	 */
	@Override
	public ScadObject copy() { 
		Cube copy = new Cube(mSizeX, mSizeY, mSizeZ, isCentered());
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}

}
