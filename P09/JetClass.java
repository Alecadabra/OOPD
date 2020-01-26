public class JetClass
{
    //Class constants
    public static final String UIDOT = new String("[-] "); //For messages
    public static final String UITAB = new String(" |  "); //Continuing stuff
    public static final String UIERR = new String("[!] "); //For errors
    public static final String UIINP = new String("[>] "); //For user input
    // Used at the start of new lines to differenciate between different 
    // messages, errors and prompts for user input
 
    //Private class fields
    private double wing;
    private int year;
    private String serial, ordnance;
    private EngineClass Eng;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, default
            engine, wing 15.0, ordnance "machine guns"
    ************************************************/
    public JetClass()
    {
        serial = new String ( "150.500" );
        year = 2000;
        Eng = new EngineClass();
        wing = 15.0;
        ordnance = new String ("machine guns");
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inSerial (String), inYear (int), inEngine (EngineClass),
            inWing (double), inOrdnance (String)
        EXPORT: adress of new JetClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public JetClass( String inSerial, int inYear, EngineClass inEng,
        double inWing, String inOrdnance )
    {
            setSerial( new String( inSerial ) );
            setYear( inYear );
            setEng( new EngineClass( inEng ) );
            setWing( inWing );
            setOrdnance( new String( inOrdnance ) );
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inJet (JetClass)
        EXPORT: adress of new JetClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public JetClass(JetClass inJet)
    {
        serial = new String( inJet.getSerial() );
        year = inJet.getYear();
        Eng = new EngineClass( inJet.getEng() );
        wing = inJet.getWing();
        ordnance = new String( inJet.getOrdnance() );
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public JetClass clone()
    {
        JetClass cloneJetClass = new JetClass( this );
        return cloneJetClass;
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

    public double getWing()
    {
        return wing;
    }

    public String getOrdnance()
    {
        return new String( ordnance );
    }

    /**********************************************
        SUBMODULE: equals
        IMPORT: inObj (object)
        EXPORT: same (boolean)
        ASSERTION: jets are equals if they share serial, year, ordnance 
            and wing
    ************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        if(inObj instanceof JetClass)
        {
            JetClass inJet = (JetClass)inObj;
            same = ( (serial.equals(inJet.getSerial())) 
                && (year == inJet.getYear()) &&
                (Eng.equals(inJet.getEng())) &&
                (Math.abs(wing - inJet.getWing())) < 0.001 &&
                ordnance.equals(inJet.getOrdnance()) );
        }
        return same;
    }

    /**********************************************
        SUBMODULE: toString
        IMPORT: none
        EXPORT: string of jetclass state for readable output
        ASSERTION: 
    ************************************************/
    public String toString()
    {
        return ("The ship " + serial + " was comissioned in " + year +
        ", its engine has " + "8" + " cylinders \n |      and runs on " +
        "a battery" + ". It is a fighter jet with a wing span of\n |      "
            + wing + " meters and equipped with " + ordnance + ".");
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of jetclass state for file output
        ASSERTION:
    ************************************************/
    public String toFileString()
    {
        return ("F," + serial + "," + year + "," + Eng.getCyl() + "," +
            Eng.getFuel() + "," + wing + "," + ordnance);
    }

    /**********************************************
        SUBMODULE: calcTravel
        IMPORT: inDistance (int)
        EXPORT: hours (double)
        ASSERTION: Finds the number of hours taken to travel a distance
    ************************************************/
    public double calcTravel( int inDistance )
    {
        double distance = ( (double)inDistance );
        double cylinders = ( (double)( Eng.getCyl() ) );

        return(
            distance / ( wing * cylinders * 150 ) );
    }

 
    //MUTATORS
    /**********************************************
        SUBMODULE: setSerial
        IMPORT: inSerial (String)
        EXPORT: none
    ************************************************/
    public void setSerial(String inSerial)
    {
        if(inSerial.equals(""))
        {
            throw new IllegalArgumentException(
                "Serial number cannot be empty" );
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
                "Year must be betweem 1950 and 2022 inclusive");
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
        SUBMODULE: setOrdnance
        IMPORT: inOrdnance (String)
        EXPORT: none
    ************************************************/
    public void setOrdnance(String inOrdnance)
    {
        /*
        if(inOrdnance.contains(","))
        {
            throw new IllegalArgumentException(
                "Ordnance cannot cotain commas because they are beyond\n" +
                UITAB + "the processing capabilities of modern computing" );
        }
        else */if(validateOrdnance(inOrdnance))
        {
            ordnance = inOrdnance;
        }
        else
        {
            throw new IllegalArgumentException( "Ordnance cannot be empty" );
        }
    }

    /**********************************************
        SUBMODULE: setWing
        IMPORT: inWing (double)
        EXPORT: none
    ************************************************/
    public void setWing(double inWing)
    {
        if(validateWing(inWing))
        {
            wing = inWing;
        }
        else
        {
            throw new IllegalArgumentException(
                "Wing span must be between 2.20 and 25.6 inclusive" );
        }
    }

    //PRIVATE SUBMODULES:
    /**********************************************
        SUBMODULE: validateSerial
        IMPORT: inSerial (String)
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
