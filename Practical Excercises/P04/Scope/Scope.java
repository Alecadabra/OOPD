/***************************************************************
* Author: Amy Affleck
* Date: 25/07/2018
* Purpose: To demonstrae scope within a program           
***************************************************************/

import java.util.*;

public class Scope
{ // CLASS SCOPE: This curly brace is the start of the class scope

    private static final int CONS = 1;

    public static void main(String[] args)
    {// MAIN SCOPE: this is the start of the scope for the submodule main
        
        Scanner sc = new Scanner(System.in);
        int var1 = 5, var2 = 10;
        int choice;
        int elseVar = 0;
 
        System.out.println("MAIN SCOPE:\t var1: " + var1 + "\t var2: " + var2);

        System.out.println("Select a scope:\n" +
                           "1: Access variable from within a nested scope \n" +
                           "2: Access variable in unconnected scope \n" +
                           "3: Unconnected scopes using same variable name\n" +
                           "4: Local scope variable of same name\n" 
                           );
        choice = sc.nextInt();

        if(choice == 1)
        {//IF SCOPE 1
            System.out.println("This if block is inside main, so var1 and " + 
                "var2 are accessible:");
            System.out.println("IF SCOPE:\t var1: " + var1 + "\t var2: " + var2);

        }//END IF SCOPE 1
        else if(choice == 2)
        {//ELSE IF SCOPE 2

            if(choice == 2)
            {//ELSEIF SCOPE A

                System.out.println("Enter a integer value for the variable elsevar");
                elseVar = sc.nextInt();
                System.out.println("ELSEIF SCOPE A:\t elseVar: " + elseVar);

            }//END ELSEIF SCOPE A
            else
            {//ELSEIF SCOPE B

                //elseVar is not declared within this scope so as far as this 
                //line is concerned it does not exist. 
                System.out.println("ELSEIF SCOPE B:\t elseVar: " + elseVar);

            }//END ELSEIF SCOPE B

        }//END ELSE IF SCOPE 2
        else if(choice == 3)
        {//ELSE IF SCOPE 3
            
            if(choice == 3)
            {//ELSEIF SCOPE C

                System.out.println("Enter a integer value for the variable elsevar");
                elseVar = sc.nextInt();
                System.out.println("ELSEIF SCOPE A:\t elseVar: " + elseVar);

            }//END ELSEIF SCOPE C
            else
            {//ELSEIF SCOPE D

                System.out.println("Enter a integer value for the variable elsevar");
                elseVar = sc.nextInt();

                System.out.println("ELSEIF SCOPE B:\t elseVar: " + elseVar);

            }//END ELSEIF SCOPE D

        }//END ELSE IF SCOPE 3
        else if (choice == 4)
        {//ELSE IF SCOPE 4

    
            System.out.println("There is a class constant CONS: " + CONS );

            if(choice == 4)
            {//ELSE IF SCOPE E
                int CONS; //declare a local varable called var1

                System.out.println("Please enter a value for CONS"); 
                CONS = sc.nextInt();
                
                //By default when you reference a variable if uses the version
                //declared in the closest scope.
                System.out.println("ELSE IF SCOPE A: CONS: " + CONS );

            }//END ELSE IF SCOPE E
            

        }//ELSE IF SCOPE 4

    }// END MAIN SCOPE

} // END CLASS SCOPE 
