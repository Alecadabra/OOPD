import java.util.*;
public class Factorial
{
    public static void main( String[]args)
    {
        int num, ii, answer = 1;
        Scanner sc = new Scanner(System.in);

System.out.println("Enter number");
num = sc.nextInt();

for( ii = 1; ii <= num; ii++)
{
    answer = answer * ii;
}

if (num < 0)
{
    System.out.println("Invalid input");
}
else 
{
    System.out.println( num + "! = " + answer);
}

    }
}
