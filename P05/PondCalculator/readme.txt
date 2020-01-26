Alec Maughan Workshop 5

Date created: 10/04/209

Date last modified: 10/04/19

Purpose: Calculate the number of animals a person can fit in the given
dimensions of a pond, all based on a table of data.

Files in project: PondCalculator.java, PondCalcualtor.class

Test Files: pondPsuedoCode.txt, PondCalculator_testResults.txt

Functionality: Gets a start or exit message from int input method. This method
has looping input validation for data type, and lower and upper bound range,
with detailed error messages for each. A person is selected from this same int
input method. from this selection, the person's name is found and assigned to
a String, and their sex is used to assign a seperate string to "his" or "her"
for later output messages. Another method finds the two possible names of the
person's animal options to use in a prompt for user input. The user selects 
an animal using the int input method and assigns a string a new name if it
needs to. A method for double input is used, this is functuionally the same
as the int input but uses double, and hasn't got a upper bound for range
validation. This double input method is used to get the 3 dimensions of the
pond and then the volume is calcualted. The no. of animals that can fit
in this volume is calcualted based on the previous animal selection. The
volume of the pond is also rounded to the nearest whole number for output
purposes. An output message then uses the name of the person, name of the fish,
the his/her string, and the no. of animals that can fit. The majority of main
loops as long as the exit condition is not met.

No known bugs
