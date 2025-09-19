import java.util.Scanner;

public class SaddlePoint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input matrix dimensions
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        // Input matrix elements
        System.out.println("Enter matrix elements:");
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Finding the saddle point
        boolean found = false;
        for(int i = 0; i < rows; i++) {
            // Find the minimum element in the current row
            int min = matrix[i][0];
            int colIndex = 0;
            for(int j = 1; j < cols; j++) {
                if(matrix[i][j] < min) {
                    min = matrix[i][j];
                    colIndex = j;
                }
            }

            // Check if this minimum element is the largest in its column
            int k;
            for(k = 0; k < rows; k++) {
                if(matrix[k][colIndex] > min) {
                    break;
                }
            }

            if(k == rows) {
                System.out.println("Saddle point found at row " + i + ", column " + colIndex + ": " + min);
                found = true;
            }
        }

        if(!found) {
            System.out.println("No saddle point found in the matrix.");
        }

        sc.close();
    }
}
