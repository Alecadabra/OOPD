/**********************
    Author: Alec Maughan
    Date: 9/5/19
    Purpose: Handle file I/O for loading and saving ships
    Porpoise: Small toothed whale, closely related to oceanic dolphins
**********************/
import java.util.*;
import java.io.*;
public class File
{
    // Class constants
    //ANSI Colours:
    public static final String CYLW = new String( "\u001B[33m" ); //Yellow
    public static final String CCYN = new String( "\u001B[36m" ); //Cyan
    public static final String CRED = new String( "\u001B[31m" ); //Red
    public static final String CC = new String( "\u001B[0m" ); //Colour reset

    //New line 'starters'. For differenciating between userinput, errors, etc.
    public static final String UIDOT = new String( "[-] " ); //Messages
    public static final String UITAB = new String( " |  " ); //Continuing
    public static final String UIERR = new String( CRED + "[!] " + CC );//Error
    public static final String UIINP = new String( CYLW + "[>] " + CC );//Input

    /***********************
        Submodule: load
        Import: fileName (String), Ships (ShipStorage)
        Export: none
        Assertion: Reads ships from file and adds them to storage
    ***********************/
    public void load(String fileName, ShipStorage Ships)
    {
        FileInputStream strm = null;
        InputStreamReader rdr;
        BufferedReader bfr;
        String fullLine = new String(); //Full line read from BufferedReader
        char type = 0; //Ship type, 'S' for Submarines and 'F' or 'J' for jets
        String[] line = new String[7]; //Array of split values from fullLine
        int lineNum = 1; //Keeps track of current line for error messages
        try
        {
            strm = new FileInputStream( fileName );
            rdr = new InputStreamReader( strm );
            bfr = new BufferedReader( rdr );
            fullLine = bfr.readLine();
            while( fullLine != null ) //Checks for end of file
            {
                line = fullLine.split( ",", 7 );
                    // Stops at ordncance to allow for commas
                type = Ships.inputType( line[0] );
                    // Char representing ship
                if( type == 'S')
                {
                    // Uses the default constructor then sets all the values.
                    // The Ships.inputX methods throw exceptions when inputs
                    // are invalid
                    SubClass Sub = new SubClass();
                    Ships.inputSerial( Sub, line[1] );
                    Ships.inputYear( Sub, line[2] );
                    Ships.inputEng( Sub, line[3], line[4] );
                    Ships.inputHull( Sub, line[5] );
                    Ships.inputDepth( Sub, line[6] );
                    Ships.addShip( Sub );
                }
                else if( ( type == 'F' ) || ( type == 'J' ) )
                {
                    JetClass Jet = new JetClass();
                    Ships.inputSerial( Jet, line[1] );
                    Ships.inputYear( Jet, line[2] );
                    Ships.inputEng( Jet, line[3], line[4] );
                    Ships.inputWing( Jet, line[5] );
                    Ships.inputOrdnance( Jet, line[6] );
                    Ships.addShip( Jet );
                }
                fullLine = bfr.readLine();
                lineNum++;
            }
            strm.close();
 
            if( lineNum == 2 ) //Successful load - One ship loaded
            {
                System.out.print( UIDOT + "Successfully loaded 1 ship\n" );
            }
            else if( lineNum > 2 ) //Succesfull load - Multiple ships loaded
            {
                System.out.print( UIDOT + "Successfully loaded " +
                    ( lineNum - 1 ) + " ships\n" );
            }
            else //No ships loaded due to empty file
            {
                System.out.print( UIERR + "Error: No ships in file" );
            }
        }
        catch( IllegalArgumentException e )
        {
            System.out.print( UIERR + "Error on line " + lineNum +
                ":" + e.getMessage() + "\n" );
            // All errors from Ships.input methods are thrown as
            // IllegalArgumentException, The messages are formatted to add
            // detail to the error message here
        }
        catch( ArrayIndexOutOfBoundsException e2 )
        {
            System.out.print( UIERR + "Error on line " + lineNum + ": " +
                line.length + " elements rather than 7\n" + UITAB +
                "Line: \"" + fullLine + "\"\n" );
            // If there are not enough values in a line
        }
        catch( FileNotFoundException e3 )
        {
            System.out.print( UIERR + "Error: File name \"" + fileName +
                "\" not found\n" );
        }
        catch( IOException e4 )
        {
            if( strm != null )
            {
                try
                {
                    strm.close();
                }
                catch( IOException e5 )
                {
                    System.out.print( "Error on line " + lineNum +
                        ": Unknown error (Sorry)\n" + UITAB + "Line: \" " +
                        fullLine + " \"\n" + UITAB + "Messages: \"" +
                        e4.getMessage() + "\", \"" + e5.getMessage() +
                        "\"\n" );
                }
            }
            else
            {
                System.out.print( "Error on line " + lineNum +
                    ": Unknown error (Sorry)\n" + UITAB + "Line: \" " +
                    fullLine + " \"\n" + UITAB + "Message: \"" +
                    e4.getMessage() + "\"\n" );
            }
        }
    }

    /***********************
        Submodule: save
        Import: fileName (String), Ships (ShipStorage)
        Export: none
        Assertion: Saves all ships in storage to file
    ***********************/
    public void save(String fileName, ShipStorage Ships)
    {
        FileOutputStream strm = null;
        PrintWriter pw;
        String[] fileArr;
        try
        {
            strm = new FileOutputStream( fileName );
            pw = new PrintWriter( strm );
            fileArr = new String[ Ships.getTotal() ];
            for( int i = 0; i < Ships.getSubNum(); i++ )
            {
                pw.println( (Ships.getSub(i)).toFileString() );
            }
            for( int i = 0; i < Ships.getJetNum(); i++ )
            {
                pw.println( (Ships.getJet(i).toFileString()) );
            }
            pw.close();
        }
        catch( IOException e3 )
        {
            if( strm != null )
            {
                try
                {
                    strm.close();
                }
                catch( IOException e4 ) {}
            }
            throw new IllegalArgumentException("File writing error: " + e3.getMessage());
        }
    }

}  
