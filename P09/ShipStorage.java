/*******************************
    Author: Alec Maughan
    Purpose: Ship storage system, stores ships in seperate arrays
    Date: 9/5/19
*******************************/
import java.util.*;
public class ShipStorage
{
    //Class constants
    public static final int MAXSHIPS = 15;
    //ANSI Colours:
    public static final String CYLW = new String( "\u001B[33m" ); //Yellow
    public static final String CCYN = new String( "\u001B[36m" ); //Cyan
    public static final String CRED = new String( "\u001B[31m" ); //Red
    public static final String CC = new String( "\u001B[0m" ); //Colour reset

    //New line 'starters'. For differenciating between userinput, errors, etc.
    public static final String UIDOT = new String( "[-] " ); //Messages
    public static final String UITAB = new String( " |  " ); //Continuing
    public static final String UIERR = new String( CRED + "[!] " + CC );//Error
    public static final String UIINP = new String( CYLW + "[>] " + CC );//Input

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
        subArr = new SubClass[ MAXSHIPS ];
        jetArr = new JetClass[ MAXSHIPS ];
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
    public String destinationCheck( int dist )
    {
        double fastSub = 0.0, fastJet = 0.0; //Hours taken for each ship type
        String outSub = new String( "" ), outJet = new String( "" );
        String outShip = new String( "" ); //Output string
        if( subArr[0] != null )
        {
            fastSub = (
                Math.round( subArr[0].calcTravel(dist) * 100.0 ) / 100.0 );
                //Sets the 1st submarine to the fastest and round to 2dp
            outSub = ( "The submarine " + subArr[0].getSerial() +
                " is the fastest at " + fastSub + " hours." );
            for( int i = 1; i < ( subNum - 1 ); i++ )
            {
                if( subArr[i].calcTravel(dist) < fastSub )
                {
                    fastSub = ( Math.round(
                        subArr[i].calcTravel(dist) * 100.0 ) / 100.0 );
                        //Sets the new fastest sub and rounds to 2dp
                    outSub = ( "The submarine " + subArr[i].getSerial() +
                        " is the fastest at " + fastSub + " hours." );
                }
            }
        }

        if( jetArr[0] != null )
        {
            fastJet = ( Math.round(
                jetArr[0].calcTravel(dist) * 100.0 ) / 100.0 );
            outJet = ( "The fighter jet " + jetArr[0].getSerial() +
                " is the fastest at " + fastJet + " hours." );
            for( int i = 1; i < ( jetNum - 1 ); i++ )
            {
                if( jetArr[i].calcTravel(dist) < fastJet )
                {
                    fastJet = ( Math.round(
                        jetArr[i].calcTravel(dist) * 100.0 ) / 100.0 );
                    outJet = ( "The fighter jet " + jetArr[i].getSerial() +
                        " is the fastest at " + fastJet + " hours." );
                }
            }
        }

        if( fastSub < fastJet )
        {
            outShip = outSub; //If the submarine is faster
        }
        else if( fastSub > fastJet )
        {
            outShip = outJet; //If the jet is faster
        }
        else if( outSub.equals( "" ) && outJet.equals( "" ) )
        {
            outShip = ( "No ships in storage" ); //If no ships were tested
        }
        else if( Math.abs( fastSub - fastJet ) < 0.001 )
        {
            if( Math.random() > 0.5 ) // If ships are equal, let the computer
            {                         // decide
                outShip = outSub;
            }
            else
            {
                outShip = outJet;
            }
        }
    
        return outShip;
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
        int count = 0; //Count for index of dupeArr
        String[] dupeArr = new String[ MAXSHIPS * 2 ];

        for( int i = 0; i < subNum; i++)
        {
            for( int j = i + 1; i < subNum; i++)
            {
                if( ( i != j ) && ( subArr[i].equals(subArr[j]) ) )
                {
                    dupeArr[count] = subArr[i].toString();
                    count++;
                }
            }
        }

        for( int i = 0; i < jetNum; i++)
        {
            for( int j = i + 1; i < jetNum; i++)
            {
                if( ( i != j ) && ( jetArr[i].equals(jetArr[j]) ) )
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

// Ship input submodules
    /************************************
        SUBMODULE: inputType
        IMPORT: sc (Scanner) or string (String)
        EXPORT: type (char)
        ASSERTION: Gets a ship type character based on either
            looping user input or string import
    ************************************/
    //Input from scanner
    public char inputType( Scanner sc )
    {
        sc = new Scanner( System.in );
        char type = '0';
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter ship type\n" + UIINP );
            try
            {
                type = sc.nextLine().toUpperCase().charAt(0);
                if( ( type == 'S' ) || ( type == 'F' ) || ( type == 'J' ) )
                {
                    fail = false;
                }
                else
                {
                    System.out.print( UIERR +
                        "Error: Input must be Submarine or Fighter Jet\n" );
                    fail = true;
                }
            }
            catch( IndexOutOfBoundsException e )
            {
                sc = new Scanner( System.in );
                System.out.print( UIERR + "Error: Input cannot be empty\n" );
                fail = true;
            }
        }
        while( fail );
        return type;
    }
    //Input from String
    public char inputType( String line )
    {
        char type = '0';
        try
        {
            type = line.toUpperCase().charAt(0);
            if( ( type != 'S' ) && ( type != 'F' ) && ( type != 'J' ) )
            {
                throw new IllegalArgumentException( "Ship type = \"" +
                    line + "\"\n" + UITAB +
                    "Ship type must be Submarine or Fighter Jet" );
            }
        }
        catch( IndexOutOfBoundsException e )
        {
            throw new IllegalArgumentException( "Ship type = \"\"\n" +
                UITAB + "Error: Input cannot be empty" );
        }
        return type;
    }

    /************************************
        SUBMODULE: inputSerial
        IMPORT: inSub (SubClass) or inJet (JetClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets serial number of imported ship based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputSerial( SubClass inSub, Scanner sc )
    {
        sc = new Scanner( System.in );
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter serial number\n" + UIINP );
            try
            {
                inSub.setSerial( sc.nextLine() );
                fail = false;
            }
            catch( IllegalArgumentException e )
            {
                sc = new Scanner( System.in );
                System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    public void inputSerial( JetClass inJet, Scanner sc )
    {
        sc = new Scanner( System.in );
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter serial number\n" + UIINP );
            try
            {
                inJet.setSerial( sc.nextLine() );
                fail = false;
            }
            catch( IllegalArgumentException e )
            {
                sc = new Scanner( System.in );
                System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Inputfrom String
    public void inputSerial( SubClass inSub, String line )
    {
        try
        {
            inSub.setSerial( line );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalArgumentException( "Serial number = \"" +
                line + "\"\n" + UITAB + e.getMessage() );
        }
    }
    public void inputSerial( JetClass inJet, String line )
    {
        try
        {
            inJet.setSerial( line );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalArgumentException( "Serial number = \"" +
                line + "\"\n" + UITAB + e.getMessage() );
        }
    }

    /************************************
        SUBMODULE: inputYear
        IMPORT: inSub (SubClass) or inJet (JetClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets manufacturing year of imported ship based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputYear( SubClass inSub, Scanner sc )
    {
        sc = new Scanner( System.in );
        String input;
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter maufacturing year\n" + UIINP );
            try
            {
                input = new String( sc.nextLine() );
                if( input.equals("") )
                {
                    System.out.print( UIERR +
                        "Error: Manufacturing year cannot be empty\n" );
                    fail = true;
                }
                else
                {
                    inSub.setYear( Integer.parseInt( input ) );
                    fail = false;
                }
            }
            catch( NumberFormatException e )
            {
                sc = new Scanner( System.in );
                System.out.println( UIERR +
                    "Error: Manufacturing year must be an integer" );
                fail = true;
            }
            catch( IllegalArgumentException e2 )
            {
                System.out.print( UIERR + "Error: " + e2.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    public void inputYear( JetClass inJet, Scanner sc )
    {
        sc = new Scanner( System.in );
        String input;
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter manufacturing year\n" + UIINP );
            try
            {
                input = new String( sc.nextLine() );
                if( input.equals("") )
                {
                    System.out.print( UIERR +
                        "Error: Manufacturing year cannot be empty\n" );
                    fail = true;
                }
                else
                {
                    inJet.setYear( Integer.parseInt( input ) );
                    fail = false;
                }
            }
            catch( NumberFormatException e )
            {
                sc = new Scanner( System.in );
                System.out.println( UIERR +
                    "Error: Manufacturing year must be an integer" );
                fail = true;
            }
            catch( IllegalArgumentException e2 )
            {
                System.out.print( UIERR + "Error: " + e2.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Inpput from String
    public void inputYear( SubClass inSub, String line )
    {
        try
        {
            inSub.setYear( Integer.parseInt( line ) );
        }
        catch( NumberFormatException e )
        {
            throw new IllegalArgumentException( "Manufacturing Year = \"" +
                line + "\"\n" + UITAB +
                "Manufacturing year must be an integer" );
        }
        catch( IllegalArgumentException e2 )
        {
            throw new IllegalArgumentException( "Manufacturing Year = \"" +
                line + "\"\n" + UITAB + e2.getMessage() );
        }
    }
    public void inputYear( JetClass inJet, String line )
    {
        try
        {
            inJet.setYear( Integer.parseInt( line ) );
        }
        catch( NumberFormatException e )
        {
            throw new IllegalArgumentException( "Manufacturing year = \"" +
                line + "\"\n" + UITAB +
                "Manufacturing year must be an integer" );
        }
        catch( IllegalArgumentException e2 )
        {
            throw new IllegalArgumentException( "Manufacturing Year = \"" +
                line + "\"\n" + UITAB + e2.getMessage() );
        }
    }

    /************************************
        SUBMODULE: inputEng
        IMPORT: inSub (SubClass) or inJet (JetClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Creates and sets the Engine of imported ship based on
            either looping user input or string import of cylinders and fuel
    ************************************/
    //Input from Scanner
    public void inputEng( SubClass inSub, Scanner sc )
    {
        sc = new Scanner( System.in );
        EngineClass Eng = new EngineClass();
        inputCyl( Eng, sc );
        inputFuel( Eng, sc );
        inSub.setEng( Eng );
    }
    public void inputEng( JetClass inJet, Scanner sc )
    {
        sc = new Scanner( System.in );
        EngineClass Eng = new EngineClass();
        inputCyl( Eng, sc );
        inputFuel( Eng, sc );
        inJet.setEng( Eng );
    }
    //Input from String
    public void inputEng( SubClass inSub, String line, String line2 )
    {
        EngineClass Eng = new EngineClass();
        inputCyl( Eng, line );
        inputFuel( Eng, line2 );
        inSub.setEng( Eng );
    }
    public void inputEng( JetClass inJet, String line, String line2  )
    {
        EngineClass Eng = new EngineClass();
        inputCyl( Eng, line );
        inputFuel( Eng, line2 );
        inJet.setEng( Eng );
    }

    /************************************
        SUBMODULE: inputCyl
        IMPORT: inEng (EngineClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets number of cylinders of imported engine based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputCyl( EngineClass inEng, Scanner sc )
    {
        sc = new Scanner( System.in );
        String input;
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter number of cylinders\n" + UIINP );
            try
            {
                input = new String( sc.nextLine() );
                if( input.equals("") )
                {
                    System.out.print( UIERR +
                        "Error: Number of cylinders cannot be empty\n" );
                    fail = true;
                }
                else
                {
                    inEng.setCyl( Integer.parseInt( input ) );
                    fail = false;
                }
            }
            catch( NumberFormatException e )
            {
                System.out.print( UIERR +
                    "Error: Number of cylinders must be an integer\n" );
                fail = true;
            }
            catch( IllegalArgumentException e2 )
            {
                System.out.print( UIERR + "Error: " + e2.getMessage() +
                    "\n" );
                fail = true;
            }
       }
        while( fail );
    }
    //Input from String
    public void inputCyl( EngineClass inEng, String line )
    {
        try
        {
            inEng.setCyl( Integer.parseInt( line ) );
        }
        catch( NumberFormatException e )
        {
            throw new IllegalArgumentException( "Number of cylinders = \"" +
                line + "\"\n" + UITAB +
                "Number of cylinders must be an integer" );
        }
        catch( IllegalArgumentException e2 )
        {
            throw new IllegalArgumentException( "Number of cylinders = \"" +
                line + "\"\n" + UITAB + e2.getMessage() );
        }
    }

    /************************************
        SUBMODULE: inputFuel
        IMPORT: inEng (EngineClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets fuel type of imported engine based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputFuel( EngineClass inEng, Scanner sc )
    {
        sc = new Scanner( System.in );
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter fuel type\n" + UIINP );
            try
            {
                inEng.setFuel( sc.nextLine() );
                fail = false;
            }
            catch( IllegalArgumentException e )
            {
                System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Input from String
    public void inputFuel( EngineClass inEng, String line )
    {
        try
        {
            inEng.setFuel( line );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalArgumentException( "Fuel type = \"" + line +
                "\"\n" + UITAB + e.getMessage() );
        }
    }

    /************************************
        SUBMODULE: inputHull
        IMPORT: inSub (SubClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets hull material of imported submarine based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputHull( SubClass inSub, Scanner sc )
    {
        sc = new Scanner( System.in );
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter hull material\n" + UIINP );
            try
            {
                inSub.setHull( sc.nextLine() );
                fail = false;
            }
            catch( IllegalArgumentException e )
            {
                System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Input from String
    public void inputHull( SubClass inSub, String line )
    {
        try
        {
            inSub.setHull( line );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalArgumentException( "Hull material = \"" + line +
                "\"\n" + UITAB + e.getMessage() );
        }
    }

    /************************************
        SUBMODULE: inputDepth
        IMPORT: inSub (SubClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets max depth of imported submarine based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputDepth( SubClass inSub, Scanner sc )
    {
        sc = new Scanner( System.in );
        String input;
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter maximum depth\n" + UIINP );
            try
            {
                input = new String( sc.nextLine() );
                if( input.equals("") )
                {
                    System.out.print( UIERR +
                        "Error: Maximum depth cannot be empty\n" );
                    fail = true;
                }
                else
                {
                    inSub.setDepth( Double.parseDouble( input ) );
                    fail = false;
                }
            }
            catch( NumberFormatException e )
            {
                sc = new Scanner( System.in );
                System.out.print( UIERR +
                    "Error: Maximum depth must be a real number\n" );
                fail = true;
            }
            catch( IllegalArgumentException e2 )
            {
                System.out.print( UIERR + "Error: " + e2.getMessage() +
                    "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Input from String
    public void inputDepth( SubClass inSub, String line )
    {
        try
        {
            inSub.setDepth( Double.parseDouble( line ) );
        }
        catch( NumberFormatException e )
        {
            throw new IllegalArgumentException( "Maximum depth =\"" + line +
                "\"\n" + UITAB + "Maximum depth must be a real number" );
        }
        catch( IllegalArgumentException e2 )
        {
            throw new IllegalArgumentException( "Maximum depth = \"" + line +
                "\"\n" + UITAB + e2.getMessage() );

        }
   }

    /************************************
        SUBMODULE: inputWing
        IMPORT: inJet (JetClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets wingspan of imported fighter jet based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputWing( JetClass inJet, Scanner sc )
    {
        sc = new Scanner( System.in );
        String input;
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter wing span\n" + UIINP );
            try
            {
                input = new String( sc.nextLine() );
                if( input.equals("") )
                {
                    System.out.print( UIERR +
                        "Error: Wing span cannot be empty\n" );
                    fail = true;
                }
                else
                {
                    inJet.setWing( Double.parseDouble( input ) );
                    fail = false;
                }
            }
            catch( NumberFormatException e )
            {
                System.out.print( UIERR +
                    "Error: Wing span must be a real numbr\n" );
                fail = true;
            }
            catch( IllegalArgumentException e2 )
            {
                System.out.print( UIERR + "Error: " + e2.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Input from String
    public void inputWing( JetClass inJet, String line )
    {
        try
        {
            inJet.setWing( Double.parseDouble( line ) );
        }
        catch( NumberFormatException e )
        {
            throw new IllegalArgumentException( "Wing span = \"" + line +
                "\"\n" + UITAB + "Wing span must be a real number" );
        }
        catch( IllegalArgumentException e2 )
        {
            throw new IllegalArgumentException( "Wing span = \"" + line +
                "\"\n" + UITAB + e2.getMessage() );

        }
    }

    /************************************
        SUBMODULE: inputOrdnance
        IMPORT: inJet (JetClass),
            sc (Scanner) or string (String)
        EXPORT: none
        ASSERTION: Sets ordnance of imported fighter jet based on either
            looping user input or string import
    ************************************/
    //Input from Scanner
    public void inputOrdnance( JetClass inJet, Scanner sc )
    {
        sc = new Scanner( System.in );
        boolean fail = false;
        do
        {
            System.out.print( UIDOT + "Enter ordnance\n" + UIINP );
            try
            {
                inJet.setOrdnance( sc.nextLine() );
                fail = false;
            }
            catch( IllegalArgumentException e )
            {
                System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                fail = true;
            }
        }
        while( fail );
    }
    //Input from String
    public void inputOrdnance( JetClass inJet, String line )
    {
        try
        {
            inJet.setOrdnance( line );
        }
        catch( IllegalArgumentException e )
        {
            throw new IllegalArgumentException( "Ordnance = \"" + line +
                "\"\n" + UITAB + e.getMessage() );
        }
    }

 
 
}
