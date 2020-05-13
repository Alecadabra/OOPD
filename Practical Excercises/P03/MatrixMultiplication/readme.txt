Alec Maughan Workshop 3

Date created: 21/03/2019

Date last modified: 21/03/19

Purpose: To multiply two matricies together from file.

Files in project: MatrixMultiplication.java, MatrixMultiplication.class,
ArrayReader.class

Test Files: pseudoMatrixMultiplication.txt, 
MatrixMultiplication_testResults.txt, matrixA.txt, matrixB.txt

Functionality: The ArrayReader class is used to get the filenames of the two 
matricies and it checks them for validation with an if statement. It assigns
them to seperate matricies (Reffered to here as A and B). Another if
statement checks is A has the same no. of columns as B's no. of rows.
If true, matrix C is created with A's no. of rows and B's no. of columns.
Three nested for loops for each common variable of the 3 arrays, i, r, and j
are used to assign the values of every entry in C's array. This array is then 
put into a string using two nested for loops for the dimensions of C, i and j.
These loops seperate entries with commas and create a new line at the end
of each row. This output string is then ouputted.

No known bugs
