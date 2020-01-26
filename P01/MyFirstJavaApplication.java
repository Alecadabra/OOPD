import java.util.*;
public class MyFirstJavaApplication
{
   public static void main( String [] args)
   {
     int numOne, numTwo, difference;
     Scanner sc = new Scanner(System.in);

     System.out.println("Enter 1st number");
     numOne = sc.nextInt(); 
     System.out.println("Enter 2nd number");
     numTwo = sc.nextInt();

     difference = numOne - numTwo;
     difference = Math.abs(difference);

     System.out.println("Difference is " + difference);
   }
}
