public class SubClass
{
    //Class constants
    public static final String STEEL = "STEEL";
    public static final String ALL = "ALLOY";
    public static final String TITAN = "TITANIUM";
    public static final String UIDOT = new String("[-] "); //For messages
    public static final String UITAB = new String(" |  "); //Continuing stuff
    public static final String UIERR = new String("[!] "); //For errors
    public static final String UIINP = new String("[>] "); //For user input
    // Used at the start of new lines to differenciate between different 
    // messages, errors and prompts for user input
 
    //Private class fields
    private double depth;
    private int year;
    private String serial, hull;
    private EngineClass Eng;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new SubClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, default
            engine, hull material ALLOY, max depth -250.0
    ************************************************/
    public SubClass()
    {
        serial = new String( "150.500" );
        year = 2000;
        Eng = new EngineClass();
        hull = new String( ALL );
        depth = -250.0;
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inSerial (String), inYear (int), inEng (EngineClass),
            inHull (String), inDepth (double)
        EXPORT: adress of new SubClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public SubClass( String inSerial, int inYear, EngineClass inEng,
        String inHull, double inDepth )
    {
        setSerial( new String( inSerial ) );
        setYear( inYear );
        setEng( new EngineClass( inEng ) );
        setHull( new String( inHull ) );
        setDepth( inDepth );
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inSub (SubClass)
        EXPORT: adress of new SubClass object
        ASSERTION: Creates new object with identical object state as import
    ************************************************/
    public SubClass(SubClass inSub)
    {
        serial = new String( inSub.getSerial() );
        year = inSub.getYear();
        Eng = new EngineClass( inSub.getEng() );
        hull = new String( inSub.getHull() );
        depth = inSub.getDepth();
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new SubClass object
        ASSERTION: Creates new object with identical object state as import
    ************************************************/
    public SubClass clone()
    {
        SubClass cloneSubClass = new SubClass( this );
        return cloneSubClass;
    }

    //ACCESSORS
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

    public String getHull()
    {
        return new String( hull );
    }

    public double getDepth()
    {
        return depth;
    }

    /**********************************************
        SUBMODULE: equals
        IMPORT: inObj (object)
        EXPORT: same (boolean)
        ASSERTION: subs are equals if they share serial, year, engine, hull 
            and depth
    ************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        if(inObj instanceof SubClass)
        {
            SubClass inSub = (SubClass)inObj;
            String upHull = new String( hull.toUpperCase() );
            String upInHull = new String( hull.toUpperCase() );

            same = ( (serial.equals(inSub.getSerial())) 
                && (year == inSub.getYear()) &&
                (Eng.equals(inSub.getEng())) &&
                (upHull.equals(upInHull)) &&
                (Math.abs(depth - inSub.getDepth())) < 0.001 );
        }
        return same;
    }

    /**********************************************
        SUBMODULE: toString
        IMPORT: none
        EXPORT: string of subclass state for readable output
        ASSERTION: 
    ************************************************/
    public String toString()
    {
        return ("The ship " + serial + " was comissioned in " + year +
        ", its engine has " + Eng.getCyl() +
        " cylinders\n |      and runs on " + Eng.getFuelString() +
        ". It is a submarine with a " + hull +
        " hull\n |      and a max depth of " + depth + " meters.");
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of subclass state for file output
        ASSERTION:
    ************************************************/
    public String toFileString()
    {
        return ("S," + serial + "," + year + "," + Eng.getCyl() + "," +
            Eng.getFuel() + "," + hull + "," + depth);
    }

    /**********************************************
        SUBMODULE: calcTravel
        IMPORT: inDistance (int)
        EXPORT: hours (double)
        ASSERTION: Finds number of hours taken to travel distance
    ************************************************/
    public double calcTravel( int inDistance )
    {
        double distance = ( (double)inDistance );
        double cylinders = ( (double)( Eng.getCyl() ) );

        return (
            ( distance / cylinders ) * ( 1 / ( 10 + ( depth * -1 ) ) ) );
    }

    //MUTATORS
    /**********************************************
        SUBMODULE: setSerial
        IMPORT: inSerial (double)
        EXPORT: none
    ************************************************/
    public void setSerial(String inSerial)
    {
        if(inSerial.equals(""))
        {
            throw new IllegalArgumentException(
                "Serial number cannot be empty");
        }
        else if(validateSerial(inSerial))
        {
            serial = inSerial;
        }
        else
        {
            throw new IllegalArgumentException(
                "Serial number must start with 3 " +
                "digits between 001 and 300\n" + UITAB +
                "inclusive and end in any three digits (###.###)" );
        }
    }

    /**********************************************
        SUBMODULE: setYear
        IMPORT: inYear (int)
        EXPORT: none
    ************************************************/
    public void setYear(int inYear)
    {
        if(validateYear(inYear))
        {
            year = inYear;
        }
        else
        {
            throw new IllegalArgumentException(
                "Year must be betweem 1950 and 2022 inclusive" );
        }
    }

    /**********************************************
        SUBMODULE: setEng
        IMPORT: inObj (double)
        EXPORT: none
    ************************************************/
    public void setEng(Object inObj)
    {
        Eng = new EngineClass( (EngineClass)inObj );
    }

    /**********************************************
        SUBMODULE: setDepth
        IMPORT: inDepth (double)
        EXPORT: none
    ************************************************/
    public void setDepth(double inDepth)
    {
        if(validateDepth(inDepth))
        {
            depth = inDepth;
        }
        else
        {
            throw new IllegalArgumentException(
                "Max depth must be between -500.0 and 0.0 inclusive"
                );
        }
    }

    /**********************************************
        SUBMODULE: setHull
        IMPORT: inHull (String)
        EXPORT: none
    ************************************************/
    public void setHull(String inHull)
    {
        if(inHull.equals(""))
        {
            throw new IllegalArgumentException(
                "Hull material cannot be empty" );
        }
        else if(validateHull(inHull))
        {
            hull = inHull;
        }
        else
        {
            throw new IllegalArgumentException( "Hull material must be " +
                "Steel, Alloy, or Titanium. Casing does not matter" );
        }
    }

    //PRIVATE SUBMODULES:
    /**********************************************
        SUBMODULE: validateSerial
        IMPORT: inSerial
        EXPORT: valid (boolean)
        ASSERTION: xxx.yyy, xxx (100-300 inc.), yyy (001-999 inc.)
    ************************************************/
    private boolean validateSerial(String inSerial)
    {
        boolean valid = false;
        try
        {
            String[] serialParts = inSerial.split("\\.");
            valid = ( (Integer.parseInt(serialParts[0]) >= 100)
                && (Integer.parseInt(serialParts[0]) <= 300)
                && (Integer.parseInt(serialParts[1]) >= 001)
                && (Integer.parseInt(serialParts[1]) <= 999) );
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
        ASSERTION:  integer between 1950 and 2022 inclusive
    ************************************************/
    private boolean validateYear(int inYear)
    {
        return (inYear >= 1950) && (inYear <= 2022);
    }

    /**********************************************
        SUBMODULE: validateDepth
        IMPORT: inDepth
        EXPORT: valid (boolean)
        ASSERTION: real num (-500 to 0.0 inclusive)
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
        return ( upHull.equals( STEEL ) || upHull.equals( ALL ) ||
            upHull.equals( TITAN ) );
    }

}
