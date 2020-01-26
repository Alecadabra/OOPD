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
    public String load( String fileName, ShipStorage Ships )
    {
        FileInputStream strm = null;
        InputStreamReader rdr;
        BufferedReader bfr;
        String fullLine = new String(); //Full line read from BufferedReader
        int lineNum = 0; //Keeps track of current line for message etc.
        int numLoaded = 0; //Number of ships loaded
        int numErrors = 0; //Number of errors occured (Each has its 
            //own message)
        String message = new String(); //Final message to return to UI
            //Contains no. of ships loaded/slots remaining and all errors
        try
        {
            if( !fileName.contains( ".csv" ) )
            {
                //If file is not a csv
                throw new IllegalArgumentException( " for file name \"" +
                    fileName + "\": File must be a .csv" );
            }
            strm = new FileInputStream( fileName );
            rdr = new InputStreamReader( strm );
            bfr = new BufferedReader( rdr );

            fullLine = bfr.readLine();
            lineNum++;
            while( fullLine != null ) //Checks for end of file
            {
                try
                {
                    loadLine( Ships, fullLine );
                    numLoaded++;
                    //Processes the line and adds the ship to storage if
                    //there are no errors
                }
                catch( IllegalArgumentException e )
                {
                    //Catches errors specific to the line that do not
                    //require file loading to stop alltogether
                    if( Ships.getSlots() == 0 )
                    {
                        //If error is that there are no ship slots left
                        //it is an error that must stop the file being read
                        //so error is thrown outside of file reading scope
                        strm.close();
                        throw new IllegalArgumentException(
                            e.getMessage() );
                    }
                    message += ( "Error on line " + lineNum + e.getMessage() +
                        ".\n" );
                    numErrors++;
                    //Adds the error to the final returned message
                }
                fullLine = bfr.readLine();
                lineNum++;
            }
            strm.close();

        }
        catch( IllegalArgumentException e )
        {
            //Errors that stop the file being loaded
            message += ( "Error" + e.getMessage() + ".\n" );
            numErrors++;
        }
        catch( FileNotFoundException e3 )
        {
            message = ( "Error: File name \"" + fileName + "\" not found\n" );
            numErrors++;
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
                    message += ( "Error on line " + lineNum +
                        ": Unknown error (Sorry). Line: \" " + fullLine +
                        " \". Messages: \"" + e4.getMessage() + "\", \"" +
                        e5.getMessage() + "\"\n" );
                }
                message += ( "Error on line " + lineNum + 
                    ": Unknown error (Sorry). Line: \" " + fullLine +
                    " \". Message: \"" + e4.getMessage() + "\"\n" );
            }
            else
            {
                message += ( "Error on line " + lineNum +
                    ": Unknown error (Sorry). Line: \" " + fullLine +
                    " \". Message: \"" + e4.getMessage() + "\"\n" );
            }
        }


        if( numErrors > 0 && numLoaded > 0 )
        {
            message = ( numLoaded + " out of " + lineNum + 
                " ships loaded successfully. " + numErrors + 
                " error(s) found.\n" + message );
        }
        else if( numErrors > 0 )
        {
            message = ( "No ships loaded. " + numErrors + 
                " error(s) found.\n" + message );
        }
        else if( numLoaded == 0 && lineNum == 1 )
        {
            //File was empty
            message = ( "No ships in file \"" + fileName + "\".\n" );
        }
        else
        {
            message = ( numLoaded + " ships loaded successfully.\n" );
        }
        message += Ships.slotsToString();
            //Message structure:
            //No. of ships loaded + no. of errors occured
            //Errors, one after the other
            //No. of ship slots remaining

        return message;
    }

    /***********************
        Submodule: loadLine
        Import: Ships (ShipStorage), fullLine (String)
        Export: none
        Assertion: Saves a ship to storage from a line
    ***********************/
    public void loadLine( ShipStorage Ships, String fullLine )
    {
        String[] line;
            //Array of ship elements: type, serial, year, cyl etc.
        try
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
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalArgumentException( " for " + e.getMessage() );
            // All errors from setter methods methods are thrown as
            // IllegalArgumentException. At each level, detail is added to
            // the messages before they are eventually output to the user
            // in a well formatted and useful way. Eg. load() adds info
            // about the line number.
        }
        catch( ArrayIndexOutOfBoundsException e2 )
        {
            throw new IllegalArgumentException( 
                ": Less than 7 comma seperated values on line" );
            // If there are not enough values in a line

        }
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
