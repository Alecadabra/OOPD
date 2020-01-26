import java.util.*;
public class MatrixMultiplication
{
    public static void main( String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int iLength, rLength, jLength;
        String fileOne, fileTwo, output = new String();
        int[][] matrixOne, matrixTwo;

        fileOne = ArrayReader.getFileName("Enter file name of matrix A");
        fileTwo = ArrayReader.getFileName("Enter file name of matrix B");
        // gets an input of the filename of each matrix .txt

        matrixOne = ArrayReader.readArray(fileOne);
        matrixTwo = ArrayReader.readArray(fileTwo);
        // assigns matrixOne & matrixTwo to 2D arrays of the imported matricies

        if ((matrixOne == null) || (matrixTwo == null))
        {
            System.out.println("Invalid input files");
        // outputs an error if the matrix files are invalid
        }
        else if (matrixOne[0].length == matrixTwo.length)
        //checks if no. of columns of mA == no. of rows of mB.
        {
            int[][] matrixThree = new int[(matrixOne.length)][matrixTwo[0].length];
            // creates mC's array. it's size is mA's rows by mB's columns
            iLength = matrixOne.length;
            rLength = matrixTwo.length;
            jLength = matrixTwo[0].length;
            // gets the length of the different dimensions for the for loops
            int i = 0, r = 0, j = 0;
            
            for (i = 0; i < iLength; i++)
            {
                for (r = 0; r < rLength; r++)
                {
                    for (j = 0; j < iLength; j++)
                    {
                        matrixThree[i][j] = (matrixOne[i][r] * matrixTwo[r][j]);
                    };
                };
            };
            for (int ii = 0; ii < iLength; ii++)
            {
                for (int jj = 0; jj < jLength; jj++)
                {
                    if (jj == (jLength - 1))
                    {
                        output = output + matrixThree[ii][jj] + "\n";
                    // makes a new line if we are at the last entry in the row
                    }
                    else
                    {
                        output = output + matrixThree[ii][jj] + ", ";
                    // adds a comma and space after each entry
                    }
                }
            }
            System.out.println( fileOne + " * " + fileTwo + " =\n" + output);
        }
        else
        {
            System.out.println("Error: These matricies cannot be multiplied");
        };
    }
}
