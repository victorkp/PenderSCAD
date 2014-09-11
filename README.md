# A Java wrapper for OpenSCAD #
Write your SCAD files in easy-to-use Java, then easily export your creation as plain text - to be imported into OpenSCAD.

# Package Overview #
## com.kaiser.pendergrast.scad.objects ##
### Cube, Sphere, Cylinder ###
Standard OpenSCAD objects that can be optionally centered when constructed.

### Tube and SquareTube ###
Cylindrical tube and square tubes that can be constructed with outer dimensions and wall thickness.

### Assembly ###
Allows for objects to be joined together in an assembly and used as a whole.
You may also write raw SCAD code when constructing an Assembly.
Example: `Assembly cube = new Assembly("cube(size=[1,2,3]);");`

### ScadObject ###
Cube, Cylinder, Assembly, Sphere, Tube, SquareTube, etc. are all child classes of the abstract ScadObject class. Extend ScadObject if you wish to define your own structure.

## com.kaiser.pendergrast.scad.operations ##
### Operations ###
Has methods to perform operations such as union, difference, intersection, and rotation. All methods return an `Assembly`.
