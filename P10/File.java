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
    /***********************
        Submodule: load
        Import: fileName (String), Ships (ShipStorage)
        Export: message (String)
        Assertion: Reads ships from file and adds them to storage
    ***********************/
    public String load(String fileName, ShipStorage Ships)
    {
        FileInputStream strm = null;
        InputStreamReader rdr;
        BufferedReader bfr;
        String fullLine = new String(); //Full line read from BufferedReader
        String[] line = new String[7]; //Array of split values from fullLine
        int lineNum = 1; //Keeps track of current line for messages etc.
        String message = new String(); //M=Final message to return to UI
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
                if( line[0].equals("S") )
                {
                    SubClass sub = new SubClass();
                    EngineClass subEng = new EngineClass();
                    sub.setSerial( line[1] );
                    sub.setYear( line[2] );
                    subEng.setCyl( line[3] );
                    subEng.setFuel( line[4] );
                    sub.setHull( line[5] );
                    sub.setDepth( line[6] );
                    sub.setEng( subEng );
                    Ships.addShip( sub );
                }
                else if( line[0].equals("F") )
                {
                    JetClass jet = new JetClass();
                    EngineClass jetEng = new EngineClass();
                    jet.setSerial( line[1] );
                    jet.setYear( line[2] );
                    jetEng.setCyl( line[3] );
                    jetEng.setFuel( line[4] );
                    jet.setWing( line[5] );
                    jet.setOrdnance( line[6] );
                    jet.setEng( jetEng );
                    Ships.addShip( jet );
                    
                }
                else
                {
                    //Line is not a submarine or a fighter jet
                    throw new IllegalArgumentException(
                        "ship type = \"" + line[0] + "\": Ship type " +
                        "must be Submarine (S) or Fighter Jet (F)" );
                }
                fullLine = bfr.readLine();
                lineNum++;
            }
            strm.close();

            if( lineNum == 2 ) //Successful load - One ship loaded
            {
                message = ( "Successfully loaded 1 ship. You have " + 
                    Ships.getSlots() + " ship slots remaining" );
            }
            else if( lineNum > 2 ) //Succesfull load - Multiple ships loaded
            {
                message = ( "Successfully loaded " + ( lineNum - 1 ) + 
                    " ships. You have " + Ships.getSlots() + 
                    " ship slots remaining" );
            }
            else //No ships loaded due to empty file
            {
                message = ( "Error: No ships in file" );
            }
        }
        catch( IllegalArgumentException e )
        {
            message = ( "Error on line " + lineNum + " for " +
                e.getMessage() + ". " + ( lineNum - 1 ) + 
                " ship(s) have been loaded. You have " + Ships.getSlots() + 
                " ship slots remaining" );
            // All errors from setter methods methods are thrown as
            // IllegalArgumentException, The messages are formatted to add
            // detail to the error message here
        }
        catch( ArrayIndexOutOfBoundsException e2 )
        {
            message = ( "Error on line " + lineNum + ": " +
                line.length + " elements on line rather than 7. Line: \"" +
                fullLine + "\". " + ( lineNum - 1 ) + 
                " ship(s) have been loaded. You have " + Ships.getSlots() + 
                " ship slots remaining" );
            // If there are not enough values in a line

        }
        catch( FileNotFoundException e3 )
        {
            message = ( "Error: File name \"" + fileName + "\" not found" );
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
                    message = ( "Error on line " + lineNum +
                        ": Unknown error (Sorry). Line: \" " + fullLine +
                        " \". Messages: \"" + e4.getMessage() + "\", \"" +
                        e5.getMessage() + "\"" );
                }
                message = ( "Error on line " + lineNum + 
                    ": Unknown error (Sorry). Line: \" " + fullLine +
                    " \". Message: \"" + e4.getMessage() + "\"" );
            }
            else
            {
                message = ( "Error on line " + lineNum +
                    ": Unknown error (Sorry). Line: \" " + fullLine +
                    " \". Message: \"" + e4.getMessage() + "\"" );
            }
        }

        if( strm != null )
        {
            //If file is still open, close it
            try
            {
                strm.close();
            }
            catch( IOException e6 )
            {
                message = ( "Error on line " + lineNum +
                    ": Unknown error (Sorry). Line: \" " + fullLine +
                    " \". Message: \"" + e6.getMessage() + "\"" );
            }
        }
        return message;
    }

    /***********************
        Submodule: save
        Import: fileName (String), Ships (ShipStorage)
        Export: message (String)
        Assertion: Saves all ships in storage to file
    ***********************/
    public String save(String fileName, ShipStorage Ships)
    {
        String message = new String(); //Message to return at end
        FileOutputStream strm = null;
        PrintWriter pw;
        String[] fileArr;
        try
        {
            strm = new FileOutputStream( fileName );
            pw = new PrintWriter( strm );
            fileArr = new String[ Ships.getShipNum() ];

            for( int i = 0; i < Ships.getShipNum(); i++ )
            {
                pw.println( (Ships.getShip(i)).toFileString() );
            }
            pw.close();
            message = ( "Saved " + Ships.getShipNum() + " ships to " +
                fileName );
        }
        catch( IOException e3 )
        {
            if( strm != null )
            {
                try
                {
                    strm.close();
                }
                catch( IOException e4 )
                {
                    message = ( "Error: Unknown error (Sorry). Messages: \""
                        + e3.getMessage() + "\", \"" + e4.getMessage() + "\""
                        );
                }
                message = ( "Error: Unknown error (Sorry). Message: \"" +
                    e3.getMessage() + "\"" );
            }
            else
            {
                message = ( "Error: Unknown error (Sorry). Message: \"" +
                    e3.getMessage() + "\"" );
            }
        }

        return message;
    }

}
