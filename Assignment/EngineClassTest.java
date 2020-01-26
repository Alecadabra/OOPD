import java.util.*;
public class EngineClassTest
{
    public static void main(String[]args)
    {
        try
        {
            EngineClass[] engine = new EngineClass[4];

            //object creation
            engine[0] = new EngineClass();
            engine[1] = new EngineClass( 15, "bio" );
            engine[2] = new EngineClass( engine[1] );
            engine[3] = engine[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < engine.length; i++)
            {
                System.out.println("Engine[" + i + "]: " + 
                    engine[i].toString());
            }

            //file print created objects
            System.out.println("FILE OUTPUT TESTS:");
            for(int i = 0; i < engine.length; i++)
            {
                System.out.println("Engine[" + i + "]: " + 
                    engine[i].toFileString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " +
                engine[1].equals(engine[3]));
            System.out.println("Equals (object) expected FALSE: " + 
                engine[0].equals(engine[3]));

            //gerrers and setters
            System.out.println("\nGETTERS AND SETTERS:");
            engine[0].setFuel(engine[1].getFuel());
            System.out.println(engine[0].getFuel() + " = " + 
                engine[1].getFuel());

            engine[0].setCyl(engine[1].getCyl());
            System.out.println(engine[0].getCyl() + " = " + 
                engine[1].getCyl());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


