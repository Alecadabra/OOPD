public class SubClass
{
    //Class constants
    public static final String STEEL = "STEEL";
    public static final String ALL = "ALLOY";
    public static final String TITAN = "TITANIUM";

    //Private class fields
    private double depth;
    private int year;
    private String serial, hull;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new SubClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, hull
            material ALLOY, max depth -250.0
    ************************************************/
    public SubClass()
    {
        serial = new String( "150.500" );
        year = 2000;
        hull = new String( ALL );
        depth = -250.0;
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inSerial (String), inYear (int), inHull (String),
            inDepth (double)
        EXPORT: adress of new SubClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public SubClass( String inSerial, int inYear, String inHull,
        double inDepth )
    {
        if ( validateSerial(inSerial) && validateYear(inYear) &&
            validateHull(inHull) && validateDepth(inDepth) )
        {
            serial = inSerial;
            year = inYear;
            hull = inHull;
            depth = inDepth;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Input Values");
        }
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inSub (SubClass)
        EXPORT: adress of new SubClass object
        ASSERTION: Creates new object with identical object state as import
    ************************************************/
    public SubClass(SubClass inSub)
    {
        serial = inSub.getSerial();
        year = inSub.getYear();
        hull = inSub.getHull();
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
        SubClass cloneSubClass;

        cloneSubClass = new SubClass(this.serial, this.year, 
            this.hull, this.depth );
        return cloneSubClass;
    }

    //ACCESSORS
    public String getSerial()
    {
        return new String(serial);
    }

    public int getYear()
    {
        return year;
    }

    public String getHull()
    {
        return new String(hull);
    }

    public double getDepth()
    {
        return depth;
    }

    /**********************************************
        SUBMODULE: equals
        IMPORT: inObj (object)
        EXPORT: same (boolean)
        ASSERTION: subs are equals if they share serial, year, hull 
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
        ", its engine has " + "8" + " cylinders\n |      and runs on " +
        "a battery" + ". It is a sub with a " + hull +
        " hull and a max\n |      depth of " + depth + " meters.");
    }

    /**********************************************
        SUBMODULE: toFileString
        IMPORT: none
        EXPORT: string of subclass state for file output
        ASSERTION:
    ************************************************/
    public String toFileString()
    {
        return ("S," + serial + "," + year + "," + "8" + "," + "BaTtErY" +
            "," + hull + "," + depth);
    }

    /**********************************************
        SUBMODULE: calcTravel
        IMPORT: dist (int)
        EXPORT: hours (double)
        ASSERTION: Stub
    ************************************************/
    public double calcTravel(int dist)
    {
        //Stub
        return 0.0;
    }

    //MUTATORS
    /**********************************************
        SUBMODULE: setSerial
        IMPORT: inSerial (double)
        EXPORT: none
    ************************************************/
    public void setSerial(String inSerial)
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
    private boolean validateSerial(String inSerial)
    {
        String[] serialParts = inSerial.split("\\.");
        return ( (Integer.parseInt(serialParts[0]) >= 100)
            && (Integer.parseInt(serialParts[0]) <= 300)
            && (Integer.parseInt(serialParts[1]) >= 001)
            && (Integer.parseInt(serialParts[1]) <= 999) );
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
