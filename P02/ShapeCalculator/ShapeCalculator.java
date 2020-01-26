import java.util.*;
import java.lang.Math.*;
public class ShapeCalculator
{
    public static void main( String []args)
    {
        String shapeName = " ";
        int shape, areaCmsqr = 0, areaMsqr = 0, diam;
        double area, area2, radius, length, width, base, height, areaMmsqr = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose shape: Circle - 1, Rectangle - 2, Triangle - 3. Or exit with 4.");
        shape = sc.nextInt();
// Gets the shape or exit

switch ( shape)
{
    case 1:
        shapeName = "circle";
        System.out.println("Enter circle diameter (cm)");
        diam = sc.nextInt();
        radius = (double)diam / 2.0;
        area =  radius * radius * Math.PI;
        areaCmsqr = (int)area % 10000;
        areaMsqr = (int)area / 10000;
        areaMmsqr = (area -((int)area)) * 100.0;
    break;
    case 2:
        shapeName = "rectangle";
        System.out.println("Enter rectangle length (cm)");
        length = sc.nextDouble();
        System.out.println("Enter rectangle width (cm)");
        width = sc.nextDouble();
        area = length * width;
        areaCmsqr = (int)area % 10000;
        areaMsqr = (int)area / 10000;
        areaMmsqr = (area -((int)area)) * 100.0;
    break;
    case 3:
        shapeName = "triangle";
        System.out.println("Enter triangle base length (mm)");
        base = sc.nextDouble();
        System.out.println("Enter triangle height (mm)");
        height = sc.nextDouble();
        area = (base * height) / 2.0;
        area2 = area / 100.0;
        areaCmsqr = (int)area2 % 10000;
        areaMsqr = (int)area2 / 10000;
        areaMmsqr = (area2 -((int)area2)) * 100.0;
    break;
    case 4:
        System.out.println("Goodbye");
    break;
    default:
        System.out.println("Invalid input");
// Gives error message if input is invalid
}

    if (( shape == 1 ) || ( shape == 2 ) || ( shape == 3 ))
// Only outputs answer message if a shape is being calculated
    {
        System.out.println("The area of the " + shapeName + " is " + areaMsqr + "m^2, " + areaCmsqr + "cm^2, " + areaMmsqr + "mm^2.");
    }
}
}
