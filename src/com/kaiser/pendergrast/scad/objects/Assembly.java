package com.kaiser.pendergrast.scad.objects;

import java.util.List;


/**
 * Keeps multiple ScadObjects fixed with respect to eachother
 * (functinoally equivalent to OpenScad's module)
 */
public class Assembly extends ScadObject {
	private String mScad;

	public Assembly() {
		mScad = "";
	}

	/**
	 * Construct an Assembly from one
	 * or many ScadObjects
	 */
	public Assembly(ScadObject... objects) {
		mScad = "";
		addObjects(objects);
	}

	/**
	 * Construct an Assembly from a list
	 * of ScadObjects
	 */
	public Assembly(List<ScadObject> objects) {
		this((ScadObject[]) objects.toArray());
	}

	/**
	 * Construct an Assembly from
	 * raw SCAD code
	 */
	public Assembly(String scad) {
		mScad = scad;
	}

	/**
	 * Add a ScadObject to this assembly
	 */
	public void addObject(ScadObject object){
		mScad += object.toScad() + "\n";
	}

	/**
	 * Add ScadObjects to this assembly
	 */
	public void addObjects(ScadObject... objects) {
		for(ScadObject object : objects){
			mScad += object.toScad() + "\n";
		}
	}

	/**
	 * Add ScadObjects to this assembly
	 */
	public void addObjects(List<ScadObject> objects) {
		addObjects((ScadObject[]) objects.toArray());
	}

	@Override
	public String getObjectScad() {
		return mScad;
	}

	@Override
	public ScadObject copy() {
		Assembly copy = new Assembly(mScad);
		copy.setTranslation(this.getTranslation());
		copy.setRotation(this.getRotation());

		return copy;
	}
}
