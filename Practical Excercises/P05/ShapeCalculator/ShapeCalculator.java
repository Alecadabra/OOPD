/*
 * Author: Alec Maughan
 * Purpose: Use submodules and looping to make a Shape area calculator for
 * circles, rectangles and triangles.
 * Date: 5/4/19
 */
import java.util.*;
public class ShapeCalculator
{
    public static void main( String []args )
    {
        String message;
        int menu;

        do
        {
            message = ("Enter shape.\nCircle: 1, Rectangle: 2, Triangle: 3." +
            "\nExit: 4.");
            do
            {
                menu = inputInt( message );
                message = "Error: Input > 4. ";
            }
            while ( ( menu < 1 ) || ( menu > 4 ) );
            // Gets menu input from 1 - 4 With input validation using
            // the inputInt method
            
            switch ( menu )
            {
                case 1:
                    circle();
                    break;
                case 2:
                    rectangle();
                    break;
                case 3:
                    triangle();
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            // Switch statement to calculate shapes from menu input
        }
        while ( menu != 4 );
        // Do-While loop for repeating entire program unless exit condition
        // is met.
    }

/*
 * Name: circle
 * Date: 5/4/19
 * Import: None
 * Export: None
 * Uses input, calculation and output submodules for circle
 */
    public static void circle()
    {
        String message;
        message = "Enter circle diameter (cm).";
        int dim1;
        double area;
        dim1 = inputInt( message );
        area = circleCalc( dim1 );
        output( "circle", area );
    }

 /*
 * Name: rectangle
 * Date: 5/4/19
 * Import:
 * Export:
 * Uses input, calculation and output submodules for rectangle
 */
    public static void rectangle()
    {
        double dim1, dim2, area;
        String message = "Enter rectangle length (cm).";
        dim1 = inputReal( message );
        message = "Enter rectangle width (cm).";
        dim2 = inputReal( message );
        area = rectangleCalc( dim1, dim2 );
        output( "rectangle", area );
    }

 /*
 * Name:
 * Date: 5/4/19
 * Import:
 * Export:
 * Uses input, calculation and output submodules for triangle
 */
    public static void triangle()
    {
        double dim1, dim2, area;
        String message = "Enter base length (mm).";
        dim1 = inputReal( message );
        message = "Enter triangle height (mm).";
        dim2 = inputReal( message );
        area = triangleCalc( dim1, dim2 );
        output( "triangle", area );
    }
    
 /*
 * Name: circleCalc
 * Date: 5/4/19
 * Import: Circle diameter
 * Export: Circle area
 * Calculates circle area from diameter
 */
    public static double circleCalc( int diam )
    {
        double radius, area;
        radius = (double)diam / 2.0;
        area = radius * radius * Math.PI;
        return area;
    }

 /*
 * Name: rectangleCalc
 * Date: 5/4/19
 * Import: Rectangle length and width
 * Export: Rectangle area
 * Calculates rectangle area from length and width
 */
    public static double rectangleCalc( double dim1, double dim2 )
    {
        double area;
        area = dim1 * dim2;
        return area;
    }

 /*
 * Name: triangleCalc
 * Date: 5/4/19
 * Import: Triangle base and height
 * Export: Triangle area
 * Calcualtes triangle area from base and height and converts to cm^2
 */
    public static double triangleCalc( double dim1, double dim2 )
    {
        double area;
        area = ( ( dim1 * dim2 ) / 2.0 ) / 100;
        return area;
    }

 /*
 * Name: inputInt
 * Date: 5/4/19
 * Import: Prompt message
 * Export: User input (Integer)
 * Uses scanner to get a user input integer with input validation to prevent
 * negative or zero values
 */
    public static int inputInt( String message )
    {
        int output;
        Scanner sc = new Scanner(System.in);
        String message2 = message;
        do
        {
            try
            {
                System.out.println( message2 );
                message2 = "Error: Input <= 0. " + message;
                output = sc.nextInt();
            }
            catch ( InputMismatchException e )
            {
                output = 0;
                sc.nextLine();
                message2 = "Error: Input is not an Integer. " + message;
            }
        }
        while( output <= 0 );
        return output;
    }

 /*
 * Name: inputReal
 * Date: 5/4/19
 * Import: Prompt message
 * Export: User input (Double)
 * Uses scanner to get a user input double with input validation to prevent
 * negative or zero values
 */
    public static double inputReal( String message )
    {
        double output;
        Scanner sc = new Scanner(System.in);
        String message2 = message;
        do
        {
            try
            {
                System.out.println( message2 );
                message2 = "Error: Input <= 0. " + message;
                output = sc.nextDouble();
            }
            catch ( InputMismatchException e )
            {
                output = 0;
                sc.nextLine();
                message2 = "Error: Input is not a Real number. " + message;
            }
        }
        while( output <= 0.0 );
        return output;
    }

 /*
 * Name: output
 * Date: 5/4/19
 * Import: Name of shape and area
 * Export: None
 * Converts area value into it's m^2, cm^2 and mm^2 components and outputs
 * along with the name of the shape.
 */
    public static void output( String shape, double area )
    {
        int cm, m;
        double mm;
        cm = (int)area % 10000;
        m = (int)area / 10000;
        mm = (double)( ( area - (int)area ) * 100 );
        System.out.println("The area of the " + shape + " is " + m +
        "m^2, " + cm + "cm^2, " + mm + "mm^2.");
    }
}
