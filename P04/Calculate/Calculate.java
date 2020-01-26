/*
 * Author: Alec Maughan
 * Purpose: Calculates tha area of a rectangle in either cm^2 or m^2 with
 * input validation.
 * Date: 1/4/19
 */


import java.util.*;
import java.text.*;
public class Calculate
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        NumberFormat df = new DecimalFormat("#.###");
        char choice;
        int length, width;
        double area;

        System.out.println("Input the length in CM");
        length = sc.nextInt();
        if(length < 0)
        {
            System.out.println("Negative values not allowed, converting to positive");
            length = Math.abs( length);
        };

        System.out.println("Input the width in CM");
        width = sc.nextInt();        
        if(width < 0)
        {
            System.out.println("Negative values not allowed, converting to positive");
            width = Math.abs( width);
        };

        System.out.println("Enter the units, Input M for Metres, or C for Centimetres");
        choice = sc.next().charAt(0);

        area = ((double)length * (double)width);

        switch (choice)
        {
            case 'C': case 'c':
                System.out.println("The area is " + df.format(area) + " cm^2");
            break;
            case 'M': case 'm':
                area = area / 10000.0;
                System.out.println("The area is " + df.format(area) + " m^2.");
            break;
            default:
                System.out.println("You have entered a wrong letter!");
        }
    }
}
