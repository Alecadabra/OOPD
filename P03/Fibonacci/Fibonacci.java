import java.util.*;
public class Fibonacci
{
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int select, number;
        int[] fibonacci = new int[19];
        String menu, error, strMenu;

        do
        {
            menu = ("Select and option:\n1. Input a number\n2. Exit");
            error = ("Error: Inalid selection.\n");
            strMenu = menu;
            do
            {
                System.out.println(strMenu);
                select = sc.nextInt();
                strMenu = error + menu;
            }
            while ((select < 1) || (select > 2));
            // this loops validates the input and loops with an erro message
        
            if ( select == 1)
            {
                menu = ("Input a number between 0 and 20");
                error = ("Error: Invalid input.\n");
                strMenu = menu;
                do
                {
                    System.out.println(strMenu);
                    number = sc.nextInt();
                    strMenu = error + menu;
                }
                while ((number <= 0) || (number >= 20));
                //validates input and loops with an error message
            
                fibonacci[0] = 0;
                fibonacci[1] = 1;
                // hardcodes the first two enties in the sequence

                for (int i = 2; i < 19; i++)
                {
                    fibonacci[i] = (fibonacci[i - 1]) + (fibonacci[i - 2]);
                };
                // claculates all entries in the sequence

                System.out.println(fibonacci[number - 1]);
                // outputs the chosen entry
            };
        }
        while ( select != 2);
        // loops the entire thing if the exit condition is not met
    }
}
