import java.util.*;
public class SubmarineClassTestHarness
{
    public static void main(String[]args)
    {
        try
        {
            SubmarineClass[] submarine = new SubmarineClass[4];

            //object creation
            submarine[0] = new SubmarineClass();
            submarine[1] = new SubmarineClass(200.123, 1985, -50, 
                SubmarineClass.TITAN);
            submarine[2] = new SubmarineClass(submarine[1]);
            submarine[3] = submarine[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < submarine.length; i++)
            {
                System.out.println("Submarine[" + i + "]: " + 
                    submarine[i].toString());
            }

            //file print created objects
            System.out.println("FILE OUTPUT TESTS:");
            for(int i = 0; i < submarine.length; i++)
            {
                System.out.println("Submarine[" + i + "]: " + 
                    submarine[i].toFileString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " +
                submarine[1].equals(submarine[3]));
            System.out.println("Equals (object) expected FALSE: " + 
                submarine[0].equals(submarine[3]));

            //gerrers and setters
            System.out.println("\nGETTERS AND SETTERS:");
            submarine[0].setSerial(submarine[1].getSerial());
            System.out.println(submarine[0].getSerial() + " = " + 
                submarine[1].getSerial());

            submarine[0].setYear(submarine[1].getYear());
            System.out.println(submarine[0].getYear() + " = " + 
                submarine[1].getYear());

            submarine[0].setDepth(submarine[1].getDepth());
            System.out.println(submarine[0].getDepth() + " = " + 
                submarine[1].getDepth());

            submarine[0].setHull(submarine[1].getHull());
            System.out.println(submarine[0].getHull() + " = " + 
                submarine[1].getHull());

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


