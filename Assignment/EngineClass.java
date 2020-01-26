/*******************************
    Author: Alec Maughan
    Purpose: Engine class, asscociated with ship class, contains cylinders
        and fuel type
    Date: 23/05/2019
*******************************/
public class EngineClass
{   
    //Class constants
    public static final String BATT = "BATTERY";
    public static final String DIES = "DIESEL";
    public static final String BIO = "BIO"; 

    //Private class fields
    private int cyl; //Engine number of cylinders
    private String fuel; //Engine fuel type

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new EngineClass object
        ASSERTION: cyl = 10, fuel = "BIO"
    ************************************************/
    public EngineClass()
    {
        cyl = 10;
        fuel = new String( BATT );
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inCyl (integer), inFuel (String)
        EXPORT: adress of new EngineClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public EngineClass( int inCyl, String inFuel )
    {
        setCyl( inCyl );
        setFuel( new String( inFuel ) );
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inEngine (EngineClass)
        EXPORT: adress of new EngineClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public EngineClass(EngineClass inEngine)
    {
        cyl = inEngine.getCyl();
        fuel = inEngine.getFuel();
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new EngineClass object
    ************************************************/
    public EngineClass clone()
    {
        EngineClass cloneEngineClass = new EngineClass( this );
        return cloneEngineClass;
    }

    //ACCESSORS
    public int getCyl()
    {
        return cyl;
    }


    public String getFuel()
    {
        return new String ( fuel );
    }

    /**********************************************
        SUBMODULE: getFuelString
        IMPORT: none
        EXPORT: fuelString
        ASSERTION: Exports a more readable String describing fuel type
            for user output
    ************************************************/
    public String getFuelString()
    {
        String fuelString = new String( fuel );

        if(fuel.toUpperCase().equals(BATT)) {
            fuelString = "a battery";
        }
        else if(fuel.toUpperCase().equals(DIES)) {
            fuelString = "diesel fuel";
        }
        else if(fuel.toUpperCase().equals(BIO)) {
            fuelString = "bio fuel";
        }
        else
        {
            throw new IllegalArgumentException("Invalid fuel type");
        }

        return fuelString;
    }

    /**********************************************
        SUBMODULE: equals
        IMPORT: inObj (object)
        EXPORT: same (boolean)
        ASSERTION: engines are equals if they share the same fuel and number
            of cylinders
    ************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        if(inObj instanceof EngineClass)
        {
            EngineClass inEngine = (EngineClass)inObj;
            same = (cyl == inEngine.getCyl()) && 
                fuel.equals(inEngine.getFuel() );
        }
        return same;
    }

    /**********************************************
        SUBMODULE: toString
        IMPORT: none
        EXPORT: string of engineclass state for readable output
        ASSERTION: 
    ************************************************/
    public String toString()
    {
        return ("its engine has " + cyl + " cylinders and runs on " +
            getFuelString() + ".");
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of engineclass state for file output
        ASSERTION:
    ************************************************/
    public String toFileString()
    {
        return ( cyl + "," + fuel );
    }

    //MUTATORS
    /**********************************************
        SUBMODULE: setCyl
        IMPORT: inCyl (int)
        EXPORT: none
        ASSERTION: Sets the number of cylinders of the current object if
            imports are valid and FAILS otherwise
    ************************************************/
    public void setCyl( int inCyl )
    {
        if( !( validateCyl( inCyl ) ) )
        {
            //If import is not a valid number of cylinders
            throw new IllegalArgumentException(
                "number of cylinders \"" + inCyl + "\": " + 
                "Number of cylinders must be between 2 and 20 inclusive" );
        }

        cyl = inCyl;
    }

    /**********************************************
        SUBMODULE: setCyl
        IMPORT: inCyl (int)
        EXPORT: none
        ASSERTION: Sets the number of cylinders of current object if imports
            are valid and FAILS otherwise
    ************************************************/
    public void setCyl( String inString )
    {
        int inCyl;
        if( inString.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "number of cylinders \"\": " + 
                "Number of cylinders cannot be empty" );
        }
        try
        {
            inCyl = Integer.parseInt( inString );
        }
        catch( NumberFormatException e )
        {
            //If import is not an integer
            throw new IllegalArgumentException(
                "number of cylinders \"" + inString + "\": " + 
                "Number of cylinders must be an integer" );
        }
        if( !( validateCyl( inCyl ) ) )
        {
            //If import is not a valid number of cylinders
            throw new IllegalArgumentException(
                "number of cylinders \"" + inString + "\": " + 
                "Number of cylinders must be between 2 and 20 inclusive" );
        }

        cyl = inCyl;
    }

    /**********************************************
        SUBMODULE: setFuel
        IMPORT: inFuel (String)
        EXPORT: none
        ASSERTION: Sets the fuel type of the current object if imports are
            valid and FAILS otherwise
    ************************************************/
    public void setFuel(String inFuel)
    {
        if( inFuel.equals( "" ) )
        {
            //If import is empty
            throw new IllegalArgumentException(
                "fuel type \"" + inFuel + "\": " + 
                "Fuel type cannot be empty" );
        }
        if( !( validateFuel( inFuel ) ) )
        {
            //If import is not a valid fuel type
            throw new IllegalArgumentException(
                "fuel type \"" + inFuel + "\": " + 
                "Fuel type must be Battery, Bio, or Diesel. " + 
                "Casing does not matter" );
        }

        fuel = inFuel;
    }

    //PRIVATE SUBMODULES:
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
        SUBMODULE: validateFuel
        IMPORT: inFuel (String)
        EXPORT: valid (boolean)
        ASSERTION: String either "BIO" or "BATTERY" or "DIESEL"
    ************************************************/
    private boolean validateFuel(String inFuel)
    {
        String upFuel = new String(inFuel.toUpperCase());
        return ( upFuel.equals(BIO) || upFuel.equals(BATT) || 
            upFuel.equals(DIES) );
    }

}
