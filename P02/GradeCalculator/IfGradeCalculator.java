import java.util.*;
public class IfGradeCalculator
{
    public static void main( String []args)
    {
        String grade = "Invalid";
        int mark, decimal;
        double unitTotal;
        Scanner sc = new Scanner(System.in);

System.out.println("Enter unit total");
unitTotal = sc.nextDouble();
// Gets the unit total

decimal = ((int)(unitTotal * 10.0)) % 10;
// Gets the number that is the 1st decimal place of the unit total
mark = (int)unitTotal;
// sets the mark to the unrounded unit total

if ( decimal > 5)
{
    mark = mark + 1;
}
// Rounds the mark up by one if it's first decimal place is greater than 5

if ( mark < 0)
    {
    grade = ("Invalid"); }
else if ( mark < 50)
    {
    grade = ("F-" + mark); }
else if ( mark < 60)
    {
    grade = ("P-5"); }
else if ( mark < 70)
    {
    grade = ("C-6"); }
else if ( mark < 80)
    {
    grade = ("D-7"); }
else if ( mark < 90)
    {
    grade = ("HD-8"); }
else if ( mark < 100)
    {
    grade = ("HD-9"); }
else if ( mark == 100)
    {
    grade = ("HD-10"); }
else if ( mark > 100)
    {
    grade = ("Invalid"); }


System.out.println(grade);
    }
}
