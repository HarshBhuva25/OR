import java.util.*;

public class MulOfMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] row = new int[2][2];
        int[][] col = new int[2][2];
        int[][] result = new int[2][2];

        System.out.println("Enter elements of first matrix:");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
            row[i][j] = sc.nextInt();

        System.out.println("Enter elements of second matrix:");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
            col[i][j] = sc.nextInt();

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    result[i][j] += row[i][k] * col[k][j];

        System.out.println(" multiplication:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                System.out.print(result[i][j] + " ");
            System.out.println();
        }
    }
}

















// import java.util.Scanner;

// public class MulOfMatrix{
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

        
//         int[][] a = new int[2][2];//row
//         int[][] b = new int[2][2];//col
//         int[][] result = new int[2][2];

     
//         System.out.println("Enter first matrix:");
//         a[0][0] = sc.nextInt();
//         a[0][1] = sc.nextInt();
//         a[1][0] = sc.nextInt();
//         a[1][1] = sc.nextInt();

//         System.out.println("Enter second  matrix:");
//         b[0][0] = sc.nextInt();
//         b[0][1] = sc.nextInt();
//         b[1][0] = sc.nextInt();
//         b[1][1] = sc.nextInt();

     
//         result[0][0] = a[0][0]*b[0][0] + a[0][1]*b[1][0];
//         result[0][1] = a[0][0]*b[0][1] + a[0][1]*b[1][1];
//         result[1][0] = a[1][0]*b[0][0] + a[1][1]*b[1][0];
//         result[1][1] = a[1][0]*b[0][1] + a[1][1]*b[1][1];

       
//         System.out.println("Matrix Multiplication Result:");
//         System.out.println(result[0][0] + " " + result[0][1]);
//         System.out.println(result[1][0] + " " + result[1][1]);
//     }
// }
