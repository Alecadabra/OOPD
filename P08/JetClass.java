public class JetClass
{   
    //Private class fields
    private double wing;
    private int year;
    private String serial, ordnance;

    /**********************************************
        Default Constructor:
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: serial number 150.500, manufacturing year 2000, wing 15.0,
            ordnance "machine guns"
    ************************************************/
    public JetClass()
    {
        serial = new String ("150.500");
        year = 2000;
        wing = 15.0;
        ordnance = new String ("machine guns");
    }

    /**********************************************
        Alternate Constructor:
        IMPORT: inSerial (String), inYear (int), inWing (double), 
            inOrdnance (String)
        EXPORT: adress of new JetClass object
        ASSERTION: creates the object if the imports are valid and FAILS
            otherwise
    ************************************************/
    public JetClass( String inSerial, int inYear, double inWing,
        String inOrdnance )
    {
        if ( validateSerial(inSerial) && validateYear(inYear) &&
            validateWing(inWing) && validateOrdnance(inOrdnance) )
        {
            serial = inSerial;
            year = inYear;
            wing = inWing;
            ordnance = inOrdnance;
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
        wing = inJet.getWing();
        ordnance = inJet.getOrdnance();
    }

    /**********************************************
        SUBMODULE: clone
        IMPORT: none
        EXPORT: adress of new JetClass object
        ASSERTION: Creates new object with identical object state as the import
    ************************************************/
    public JetClass clone()
    {
        JetClass cloneJetClass;

        cloneJetClass = new JetClass(this.serial, this.year, 
            this.wing, this.ordnance );
        return cloneJetClass;
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

    public double getWing()
    {
        return wing;
    }

    public String getOrdnance()
    {
        return new String(ordnance);
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
        return ("F," + serial + "," + year + "," + "8" + "," + "BaTtErY" +
            "," + wing + "," + ordnance);
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
        IMPORT: inSerial (String)
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
        IMPORT: inSerial (String)
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
