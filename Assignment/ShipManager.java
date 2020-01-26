/*******************************
    Author: Alec Maughan
    Purpose: Main method that runs the main menu and handles any last resort
        exceptions
    Date: 9/5/19
*******************************/
import java.util.*;
public class ShipManager
{
    public static void main( String[] args )
    {
        UI UserInterface = new UI();

        try
        {
            UserInterface.mainMenu();
        }
        catch( Exception e )
        {
            UserInterface.displayCrash();
        }

    }

}
