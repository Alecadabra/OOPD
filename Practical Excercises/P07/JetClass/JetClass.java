public class JetClass
{   
    //Private class fields
    private double serial, wing;
    private int year;
    private String ordnance;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, ordnance
            "machine guns", wing 15.0
    ************************************************/
    public JetClass()
    {
        serial = 150.500;
        year = 2000;
        ordnance = new String ("machine guns");
        wing = 15.0;
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inSerial (double), inYear (int), inOrdnance (String), 
            inWing (double)
        EXPORT: adress of new JetClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public JetClass( double inSerial, int inYear, String inOrdnance,
        double inWing )
    {
        if ( validateSerial(inSerial) && validateYear(inYear) &&
            validateOrdnance(inOrdnance) && validateWing(inWing) )
        {
            serial = inSerial;
            year = inYear;
            ordnance = inOrdnance;
            wing = inWing;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Input Values");
        }
    }

    /**********************************************
        Copy Constructor:
        IMPORT: inJet (JetClass)
        EXPORT: adress of new JetClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public JetClass(JetClass inJet)
    {
        serial = inJet.getSerial();
        year = inJet.getYear();
        ordnance = inJet.getOrdnance();
        wing = inJet.getWing();
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, max ordnance
            -250.0, wing material ALLOY
    ************************************************/
    public JetClass clone()
    {
        JetClass cloneJetClass;

        cloneJetClass = new JetClass(this.serial, this.year, 
            this.ordnance, this.wing );
        return cloneJetClass;
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

    public String getOrdnance()
    {
        return ordnance;
    }

    public double getWing()
    {
        return wing;
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
            same = ( (Double.toString(serial)).equals(Double.toString(
                inJet.getSerial())) 
                && (year == inJet.getYear()) &&
                ordnance.equals(inJet.getOrdnance()) &&
                (Math.abs(wing - inJet.getWing())) < 0.001 );
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
            " ENGINEINFO " + "It is a fighter jet with a wing span of " + wing +
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
        return ("F," + serial + "," + "ENIGNEINFO" + "," + wing + ","
            + ordnance);
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
        SUBMODULE: setOrdnance
        IMPORT: inOrdnance (String)
        EXPORT: none
    ************************************************/
    public void setOrdnance(String inOrdnance)
    {
        if(validateOrdnance(inOrdnance))
        {
            ordnance = inOrdnance;
        }
        else
        {
            throw new IllegalArgumentException("Invalid ordnance");
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
            throw new IllegalArgumentException("Invalid wingspan");
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
