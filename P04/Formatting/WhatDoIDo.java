/*
 * Author: Alec Maughan
 * Purpose: Uses submodules to get prompted and unprompted user inputs and to
 * output random numbers in various ways.
 * Date: 1/4/19
 */

import java.util.*;
public class WhatDoIDo
{
    public static void main( String[] args )
    {

        switch( promptedInputMethod( "Select an option:\n1: Random number " + 
            "(0-10)\n2: Random number (0-1) * 42 + 42\n3: Random number " +
            "(0-1) * 42 + 1\n4: Exit" ) )
        // Gets a user inputted integer with the prompted message above and
        // applies a switch statement depending on it's value.
        {
            case 1:

                /*
                 * Not useful so commented out
                int choice = blankInputMethod();
                Gets a user inputted integer with no prompted message and
                does nothing with it
                 */

                if( promptedInputMethod( "Enter '1' to continue" ) == 1 )
                {
                    randomVoidMethodA();
                // Gets a user inputted integer with the prompted message
                // "Enter '1' to continue", if it is equal to 1, a random
                // number between 0 and 10 is output.

                /*
                 * Not useful so commented out                
                else
                {
                    int usefulThing = randomVoidMethodB;
                    // sets usefulThing to 0. Not actually useful.
                }
                 */

                }; // end if

            break;

            case 2:
                System.out.println( randomReturnMethodA() );
                // Outputs a number from randomReturnMethodA
            break;

            case 3:
                System.out.println( randomReturnMethodB() );
                // Outputs a number from randomReturnMethodB
            break;
        } // end switch

    } // end main method

    public static int randomReturnMethodA()
    {
    // randomReturnMethodA gets a random number, multiplies it by 42 and
    // then adds it to 42 and returns this number truncated as an integer.
    // The final number is returned to main rather than outputting it inside
    // the method.
        Random rand = new Random();
        double randomNumber = rand.nextDouble(); 
        int output = (int)( randomNumber * 42.0 + 42.0 );
        System.out.println(randomNumber);
        return output;
    }


    public static int randomReturnMethodB()
    {
    // randomReturnMethodB gets a random number, multiplies it by 42 and then
    // adds 1.The final number is returned to main rather than outputting it
    // inside the method. This method is not used in main.
        Random rand = new Random();
        double randomNumber = rand.nextDouble();
        int output = (int)( randomNumber * 42.0 + 1.0 );
        System.out.println(randomNumber);
        return output;
    } 

    /*
     * Not used in main so commented out
    public static int blankInputMethod()
    {
    // blankInputMethod gets an integer input and returns it. Not used
    // anywhere in main
        Scanner sc = new Scanner(System.in);
        int output = sc.nextInt();
        return output;
    }
     */

    public static int promptedInputMethod( String message )
    {
    // promptedInputMethod prompts the user with an imported message string,
    // gets an integer input and returns it.
        Scanner sc = new Scanner(System.in);
        System.out.println( message );
        int output = sc.nextInt();
        return output;
    }

    public static void randomVoidMethodA()
    {
    // randomVoidMethodA outputs "Number: " and then a random number between
    // 0 and 10. The final number is output inside the method rather than
    // returning the value to main. 
        Random rand = new Random();
        int randomNumber = rand.nextInt(10);
        String output = new String( "Number: " + randomNumber );
        System.out.println( output );
    }

    /*
     * Not used in main so commented out
    public static void randomVoidMethodB()
    {
    // randomVoidMethodB outputs "Number: " and then a random number between
    // 0 and 90 added to 10. The final number is output inside the method
    // rather than returning the value to main.
        Random rand = new Random();
        int randomNumber = rand.nextInt(90);
        String output = new String( "Number: " + randomNumber + 10 );
        System.out.println( output );
    }  
     */

}
