import java.util.*;
public class DivisionChecker
{
    public static void main( String[]args)
    {
        String divisible = " ";
        int numOne, numTwo;
        Scanner sc = new Scanner(System.in);

        System.out.println("Input 1st number");
        numOne = sc.nextInt();
        System.out.println("Input 2nd number");
        numTwo = sc.nextInt();

if ( numTwo != 0)
{
    {
    if (( numOne % numTwo) == 0)
    {
        {
        divisible = "Divisible";
        }
    else
        {
        divisible = "Not Divisible";
        }
    }
    }
else
    {
    divisible = "Not Divisible";
    }
}
System.out.println( divisible);
    }
}
