/*******************************
    Author: Alec Maughan
    Purpose: Submarine class inheriting serial, year and engine from ship 
        class, contaitning depth and hull
    Date: 23/05/2019
*******************************/
public class SubClass extends ShipClass
{
    //Class constants
    public static final String STEEL = "STEEL";
    public static final String ALL = "ALLOY";
    public static final String TITAN = "TITANIUM";

    //Private class fields
    private double depth; //Submarine max depth
    private String hull; //Submarine hull material

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new SubClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, default
            engine, hull material ALLOY, max depth -250.0
    ************************************************/
    public SubClass()
    {
        super();
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
        super( inSerial, inYear, inEng );
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
        super( inSub );
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
            String upInHull = new String( inSub.getHull().toUpperCase() );

            same = ( (super.equals( inSub ) &&
                ( upHull.equals( upInHull ) ) &&
                ( Math.abs( depth - inSub.getDepth() ) ) < 0.001 ) );
        }
        return same;
    }

    /**********************************************
        SUBMODULE: toString
        IMPORT: none
        EXPORT: string of subclass state for readable output
        ASSERTION: Exports a string containing all classfields in a human
            readable format
    ************************************************/
    public String toString()
    {
        return ( "The ship " + super.getSerial() + " was comissioned in " +
            super.getYear() + ", " + super.getEng().toString() +
            " It is a submarine with a " + hull +
            " hull and a max depth of " + depth + " meters." );
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of subclass state for file output
        ASSERTION: Exports a string containing all classfields in a format
            for fileIO
    ************************************************/
    public String toFileString()
    {
        return ( "S," + super.getSerial() + "," + super.getYear() + "," +
            super.getEng().toFileString() + "," + hull + "," + depth );
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
        double cylinders = ( (double)( super.getEng().getCyl() ) );

        return (
            ( distance / cylinders ) * ( 1 / ( 10 + ( depth * -1 ) ) ) );
    }

    //MUTATORS
    /**********************************************
        SUBMODULE: setDepth
        IMPORT: inDepth (double)
        EXPORT: none
    ************************************************/
    public void setDepth(double inDepth)
    {
        if( !( validateDepth( inDepth ) ) )
        {
            //If import is not a valid max depth
            throw new IllegalArgumentException(
                "max depth \"" + inDepth + "\": " +
                "Max depth must be between -500.0 and 0.0 inclusive" );
        }

        depth = inDepth;
    }

    /**********************************************
        SUBMODULE: setDepth
        IMPORT: inString (String)
        EXPORT: none
    ************************************************/
    public void setDepth( String inString )
    {
        double inDepth;
        if( inString.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "max depth \"\": Max depth cannot be empty" );
        }
        try
        {
            inDepth = Double.parseDouble( inString );
        }
        catch( NumberFormatException e )
        {
            //If import is not a double
            throw new IllegalArgumentException(
                "max depth \"" + inString + "\": " +
                "Max depth must be a real number" );
        }
        if( !( validateDepth( inDepth ) ) )
        {
            //If import is not a valid max depth
            throw new IllegalArgumentException(
                "max depth \"" + inString + "\": " + 
                "Max depth must be between -500.0 and 0.0 inclusive" );
        }

        depth = inDepth;
    }

    /**********************************************
        SUBMODULE: setHull
        IMPORT: inHull (String)
        EXPORT: none
    ************************************************/
    public void setHull(String inHull)
    {
        if( inHull.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "hull material \"" + inHull + "\": " + 
                "Hull material cannot be empty" );
        }
        else if( !( validateHull( inHull ) ) )
        {
            //If import is not a valid hull material
            throw new IllegalArgumentException(
                "hull material \"" + inHull + "\": " +
                "Hull material must be Steel, Alloy, or Titanium. " + 
                "Casing does not matter" );
        }

        hull = inHull;
        
    }

    //PRIVATE SUBMODULES:
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
