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
        Export: none
        Assertion: Reads ships from file and adds them to storage
    ***********************/
    public void load(String fileName, ShipStorage Ships)
    {
        FileInputStream strm = null;
        InputStreamReader rdr;
        BufferedReader bfr;
        String line = new String();
        char shipType = 0;
        String[] lineArr;
        try
        {
            strm = new FileInputStream( fileName );
            rdr = new InputStreamReader( strm );
            bfr = new BufferedReader( rdr );

            line = bfr.readLine();
            while( line != null )
            {
                lineArr = line.split(",");
                shipType = (lineArr[0].toUpperCase().charAt(0));

                if( ( lineArr.length == 7 ) &&
                    ((shipType == 'S') || (shipType == 'F')) &&
                    ( validateSerial(lineArr[1]) ) &&
                    ( validateYear(Integer.parseInt(lineArr[2])) ) &&
                    ( validateCyl(Integer.parseInt(lineArr[3])) ) &&
                    ( validateFuel(lineArr[4]) ) )
                {
                    if( (shipType == 'S') &&
                        (validateHull(lineArr[5])) &&
                        (validateDepth(Double.parseDouble(lineArr[6])) ) )
                    {
                        EngineClass eng = new EngineClass(
                            (Integer.parseInt(lineArr[3])), lineArr[4] );
                        SubClass shipObj = new SubClass(
                            lineArr[1], (Integer.parseInt(lineArr[2])),
                            lineArr[5], (Double.parseDouble(lineArr[6])) );
                        Ships.addShip(shipObj);
                    }
                    else if( (shipType == 'F') &&
                        ( validateWing(Double.parseDouble(lineArr[5])) ) &&
                        ( validateOrdnance(lineArr[6]) ) )
                    {
                        EngineClass eng = new EngineClass(
                            (Integer.parseInt(lineArr[3])), lineArr[4]);
                        JetClass shipObj = new JetClass(
                            lineArr[1], (Integer.parseInt(lineArr[2])),
                            (Double.parseDouble(lineArr[5])), lineArr[6]);
                        Ships.addShip(shipObj);
                    }
                    else
                    {
                        if( shipType == 'S' )
                        {
                            throw new IllegalArgumentException(
                                "Invalid submarine details in target file" +
                                "\n |  (Hull material: " + lineArr[5] +
                                ", Maximum depth: " + lineArr[6] + ")" );
                        }
                        else if( shipType == 'F' )
                        {
                            throw new IllegalArgumentException(
                                "Invalid fighter jet details in target file" +
                                "\n |  (Wing span: " + lineArr[5] +
                                ", Ordnance: " + lineArr[6] + ")" );
                        }
                    }
                }
                else if( lineArr.length == 7 )
                {
                    throw new IllegalArgumentException(
                        "Invalid ship details in target file" +
                        "\n |  (Ship Type: " + shipType + ", Serial number: " +
                        lineArr[1] + ", Manufacturing year: " + lineArr[2] +
                        ", Number of cylinders: " + lineArr[3] +
                        ", Fuel type: " + lineArr[4] + ")" );
                }
                else if( lineArr.length == 1 )
                {
                    throw new IllegalArgumentException(
                        "Invalid data in file\n |  ("
                        + lineArr.length + " comma seperated value rather"
                        + " than 7)" );
                }
                else // Literally only difference between these methods
                     // is "value" and "values"
                {
                    throw new IllegalArgumentException(
                        "Invalid data in file\n |  ("
                        + lineArr.length + " comma seperated values rather"
                        + " than 7)" );
                }
                line = bfr.readLine();
            }
            strm.close();
        }
        catch( NumberFormatException e2 )
        {
            throw new IllegalArgumentException(
                "Invalid ship details in target file\n |  ("
                    + e2.getMessage() + ")" );

        }
        catch( FileNotFoundException e3 )
        {
            throw new IllegalArgumentException("File name not found\n |  ("
                + fileName + ")" );
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
                    throw new IllegalArgumentException(
                        "File reading error\n |  (" + e5.getMessage() + ")" );
                }
            }
            else
            {
                throw new IllegalArgumentException("File reading error\n |  ("
                    + e4.getMessage() + ")" );
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
        catch( ArrayIndexOutOfBoundsException e )
        {
            throw new IllegalArgumentException("Array limits exceeded");
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
                



 

//Private Validation Submodules
    /**********************************************
        SUBMODULE: validateSerial
        IMPORT: inSerial
        EXPORT: valid (boolean)
        ASSERTION: xxx.yyy, xxx (100-300 inc.), yyy (001-999 inc.)
    ************************************************/
    private boolean validateSerial(String inSerial)
    {
        boolean valid = false;
        try{
        String[] serialParts = inSerial.split("\\.");
        valid = ( (Integer.parseInt(serialParts[0]) >= 100)
            && (Integer.parseInt(serialParts[0]) <= 300)
            && (Integer.parseInt(serialParts[1]) >= 001)
            && (Integer.parseInt(serialParts[1]) <= 999) );
        }
        catch(NumberFormatException e) {}
        return valid;
    }

    /**********************************************
        SUBMODULE: validateYear
        IMPORT: inYear (int)
        EXPORT: valid (boolean)
        ASSERTION:  integer between 1950 and 2022 inclusive
    ************************************************/
    private boolean validateYear(int inYear)
    {
        return (inYear >= 1950) && (inYear <= 2022);
    }

    /**********************************************
        SUBMODULE: validateFuel
        IMPORT: inFuel (String)
        EXPORT: valid (boolean)
        ASSERTION: String either "BIO" or "BATTERY" or "DIESEL"
    ************************************************/
    private boolean validateFuel(String inFuel)
    {
        String upFuel = new String(inFuel.toUpperCase());
        return ( upFuel.equals( "BIO" ) || upFuel.equals( "BATTERY" ) || 
            upFuel.equals( "DIESEL" ) );
    }

    /**********************************************
        SUBMODULE: validateCyl
        IMPORT: inCyl (int)
        EXPORT: valid (boolean)
        ASSERTION: integer between 2 and 20 inclusive
    ************************************************/
    private boolean validateCyl(int inCyl)
    {
        return ( (inCyl >= 2) && (inCyl <= 20) );
    }

    /**********************************************
        SUBMODULE: validateDepth
        IMPORT: inDepth
        EXPORT: valid (boolean)
        ASSERTION: real num (-550 to 0.0 inclusive)
    ************************************************/
    private boolean validateDepth(double inDepth)
    {
        return ( (inDepth > -500.0) && (inDepth < 0.1) ) ||
            (Math.abs(inDepth + 500.0) < 0.001 ) ||
            (Math.abs(inDepth - 0.0 ) < 0.001 );
    }

    /**********************************************
        SUBMODULE: validateHull
        IMPORT: inHull (String)
        EXPORT: valid (boolean)
        ASSERTION: string either "STEEL" or "ALLOY" or "TITANIUM"
    ************************************************/
    private boolean validateHull(String inHull)
    {
        String upHull = new String(inHull.toUpperCase());
        return ( upHull.equals( "STEEL" ) || upHull.equals( "ALLOY" ) ||
            upHull.equals( "TITANIUM" ) );
    }

    /**********************************************
        SUBMODULE: validateOrdnance
        IMPORT: inOrdnance (String)
        EXPORT: valid (boolean)
        ASSERTION: non-empty String
    ************************************************/
    private boolean validateOrdnance(String inOrdnance)
    {
        return (!(inOrdnance.equals("")));
    }

    /**********************************************
        SUBMODULE: validateWing
        IMPORT: inWing (double)
        EXPORT: valid (boolean)
        ASSERTION: double between 2.20 and 25.6 inclusive
    ************************************************/
    private boolean validateWing(double inWing)
    {
        return ( (inWing > 2.20) && (inWing < 25.6) ) ||
            (Math.abs(inWing - 2.20) < 0.001 ) ||
            (Math.abs(inWing - 25.6) < 0.001);
    }
 
}
