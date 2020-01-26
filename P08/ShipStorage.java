/*******************************
    Author: Alec Maughan
    Purpose: Ship storage system, stores ships in seperate arrays
    Date: 9/5/19
*******************************/
public class ShipStorage
{
    //Class constants
    public static final int MAXSHIPS = 15;

    //Private class fields
    private SubClass[] subArr;
    private JetClass[] jetArr;
    private int subNum, jetNum;

    /************************************
        Default Contructor
        IMPORT: none
        EXPORT: memory address of new ShipStorage object
        ASSERTION: initialises arrays
    ************************************/
    public ShipStorage()
    {
        subArr = new SubClass[MAXSHIPS];
        jetArr = new JetClass[MAXSHIPS];
        subNum = 0;
        jetNum = 0;
    }

    /************************************
        SUBMODULE: addShip (SubClass)
        IMPORT: inSub (Subclass)
        EXPORT: none
        ASSERTION: adds sub object to subArr if there is space in the array
            and the object is not null
    ************************************/
    public void addShip(SubClass inSub)
    {
        if( ( inSub != null ) && ( subNum < MAXSHIPS ) )
        {
            subArr[subNum] = inSub;
            subNum++;
        }
        else if( inSub == null )
        {
            throw new IllegalArgumentException("Invalid SubClass Object");
            // needs error based on which if statement was false
        }
        else if( subNum >= MAXSHIPS )
        {
            throw new IllegalArgumentException("Number of Submarines has "
                + "reached the maximum capacity (" + MAXSHIPS + ")" );
        }
    }

    /************************************
        SUBMODULE: addShip (SubClass)
        IMPORT: inJet (JetClass)
        EXPORT: none
        ASSERTION: adds sub object to jetArr if there is space in the array
            and the object is not null
    ************************************/
    public void addShip(JetClass inJet)
    {
        if( ( inJet != null ) && ( jetNum < MAXSHIPS ) )
        {
            jetArr[jetNum] = inJet;
            jetNum++;
        }
        else if( inJet == null )
        {
            throw new IllegalArgumentException("Invalid JetClass Object");
        }
        else if( jetNum >= MAXSHIPS )
        {
            throw new IllegalArgumentException("Number of Fighter Jets has "
                + "reached the maximum capacity (" + MAXSHIPS + ")" );
        }
    }

    /************************************
        SUBMODULE: destinationCheck
        IMPORT: dist (int)
        EXPORT: outShip (String)
        ASSERTION: Loops through both arrays and uses clacTravel to find the 
            ship that travels dist in the shortest amount of time and
            outputs String of fastest ship
    ************************************/
    public String destinationCheck(int dist)
    {
        double fastSub = 0.0, fastJet = 0.0, fastShip = 0.0;
        String outSub = new String(""), outJet = new String(""), outShip;
        if( subArr[0] != null )
        {
            fastSub = subArr[0].calcTravel(dist);
            outSub = ( "Ship: " + subArr[0].getSerial() + " is the fastest at "
                + fastSub + " hours." );
            for( int i = 1; i < ( subNum - 1 ); i++ )
            {
                if( subArr[i].calcTravel(dist) < fastSub )
                {
                    fastSub = subArr[i].calcTravel(dist);
                    outSub = ( "Ship: " + subArr[i].getSerial() +
                        " is the fastest at " + fastSub + " hours." );
                }
            }
        }

        if( jetArr[0] != null )
        {
            fastJet = jetArr[0].calcTravel(dist);
            outJet = ( "Ship: " + jetArr[0].getSerial() + " is the fastest at "
                + fastJet + " hours." );
            for( int i = 1; i < ( jetNum - 1 ); i++ )
            {
                if( jetArr[i].calcTravel(dist) < fastJet )
                {
                    fastJet = jetArr[i].calcTravel(dist);
                    outJet = ( "Ship: " + jetArr[i].getSerial() +
                        " is the fastest at " + fastJet + " hours." );
                }
            }
        }

        if( fastSub < fastJet )
        {
            outShip = outSub;
        }
        else
        {
            outShip = outJet;
        }
    
        //Stub
        return "Destination Checking not yet implemented";
    }

    /************************************
        SUBMODULE: findDuplicates
        IMPORT: none
        EXPORT: dupeArr (String[])
        ASSERTION: Finds duplicate ships and stores their .toString's in an
            array
    ************************************/
    public String[] findDuplicates()
    {
        int count = 0;
        String[] dupeArr = new String[ MAXSHIPS * 2 ];

        for( int i = 0; i < subNum; i++)
        {
            for( int j = i + 1; i < subNum; i++) 
            { 
                if( (i != j) && (subArr[i].equals(subArr[j])) ) 
                { 
                    dupeArr[count] = subArr[i].toString();
                    count++;
                }
            }
        }

        for( int i = 0; i < jetNum; i++)
        {
            for( int j = 0; i < jetNum; i++)
            {
                if( (i != j) && (jetArr[i].equals(jetArr[j])) )
                {
                    dupeArr[count] = jetArr[i].toString();
                    count++;
                }
            }
        }
        return dupeArr;
    }

    //GETTERS
    /************************************
        SUBMODULE: getSub
        IMPORT: index (int)
        EXPORT: copy of SubClass object at index in array
    ************************************/
    public SubClass getSub(int index)
    {
        SubClass outSub;
        if (subArr[index] != null)
        {
            outSub = new SubClass(subArr[index]);
        }
        else
        {
            throw new IllegalArgumentException("No sub at index " + index);
        }
        return outSub;
    }

     /************************************
        SUBMODULE: getJet
        IMPORT: index (int)
        EXPORT: copy of JetClass object at index in array
    ************************************/
    public JetClass getJet(int index)
    {
        JetClass outJet;
        if (jetArr[index] != null)
        {
            outJet = new JetClass(jetArr[index]);
        }
        else
        {
            throw new IllegalArgumentException("No jet at index " + index);
        }
        return outJet;
    }

    /************************************
        SUBMODULE: getSubNum
        IMPORT: none
        EXPORT: subNum (int)
    ************************************/
    public int getSubNum()
    {
        return subNum;
    }

    /************************************
        SUBMODULE: getJetNum
        IMPORT: none
        EXPORT: jetNum (int)
    ************************************/
    public int getJetNum()
    {
        return jetNum;
    } 

    /************************************
        SUBMODULE: getTotal
        IMPORT: none
        EXPORT: Sum of jetNum and subNum
    ************************************/
    public int getTotal()
    {
        return ( subNum + jetNum );
    } 
 
    // MUTATORS
    /************************************
        SUBMODULE: setSub
        IMPORT: inObj (Object), index (int)
        EXPORT: none
        ASSERTION: Sets the SubClass object at the specified index unless the
            index is not initialised
    ************************************/
    public void setSub(Object inObj, int index)
    {
        if( inObj instanceof SubClass )
        {
            SubClass inSub = (SubClass)inObj;
            if( index < subNum )
            {
                subArr[index] = inSub;
            }
            else
            {
                throw new IllegalArgumentException(
                    "SubClass Object at index not initialised");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid SubClass Object");
        }
    }

    /************************************
        SUBMODULE: setJet
        IMPORT: inObj (Object), index (int)
        EXPORT: none
        ASSERTION: Sets the JetClass object at the specified index unless the
            index is not initialised
    ************************************/
    public void setJet(Object inObj, int index)
    {
        if( inObj instanceof JetClass )
        {
            JetClass inJet = (JetClass)inObj;
            if( index < jetNum )
            {
                jetArr[index] = inJet;
            }
            else
            {
                throw new IllegalArgumentException(
                    "JetClass Object at index not initialised");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid JetClass Object");
        }
    }

    // EQUALS
    /************************************
        SUBMODULE: equals
        IMPORT: arrOne (Object[]), arrTwo (Object[])
        EXPORT: equal (boolean)
        ASSERTION: Returns true if two arrays are equal
    ************************************/
    public boolean equals(Object[] arrOne, Object[] arrTwo)
    {
        boolean equal = true;
        if( arrOne.length != arrTwo.length )
        {
            equal = false;
        }
        else
        {
            int count = 0;
            do
            {
                equal = ( arrOne[count].equals(arrTwo[count]) );
                count++;
            }
            while( equal && ( count < arrOne.length ) );
        }
        return equal;
    }
}
