/*
 * Author: Alec Maughan
 * Purpose: Calculates the no. of animals that can fit in a pond.
 * Date: 11/04/2019
 */

import java.util.*;
public class PondCalculator
{
    public static void main( String[]args )
    {
        Scanner sc = new Scanner(System.in);
        boolean exit;
        String message;
        // Person variables
        int personMenu;
        String personName, personHisHer;
        // Fish variables
        int fishMenu;
        double fishNum, fishVolume;
        String fishName, fishAltName;
        // Pond variables
        int pondFish;
        double pondDepth, pondLength, pondWidth, pondVolume;

        exit = exitPrompt( 1 );
        while( !exit )
        {
            // Person
            // Gets the person as an int between 1 and 3, gets a name string
            // based on this and a his/her string based on their sex
            message = ("Select person:\n1: Joey, 2: Cory, 3: Rachel");
            personMenu = inputInt( message, 1, 3 );
            personName = findPersonName( personMenu );
            personHisHer = findPersonHisHer( personMenu );

            // Fish
            // Gets the fish names for the chosen person, gets the chosen fish
            // as an int relative to the person, gets a number that describes
            // the fish independent of the person, changes the fish name if
            // needed.
            fishName = findFishName( calcFishNum( personMenu, 1 ) );
            fishAltName = findFishName( calcFishNum( personMenu, 2 ) );
            message = ( "Select fish:\n1: " + fishName + ", 2: " +
            fishAltName + "." );
            fishMenu = inputInt( message, 1, 2 );
            fishNum = calcFishNum( personMenu, fishMenu );
            if( fishMenu == 2 )
            {
                fishName = fishAltName;
            };

            // Pond
            // Calculates the pond's volume based on input of all pond
            // dimensions
            pondDepth = inputReal( "Input pond depth (m)", 0 );
            pondLength = inputReal( "Input pond length (m)", 0 );
            pondWidth = inputReal( "Input pond width (m)", 0 );
            pondVolume = pondDepth * pondLength * pondWidth;

            // Calculations
            // Calculates the volume needed for the chosen fish, finds the no.
            // of fish that can fit in the pond volume (rounded down to int)
            // and rounds the pond volume to nearest whole number for display
            // purposes
            fishVolume = calcFishVolume( fishNum );
            pondFish = (int)( pondVolume * fishVolume );
            pondVolume = Math.round( pondVolume );

            // Output
            System.out.println( personName + " can store " + pondFish + " " +
            fishName + " in " + personHisHer + " " + pondVolume + "m^3 pond." );

            // Exit or Loop
            exit = exitPrompt( 2 );
        };
    }

    /*
     * Name: exitPrompt
     * Import: message (int)
     * Export: exit (boolean)
     * Prompts the user to continue or to exit the program. Has different
     * messages for the start and the exit of the program (Determined by
     * message int).
     */
    public static boolean exitPrompt( int message )
    {
        int num;
        String prompt = new String ("Error. Line 83");
        boolean exit;
        switch( message )
        {
            case 1:
                prompt = ( "Welcome. Enter '1' to start or '2' to exit." );
                break;
            case 2:
                prompt = ( "Enter '1' to go again or '2' to exit." );
                break;
        };
        num = inputInt( prompt, 1, 2 );
        switch( num )
        {
            case 1:
                exit = false;
                break;
            case 2:
                exit = true;
                System.out.println( "Goodbye :)" );
                break;
            default:
                exit = false;
                System.out.println("Error. Line 105");
        };
        return exit;
    }

    /*
     * Name: inputInt
     * Import: message (String), min (int), max (int)
     * Export: output (int)
     * Gets an int input with min and max specified input validation.
     */
    public static int inputInt( String message, int min, int max )
    {
        Scanner sc = new Scanner(System.in);
        int output;
        boolean loop = false;
        
        System.out.println( message );
        do
        {
            try
            {
                output = sc.nextInt();
                loop = false;
            }
            catch ( InputMismatchException e )
            {
                output = 0;
                sc.nextLine();
                System.out.println( "Error, Invalid Input: Input must be " +
                "an Integer.\n" + message );
                loop = true;
            }
        }
        while ( loop );

        while( ( output < min ) || ( output > max ) )
        {
            if( output < min )
            {
                System.out.println( "Error, Invalid Input: " + output +
                " < " + min + ".\n" + message );
                output = sc.nextInt();
            }
            else if( output > max )
            {
                System.out.println( "Error, Invalid Input: " + output +
                " > " + max + ".\n" + message );
                output = sc.nextInt();
            };
        }
        return output;
    }

    /*
     * Name: inputReal
     * Import: message (String), min (Real)
     * Export: output (Real)
     * Gets a double input with min specified input validation.
     */ 
    public static double inputReal( String message, double min )
    {
        Scanner sc = new Scanner(System.in);
        double output = 0;
        String messageB = message;

        do
        {
            try
            {
                System.out.println( messageB );
                output = sc.nextDouble();
                messageB = ( "Error, Invalid Input: " + output + " <= " + min +
                ".\n" + message );
            }
            catch ( InputMismatchException e )
            {
                output = min;
                sc.nextLine();
                messageB = ( "Error, Invalid Input: Input must be a Real " +
                "number.\n" + message );
            }
        }
        while( output <= min );
        return output;
    }

    /*
     * Name: findFishName
     * Import: fishNum (double)
     * Export: fish (String)
     * Finds the plural name of a fish from a fish number.
     */ 
    public static String findFishName( double fishNum )
    {
        int fishInt;
        String fish;
        fishNum = ( fishNum * 10.0 );
        fishInt = (int)( fishNum );
        switch( fishInt )
        {
            case 11: 
                fish = ( "Sting Rays" ); 
                break; 
            case 12:
                fish = ( "Arowana" );
                break;
            case 21:
                fish = ( "Koi" );
                break;
            case 22:
                fish = ( "Puffer Fish" );
                break;
            case 31:
                fish = ( "Turtles" );
                break;
            case 32:
                fish = ( "Frogs" );
                break;
            default:
                fish = ( "???" );
                System.out.println( "Error. Line 196" );
        };
        return fish;
    }

    /*
     * Name: calcFishNum
     * Import: person (int), fishMenu (int)
     * Export: fishNum (double)
     * Calcualtes the fish number of a fish from it's person and menu select.
     */ 
    public static double calcFishNum( int person, int fishMenu )
    {
        double fishNum;
        fishNum = ( (double)fishMenu ) / 10;
        fishNum = ( (double)person ) + fishNum;
        // fishNum will be 1.1, 1.2, 2.1, 2.2, 3.1, or 3.2.
        return fishNum;
    }

    /*
     * Name: calcFishVolume
     * Import: fishNum (double)
     * Export: fish (double)
     * Calculates the volume that a fish takes up from a fish number.
     */ 
    public static double calcFishVolume( double fishNum )
    {
        int fishInt;
        double fish;
        fishNum = ( fishNum * 10.0 );
        fishInt = (int)( fishNum );
        switch( fishInt )
        {
            case 11:
                fish = 0.5;
                break;
            case 12:
                fish = 0.4;
                break;
            case 21:
                fish = 0.6;
                break;
            case 22:
                fish = 0.8;
                break;
            case 31:
                fish = 1.2;
                break;
            case 32:
                fish = 4.5;
                break;
            default:
                fish = 0;
                System.out.println( "Error. Line 250" );
        };
        return fish;
    }

    /*
     * Name: findPersonName
     * Import: num (int)
     * Export: name (String)
     * Get's a person's name from person menu selection
     */ 
    public static String findPersonName( int num )
    {
        String name;
        switch( num )
        {
            case 1:
                name = ( "Joey" );
                break;
            case 2:
                name = ( "Cory" );
                break;
            case 3:
                name = ( "Rachel" );
                break;
            default:
                name = ( "???" );
                System.out.println( "Error. Line 277" );
        }
        return name;
    }

    /*
     * Name: findPersonHisHer
     * Import: personMenu (int)
     * Export: hisher (String)
     * Find's if a person should be reffered to as 'his' or 'her' from menu
     * selection
     */ 
    public static String findPersonHisHer( int personMenu )
    {
        String hisher;
        switch( personMenu )
        {
            case 1: case 2:
                hisher = ( "his" );
                break;
            case 3:
                hisher = ( "her" );
                break;
            default:
                hisher = ( "???" );
                System.out.println( "Error. Line 302" );
        }
        return hisher;
    }
}
