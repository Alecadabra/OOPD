/*******************************
    Author: Alec Maughan
    Purpose: All user interface for ShipManager, user input and output
    Date: 9/5/19
*******************************/
import java.util.*;
public class UI
{
    //Class Constants
    public static final int MAXCHARS = 68; //Max. no. of characters per line
    //ANSI Colours:
    public static final String CYLW = new String( "\u001B[33m" ); //Yellow
    public static final String CCYN = new String( "\u001B[36m" ); //Cyan
    public static final String CRED = new String( "\u001B[31m" ); //Red
    public static final String CC = new String( "\u001B[0m" ); //Colour reset
    //New line 'starters'. For differenciating between userinput, errors, etc.
    public static final String UIDOT = new String( "[-]" ); //Messages
    public static final String UITAB = new String( " | " ); //Continuing
    public static final String UIERR = new String( CRED + "[!]" + CC );//Error
    public static final String UIINP = new String( CYLW + "[>]" + CC );//Input
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
        ASSERTION: Calls display methods for title and menu and calls utility
            methods based on user input from inputMenu or returned menu
            option from a utility
    ***************************/
    public void mainMenu()
    {
        char menu = 'M';
            //userMenu for main menu input, menu returns input from a util
        while( menu != 'E' )
        {
            if( menu == 'M' )
            {
                //If showing the main menu
                displayTitle();
                displayMenu();
                menu = inputMenu( "Please select an option:" );
            }
            if( menu != 'E' && menu != 'M' )
            {
                //If not exiting the program
                displayTitle();
            }
            //Utility submodules loadShips,viewShips etc return a menu option
            switch(menu)
            {
                case 'L':
                    //Load ships from file
                    System.out.print( UIL );
                    menu = loadShips();
                    break;
                case 'S':
                    //Save ships to file
                    System.out.print( UIS );
                    menu = saveShips();
                    break;
                case 'N':
                    // Create a new ship from user input
                    System.out.print( UIN );
                    menu = createShip();
                    break;
                case 'V':
                    // Diplay all stored ships
                    System.out.print( UIV );
                    menu = viewShips();
                    break;
                case 'D':
                    // Destination calculation stub
                    System.out.print( UID );
                    menu = destination();
                    break;
                case 'F':
                    // Display duplicate ships
                    System.out.print( UIF );
                    menu = duplicates();
                    break;
                case 'E':
                    // Exit program
                    break;
            } //End switch
        } //End while
        displayClear();
            //Clears the terminal
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
        String inString = new String("");
        boolean loop = false;
        Scanner sc = new Scanner( System.in );

        do
        {
            System.out.print( CYLW + UIDOT + CC + " " + message + "\n" +
                UIINP + " " );
            inString = sc.nextLine();
                //printInp() not used as the ANSI colour strings in some
                //messages make the line splitter think lines are longer
                //than they actually are
            if( !inString.equals("") )
            {
                switch(inString.toUpperCase().charAt(0))
                {
                    case 'L': case 'S': case 'N': case 'V': case 'D':
                        case 'F': case 'E': case 'M':
                        menu = inString.toUpperCase().charAt(0);
                            // Sets output char to first letter in String
                            // if letter is valid
                        loop = false;
                        break;
                    default:
                        printErr( "Error: Invalid menu selection \"" +
                            inString + "\"" );
                        loop = true;
                        break;
                }
            }
            else
            {
                sc = new Scanner( System.in ); //Clears input buffer
                printErr( "Error: Menu selection cannot be empty" );
                loop = true;
            }
        } //End dowhile
        while( loop );

        return menu;
    }

// Printing submodules
    /***************************
        SUBMODULE: print
        IMPORT: printString
        EXPORT: none
        ASSERTION: Prints to the terminal
    ***************************/
    public void print( String printString )
    {
        System.out.print( splitLine( ( printString + "\n" ), UIDOT, UITAB )
            );
    }
 
    /***************************
        SUBMODULE: printInp
        IMPORT: printString
        EXPORT: String from user input
        ASSERTION: Prints to the terminal with user input on next
            line
    ***************************/
    public String printInp( String printString )
    {
        Scanner sc = new Scanner( System.in );
        System.out.print( splitLine( ( printString + "\n" + UIINP ),
            ( CYLW + UIDOT + CC ), ( CYLW + UITAB + CC ) ) + " " );
        return sc.nextLine();
    }

    /***************************
        SUBMODULE: printErr
        IMPORT: printString
        EXPORT: none
        ASSERTION: Prints an error to the terminal
    ***************************/
    public void printErr( String printString )
    {
        System.out.print( splitLine( ( printString + "\n" ), UIERR,
            ( CRED + UITAB + CC ) ) );
    }

    /***************************
        SUBMODULE: printLine()
        IMPORT: none
        EXPORT: none
        ASSERTION: Prints a line breaker (" | ") to the terminal
    ***************************/
    public void printLine()
    {
        System.out.print( UITAB + "\n" );
    }

    /***************************
        SUBMODULE: splitLine
        IMPORT: text (String), first (String), second (String)
        EXPORT: outText
        ASSERTION: Splits a String into multiple lines to fit the text into
            designated program area
    ***************************/
    public String splitLine( String text, String first, String second )
    {
        //first (String) is first line starter to use (Generally "[-]")
        //second (String) is line starter after that (Generally" | ")
        String[] words = text.split(" ");
            //Array of each word in imported String
        int[] numLetters = new int[ words.length ];
            //Array of number of letters in each word in words array
        String outText = new String( first );
            //Final text to export
        int lineChars = 0;
            //No. of characters in curent line

        for( int k = 0; k < words.length; k++ )
        {
            //Checks if any words are longer than the maximum
            if( words[k].length() >= MAXCHARS )
            {
                String longWord = new String( words[k] );
                words[k] = "";
                for( int l = 0; l < ( MAXCHARS - 4 ); l++ )
                {
                    words[k] += longWord.charAt(l);
                }
                words[k] += "...\n";
                //Truncates word and adds "..." to end
            }
        }

        for( int i = 0; i < words.length; i++ )
        {
            //Sets each element in array to no. of chars in corresponding word
            numLetters[i] = words[i].length();
            /*
            // Not used in program
            if( words[i].contains( "u001B[" ) )
            {
                //If words has ANSI colours, ignore their length
                numLetters[i] -= 10;
            }
            */
        }

        //Creating lines
        for( int j = 0; j < words.length; j++ )
        {
            if( words[j].equals("\n") )
            {
                //If current word is a newline
                outText += ( "\n" + second );
                lineChars = numLetters[j];
            }
            else if( lineChars + numLetters[j] < MAXCHARS )
            {
                //If adding the no. of characters in the current word to the
                //line will not exceed the max no. of chars per line
                lineChars += ( numLetters[j] + 1 );
                outText += ( " " + words[j] );
            }
            else
            {
                //If adding the no. of characters in the current word to the
                //line WILL exceed the max no. of chars per line
                outText += "\n" + second + " " + words[j];
                lineChars = numLetters[j];
            }
        }
        return outText;
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
        String type = new String();
            //Ship type, 'S' for Submarine, 'J' or 'F' for Jet
        int num = 0;
            //Keeps track of type of ship and current classfield in question
            //First digit is 1 for submarines or 2 for fighter jets
            //Second digit is the classfield, eg 1 is serial, 4 is fuel
        SubClass sub = new SubClass();
        EngineClass subEng = new EngineClass();
        JetClass jet = new JetClass();
        EngineClass jetEng = new EngineClass();

        if( Ships.getSlots() == 0 )
        {
            //If there are no ship slots left
            print( "You cannot add another ship as there are no ship " +
                "slots left. (Max = " + Ships.MAXSHIPS + ")" );
            printLine();
        }
        else
        {
            type = printInp( "Enter ship type" ).toUpperCase();
            while( !type.equals( "S" ) && !type.equals( "F" ) )
            {
                if( type.equals( "" ) )
                {
                    //If input is empty
                    printErr(
                        "Error with ship type \"\": Ship type cannot be empty" 
                        );
                }
                else
                {
                    //If input is something other than S or F
                    printErr( "Error with ship type \"" + type + 
                        "\": Ship type must be Submarine (S) or Fighter " +
                        "Jet (F)" );
                }
                type = printInp( "Enter ship type" ).toUpperCase();
            }
            printLine();

            if( type.equals("S") )
            {
                num = 11;
            }
            else if( type.equals( "F" ) )
            {
                num = 21;
            }
        }

        while( ( num <= 17 && num >= 10 ) || ( num <= 27 && num >= 20 ) )
        {
            try
            {
                switch( num )
                {
                    case 11:
                        //Input serial number for sub
                        sub.setSerial( printInp( "Enter serial number" ) );
                        break;
                    case 21:
                        //Input serial number for jet
                        jet.setSerial( printInp( "Enter serial number" ) );
                        break;
                    case 12:
                        //Input manufacturing year for sub
                        sub.setYear( printInp( "Enter manufacturing year" ) );
                        break;
                    case 22:
                        //Input manufacturing year for jet
                        jet.setYear( printInp( "Enter manufacturing year" ) );
                        break;
                    case 13:
                        //Input number of cylinders for sub
                        subEng.setCyl( printInp( "Enter number of cylinders" 
                            ) );
                        break;
                    case 23:
                        //Input number of cylinders for jet
                        jetEng.setCyl( printInp( "Enter number of cylinders" 
                            ) );
                        break;
                    case 14:
                        //Input fuel type for sub
                        subEng.setFuel( printInp( "Enter fuel type" ) );
                        break;
                    case 24:
                        //Input fuel type for jet
                        jetEng.setFuel( printInp( "Enter fuel type" ) );
                        break;
                    case 15:
                        //Input hull material
                        sub.setHull( printInp( "Enter hull material" ) );
                        break;
                    case 16:
                        //Input max depth
                        sub.setDepth( printInp( "Enter max depth" ) );
                        break;
                    case 25:
                        //Input wing span
                        jet.setWing( printInp( "Enter wing span" ) );
                        break;
                    case 26:
                        //Input ordnance
                        jet.setOrdnance( printInp( "Enter ordnance" ) );
                        break;
                    case 17: //Add sub to storage
                        sub.setEng( subEng ); //Sets engine of the ship
                        Ships.addShip( sub ); //Adds the ship to storage
                        print( "Ship " + sub.getSerial() + 
                            " added successfully. " + Ships.slotsToString() );
                        break;
                    case 27: //Add jet to storage
                        jet.setEng( jetEng ); //Sets engine of the ship
                        Ships.addShip( jet ); //Adds the ship to storage
                        print( "Ship " + jet.getSerial() + 
                            " added successfully. " + Ships.slotsToString() );
                        break;
                    default:
                        //Error
                        num = 30;
                        printErr( "Error: Unknown error (Sorry)" );
                        break;
                }//End switch
                num++;
                printLine();
                    //Increments the number to set the next element of ship
            }//End try block
            catch( IllegalArgumentException e )
            {
                printErr( "Error with " + e.getMessage() );
                    //The setters throw IllegalArgumentExceptions with
                    //messages that make sense when added to this message
            }
        }//End while loop

        return inputMenu( "Please select: \n" + CYLW + UITAB + " N" + 
            CC + "ew ship, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E"
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
        File file = new File(); //FileIO object
        String fileName = new String();
        String message = new String(); //Final message returned from file.load
        String[] messageArr; //Array of returned messages from file.load

        fileName = printInp( "Enter file name to load ships from" );
        while( fileName.equals("") )
        {
            printErr( "Error: File name cannot be empty" );
            fileName = printInp( "Enter file name to load ships from" );
            //Looping input validation for empty input
        }

        message = file.load( fileName, Ships );
            //Imports filename and ShipStorage obj. file.load method
            //returns a message String depending on what happened

        printLine();
        if( !message.contains( "\n" ) )
        {
            //If file.load returns no errors
            print( message );
            printLine();
        }
        else
        {
            messageArr = message.split( "\n" );
            for( int i = 0; i < messageArr.length; i++ )
            {
                if( messageArr[i].contains( "Error" ) )
                {
                    printErr( messageArr[i] );
                }
                else
                {
                    print( messageArr[i] );
                }
                printLine();
            }
        }

        return inputMenu( "Please select: \n" + CYLW + UITAB + " L" + 
            CC + "oad another file, " + CYLW + "V" + CC + "iew ships, " + CYLW
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
        File file = new File(); //FileIO Object
        String message = new String(); //Message returned by file.save
        String fileName = new String();

        fileName = printInp( "Enter file name to save ships to" );
        while( fileName.equals( "" ) )
        {
            printErr( "Error: File name cannot be empty" );
            fileName = printInp( "Enter file name to save ships to" );
        }

        message = file.save( fileName, Ships );
            //file.load returns a message based on what happened
        if( message.contains( "Error" ) )
        {
            printErr( message );
        }
        else
        {
            print( message );
        }
        printLine();

        return inputMenu( "Please select: \n" + CYLW + UITAB + " V" + 
            CC + "iew ships, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E" +
            CC + "xit program" );
    }


    /************************************
        SUBMODULE: viewShips
        IMPORT: none
        EXPORT: menu (char)
        ASSERTION: Prints all ship's .toString()'s
    ************************************/
    public char viewShips()
    {
        for( int i = 0; i < Ships.getShipNum(); i++ )
        {
            print( Ships.getShip(i).toString() );
                //getShip() returns a copy of the ship at an index
            printLine();
        }

        if( Ships.getShipNum() == 0 )
        {   // If there are no ships in storage
            print( "No ships in storage. " + Ships.slotsToString() );
        }
        else
        {   // If there were ships in storage
            print( Ships.slotsToString() );
        }
        printLine();

        return inputMenu( "Please select: \n" + CYLW + UITAB + " F" + CC +
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
        ShipClass[] dupeArr = Ships.findDuplicates();
        int i = 0; //Index used for while loop for outputting dupes
        if( Ships.getShipNum() == 0 )
        {
            //If there are no ships in storage
            print( "No ships in storage" );
        }
        else if( dupeArr[0] == null )
        {
            //If there are ships in storage but no duplicates
            print( "Checked " + Ships.getShipNum() +
                " ships. No duplicates found" );
        }
        else
        {
            //If there are ships in storage and there are duplicates
            print( "Checked " + Ships.getShipNum() + " ships. Duplicates:" );

            while( ( i < dupeArr.length ) && ( dupeArr[i] != null ) )
            {
                printLine();
                print( dupeArr[i].toString() );
                i++;
            }
        }
        printLine();

        return inputMenu( "Please select: \n" + CYLW + UITAB + " V" + 
            CC + "iew ships, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E" +
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
        String fastest, input, output;
        boolean loop = false;
        int distance = 0;
        if( Ships.getShipNum() != 0 )
        {
            do
            {
                input = printInp( "Enter distance to travel (meters)" );
                try
                {
                    if( input.equals("") )
                    {
                        //If input is empty
                        printErr( "Error: Distance cannot be empty" );
                        loop = true;
                    }
                    else
                    {
                        distance = ( (int)Double.parseDouble( input ) );
                        loop = false;
                        if( distance <= 0 )
                        {
                            //If input is 0 or less
                            printErr( "Error: Distance must be greater "
                                + "than 0 meters");
                            loop = true;
                        }
                    }
                }
                catch( NumberFormatException e )
                {
                    //If input cannot be parsed to double
                    printErr( "Error: Distance must be a real number" );
                    loop = true;
                }
            }
            while( loop );

            print( Ships.destinationCheck( distance ) );
        }
        else
        {
            //If there are no ships in storage
            print( "There are no ships in storage" );
        }
        printLine();

        return inputMenu( "Please select: \n" + CYLW + UITAB + " V" + 
            CC + "iew ships, " + CYLW + "M" + CC + "ain menu, " + CYLW + "E" +
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
        ASSERTION: Clears the terminal and moves cursor to top left using
            ANSI output
    ************************************************/
    public void displayClear()
    {
        System.out.print( String.format( "\033[H\033[2J" ) );
        //Clears the terminal and moves cursor to top left of terminal
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
        displayClear();
        printErr( "Program encountered an unexpected error\n" );
        //Clears the terminal, moves cursor to top left of terminal and
        //prints crash message
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
        displayClear();
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
