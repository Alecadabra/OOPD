/*******************************
    Author: Alec Maughan
    Purpose: Ship storage system, stores ships in seperate arrays
    Date: 9/5/19
*******************************/
import java.util.*;
public class ShipStorage
{
    //Class constants
    public static final int MAXSHIPS = 30;

    //Private class fields
    private ShipClass[] shipArr; //Contains all ships
    private int shipNum;
        //Index to use when adding a ship, also equal to the no. of ships + 1

    /************************************
        Default Contructor
        IMPORT: none
        EXPORT: memory address of new ShipStorage object
        ASSERTION: initialises arrays
    ************************************/
    public ShipStorage()
    {
        shipArr = new ShipClass[ MAXSHIPS ];
        shipNum = 0;
    }

    /************************************
        SUBMODULE: addShip
        IMPORT: inShip (ShipClass)
        EXPORT: none
        ASSERTION: adds ship object to shipArr if there is space in the array
            and the object is not null
    ************************************/
    public void addShip(ShipClass inShip)
    {
        if( ( inShip != null ) && ( shipNum < MAXSHIPS ) )
        {
            shipArr[ shipNum ] = inShip;
            shipNum++;
        }
        else if( inShip == null )
        {
            throw new IllegalArgumentException( "Invalid Ship" );
        }
        else if( shipNum >= MAXSHIPS )
        {
            throw new IllegalArgumentException(
                "ship storage: Number of Ships has "
                + "reached the maximum capacity (" + MAXSHIPS + ")" );
        }
    }

    /************************************
        View Ships implemented in UI.java
    ************************************/

    /************************************
        SUBMODULE: destinationCheck
        IMPORT: dist (int)
        EXPORT: outString (String)
        ASSERTION: Loops through both arrays and uses clacTravel to find the 
            ship that travels dist in the shortest amount of time and
            outputs String of fastest ship
    ************************************/
    public String destinationCheck( int dist )
    {
        double hours = 0.0; //Hours taken to travel dist
        String outString = new String( "" ); //Output string

        if( shipArr[0] != null )
        {
            hours = (
                Math.round( shipArr[0].calcTravel( dist ) * 100.0 ) / 100.0 );
                //Sets the 1st submarine to the fastest and round to 2dp
            outString = ( "The submarine " + shipArr[0].getSerial() +
                " is the fastest at " + hours + " hours." );
            for( int i = 1; i < ( shipNum - 1 ); i++ )
            {
                if( shipArr[i].calcTravel(dist) < hours )
                {
                    hours = ( Math.round(
                        shipArr[i].calcTravel(dist) * 100.0 ) / 100.0 );
                        //Sets the new fastest sub and rounds to 2dp
                    outString = ( "The ship " + shipArr[i].getSerial() +
                        " is the fastest at " + hours + " hours." );
                }
            }
        }

       return outString;
    }

    /************************************
        SUBMODULE: findDuplicates
        IMPORT: none
        EXPORT: dupeArr (ShipClass[])
        ASSERTION: Finds duplicate ships and stores their .toString's in an
            array
    ************************************/
    public ShipClass[] findDuplicates()
    {
        int count = 0; //Count for index of dupeArr
        ShipClass[] dupeArr = new ShipClass[ shipNum ];
        int k; //Index used in while loop
        boolean exists; //Used in while loop

        for( int i = 0; i < shipNum; i++)
        {
            for( int j = i + 1; j < shipNum; j++)
            {
                if( ( i != j ) && ( shipArr[i].equals( shipArr[j] ) ) )
                {
                    k = 0;
                    exists = false;
                    while( !exists && dupeArr[k] != null )
                    {
                        //Loops until all dupes are checked or if ship is
                        //found in dupeArr
                        exists = dupeArr[k].equals( shipArr[i] );
                        k++;
                        //Checks if duplicate already exists in dupeArr
                    }
                    if( !exists )
                    {
                        //If duplicate is not already in dupeArr, add it
                        dupeArr[count] = shipArr[i];
                        count++;
                    }
                }
            }
        }

        return dupeArr;
    }

    //GETTERS
    /************************************
        SUBMODULE: getShip
        IMPORT: index (int)
        EXPORT: copy of ShipClass object at index in array
    ************************************/
    public ShipClass getShip( int index )
    {
        if ( shipArr[index] == null )
        {
            throw new IllegalArgumentException( "No ship at index " + index );
        }

        return shipArr[index].clone();
    }

    /************************************
        SUBMODULE: getShipNum
        IMPORT: none
        EXPORT: shipNum (int)
    ************************************/
    public int getShipNum()
    {
        return shipNum;
    }

    /************************************
        SUBMODULE: getSlots
        IMPORT: none
        EXPORT: int of number of ship slots left in array
    ************************************/
    public int getSlots()
    {
        return ( MAXSHIPS - shipNum );
    }

    /************************************
        SUBMODULE: slotsToString
        IMPORT: none
        EXPORT: String of ship capacity
    ************************************/
    public String slotsToString()
    {
        String slotsString = new String();
        if( getSlots() == 1 )
        {
            slotsString = ( "You have " + getSlots() + 
                " ship slot remaining (" + shipNum + "/" + MAXSHIPS + ")" );
        }
        else
        {
            slotsString = ( "You have " + getSlots() + 
                " ship slots remaining (" + shipNum + "/" + MAXSHIPS + ")" );
        }
        return slotsString;
    }

    /************************************
        SUBMODULE: equals
        IMPORT: inStorage
        EXPORT: equal (boolean)
        ASSERTION: Returns true if ship storage objects are equal
    ************************************/
    public boolean equals( ShipStorage inStorage )
    {
        boolean equal = true;
        int i = 0;
        if( shipNum != inStorage.getShipNum() )
        {
            equal = false;
        }
        while( equal && i < shipArr.length )
        {
            if( inStorage.getShip(i) == null )
            {
                equal = false;
            }
            else
            {
                equal = shipArr[i].equals( inStorage.getShip(i) );
                i++;
            }
        }
        return equal;
    }

}
