import java.util.*;
public class CaseGradeCalculator
{
    public static void main( String []args)
    {
        String grade = "Invalid";
        int mark, decimal, smallMark;
        double unitTotal;
        Scanner sc = new Scanner(System.in);

System.out.println("Enter unit total");
unitTotal = sc.nextDouble();
// Gets the unit total

decimal = ((int)(unitTotal * 10.0)) % 10;
// Gets the first digit of the unit total for rounding later
mark = (int)unitTotal;
// gets the unrounded mark


if ( decimal > 5)
{
    mark = mark + 1;
}
//adds one to the unrounded mark if the first decima; is > 5

smallMark = mark / 10;
//truncates the mark so that 0 = 0, 10 = 1. 99 = 9, 100 = 10 etc.

if ( (mark >= 0) && (mark <= 100))
{
// only changes grade from Invalid if the mark is between 0 and 100
// this prevents negatives and marks > 100 from being assigned a grade
    switch ( smallMark)
    {
        case 0: case 1: case 3: case 4:
            grade = ("F-" + mark);
        break;
        case 5:
            grade = "P-5";
        break;
        case 6:
            grade = "C-6";
        break;
        case 7:
            grade = "D-7";
        break;
        case 8:
            grade = "HD-8";
        break;
        case 9:
            grade = "HD-9";
        break;
        case 10:
            grade = "HD-10";
        break;
    }
}

System.out.println(grade);
    }
}

