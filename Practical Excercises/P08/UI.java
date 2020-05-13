/*******************************
    Author: Alec Maughan
    Purpose: All user interface for ShipManager, user input and output
    Date: 9/5/19
*******************************/
import java.util.*;
public class UI
{
    //Class Constants
    public static final String UIDOT = new String("[-] "); //For messages
    public static final String UITAB = new String(" |  "); //Continuing stuff
    public static final String UIERR = new String("[!] "); //For errors
    public static final String UIINP = new String("[>] "); //For user input
    // Used at the start of new lines to differenciate between different 
    // messages, errors and prompts for user input
    public static final String UIM = new String(
        "\n[M]  -  -  -  -  -  -  -  -  -  - [ Menu ]" +
        " -  -  -  -  -  -  -  -  -  -  [M]\n" + UITAB + "\n");
    public static final String UIL = new String(
        "\n[L]  -  -  -  -  -  -  -  -  - [ Load Ships ]" +
        " -  -  -  -  -  -  -  -  -  [L]\n" + UITAB + "\n");
    public static final String UIS = new String(
        "\n[S]  -  -  -  -  -  -  -  -  - [ Save Ships ]" +
        " -  -  -  -  -  -  -  -  -  [S]\n" + UITAB + "\n");
    public static final String UIN = new String(
        "\n[N]  -  -  -  -  -  -  -  -  -  [ New Ship ]" +
        "  -  -  -  -  -  -  -  -  -  [N]\n" + UITAB + "\n");
    public static final String UIV = new String(
        "\n[V]  -  -  -  -  -  -  -  -  - [ View Ships ]" +
        " -  -  -  -  -  -  -  -  -  [V]\n" + UITAB + "\n");
    public static final String UID = new String(
        "\n[D]  -  -  -  -  -  -  - [ Destination Calculator ]" +
        " -  -  -  -  -  -  -  [D]\n" + UITAB + "\n");
    public static final String UIF = new String(
        "\n[F]  -  -  -  -  -  -  -  -  - [ Duplicates ]" +
        " -  -  -  -  -  -  -  -  -  [F]\n" + UITAB + "\n");
    public static final String UIE = new String(
        "[E]  -  -  -  -  -  -  -  -  - [ Goodbye :) ]" +
        " -  -  -  -  -  -  -  -  -  [E]\n");
    // Used at the start of different utilities

    //Class fields
    private ShipStorage Ships;

    /***************************
        Default Constructor
        IMPORT: none
        EXPORT: memory address of new UI object
        ASSERTION: Initialises Ships Object
    ***************************/
    public UI()
    {
        Ships = new ShipStorage();
    }

     /***************************
        SUBMODULE: mainMenu
        IMPORT: none
        EXPORT: none
        ASSERTION: Outputs the main menu and handles user input and method
            calling
    ***************************/
    public void mainMenu()
    {
        boolean exit = false;
        char menu;
        displayTitle();
        while(!exit)
        {
            displayMenu();
/*            System.out.print( UIM +
                UIDOT + "Main Menu:\n" + UITAB + "\n" +
                UITAB + "L - Load Ships from a file\n" +
                UITAB + "S - Save Ships to a file\n" +
                UITAB + "N - New Ship\n" +
                UITAB + "V - View all Ships\n" +
                UITAB + "D - Destination Calculator\n" +
                UITAB + "F - Duplicate Finder\n" +
                UITAB + "E - Exit\n" +
                UITAB + "\n" ); */
            menu = inputMenu();
            switch(menu)
            {
                case 'L':
                    //Load ships from file
                    System.out.print( UIL );
                    loadShips();
                    break;
                case 'S':
                    //Save ships to file
                    System.out.print( UIS );
                    saveShips();
                    break;
                case 'N':
                    // Create a new ship from user input
                    createShip();
                    break;
                case 'V':
                    // Diplay all stored ships
                    System.out.print( UIV + viewShips() );
                    break;
                case 'D':
                    // Destination calculation stub
                    System.out.print( UID );
                    System.out.print( destination() + "\n" );
                    break;
                case 'F':
                    // Display duplicate ships
                    System.out.print( UIF + duplicatesString() + "\n" );
                    break;
                case 'E':
                    // Exit program
                    System.out.print( UIE + "\n" );
                    exit = true;
                    /*exit = inputBoolean(
                        ( UIDOT + "Are you sure you want to exit? (y/n)" ),
                        'y', 'n');*/
                    break;
            } //End switch
        } //End while
    }
        
    /***************************
        SUBMODULE: inputMenu
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Gets a user input char either l,s,n,v,d,f,e upper/lower case
    ***************************/
    public char inputMenu()
    {
        char menu = '0';
        boolean loop = false;
        String message = new String( UIDOT + "Please select an option" );
        String inString = new String("");
        Scanner sc = new Scanner(System.in);
        String error = new String("");
        do
        {
            try
            {
                System.out.print( error + message + "\n" + UIINP );
                // Error message is empty string whenever no error is set
                inString = sc.nextLine();
                switch(inString.toUpperCase().charAt(0))
                {
                    case 'L': case 'S': case 'N': case 'V': case 'D':
                        case 'F': case 'E':
                        menu = inString.toUpperCase().charAt(0);
                            // Sets output char to first letter in String
                            // if letter is valid
                        error = ("");
                        break;
                    default:
                        error = ( UIERR + "Error: Menu selection must be " +
                            "a valid menu option\n" );
                            // Sets the error message when something goes
                            // wrong
                        break;
                }
            }
            catch(StringIndexOutOfBoundsException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Menu selection cannot be empty\n" );
            }   
        } //End dowhile
        while(!error.equals(""));
            // Loops if an error message has been set
        return menu;
    }

    /***************************
        SUBMODULE: createShip
        IMPORT: none
        EXPORT: none
        ASSERTION: creates new sub or jet from user input and adds to
            respective array in Ships
    ***************************/
    public void createShip()
    {
        boolean loop = false, sub = true;
        String serial, fuel, hull, ordnance;
        int year, cyl;
        double depth, wing;

        System.out.print( UIN );
        do{
            System.out.print( UIDOT + "New Ship\n" );
            sub = inputBoolean(( UIDOT + "Enter ship type" ), 's', 'f');
            serial = new String(inputSerial());
            year = inputYear();
            cyl = inputCyl();
            fuel = new String(inputFuel());
            EngineClass inEngine = new EngineClass(cyl,fuel);
            if(sub) //Runs if ship is a Submarine
            {
                hull = new String(inputHull());
                depth = inputDepth();
                SubClass inputSub = new SubClass(serial,year,hull,depth);
                Ships.addShip(inputSub);
            }
            else //Runs if ship is a fighter jet
            {
                wing = inputWing();
                ordnance = new String(inputOrdnance());
                JetClass inputJet = new JetClass(serial,year,wing,ordnance);
                Ships.addShip(inputJet);
            }
            System.out.print( UITAB + "Ship added sucessfully\n" );
            loop = inputBoolean(( UIDOT + "Add another ship? (y/n)"),
                'y', 'n' );
            //Loop condition for whether or not the user wants to add another
            //ship
        }
        while(loop);
    }

     /***************************
        SUBMODULE: inputBoolean
        IMPORT: message (String), trueChar (char), falseChar (char)
        EXPORT: out (boolean)
        ASSERTION: Gets user input of a character for boolean output
            based on imported message String and two imported valid chars
    ***************************/
    public boolean inputBoolean(String message, char trueChar, char falseChar)
    {
        Scanner sc = new Scanner(System.in);
        String error = new String(""), inString;
        char inChar;
        boolean out = false; //Output
        char trueCharUp = (Character.toUpperCase(trueChar));
        char falseCharUp = (Character.toUpperCase(trueChar));
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            // Input validation system explained in inputMenu()
            try
            {
                inString = new String(sc.nextLine());
                inChar = inString.charAt(0);
                if( (inChar == trueChar) || (inChar == trueCharUp) )
                { // User inputs the 'true' char
                    out = true;
                    error = ("");
                }
                else if( (inChar == falseChar) || (inChar == falseCharUp) )
                { // User inputs the 'false' char
                    out = false;
                    error = ("");
                }
                else
                { // User inputs other char
                    error = ( UIERR + "Error: Input must be " + trueCharUp +
                        " or " + falseCharUp + "\n" );
                }
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Input must be " + trueChar +
                    " or " + falseChar + "\n" );
            }
            catch(StringIndexOutOfBoundsException e)
            { // User inputs nothing
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Input cannot be empty\n" );
            }
        }
        while(!error.equals(""));
        return out;
    }

    /***************************
        SUBMODULE: inputSerial
        IMPORT: none
        EXPORT: serial (String)
        ASSERTION: Gets a user input serial number for a ship
    ***************************/
    public String inputSerial()
    {
        Scanner sc = new Scanner(System.in);
        String error = new String("");
        String message = new String( UIDOT + "Enter ship serial number" );
        String serial = new String(); //Input/output serial
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            serial = new String(sc.nextLine());
            if(validateSerial(serial))
            {
                error = ("");
            }
            else
            {
                error = ( UIERR + "Error: Serial number must start with 3 " +
                    "digits between 001\n" + UITAB + "and 300 inclusive" +
                    " and end in any three digits (###.###)\n" );
            }
        }
        while(!error.equals(""));
        return serial;
    }

     /***************************
        SUBMODULE: inputYear
        IMPORT: none
        EXPORT: year (int)
        ASSERTION: Gets a user input manufacturing year integer for a ship
    ***************************/
    public int inputYear()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter ship manufacturing year" );
        String error = new String("");
        int year = 0; //Input/output year
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            try
            {
                year = sc.nextInt();
                if(validateYear(year))
                {
                    error = ("");
                }
                else
                {
                    error = ( UIERR + "Error: Year must be between 1950" +
                        " and 2022 inclusive\n" );
                }
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Year must be an Integer\n" );
            }
        }
        while(!error.equals(""));
        return year;
    }

    /***************************
        SUBMODULE: inputFuel
        IMPORT: none
        EXPORT: fuel (String)
        ASSERTION: Gets a user input fuel type for a ship's engine
    ***************************/
    public String inputFuel()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter the fuel type" );
        String error = new String("");
        String fuel; // Input/output fuel
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            fuel = new String(sc.nextLine());
            if(validateFuel(fuel))
            {
                error = ("");
            }
            else
            {
                error = ( UIERR + "Error: Fuel must be Battery, Diesel" +
                    " or Bio\n" );
            }
        }
        while(!error.equals(""));
        return fuel;
    }

     /***************************
        SUBMODULE: inputCyl
        IMPORT: none
        EXPORT: cyl (int)
        ASSERTION: Gets a user input number of cylinders for a ship's engine
    ***************************/
    public int inputCyl()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter the number of cylinders" );
        String error = new String("");
        int cyl = 5; //Input/output cyl;
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            try
            {
                cyl = sc.nextInt();
                if(validateCyl(cyl))
                {
                    error = ("");
                }
                else
                {
                    error = ( UIERR + "Error: Number of cylinders must be" +
                        " between 2 and 20 inclusive\n" );
                }
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Number of Cylinders must be an "
                    + "integer\n" );
            }
        }
        while(!error.equals(""));
        return cyl;
    }

    /***************************
        SUBMODULE: inputDepth
        IMPORT: none
        EXPORT: depth (double)
        ASSERTION: Gets a user input max depth of a submarine
    ***************************/
    public double inputDepth()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter submarine max depth" );
        String error = new String("");
        double depth = 0; //Input/output depth
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            try
            {
                depth = sc.nextDouble();
                if(validateDepth(depth))
                {
                    error = ("");
                }
                else
                {
                    error = ( UIERR + "Error: Max depth must be between -500.0"
                        + " and 0.0 inclusive\n" );
                }
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Max depth must be a real number\n" );
            }
        }
        while(!error.equals(""));
        return depth;
    }

     /***************************
        SUBMODULE: inputHull
        IMPORT: none
        EXPORT: hull (String)
        ASSERTION: Gets a user input hull material for a submarine
    ***************************/
    public String inputHull()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter submarine hull material" );
        String error = new String("");
        String hull; //Input/output hull
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            hull = new String(sc.nextLine());
            if(validateHull(hull))
            {
                error = ("");
            }
            else
            {
                error = ( UIERR + "Error: Hull must be Steel, Alloy or " +
                    "Titanium, casing does not matter\n" );
            }
        }
        while(!error.equals(""));
        return hull;
    }

    /***************************
        SUBMODULE: inputOrdnance
        IMPORT: none
        EXPORT: ordnance (String)
        ASSERTION: Gets a user input ordnance for a fighter jet
    ***************************/
    public String inputOrdnance()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter Fighter Jet ordnance" );
        String error = new String("");
        String ordnance; //Input/output ordnance
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            ordnance = new String(sc.nextLine());
            if(validateOrdnance(ordnance))
            {
                error = ("");
            }
            else
            {
                error = ( UIERR + "Error: Ordnance cannot be empty\n" );
            }
            if(ordnance.contains(","))
            {
                error = ( UIERR + "Error: You can't have commas in the "
                    + "ordnance becuase they\n" + UITAB + "are beyond the "
                    + "processing capabilities of modern computing\n" );
                    // File reading splits the file at ","
            }
        }
        while(!error.equals(""));
        return ordnance;
    }

     /***************************
        SUBMODULE: inputWing
        IMPORT: none
        EXPORT: wing (double)
        ASSERTION: Gets a user input wingspan for a fighter jet
    ***************************/
    public double inputWing()
    {
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter Fighter Jet wing span" );
        String error = new String("");
        double wing = 0; //Input/output wing
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            try
            {
                wing = sc.nextDouble();
                if(validateWing(wing))
                {
                    error = ("");
                }
                else
                {
                    error = ( UIERR + "Error: Wing span must be between " +
                        "2.20 and 25.6 inclusive\n" );
                }
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Wing span must be a real number\n" );
            }
        }
        while(!error.equals(""));
        return wing;
    }

//Private Validation Submodules
    /**********************************************
        SUBMODULE: validateSerial
        IMPORT: inSerial
        EXPORT: valid (boolean)
        ASSERTION: xxx.yyy, xxx (100-300 inc.), yyy (001-999 inc.)
    ************************************************/
    private boolean validateSerial(String inSerial)
    {
        boolean valid = false;
        try{
        String[] serialParts = inSerial.split("\\.");
        valid = ( (Integer.parseInt(serialParts[0]) >= 100)
            && (Integer.parseInt(serialParts[0]) <= 300)
            && (Integer.parseInt(serialParts[1]) >= 001)
            && (Integer.parseInt(serialParts[1]) <= 999) );
        }
        catch(NumberFormatException e) {}
        return valid;
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
        SUBMODULE: validateFuel
        IMPORT: inFuel (String)
        EXPORT: valid (boolean)
        ASSERTION: String either "BIO" or "BATTERY" or "DIESEL"
    ************************************************/
    private boolean validateFuel(String inFuel)
    {
        String upFuel = new String(inFuel.toUpperCase());
        return ( upFuel.equals( "BIO" ) || upFuel.equals( "BATTERY" ) || 
            upFuel.equals( "DIESEL" ) );
    }

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
        SUBMODULE: validateDepth
        IMPORT: inDepth
        EXPORT: valid (boolean)
        ASSERTION: real num (-550 to 0.0 inclusive)
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
        return ( upHull.equals( "STEEL" ) || upHull.equals( "ALLOY" ) ||
            upHull.equals( "TITANIUM" ) );
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

     /**********************************************
        SUBMODULE: loadShips
        IMPORT: none
        EXPORT: none
        ASSERTION: Gets a file name and uses file.load to load ships into 
            storage from a file
    ************************************************/
    public void loadShips()
    {
        File file = new File();
        Scanner sc = new Scanner(System.in);
        String message = new String( UIDOT + "Enter file name to load ships"
            + " from");
        String error = new String("");
        String fileName;
        int initialTotal = Ships.getTotal(); // Initial number of ships
        int finalTotal;
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            fileName = new String( sc.nextLine() );
            if( !fileName.equals("") )
            {
                error = ("");
            }
            else
            {
                error = ( UIERR + "Error: File name cannot be emtpy\n" );
            }
        }
        while( !error.equals("") );

        try
        {
            file.load( fileName, Ships );
                //Imports filename and ShipStorage obj
            finalTotal = ( Ships.getTotal() - initialTotal );
            System.out.print( UIDOT + "Loaded " + finalTotal +
                " ships successfully\n");
        }
        catch( IllegalArgumentException e )
        {
            System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                // All errors inside file.load are thrown as 
                // IllegalArgumentException with readable and helpful
                // error messages
        }
        catch( ArrayIndexOutOfBoundsException e2 )
        {
            System.out.println( UIERR +
                "Error: File does not contain valid data\n" );   
        }
    }

    /**********************************************
        SUBMODULE: saveShips
        IMPORT: none
        EXPORT: none
        ASSERTION: Gets a file name and uses file.save() to save all ships
            in storage to file
    ************************************************/
    public void saveShips()
    {
        Scanner sc = new Scanner(System.in);
        File file = new File(); //Creates File Object
        String message = new String( UIDOT + "Enter file name to save ships"
            + " to");
        String error = new String("");
        String fileName;
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            fileName = new String( sc.nextLine() );
            if( !fileName.equals("") )
            {
                error = ("");
            }
            else
            {
                error = ( UIERR + "Error: File name cannot be empty\n" );
            }
        }
        while( !error.equals("") );

        try
        {
            file.save( fileName, Ships );
            System.out.print( UIDOT + "Saved " + Ships.getTotal() + 
                " ships to " + fileName + "\n" );
        }
        catch( IllegalArgumentException e )
        {
            System.out.print( UIERR + "Error: " + e.getMessage() + "\n" );
                // All errors inside file.save are thrown as 
                // IllegalArgumentException with readable and helpful
                // error messages
        }
    }



    /************************************
        SUBMODULE: viewShips
        IMPORT: none
        EXPORT: ships (String)
        ASSERTION: Puts all ship's .toString's in a single String
    ************************************/
    public String viewShips()
    {
        String ships = new String( UIDOT + "Ships in Storage:\n" + UITAB +
            "\n" );

        for( int i = 0; i < Ships.getSubNum(); i++ )
        {   // Submarines are printed first
            ships = ( ships + UIDOT + Ships.getSub(i).toString() + 
                "\n" + UITAB + "\n" );
        }
        for( int i = 0; i < Ships.getJetNum(); i++ )
        {   // Fighter jets then printed
            ships = ( ships + UIDOT + Ships.getJet(i).toString() +
                "\n" + UITAB + "\n" );
        }
        if( ships.equals( UIDOT + "Ships in Storage:\n" + UITAB + "\n" ) )
        {   // If export string is unchanged, there are no ships stored
            ships = (UIDOT + "No ships in storage. You have " +
                ( Ships.MAXSHIPS - Ships.getTotal() )
                + " ship slots left\n" );
        }
        else
        {
            ships = ( ships + UIDOT + "You have " + 
                ( Ships.MAXSHIPS - Ships.getTotal() )
                + " ship slots left. (" + (Ships.MAXSHIPS - Ships.getSubNum())
                + " Submarines and " + (Ships.MAXSHIPS - Ships.getJetNum())
                + " Fighter Jets)\n" );
                // Shows how many slots in the ShipStorage arrays are
                // remaining
        }
        return ships;
    }

    /**********************************************
        SUBMODULE: duplicatesString
        IMPORT: none
        EXPORT: dupes (String)
        ASSERTION: Makes a long String of all ship duplicates using the
            findDuplicates method in ShipStorage
    ************************************************/
    public String duplicatesString()
    {
        String dupes = new String("");
        String[] dupeArr = Ships.findDuplicates();
        int dupeNum = 0;
        if( ( dupeArr[0] != null ) &&
            ( Ships.getTotal() > 0 ) )
        {
            dupes = ( UIDOT + "Checked " + Ships.getTotal() +
                " ships. Duplicates:" );

            for( int i = 0; i < (dupeArr.length - 1); i++ )
            {
                if( dupeArr[i] != null )
                {
                    dupes = ( dupes + "\n" + UITAB + "\n" + UIDOT +
                        dupeArr[i]  );
                    dupeNum++;
                }
            }
            dupes = ( dupes + "\n" + UITAB + "\n" + UIDOT + "Total: " +
                dupeNum );
        }
        else if( ( Ships.getSubNum() == 0 ) && ( Ships.getJetNum() == 0 ) )
        { // If there are no ships stored
            dupes = ( UIDOT + "No ships in storage" );
        }
        else
        {   // If there are ships stored but no duplicates
            dupes = ( UIDOT + "Checked " + Ships.getTotal() +
                " ships. No duplicates found" );
        }
        return dupes;
    }

    /**********************************************
        SUBMODULE: destination
        IMPORT: none
        EXPORT: fastest (String)
        ASSERTION: Exports the ship that takes the shortest amount of time
            to travel a distance using the destinationCheck method in 
            ShipStorage
    ************************************************/
    public String destination()
    {
        Scanner sc = new Scanner(System.in);
        String fastest;
        String message = new String( UIDOT + "Enter distance to travel" );
        String error = new String("");
        int distance = 0;
        do
        {
            System.out.print( error + message + "\n" + UIINP );
            try
            {
                distance = sc.nextInt();
                if(distance <= 0)
                {
                    error = ( UIERR + "Error: Distance must be greater "
                        + "than 0\n");
                }
                else
                {
                    error = ("");
                }
            }
            catch(InputMismatchException e)
            {
                sc.nextLine(); //Clears input buffer
                error = ( UIERR + "Error: Input must be an Integer\n" );
            }
        }
        while(!error.equals(""));
        
        fastest = new String( UIDOT + Ships.destinationCheck(distance) );
        return fastest;
    }

    /**********************************************
        SUBMODULE: diplayMenu
        IMPORT: none
        EXPORT: none
        ASSERTION: Prints the main menu
    ************************************************/
    public void displayMenu()
    {
        System.out.print( UIM +
            " |  [ L - Load Ships from a file ]        [ S - Save ships to a file ]\n" +
            " |         [ N - New Ship ]                  [ V - View all ships ]\n" +
            " |  [ D - Destination Calculator ]          [ F - Duplicate Finder ]\n" +
            " |           [ E - Exit ]\n" + UITAB + "\n" );
    }

    /**********************************************
        SUBMODULE: Displaytitle
        IMPORT: none
        EXPORT: none
        ASSERTION: Prints the title, version number is randomly generated lol
    ************************************************/
    public void displayTitle()
    {
        String version = new String( "v" + ((int)(Math.random() * 10)) + "." +
            ((int)(Math.random() * 10)) +
            ((int)(Math.random() * 10))); // "v#.##"
        System.out.print(
            "[ ]-----------------------------------------" +
            "-----------------------------[ ]\n" +
            " |      _____ _     _        _  _           " +
            "                              |\n" +
            " |     / ____| |__ |_|_ __  / \\/ \\  __ _ _" +
            " __   __ _  __ _  ___ _ __      |\n" +
            " |     \\___ \\|  _ \\| |  _ \\| |\\/| |/ _ " +
            " |  _ \\ / _  |/ _  |/ _ \\  __|     |\n" +
            " |      ___) | | | | | |_| | |  | | |_| | | " +
            "| | (_| | |_| |  __/ |        |\n" +
            " |     |____/|_| |_|_|  __/|_|  |_|\\__,_|_|" +
            " |_|\\__,_|\\__  |\\___|_|        |\n" +
            " |                   | |                    " +
            "          _/  |               |\n" +
            " |                   |_|                    " +
            "         |___/          " + version + " |\n" +
            "[ ]-----------------------------------------" +
            "-----------------------------[ ]\n"
            );
    }
}
