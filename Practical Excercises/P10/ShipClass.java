/*******************************
    Author: Alec Maughan
    Purpose: Super class for subs and jets, has serial num, year and engine
    Date: 23/05/2019
*******************************/
public abstract class ShipClass
{
    //Private class fields
    private String serial; //Ship serial number
    private int year; //Ship manufacturing year
    private EngineClass Eng; //Ship engine

    /**********************************************
        DEFAULT CONSTRUCTOR
        IMPORT: none
        EXPORT: memory adress of new ShipClass object
        ASSERTION: Constructs shipclass object using valid state
    ************************************************/ 
    public ShipClass()
    {
        super();
        serial = new String( "150.500" );
        year = 2000;
        Eng = new EngineClass();
    }

    /**********************************************
        ALTERNATE CONSTRUCTOR
        IMPORT: inSerial (String), inYear (int), inEng (EngineClass)
        EXPORT: memory adress of new ShipClass object
        ASSERTION: Constructs shipclass object using imported state
    ************************************************/ 
    public ShipClass( String inSerial, int inYear, EngineClass inEng )
    {
        super();
        setSerial( new String( inSerial ) );
        setYear( inYear );
        setEng( new EngineClass( inEng ) );
    }

    /**********************************************
        COPY CONSTRUCTOR
        IMPORT: inShip (ShipClass)
        EXPORT: memory adress of new ShipClass object
        ASSERTION: Constructs shipclass object using state of imported object
    ************************************************/  
    public ShipClass( ShipClass inShip )
    {
        setSerial( new String( inShip.getSerial() ) );
        setYear( inShip.getYear() );
        setEng( new EngineClass( inShip.getEng() ) );
    }

    public abstract ShipClass clone();

    //Acessors
    public String getSerial()
    {
        return new String( serial );
    }

    public int getYear()
    {
        return year;
    }

    public EngineClass getEng()
    {
        return new EngineClass( Eng );
    }

    //Setters
    /**********************************************
        SUBMODULE: setSerial
        IMPORT: inSerial (String)
        EXPORT: none
        ASSERTION: Sets serial number of object
    ************************************************/ 
    public void setSerial( String inSerial )
    {
        if( inSerial.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "serial number \"" + inSerial + 
                "\": Serial number cannot be empty" );
        }
        if( !( validateSerial( inSerial ) ) )
        {
            //If import is not a valid serial number
            throw new IllegalArgumentException( 
                "serial number \"" + inSerial + "\": Serial number must " +
                "start with 3 digits between 001 and 300 inclusive and " +
                "end in three digits between 001 and 999 inclusive (###.###)"
                );
        }

        serial = inSerial;
    }

    /**********************************************
        SUBMODULE: setYear
        IMPORT: inYear (int)
        EXPORT: none
        ASSERTION: Sets manufacturing year of object
    ************************************************/ 
    public void setYear( int inYear )
    {
        if( !( validateYear( inYear ) ) )
        {
            //If import is not a valid manufacturing year
            throw new IllegalArgumentException(
                "manufacturing year \"" + inYear + "\": " +
                "Manufacturing year must be between 1950 and 2022 inclusive"
                );
        }

        year = inYear;
    }

    /**********************************************
        SUBMODULE: setYear
        IMPORT: inYear (String)
        EXPORT: none
        ASSERTION: Sets maufacturing year of object from a parsed String
    ************************************************/ 
    public void setYear( String inString )
    {
        int inYear;
        if( inString.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "manufacturing year \"" + inString +
                "\": Manufacturing year cannot be empty" );
        }
        try
        {
            inYear = Integer.parseInt( inString );
        }
        catch( NumberFormatException e )
        {
            //If import is not an integer
            throw new IllegalArgumentException(
                "manufacturing year \"" + inString + 
                "\": Manufacturing year must be an integer" );
        }
        if( !( validateYear( inYear ) ) )
        {
            //If import is not a valid manufacturing year
            throw new IllegalArgumentException(
                "manufacturing year \"" + inString + "\": " +
                "Manufacturing year must be between 1950 and 2022 inclusive" );
        }

        year = inYear;
    }

    /**********************************************
        SUBMODULE: setEng
        IMPORT: inEng (EngineClass)
        EXPORT: none
        ASSERTION: Sets the engine of object
    ************************************************/  
    public void setEng( EngineClass inEng )
    {
        Eng = new EngineClass( (EngineClass)inEng );
    }

    /**********************************************
        SUBMODULE: equals
        IMPORT: inObj (Object)
        EXPORT: same (boolean)
        ASSERTION: Checks the equality of two ShipClass objects
    ************************************************/  
    public boolean equals( Object inObj )
    {
        boolean same = false;
        if( inObj instanceof ShipClass )
        {
            ShipClass inShip = (ShipClass)inObj;
            same = ( serial.equals( inShip.getSerial() ) &&
                ( year == inShip.getYear() ) );
        }
        return same;
    }

    public abstract String toString();

    public abstract String toFileString();

    public abstract double calcTravel( int inDistance );

    //Private validation submodules
    /**********************************************
        SUBMODULE: validateSerial
        IMPORT: inSerial (String)
        EXPORT: valid (boolean)
        ASSERTION: Checks the validity of the import as a serial number
    ************************************************/  
    private boolean validateSerial( String inSerial )
    {                                                                   
        boolean valid = false;
        try
        {
            String[] serialParts = inSerial.split("\\.");
            valid = ( ( serialParts[0].length() == 3 )
                    //Check there are 3 digits in first part
                && ( Integer.parseInt( serialParts[0] ) >= 100 )
                    //Check that first part is greater than 100 inc.
                && ( Integer.parseInt( serialParts[0] ) <= 300 )
                    //Check that first part is less than 300 inc.
                && ( serialParts[1].length() == 3 )
                    //Check to make sure there are 3 digits in 2nd part
                && !( serialParts[1].equals( "000" ) ) );
                    //Check to see first 3 digits are not 000 in 2nd part
         }
         catch( ArrayIndexOutOfBoundsException e )
         {
             valid = false;
         }
         catch( NumberFormatException e2 )
         {
             valid = false;
         }
         return valid;
    }

    /**********************************************
        SUBMODULE: validateYear
        IMPORT: inYear (int)
        EXPORT: valid (boolean)
        ASSERTION: Checks the validity of the import as a manufacturing year
    ************************************************/  
    private boolean validateYear( int inYear )
    {
        return ( inYear >= 1950 ) && ( inYear <= 2022 );
    }

}
