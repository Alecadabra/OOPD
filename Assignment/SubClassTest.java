import java.util.*;
public class SubClassTest
{
    public static void main(String[]args)
    {
        try
        {
            SubClass[] sub = new SubClass[4];

            //object creation
            sub[0] = new SubClass();
            EngineClass eng = new EngineClass( 5, "battery" );
            sub[1] = new SubClass("200.123", 1985, eng, "titanIUM",-50);
            sub[2] = new SubClass(sub[1]);
            sub[3] = sub[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < sub.length; i++)
            {
                System.out.println("Sub[" + i + "]: " + 
                    sub[i].toString());
            }

            //file print created objects
            System.out.println("FILE OUTPUT TESTS:");
            for(int i = 0; i < sub.length; i++)
            {
                System.out.println("Sub[" + i + "]: " + 
                    sub[i].toFileString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " +
                sub[1].equals(sub[3]));
            System.out.println("Equals (object) expected FALSE: " + 
                sub[0].equals(sub[3]));

            //gerrers and setters
            System.out.println("\nGETTERS AND SETTERS:");
            sub[0].setSerial(sub[1].getSerial());
            System.out.println(sub[0].getSerial() + " = " + 
                sub[1].getSerial());

            sub[0].setYear(sub[1].getYear());
            System.out.println(sub[0].getYear() + " = " + 
                sub[1].getYear());

            sub[0].setDepth(sub[1].getDepth());
            System.out.println(sub[0].getDepth() + " = " + 
                sub[1].getDepth());

            sub[0].setHull(sub[1].getHull());
            System.out.println(sub[0].getHull() + " = " + 
                sub[1].getHull());

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


