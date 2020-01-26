/*******************************
    Author: Alec Maughan
    Purpose: All user interface for ShipManager, user input and output
    Date: 9/5/19
*******************************/
import java.util.*;
public class UI
{
    //Class Constants
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

    //Utility 'headings' to differenciate between different utilities
    public static final String UIM = new String(
        "\n" + CCYN + "[M]" + CC + "  -  -  -  -  -  -  -  -  -  - " + CCYN +
        "[ Menu ]" + CC + " -  -  -  -  -  -  -  -  -  -  " + CCYN + "[M]" +
        CC + "\n" + UITAB + "\n");
    public static final String UIL = new String(
        "\n" + CCYN + "[L]" + CC + "  -  -  -  -  -  -  -  -  - " + CCYN +
        "[ Load Ships ]" + CC + " -  -  -  -  -  -  -  -  -  [L]\n" + UITAB +
        "\n");
    public static final String UIS = new String(
        "\n" + CCYN + "[S]" + CC + "  -  -  -  -  -  -  -  -  - " + CCYN +
        "[ Save Ships ]" + CC + " -  -  -  -  -  -  -  -  -  " + CCYN + "[S]"
        + CC + "\n" + UITAB + "\n");
    public static final String UIN = new String(
        "\n" + CCYN + "[N]" + CC + "  -  -  -  -  -  -  -  -  -  " + CCYN +
        "[ New Ship ]" + CC + "  -  -  -  -  -  -  -  -  -  " + CCYN + "[N]"
        + CC + "\n" + UITAB + "\n");
    public static final String UIV = new String(
        "\n" + CCYN + "[V]" + CC + "  -  -  -  -  -  -  -  -  - " + CCYN +
        "[ View Ships ]" + CC + " -  -  -  -  -  -  -  -  -  " + CCYN + "[V]"
        + CC + "\n" + UITAB + "\n");
    public static final String UID = new String(
        "\n" + CCYN + "[D]" + CC + "  -  -  -  -  -  -  - " + CCYN +
        "[ Destination Calculator ]" + CC + " -  -  -  -  -  -  -  " + CCYN +
        "[D]" + CC + "\n" + UITAB + "\n");
    public static final String UIF = new String(
        "\n" + CCYN + "[F]" + CC + "  -  -  -  -  -  -  -  -  - " + CCYN +
        "[ Duplicates ]" + CC + " -  -  -  -  -  -  -  -  -  " + CCYN + "[F]"
        + CC + "\n" + UITAB + "\n");

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
        char menu = '0', userMenu = '0', utilMenu = 'M';
            //userMenu for main menu input, utilMenu returns input from a util
        while( menu != 'E' )
        {
            if( ( utilMenu == 'M' ) && ( utilMenu != 'E' ) )
            {
                displayTitle();
                displayMenu();
                menu = inputMenu( "Please select an option:" );
            }
            else
            {
                menu = utilMenu;
            }
            if( menu != 'E' )
            {
                displayTitle();
            }
            switch(menu)
            {
                case 'L':
                    //Load ships from file
                    System.out.print( UIL );
                    utilMenu = loadShips();
                    break;
                case 'S':
                    //Save ships to file
                    System.out.print( UIS );
                    utilMenu = saveShips();
                    break;
                case 'N':
                    // Create a new ship from user input
                    System.out.print( UIN );
                    utilMenu = createShip();
                    break;
                case 'V':
                    // Diplay all stored ships
                    System.out.print( UIV );
                    utilMenu = viewShips();
                    break;
                case 'D':
                    // Destination calculation stub
                    System.out.print( UID );
                    utilMenu = destination();
                    break;
                case 'F':
                    // Display duplicate ships
                    System.out.print( UIF );
                    utilMenu = duplicates();
                    break;
                case 'E':
                    // Exit program
                    displayExit();
                    break;
            } //End switch
        } //End while
    }
        
    /***************************
        SUBMODULE: inputMenu
        IMPORT: message (String)
        EXPORT: menu (char)
        ASSERTION: Gets a user input char either l,s,n,v,d,f,e,m
            either upper or lower case
    ***************************/
    public char inputMenu( String message )
    {
        char menu = '0';
        String error = new String(""), inString = new String("");
        Scanner sc = new Scanner( System.in );
        do
        {
            System.out.print( error + UIDOT + message + "\n" + UIINP );
            // Error message is empty string whenever no error is set
            inString = sc.nextLine();
            if( !inString.equals("") )
            {
                switch(inString.toUpperCase().charAt(0))
                {
                    case 'L': case 'S': case 'N': case 'V': case 'D':
                        case 'F': case 'E': case 'M':
                        menu = inString.toUpperCase().charAt(0);
                            // Sets output char to first letter in String
                            // if letter is valid
                        error = ("");
                        break;
                    default:
                        error = ( UIERR + "Error: Invalid menu selection \"" +
                            inString + "\"\n" );
                            // Sets the error message when something goes
                            // wrong
                        break;
                }
            }
            else
            {
                sc = new Scanner( System.in ); //Clears input buffer
                error = ( UIERR + "Error: Menu selection cannot be empty\n" );
            }
        } //End dowhile
        while(!error.equals(""));
            // Loops if an error message has been set
        return menu;
    }

// Utitity submodules
    /***************************
        SUBMODULE: createShip
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: creates new sub or jet from user input and adds to
            respective array in Ships
    ***************************/
    public char createShip()
    {
        char type = '0'; //Ship type, 'S' for Submarine, 'J' or 'F' for Jet
        Scanner sc = new Scanner( System.in );
        char menu = '0';

        System.out.print( UIDOT + "New Ship\n" );
        type = Ships.inputType( sc );
        if( type == 'S' )
        {
            SubClass Sub = new SubClass();
            Ships.inputSerial( Sub, sc );
            Ships.inputYear( Sub, sc );
            Ships.inputEng( Sub, sc );
            Ships.inputHull( Sub, sc );
            Ships.inputDepth( Sub, sc );
            Ships.addShip( Sub );
        }
        else if( ( type == 'F' ) || ( type == 'J' ) )
        {
            JetClass Jet = new JetClass();
            Ships.inputSerial( Jet, sc );
            Ships.inputYear( Jet, sc );
            Ships.inputEng( Jet, sc );
            Ships.inputWing( Jet, sc );
            Ships.inputOrdnance( Jet, sc );
            Ships.addShip( Jet );
        }
        System.out.print( UIDOT + "Ship added sucessfully\n" + UITAB +
            "You have " + ( Ships.MAXSHIPS - Ships.getTotal() )
            + " ship slots left\n" + UITAB + "\n" );

        return inputMenu( "Please select:\n" + UITAB + CYLW + "N" + CC +
            "ew ship, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E"
            + CC + "xit program" );
    }

     /**********************************************
        SUBMODULE: loadShips
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Gets a file name and uses file.load to load ships into 
            storage from a file
    ************************************************/
    public char loadShips()
    {
        File file = new File();
        Scanner sc = new Scanner( System.in );
        String message = new String( UIDOT + "Enter file name to load ships"
            + " from");
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
                error = ( UIERR + "Error: File name cannot be emtpy\n" );
            }
        }
        while( !error.equals("") );

        try
        {
            file.load( fileName, Ships );
                //Imports filename and ShipStorage obj
        }
        catch( IllegalArgumentException e )
        {
            System.out.print( UIERR + "Error:" + e.getMessage() + "\n" );
                // All errors inside file.load are thrown as 
                // IllegalArgumentException with readable and helpful
                // error messages
        }
        catch( ArrayIndexOutOfBoundsException e2 )
        {
            System.out.println( UIERR +
                "Error: File does not contain valid data\n" );   
        }

        System.out.print( UITAB + "\n" );

        return inputMenu( "Please select:\n" + UITAB + CYLW + "L" + CC +
            "oad another file, " + CYLW + "V" + CC + "iew ships, " + CYLW
            + "M" + CC + "ain menu, " + CYLW + "E" + CC + "xit program" );
   }

    /**********************************************
        SUBMODULE: saveShips
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Gets a file name and uses file.save() to save all ships
            in storage to file
    ************************************************/
    public char saveShips()
    {
        Scanner sc = new Scanner( System.in );
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
        
        System.out.print( UITAB + "\n" );

        return inputMenu( "Please select:\n" + UITAB + CYLW + "V" + CC +
            "iew ships, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E" +
            CC + "xit program" );
    }


    /************************************
        SUBMODULE: viewShips
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Puts all ship's .toString's in a single String
    ************************************/
    public char viewShips()
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
                ( ( Ships.MAXSHIPS * 2 ) - Ships.getTotal() )
                + " ship slots left. (" + (Ships.MAXSHIPS - Ships.getSubNum())
                + " Submarines and " + (Ships.MAXSHIPS - Ships.getJetNum())
                + " Fighter Jets)\n" );
                // Shows how many slots in the ShipStorage arrays are
                // remaining
        }
        System.out.print( ships + UITAB + "\n" );

        return inputMenu( "Please select:\n" + UITAB + CYLW + "F" + CC +
            "ind Duplicates, " + CYLW + "M" + CC + "ain menu, " + CYLW +
            "E" + CC + "xit program" );
    }

    /**********************************************
        SUBMODULE: duplicates
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Makes a long String of all ship duplicates using the
            findDuplicates method in ShipStorage
    ************************************************/
    public char duplicates()
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
        System.out.print( dupes + "\n" + UITAB + "\n" );

        return inputMenu( "Please select:\n" + UITAB + CYLW + "V" + CC +
            "iew ships, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E" +
            CC + "xit program" );
     }

    /**********************************************
        SUBMODULE: destination
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Exports the ship that takes the shortest amount of time
            to travel a distance using the destinationCheck method in 
            ShipStorage
    ************************************************/
    public char destination()
    {
        Scanner sc = new Scanner( System.in );
        String fastest, input, output;
        String message = new String( UIDOT +
            "Enter distance to travel (meters)" );
        String error = new String("");
        int distance = 0;
        if( ( Ships.getSubNum() != 0 ) && ( Ships.getJetNum() != 0 ) )
        {
            do
            {
                System.out.print( error + message + "\n" + UIINP );
                try
                {
                    input = sc.nextLine();
                    if( input.equals("") )
                    {
                        error = ( UIERR + "Error: Distance cannot be empty\n" );
                    }
                    else
                    {
                        distance = ( (int)Double.parseDouble( input ) );
                        error = ( "" );
                        if(distance <= 0)
                        {
                            error = ( UIERR + "Error: Distance must be greater "
                                + "than 0 meters\n");
                        }
                    }
                }
                catch( NumberFormatException e )
                {
                    sc = new Scanner( System.in ); //Clears input buffer
                    error = ( UIERR + "Error: Distance must be a real number\n" );
                }
            }
            while( !error.equals( "" ) );

            System.out.print( UIDOT + Ships.destinationCheck( distance ) +
                "\n" + UITAB + "\n" );
        }
        else //If there are no ships in storage
        {
            System.out.print( UIDOT + "There are no ships in storage\n"
                + UITAB + "\n" );
        }

        return inputMenu( "Please select:\n" + UITAB + CYLW + "V" + CC +
            "iew ships, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E" +
            CC + "xit program" );
    }

// Display Submodules
    /**********************************************
        SUBMODULE: diplayMenu
        IMPORT: none
        EXPORT: none
        ASSERTION: Prints the main menu
    ************************************************/
    public void displayMenu()
    {
        System.out.print( UIM +
            " |  [ " + CYLW + "L" + CC + " - Load Ships from a file ]        "
            + "[ " + CYLW + "S" + CC + " - Save ships to a file ]\n" +
            " |         [ " + CYLW + "N" + CC + " - New Ship ]               "
            + "   [ " + CYLW + "V" + CC + " - View all ships ]\n" +
            " |  [ " + CYLW + "D" + CC + " - Destination Calculator ]        "
            + "  [ " + CYLW + "F" + CC + " - Duplicate Finder ]\n" +
            " |           [ " + CYLW + "E" + CC + " - Exit ]\n" + UITAB + "\n"
            );
    }

    /**********************************************
        SUBMODULE: diplayExit
        IMPORT: none
        EXPORT: none
        ASSERTION: Clears the screen for program exit
    ************************************************/
    public void displayExit()
    {
        System.out.print( String.format( "\033[2J\033[;H" ) );
    }

    /**********************************************
        SUBMODULE: diplayCrash
        IMPORT: none
        EXPORT: none
        ASSERTION: Clears the screen for program exit and shows crash message
            for ShipManager class base Esception handling
    ************************************************/
    public void displayCrash()
    {
        System.out.print( String.format( "\033[2J\033[;H" +
            UIERR + "Program encountered an unexpected error\n" ) );
    }

    /**********************************************
        SUBMODULE: displayTitle
        IMPORT: none
        EXPORT: none
        ASSERTION: Prints the title, version number is randomly generated lol
    ************************************************/
    public void displayTitle()
    {
        String version = new String( "v" + ((int)(Math.random() * 10)) + "." +
            ((int)(Math.random() * 10)) +
            ((int)(Math.random() * 10))); // "v#.##"
            //Random version number generation
        System.out.print( String.format( "\033[2J\033[;H" ) );
            //Clears the terminal and moves to upper left of screen
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
            //"ShipManager" ascii art with version number
    }
}
