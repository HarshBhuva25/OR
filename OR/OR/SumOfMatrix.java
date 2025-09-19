import java.util.*;

public class SumOfMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter row: ");
        int row = sc.nextInt();
        System.out.print("Enter column: ");
        int col = sc.nextInt();

        int[][] mat1 = new int[row][col];
        int[][] mat2 = new int[row][col];
        int[][] sum = new int[row][col];

        System.out.println("Enter first matrix:");
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mat1[i][j] = sc.nextInt();

        System.out.println("Enter second matrix:");
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mat2[i][j] = sc.nextInt();

        System.out.println("Sum of matrices:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum[i][j] = mat1[i][j] + mat2[i][j];
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }
}
