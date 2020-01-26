public class EngineClass
{   
    public static final String BATT = "BATTERY";
    public static final String DIES = "DIESEL";
    public static final String BIO = "BIO"; 

   //Private class fields
    private String fuel;
    private int cyl;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new EngineClass object
        ASSERTION: fuel = "BIO", cyl = 10
    ************************************************/
    public EngineClass()
    {
        fuel = new String(BATT);
        cyl = 10;
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inFuel (String), inCyl (int)
        EXPORT: adress of new EngineClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public EngineClass( String inFuel, int inCyl )
    {
        if ( validateFuel(inFuel) && validateCyl(inCyl) )
        {
            fuel = inFuel;
            cyl = inCyl;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Input Values");
        }
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inEngine (EngineClass)
        EXPORT: adress of new EngineClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public EngineClass(EngineClass inEngine)
    {
        fuel = inEngine.getFuel();
        cyl = inEngine.getCyl();
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new EngineClass object
    ************************************************/
    public EngineClass clone()
    {
        EngineClass cloneEngineClass;

        cloneEngineClass = new EngineClass(this.fuel, this.cyl );
        return cloneEngineClass;
    }

    //ACCESSORS
    public String getFuel()
    {
        return fuel;
    }

    public int getCyl()
    {
        return cyl;
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
            same = fuel.equals(inEngine.getFuel()) &&
                (cyl == inEngine.getCyl());
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
        String fuelString = fuel;
        if(fuel.equals(BATT)) {
            fuelString = "a battery";
        }
        else if(fuel.equals(DIES)) {
            fuelString = "diesel";
        }
        else if(fuel.equals(BIO)) {
            fuelString = "bio fuel";
        }
        return ("its engine has " + cyl + " cylinders and runs on " +
            fuelString + ".");
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
        SUBMODULE: setFuel
        IMPORT: inFuel (String)
        EXPORT: none
    ************************************************/
    public void setFuel(String inFuel)
    {
        if(validateFuel(inFuel))
        {
            fuel = inFuel;
        }
        else
        {
            throw new IllegalArgumentException("Invalid fuel");
        }
    }

    /**********************************************
        SUBMODULE: setCyl
        IMPORT: inCyl (int)
        EXPORT: none
    ************************************************/
    public void setCyl(int inCyl)
    {
        if(validateCyl(inCyl))
        {
            cyl = inCyl;
        }
        else
        {
            throw new IllegalArgumentException("Invalid number of cylinders");
        }
    }

    //PRIVATE SUBMODULES:
    /**********************************************
        SUBMODULE: validateFuel
        IMPORT: inFuel (String)
        EXPORT: valid (boolean)
        ASSERTION: String either "BIO" or "BATTERY" or "DIESEL"
    ************************************************/
    private boolean validateFuel(String inFuel)
    {
        return ( inFuel.equals(BIO) || inFuel.equals(BATT) || 
            inFuel.equals(DIES) );
    }

    /**********************************************
        SUBMODULE: validatCyl
        IMPORT: inCyl (int)
        EXPORT: valid (boolean)
        ASSERTION: integer between 2 and 20 inclusive
    ************************************************/
    private boolean validateCyl(int inCyl)
    {
        return ( (inCyl >= 2) && (inCyl <= 20) );
    }

}
