package com.kaiser.pendergrast.scad.operations;

import java.util.List;

import com.kaiser.pendergrast.scad.objects.Assembly;
import com.kaiser.pendergrast.scad.objects.ScadObject;

/**
 * Contains methods for difference, union
 * intersection, and other operations on ScadObjects
 */
public class Operations {

	private static final String DIFFERENCE_SCAD = 
						"difference() {\n" +
						"%s\n" + 
						"}";

	private static final String UNION_SCAD = 
						"union() {\n" +
						"%s\n" + 
						"}";

	private static final String INTERSECTION_SCAD = 
						"intersection() {\n" +
						"%s\n" + 
						"}";

	private static final String ROTATION_SCAD = 
						"rotation([%s,%s,%s]) {\n" +
						"%s\n" + 
						"}";

	private static final String MINKOWSKI_SCAD = 
						"minkowski() {\n" +
						"%s\n" + 
						"}";

	private static final String HULL_SCAD = 
						"hull() {\n" +
						"%s\n" + 
						"}";

	private static final String SCALE_SCAD = 
						"scale(v = [%s, %s, %s]) {\n" +
						"%s\n" + 
						"}";
	
	private static final String MIRROR_SCAD = 
			"mirror([%s, %s, %s]) {\n" +
			"%s\n" + 
			"}";

	private static Assembly doOperation(String operationScad, ScadObject... objects) {
		String objectScad = "";
		for(ScadObject object : objects) {
			objectScad += object.toScad() + "\n";
		}

		return new Assembly(String.format(operationScad, objectScad));
	}

	/**
	 * Find the difference of the ScadObjects
	 * The first ScadObject will have the intersections of the
	 * other ScadObjects removed from it
	 */
	public static Assembly difference(ScadObject... objects) {
		if(objects.length < 2) {
			throw new IllegalArgumentException("Difference needs at least two ScadObjects");
		}

		return doOperation(DIFFERENCE_SCAD, objects);
	}

	/**
	 * Find the difference of the ScadObjects
	 * The first ScadObject will have the intersections of the
	 * other ScadObjects removed from it
	 */
	public static Assembly difference(List<ScadObject> objects) {
		return Operations.difference((ScadObject[]) objects.toArray());
	}

	/**
	 * The intersection of the ScadObjects
	 * will be put into an Assembly
	 */
	public static Assembly intersecton(ScadObject... objects) {
		if(objects.length < 2) {
			throw new IllegalArgumentException("intersecton needs at least two ScadObjects");
		}

		return doOperation(INTERSECTION_SCAD, objects);
	}

	/**
	 * The intersection of the ScadObjects
	 * will be put into an Assembly
	 */
	public static Assembly intersecton(List<ScadObject> objects) {
		return Operations.intersecton((ScadObject[]) objects.toArray());
	}

	/**
	 * The intersection of the ScadObjects
	 * will be put into an Assembly
	 */
	public static Assembly intersecton(ScadObject object1, ScadObject object2) {
		return Operations.intersecton(object1, object2);
	}

	/**
	 * The ScadObjects will be unioned together
	 * into one Assembly
	 */
	public static Assembly union(ScadObject... objects) {
		if(objects.length < 2) {
			throw new IllegalArgumentException("union needs at least two ScadObjects");
		}

		return doOperation(UNION_SCAD, objects);
	}

	/**
	 * The ScadObjects will be unioned together
	 * into one Assembly
	 */
	public static Assembly union(List<ScadObject> objects) {
		return Operations.union((ScadObject[]) objects.toArray());
	}

	/**
	 * The ScadObjects will be rotated by the specified amount
	 */
	public static Assembly rotation(double rotationX, double rotationY, double rotationZ, ScadObject... objects) {
		return doOperation(String.format(ROTATION_SCAD, "" + rotationX, "" + rotationY, "" + rotationZ), objects);
	}

	/**
	 * The ScadObjects will be rotated by the specified amount
	 */
	public static Assembly rotation(double rotationX, double rotationY, double rotationZ, List<ScadObject> objects) {
		return rotation(rotationX, rotationY, rotationZ, (ScadObject[]) objects.toArray());
	}

	/**
	 * The ScadObjects will be rotated by the specified amount
	 */
	public static Assembly scale(double scaleX, double scaleY, double scaleZ, ScadObject... objects) {
		return doOperation(String.format(SCALE_SCAD, "" + scaleX, "" + scaleY, "" + scaleZ), objects);
	}

	/**
	 * The ScadObjects will be rotated by the specified amount
	 */
	public static Assembly scale(double scaleX, double scaleY, double scaleZ, List<ScadObject> objects) {
		return scale(scaleX, scaleY, scaleZ, (ScadObject[]) objects.toArray());
	}

	/**
	 * Performs a minkowski operation of the minkowskiObject
	 * on the object given
	 */
	public static Assembly minkowski(ScadObject object, ScadObject minkowskiObject) {
		return doOperation(MINKOWSKI_SCAD, object, minkowskiObject);
	}

	/**
	 * The ScadObjects will be hulled
	 */
	public static Assembly hull(ScadObject... objects) {
		return doOperation(HULL_SCAD, objects);
	}

	/**
	 * The ScadObjects will be hulled
	 */
	public static Assembly rotation(List<ScadObject> objects) {
		return hull((ScadObject[]) objects.toArray());
	}
	
	/**
	 * Mirror the ScadObject over the X axis
	 */
	public static Assembly mirrorX(ScadObject object) {
		return new Assembly(String.format(MIRROR_SCAD, 1, 0, 0, object.toScad()));
	}
	
	/**
	 * Mirror the ScadObject over the Y axis
	 */
	public static Assembly mirrorY(ScadObject object) {
		return new Assembly(String.format(MIRROR_SCAD, 0, 1, 0, object.toScad()));
	}
	
	/**
	 * Mirror the ScadObject over the Z axis
	 */
	public static Assembly mirrorZ(ScadObject object) {
		return new Assembly(String.format(MIRROR_SCAD, 0, 0, 1, object.toScad()));
	}
}
