import java.util.*;
public class ShipClassTest
{
    public static void main( String[] args )
    {
        ShipClass[] ship = new ShipClass[6];

        //Object Creation
        ship[0] = new SubClass();
        EngineClass eng = new EngineClass();
        ship[1] = new SubClass( "123.123", 2000, eng, "TITANIUM", -25.4 );
        //ship[2] = new SubClass( ship[1] );
        ship[2] = ship[1].clone();
        ship[3] = new JetClass();
        ship[4] = new JetClass( "213.321", 1999, eng, 12.4, "guns" );
        //ship[6] = new JetClass( ship[5] );
        ship[5] = ship[4].clone();

        //Testing .toString()
        System.out.println( "\nTOSTRING:" );
        for( int i = 0; i < ship.length; i++ )
        {
            System.out.println( ship[i].toString() );
        }

        //equals method
        System.out.println("\nEQUALS METHOD TESTS:");
        System.out.println("Equals (object) expected TRUE: " +
            ship[1].equals(ship[2]));
        System.out.println("Equals (object) expected FALSE: " + 
            ship[0].equals(ship[2]));
        System.out.println("Equals (object) expected TRUE: " +
            ship[4].equals(ship[5]));
        System.out.println("Equals (object) expected FALSE: " + 
            ship[3].equals(ship[5]));


        //getteers and setters
        System.out.println("\nGETTERS AND SETTERS:");
        ship[0].setSerial(ship[1].getSerial());
        System.out.println(ship[0].getSerial() + " = " + 
            ship[1].getSerial());

        ship[0].setYear(ship[1].getYear());
        System.out.println(ship[0].getYear() + " = " + 
            ship[1].getYear());

        ship[0].setEng( ship[1].getEng() );
        System.out.println( ship[0].getEng().toString() + " = " +
            ship[1].getEng().toString() );

        ship[3].setSerial(ship[4].getSerial());
        System.out.println(ship[3].getSerial() + " = " + 
            ship[4].getSerial());

        ship[3].setYear(ship[4].getYear());
        System.out.println(ship[3].getYear() + " = " + 
            ship[4].getYear());

        ship[3].setEng( ship[4].getEng() );
        System.out.println( ship[3].getEng().toString() + " = " +
            ship[4].getEng().toString() );
    }

}
