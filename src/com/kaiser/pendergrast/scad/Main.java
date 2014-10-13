package com.kaiser.pendergrast.scad;

import com.kaiser.pendergrast.scad.objects.Assembly;
import com.kaiser.pendergrast.scad.objects.Cube;
import com.kaiser.pendergrast.scad.objects.Cylinder;
import com.kaiser.pendergrast.scad.objects.ScadObject;
import com.kaiser.pendergrast.scad.operations.Operations;
import com.kaiser.pendergrast.scad.util.Debug;

public class Main {

	public static void main(String[] args) {
		double shellLength = 65;
		double shellRimRadius = 12;
		double wallWidth = 8;
		
		double numberOfShells = 4.6;
		double openingWidthPercentage = 0.95;
		
		double beltHeight = 50;
		double beltThickness = 10;
		
		ScadObject topCylinder = new Cylinder(shellRimRadius + wallWidth, shellLength + 2 * wallWidth).setRotation(0, 90, 0);
		ScadObject bottomCylinder = topCylinder.copy();
		bottomCylinder.setTranslation(0, -shellRimRadius * numberOfShells, 0);
		ScadObject bottomBox = new Cube(shellLength + 2 * wallWidth, 2 * (shellRimRadius + wallWidth), 2 * (shellRimRadius + wallWidth), true)
								.setTranslationX(shellLength/2 + wallWidth);
		
		Assembly hullBox = Operations.hull(topCylinder, bottomCylinder);
		hullBox = Operations.union(hullBox, bottomBox);
		
		ScadObject window = new Cube(shellLength * 0.6, shellRimRadius + wallWidth, (numberOfShells + 3) * shellRimRadius, false)
								.setRotationX(90)
								.setTranslationX( (shellLength + 2 * wallWidth - shellLength * 0.6) / 2)
								.setTranslationY(shellRimRadius);
		
		ScadObject shellPocket = 
								Operations.hull(
									new Cylinder(shellRimRadius, shellLength).setRotation(0, 90, 0),
									new Cylinder(shellRimRadius, shellLength)
										.setRotation(0, 90, 0)
										.setTranslation(0, -shellRimRadius * numberOfShells, 0),
									new Cylinder(openingWidthPercentage * shellRimRadius, shellLength)
										.setRotation(0, 90, 0)
										.setTranslation(0, -shellRimRadius * (numberOfShells + 1), 0)									
								).setTranslationX(wallWidth);
		
		hullBox = Operations.difference(hullBox, window, shellPocket);
		
		hullBox.setTranslationZ(shellRimRadius + wallWidth);
		
		ScadObject beltLoop = 
								Operations.difference(
										new Cube(shellLength, 2 * wallWidth + beltHeight, wallWidth + beltThickness, true)
											.setTranslation(wallWidth + shellLength / 2, -3, -(wallWidth + beltThickness) / 2),
										new Cube(shellLength, beltHeight, beltThickness, true)
											.setTranslation(wallWidth + shellLength / 2, -wallWidth / 2, -wallWidth/2)
								);
		
		hullBox = Operations.union(hullBox, beltLoop);
		

		Debug.write(hullBox);
	}

}
