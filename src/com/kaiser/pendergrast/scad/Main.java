package com.kaiser.pendergrast.scad;

import com.kaiser.pendergrast.scad.objects.Cube;
import com.kaiser.pendergrast.scad.objects.Sphere;
import com.kaiser.pendergrast.scad.util.Debug;

public class Main {

	public static void main(String[] args) {
		
		Cube cube1 = new Cube(3, 4, 5, true);
		cube1.setTranslation(4.2, Math.PI, Math.E / 5);
		cube1.setRotation(19, 180, -35);
		
		Cube cube2 = (Cube) cube1.copy();
		cube2.setTranslationY(45678);
		
		Sphere sphere = new Sphere(3);
		
		
		Debug.write(cube1.toScad());
		Debug.write(cube2.toScad());
		Debug.write(sphere.toScad());

	}

}
