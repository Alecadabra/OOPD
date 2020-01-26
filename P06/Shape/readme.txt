Alec Maughan Workshop 6

Date created: 15/04/209

Date last modified: 18/04/19

Purpose: To calculate the area of a chosen shape, split it to it's square 
metre, square centimetre and square millimetre components. Also verify if 
inputted dimensions are valid and also looping the whole program, using
lots of submodules. Input/output is done either by the user or from a file.

Files in project: Shape.class, Shape.java

Test Files: shapeCalculatorPseudoCode.txt, shapes_file.csv

Functionality: The program asks for an input to calculate the area of either a
triangle, rectangle or circle or to exit the program. This input is checked for
validity and looped if it is not valid. Then dimensions are inputted for
the chosen shape (or the program exits depending on input). The program checks
the validity of the input and loops with an error message if they are not
valid. The program calculates the area of the chosen shape and splits it to 
it's square metre, square centimetre and square millimetre components and 
outputs this to the user, and loops the entire thing. The entire process
is split into submodules, one for integer input, one for double input, one
for the final output, one for the area calculations of each shape, and one
for the input/calculation/output process of each shape. All input validations
test for both datatype and range, with tailored error messages for each result.
All of this can also we done using file I/O, with multiples shape areas being
calculated from a specified file input and output to a file.

No known bugs
