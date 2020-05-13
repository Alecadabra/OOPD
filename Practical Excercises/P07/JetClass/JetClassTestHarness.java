import java.util.*;
public class JetClassTestHarness
{
    public static void main(String[]args)
    {
        try
        {
            JetClass[] jet = new JetClass[4];

            //object creation
            jet[0] = new JetClass();
            jet[1] = new JetClass(200.123, 1985, "rocket launchers", 10.0);
            jet[2] = new JetClass(jet[1]);
            jet[3] = jet[1].clone();

            //print out created objects
            System.out.println("CONSTRUCTOR TESTS:");
            for(int i = 0; i < jet.length; i++)
            {
                System.out.println("Jet[" + i + "]: " + 
                    jet[i].toString());
            }

            //file print created objects
            System.out.println("FILE OUTPUT TESTS:");
            for(int i = 0; i < jet.length; i++)
            {
                System.out.println("Jet[" + i + "]: " + 
                    jet[i].toFileString());
            }

            //equals method
            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " +
                jet[1].equals(jet[3]));
            System.out.println("Equals (object) expected FALSE: " + 
                jet[0].equals(jet[3]));

            //gerrers and setters
            System.out.println("\nGETTERS AND SETTERS:");
            jet[0].setSerial(jet[1].getSerial());
            System.out.println(jet[0].getSerial() + " = " + 
                jet[1].getSerial());

            jet[0].setYear(jet[1].getYear());
            System.out.println(jet[0].getYear() + " = " + 
                jet[1].getYear());

            jet[0].setOrdnance(jet[1].getOrdnance());
            System.out.println(jet[0].getOrdnance() + " = " + 
                jet[1].getOrdnance());

            jet[0].setWing(jet[1].getWing());
            System.out.println(jet[0].getWing() + " = " + 
                jet[1].getWing());

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}


