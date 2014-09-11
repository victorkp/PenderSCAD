package com.kaiser.pendergrast.scad;

import com.kaiser.pendergrast.scad.objects.Assembly;
import com.kaiser.pendergrast.scad.objects.Cube;
import com.kaiser.pendergrast.scad.objects.Sphere;
import com.kaiser.pendergrast.scad.objects.SquareTube;
import com.kaiser.pendergrast.scad.objects.Tube;
import com.kaiser.pendergrast.scad.operations.Operations;
import com.kaiser.pendergrast.scad.util.Debug;

public class Main {

	public static void main(String[] args) {
		
		Cube cube1 = new Cube(3, 4, 5, true);
		cube1.setTranslation(4.2, Math.PI, Math.E / 5);
		cube1.setRotation(19, 180, -35);
		
		Cube cube2 = (Cube) cube1.copy();
		cube2.setTranslationY(45678);
		
		Sphere sphere = new Sphere(3);

		Assembly diff = Operations.difference(sphere, cube1, cube2);
		diff.setTranslation(7, 3, 4);
		diff.setRotation(90, 180, 270);

		Tube tube1 = new Tube(2, 0.125, 6, true);

		Tube tube2 = new Tube(2, 0.125, 6, false);
		tube2.setTranslationX(6);

		SquareTube tube3 = new SquareTube(2, 0.125, 6, true);
		tube3.setTranslationX(-6);

		Debug.write(tube1);
		Debug.write(tube2);
		Debug.write(tube3);
	}

}
