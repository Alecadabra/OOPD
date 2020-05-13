public class SubmarineClass
{
    //Class constants
    public static final String STEEL = "STEEL";
    public static final String ALL = "ALLOY";
    public static final String TITAN = "TITANIUM";

    //Private class fields
    private double serial, depth;
    private int year;
    private String hull;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new SubmarineClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, max depth
            -250.0, hull material ALLOY
    ************************************************/
    public SubmarineClass()
    {
        serial = 150.500;
        year = 2000;
        depth = -250.0;
        hull = new String( ALL );
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inSerial (double), inYear (int), inDepth (double), 
            inHull (String)
        EXPORT: adress of new SubmarineClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public SubmarineClass( double inSerial, int inYear, double inDepth,
        String inHull )
    {
        if ( validateSerial(inSerial) && validateYear(inYear) &&
            validateDepth(inDepth) && validateHull(inHull) )
        {
            serial = inSerial;
            year = inYear;
            depth = inDepth;
            hull = inHull;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Input Values");
        }
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inSubmarine (SubmarineClass)
        EXPORT: adress of new SubmarineClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public SubmarineClass(SubmarineClass inSubmarine)
    {
        serial = inSubmarine.getSerial();
        year = inSubmarine.getYear();
        depth = inSubmarine.getDepth();
        hull = inSubmarine.getHull();
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new SubmarineClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, max depth
            -250.0, hull material ALLOY
    ************************************************/
    public SubmarineClass clone()
    {
        SubmarineClass cloneSubmarineClass;

        cloneSubmarineClass = new SubmarineClass(this.serial, this.year, 
            this.depth, this.hull );
        return cloneSubmarineClass;
    }

    //ACCESSORS
    public double getSerial()
    {
        return serial;
    }

    public int getYear()
    {
        return year;
    }

    public double getDepth()
    {
        return depth;
    }

    public String getHull()
    {
        return hull;
    }

    /**********************************************
        SUBMODULE: equals
        IMPORT: inObj (object)
        EXPORT: same (boolean)
        ASSERTION: submarines are equals if they share serial, year, depth 
            and hull
    ************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        if(inObj instanceof SubmarineClass)
        {
            SubmarineClass inSubmarine = (SubmarineClass)inObj;
            same = ( (Double.toString(serial)).equals(Double.toString(
                inSubmarine.getSerial())) 
                && (year == inSubmarine.getYear()) &&
                (Math.abs(depth - inSubmarine.getDepth())) < 0.001 &&
                (hull.equals(inSubmarine.getHull())) );
        }
        return same;
    }

    /**********************************************
        SUBMODULE: toString
        IMPORT: none
        EXPORT: string of submarineclass state for readable output
        ASSERTION: 
    ************************************************/
    public String toString()
    {
        return ("The ship " + serial + " was comissioned in " + year +
            " ENGINEINFO " + "It is a submarine with a " + hull +
            " hull and a max depth of " + depth + " meters.");
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of submarineclass state for file output
        ASSERTION:
    ************************************************/
    public String toFileString()
    {
        return ("S," + serial + "," + "ENIGNEINFO" + "," + hull + ","
            + depth);
    }

    //MUTATORS
    /**********************************************
        SUBMODULE: setSerial
        IMPORT: inSerial (double)
        EXPORT: none
    ************************************************/
    public void setSerial(double inSerial)
    {
        if(validateSerial(inSerial))
        {
            serial = inSerial;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Serial number");
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
            throw new IllegalArgumentException("Invalid manufacturing year");
        }
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
            throw new IllegalArgumentException("Invalid manufacturing year");
        }
    }

    /**********************************************
        SUBMODULE: setHull
        IMPORT: inHull (String)
        EXPORT: none
    ************************************************/
    public void setHull(String inHull)
    {
        if(validateHull(inHull))
        {
            hull = inHull;
        }
        else
        {
            throw new IllegalArgumentException("Invalid hull material");
        }
    }

    //PRIVATE SUBMODULES:
    /**********************************************
        SUBMODULE: validateSerial
        IMPORT: inSerial
        EXPORT: valid (boolean)
        ASSERTION: xxx.yyy, xxx (100-300 inc.), yyy (001-999 inc.)
    ************************************************/
    private boolean validateSerial(double inSerial)
    {
        return ( ((int)inSerial >= 100) && ((int)inSerial <= 300) &&
            ((int)(inSerial * 1000000) % 1000 == 0) );
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
        ASSERTION: real num (-550 to 0.0 inclusive)
    ************************************************/
    private boolean validateDepth(double inDepth)
    {
        return ( (inDepth > -500.0) && (inDepth < 0.0) ) ||
            ( (Math.abs(inDepth + 500.0) < 0.001 ) && 
            (Math.abs(inDepth) < 0.001) );
    }

    /**********************************************
        SUBMODULE: validateHull
        IMPORT: inHull (String)
        EXPORT: valid (boolean)
        ASSERTION: string either "STEEL" or "ALLOY" or "TITANIUM"
    ************************************************/
    private boolean validateHull(String inHull)
    {
        return ( inHull.equals( STEEL ) || inHull.equals( ALL ) ||
            inHull.equals( TITAN ) );
    }

}
