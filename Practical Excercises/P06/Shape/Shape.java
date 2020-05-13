/*
 * Author: Alec Maughan
 * Purpose: Use submodules and looping to make a Shape area calculator for
 * circles, rectangles and triangles from either user or file input/output
 * Date: 18/4/19
 */
import java.util.*;
import java.io.*;
public class Shape
{
    public static void main( String []args )
    {
        String message;
        int menu;

        do
        {
            message = ("Select an option:\n1. Calculate the area of a circle" +
            "\n2. Calculate the area of a rectangle\n3. Calculate the area" +
            " of a triangle\n4. Calculate areas from a file\n5. Exit"); 
            do
            {
                menu = inputInt( message );
                message = "Error: Input > 5. ";
            }
            while ( ( menu < 1 ) || ( menu > 5 ) );
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
                    String[] shapeArr = new String[20];
                    double[] dim1Arr = new double[20];
                    double[] dim2Arr = new double[20];
                    double[] areaArr = new double[20];
                    file( shapeArr, dim1Arr, dim2Arr, areaArr );
                    break;
                case 5:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            // Switch statement to calculate shapes from menu input
        }
        while ( menu != 5 );
        // Do-While loop for repeating entire program unless exit condition
        // is met.
    }
/*
 * Name: file
 * Date: 18/04/2019
 * Import: shapeArr[], dim1Arr[], dim2Arr[], areaArr[]
 * Export: none
 * Handles every step of file I/O shape calculating
 */
    public static void file( String[] shapeArr, double[] dim1Arr,
    double[] dim2Arr, double[] areaArr ) {
        Scanner sc = new Scanner(System.in);
        String fileName;
        System.out.println( "Enter file name" );
        fileName = sc.nextLine();
        // gets filename input
       
        fileReader( fileName, shapeArr, dim1Arr, dim2Arr );
        fileCalc( shapeArr, dim1Arr, dim2Arr, areaArr );
        fileWriter( shapeArr, areaArr );
        if ( areaArr[0] != 0 ) {
            System.out.println( "Done! output.txt has been created" );
        }
    }
/*
 * Name: fileReader
 * Date: 18/04/2019
 * Import: fileName, shapeArr[]. dim1Arr[], dim2Arr[]
 * Export: none
 * Does the file reading and assignment to arrays
 */
    public static void fileReader( String fileName, String[] shapeArr,
    double[] dim1Arr, double[] dim2Arr ) {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        
        String line = null;
        double dim1 = 0, dim2 = 0;
        int lineNum = 0, count = 0;
        char shape;

        try {
            fileStrm = new FileInputStream( fileName );
            rdr = new InputStreamReader( fileStrm );
            bufRdr = new BufferedReader( rdr );

            line = bufRdr.readLine();
            lineNum++; //reads a line and then increments
            while ( ( line != null ) && (count < 19 ) ) {
                shape = line.charAt(0);
                switch ( shape ) { //switch based don current shape letter
                    case 'C':
                        line = bufRdr.readLine();
                        lineNum++; //reads line then increments
                        dim1 = doubleParser( line ); //parses string to double
                        dim2 = 0;// dim2 not used for circles so set to 0
                        line = bufRdr.readLine();
                        break;
                    case 'R': case 'T':
                        line = bufRdr.readLine();
                        lineNum++;
                        dim1 = doubleParser( line );
                        line = bufRdr.readLine();
                        lineNum++;
                        dim2 = doubleParser( line );
                        line = bufRdr.readLine();
                        lineNum++;
                        break;
                    default:
                        System.out.println( "Error in file processing" );
                        break;
                }

                try {
                    shapeArr[count] = ( "" + shape );
                    dim1Arr[count] = dim1;
                    dim2Arr[count] = dim2;
                    count++;
                }
                catch ( ArrayIndexOutOfBoundsException e ) {
                    System.out.println( "Maximum number of shapes exceeded, "
                    + "only calulating first 20" );
                }
            } // End while 
            fileStrm.close();
        }
        catch ( IOException e2 ) {
            if ( fileStrm != null ) {
                try {
                    fileStrm.close();
                }
                catch ( IOException e3 ) {
                }
            }
            System.out.println( "Error in file processing: " +
            e2.getMessage() );
        }
    }
/*
 * Name: doubleParser
 * Date: 18/04/2019
 * Import: line (string)
 * Export: out (double)
 * parses string to double for assigning file input to an array of doubles
 */
    public static double doubleParser( String line ) {
        double out = 0.0;
        try {
            out = Double.parseDouble( line );
        }
        catch ( NumberFormatException e4 ) {
            System.out.println( "Error in file processing: " +
            e4.getMessage() );
            line = null;
        }
        return out;
    }
 /*
 * Name: fileCalc
 * Date: 18/4/19
 * Import: shapeArr[], dim1Arr[], dim2Arr[], areaArr[]
 * Export: none
 * Does area calculations for file I/O and puts answers in an array
 */       
    public static void fileCalc( String[] shapeArr, double[] dim1Arr,
    double[] dim2Arr, double[] areaArr ) {
        int count = 0, diam;
        double length, width, base, height;
        char shape;
        while ( ( count < 19 ) && ( shapeArr[count] != null ) ) {
            shape = shapeArr[count].charAt(0);
            switch ( shape ) {
                case 'C':
                    diam = ( (int)dim1Arr[count] );
                    areaArr[count] = circleCalc( diam );
                    break; //calculates area and stores it in area array
                case 'R':
                    length = dim1Arr[count];
                    width = dim2Arr[count];
                    areaArr[count] = rectangleCalc( length, width );
                    break;
                case 'T':
                    base = dim1Arr[count];
                    height = dim2Arr[count];
                    areaArr[count] = triangleCalc( base, height );
                    break;
                default:
                    System.out.println( "Error in area calculation" );
            }
            count++;
        }
    }
/*
 * Name: fileWriter
 * Date: 18/4/19
 * Import: shapeArr[], areaArr[]
 * Export: none
 * Writes shape letters and areas to an external txt file in a specific order
 */
    public static void fileWriter( String shapeArr[], double areaArr[] ) {
        int lineNum = 0;
        boolean finished = false, exit = false;
        
        try {
            PrintWriter writer = new PrintWriter( "output.txt", "UTF-8" );
    
            // Rectangles
            while ( !finished && !exit && ( shapeArr[lineNum] != null ) ) {
                try {
                    if ( shapeArr[lineNum].equals("R") ) {
                        // only proceeds if shape is a rectangle
                        writer.println( shapeArr[lineNum] + "," +
                        areaArr[lineNum] );
                    } // prints are to file from area array
                }
                catch ( ArrayIndexOutOfBoundsException e6 ) {
                    finished = true;
                }
                lineNum++;
            }

            // Circles
            lineNum = 0;
            finished = false;
            while ( !finished && !exit  && ( shapeArr[lineNum] != null ) ) {
                try {
                    if ( shapeArr[lineNum].equals("C") ) {
                        writer.println( shapeArr[lineNum] + "," +
                        areaArr[lineNum] );
                    }
                }
                catch ( ArrayIndexOutOfBoundsException e6 ) {
                    finished = true;
                }
                lineNum++;
            }

            // Triangles
            lineNum = 0;
            finished = false;
            while ( !finished && !exit  && ( shapeArr[lineNum] != null ) ) {
                try {
                    if ( shapeArr[lineNum].equals("T") ) {
                        writer.println( shapeArr[lineNum] + "," +
                        areaArr[lineNum] );
                    }
                }
                catch ( ArrayIndexOutOfBoundsException e6 ) {
                    finished = true;
                }
                lineNum++;
            }
            writer.close();
        } // End try
        catch ( FileNotFoundException e9 ) {
            System.out.println( "Error, file not found" );
        }
        catch ( UnsupportedEncodingException e10 ) {
            System.out.println( "Error, unsupported encoding" );
        }

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
