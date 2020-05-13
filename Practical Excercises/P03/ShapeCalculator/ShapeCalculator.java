import java.util.*;
import java.lang.Math.*;
public class ShapeCalculator
{
    public static void main( String []args)
    {
        String menu, error, str, strDiam, strLength, strWidth, strBase, strHeight;
        int shape, areaCmsqr = 0, areaMsqr = 0, diam;
        double area, area2, radius, length, width, base, height, areaMmsqr = 0;
        Scanner sc = new Scanner(System.in);

menu = ("Enter shape.\nCircle: 1, Rectangle: 2, Triangle: 3.\nExit: 4.");
// assigns a menu message for the input of the shape number
error = ("Error: Invalid input. ");
// assigns an error message for invalid answers


do
{

    str = menu;
    // assigns the string 'str' to the menu value
    // otherwise loops would still have an error message in the initial input
    // of shape dimensions

    do
    {
        System.out.println( str);
        str = error + str;
        shape = sc.nextInt();
    }
    while ( ( shape < 1) || ( shape > 4));
    // gets the shape number and loops with an error message if the 
    // shape is invalid
    // to prevent (error + error + menu) we use the 'str' string

    switch ( shape)
    // switch statement to perform different actions based
    // on the value of shape.
    {
        case 1:
            strDiam = "Enter circle diameter (cm).";
            do
            {
                System.out.println( strDiam);
                strDiam = error + strDiam;
                diam = sc.nextInt();
            }
            while ( diam <= 0);
            // gets the circle diameter and loops with an error message if 
            // it is below or equal to zero
            radius = (double)diam / 2.0;
            area =  radius * radius * Math.PI;
            // finds the area of a circle with pi*r^2
            areaCmsqr = (int)area % 10000;
            areaMsqr = (int)area / 10000;
            areaMmsqr = (area -((int)area)) * 100.0;
            // splits the area into its m^2, cm^2 and mm^2 components
            System.out.println("The area of the circle is " + areaMsqr +
            "m^2, " + areaCmsqr + "cm^2, " + areaMmsqr + "mm^2.");
            // outputs answer in all components
        break;
        case 2:
            strLength = "Enter triangle length (cm).";
            do
            {
                System.out.println( strLength);
                strLength = error + strLength;
                length = sc.nextDouble();
            }
            while ( length <= 0.0);
            strWidth = "Enter rectangle width (cm).";
            do
            {
                System.out.println( strWidth);
                strWidth = error + strWidth;
                width = sc.nextDouble();
            }
            while ( width <= 0.0);
            area = length * width;
            // finds area of rectangle with length * width
            areaCmsqr = (int)area % 10000;
            areaMsqr = (int)area / 10000;
            areaMmsqr = (area -((int)area)) * 100.0;
            System.out.println("The area of the rectangle is " + areaMsqr +
            "m^2, " + areaCmsqr + "cm^2, " + areaMmsqr + "mm^2.");
        break;
        case 3:
            strBase = "Enter triangle base (mm).";
            do
            {
                System.out.println( strBase);
                strBase = error + strBase;
                base = sc.nextDouble();
            }
            while ( base <= 0.0);
            strHeight = "Enter triangle height (mm).";
            do
            {
                System.out.println( strHeight);
                strHeight = error + strHeight;
                height = sc.nextDouble();
            }
            while ( height <= 0.0);
            area = (base * height) / 2.0;
            // finds area of triangle with (base*height)/2
            area2 = area / 100.0;
            // convets area from mm^2 to cm^2
            areaCmsqr = (int)area2 % 10000;
            areaMsqr = (int)area2 / 10000;
            areaMmsqr = (area2 -((int)area2)) * 100.0;
            System.out.println("The area of the triangle is " + areaMsqr +
            "m^2, " + areaCmsqr + "cm^2, " + areaMmsqr + "mm^2.");
        break;
        case 4:
            System.out.println("Goodbye");
            // prints goodbye message for exit condition
        break;
        default:
            System.out.println("Invalid input");
            // Error message for invalid input just in case
    }
}
while ( shape != 4);
// do-while loop that loops the menu selection and area calculation unless
// the exit condition is selected

    }
}
