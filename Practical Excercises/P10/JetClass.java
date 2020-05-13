/*******************************
    Author: Alec Maughan
    Purpose: Fighter jet class inheriting serial, year and engine from ship
        class, contains wing and ordnance
    Date: 23/05/2019
*******************************/
public class JetClass extends ShipClass
{
    //Private class fields
    private double wing; //Fighter jet wing span
    private String ordnance; //Fighter jet ordnance

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, default
            engine, wing 15.0, ordnance "machine guns"
    ************************************************/
    public JetClass()
    {
        super();
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
            super( inSerial, inYear, inEng );
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
        super( inJet );
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
            same = ( ( super.equals( inJet ) ) &&
                ( Math.abs( wing - inJet.getWing() ) < 0.001 ) &&
                ( ordnance.equals(inJet.getOrdnance() ) ) );
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
        return ("The ship " + super.getSerial() + " was comissioned in " +
        super.getYear() + ", " + super.getEng().toString() +
        " It is a fighter jet with a wing span of " + wing +
        " meters and equipped with " + ordnance + ".");
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of jetclass state for file output
        ASSERTION:
    ************************************************/
    public String toFileString()
    {
        return ( "F," + super.getSerial() + "," + super.getYear() + "," +
            super.getEng().toFileString() + "," + wing + "," + ordnance );
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
        double cylinders = ( (double)( super.getEng().getCyl() ) );

        return(
            distance / ( wing * cylinders * 150 ) );
    }

 
    //MUTATORS
    /**********************************************
        SUBMODULE: setOrdnance
        IMPORT: inOrdnance (String)
        EXPORT: none
    ************************************************/
    public void setOrdnance(String inOrdnance)
    {
        if( !( validateOrdnance( inOrdnance ) ) )
        {
            //If import is not valid ordnance/is empty
            throw new IllegalArgumentException(
                "ordnance \"" + inOrdnance + "\":\n |  " +
                "Ordnance cannot be empty" );
        }

        ordnance = inOrdnance;
    }

    /**********************************************
        SUBMODULE: setWing
        IMPORT: inWing (double)
        EXPORT: none
    ************************************************/
    public void setWing(double inWing)
    {
        if( !( validateWing( inWing ) ) )
        {
            //If import is not a vlaid wing span
            throw new IllegalArgumentException(
                "wing span \"" + inWing + "\":\n |  " + 
                "Wing span must be between 2.20 and 25.6 inclusive" );
        }

        wing = inWing;
    }

    /**********************************************
        SUBMODULE: setWing
        IMPORT: inWing (double)
        EXPORT: none
    ************************************************/
    public void setWing( String inString )
    {
        double inWing;
        if( inString.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "wing span \"\": Wing span cannot be empty" );
        }
        try
        {
            inWing = Double.parseDouble( inString );
        }
        catch( NumberFormatException e )
        {
            //If import is not a double
            throw new IllegalArgumentException(
                "wing span \"" + inString + "\": " + 
                "Wing span must be a real number" );
        }
        if( !( validateWing( inWing ) ) )
        {
            //If import is not a valid wing span
            throw new IllegalArgumentException(
                "wing span \"" + inString + "\": " + 
                "Wing span must be between 2.20 and 25.6 inclusive" );
        }

        wing = inWing;
    }

    //PRIVATE SUBMODULES:
    /**********************************************
        SUBMODULE: validateOrdnance
        IMPORT: inOrdnance (String)
        EXPORT: valid (boolean)
        ASSERTION: non-empty String
    ************************************************/
    private boolean validateOrdnance(String inOrdnance)
    {
        return ( !( inOrdnance.equals( "" ) ) );
    }

    /**********************************************
        SUBMODULE: validateWing
        IMPORT: inWing (double)
        EXPORT: valid (boolean)
        ASSERTION: double between 2.20 and 25.6 inclusive
    ************************************************/
    private boolean validateWing(double inWing)
    {
        return ( ( (inWing > 2.20) && (inWing < 25.6) ) ||
            ( Math.abs( inWing - 2.20 ) < 0.001 ) ||
            ( Math.abs( inWing - 25.6 ) < 0.001 ) );
    }

}
