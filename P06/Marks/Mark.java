import java.util.*;
import java.io.*;
public class Mark
{
    public static void main( String[]args )
    {
        Scanner sc = new Scanner( System.in );
        String[] nameArray = new String[20];
        double[] markArray = new double[20];
        String fileName, nameInput;
        boolean errorExit, found;
        int count;
    
        System.out.println( "Enter file name" );
        fileName = sc.nextLine();

        errorExit = false;
        fileReader( nameArray, markArray, fileName );

        while ( !errorExit && ( exitMenu() == 1 ) ) {
            System.out.println( "Enter name of student" );
            nameInput = sc.nextLine();
            found = false;
            count = 0;
            while ( ( count < 20 ) && !found ) {
                try {
                    if ( nameArray[count].equals( nameInput ) ) {
                        found = true;
                    }
                    else {
                        count++;
                    }
                }
                catch( NullPointerException e7 ) {
                    System.out.println( "Error: " + e7.getMessage() );
                    count = 20;
        
            } // End while
            if ( found ) {
                System.out.println( "Mark: " + markArray[count] );
            }
            else {
                System.out.println( "Student not found" );
            } // End if
        } // End while
    }

    private static void fileReader( String[] nameArray, double[] markArray, String fileName )
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int count;
        String name, mark;
        boolean errorExit = false;
        try {
            fileStrm = new FileInputStream( fileName );
            rdr = new InputStreamReader( fileStrm );
            bufRdr = new BufferedReader( rdr );

            count = 0;
            name = bufRdr.readLine();
            mark = bufRdr.readLine();
            while ( ( name != null ) && ( mark != null ) ) {
                nameArray[ count ] = name;
                try {
                    markArray[ count ] = Double.parseDouble( mark );
                }
                catch ( NumberFormatException e ) {
                    // Line can't be a double
                    mark = null;
                }
                catch ( NullPointerException e2 ) {
                    // Line is null
                    mark = null;
                }
                count++;
                if ( mark != null ) {
                    name = bufRdr.readLine(); 
                    mark = bufRdr.readLine();
                }
            } // End while
            fileStrm.close();
        } // End try
        catch ( IOException e3 ) {
            if ( fileStrm != null ) {
                try {
                    fileStrm.close();
                }
                catch ( IOException e5 ) {
                }
            } // End if  
            System.out.println( "Error in file processing: " + e3.getMessage()  );
            errorExit = true;
        } // End catch
        catch ( ArrayIndexOutOfBoundsException e4 ) {
            System.out.println( "Over 20 lines in file, only using first 20" );
        } // End catch
    }

    public static int exitMenu()
    {
        Scanner sc = new Scanner(System.in);
        String message;
        int output;
        boolean loop;
        message = ( "Select an option:\n1. Display mark\n2. Exit" );
        System.out.println( message );
        do {
            try {
                output = sc.nextInt();
                loop = false;
            }
            catch ( InputMismatchException e ) {
                output = 0;
                sc.nextLine();
                System.out.println( "Error, Invalid Input: " +
                "Input must be an Integer.\n" + message );
                loop = true;
            } // End catch
        }
        while ( loop );
    
        while ( ( output < 1 ) || ( output > 2 ) ) {
            if ( output < 1 ) {
                System.out.println( "Error, Invalid Input\n" + message );
                output = sc.nextInt();
            }
            else if ( output > 2 ) {
                System.out.println( "Error, Invalid Input\n" + message );
                output = sc.nextInt();
            } // End if
        }
        return output;
    }
}
