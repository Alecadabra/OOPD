Alec Maughan Workshop 2 Readme

Date created: 14/03/2019

Date last modified: 14/03/2019

Purpose: To categorise a unit total into a grade. Using case.

Files in project: CaseGradeCalculator.java, CaseGradeCalculator.class

Test files: GradeCase_testResults.txt, pseudoCaseCalculator.txt

Functionality: The program gets the unit total, truncates it into an integer and rounds the integer by checking if it's first decimal point of the original unit total is greater than 5. It then makes a truncated version of this rounded mark by doing (mark DIV 10), so that 10 = 1, 99 = 9, 100 = 10, etc. An if statement then checks if the rounded mark is between 0 and 100, and if this is true the truncated mark goes through a case statement that assigns a grade string (of default value "Invalid") a grade based on the truncated mark's value. Then it prints this grade string.

No known bugs
