public class Game {
    public static void main(String[] args) {
        // Payoff matrix from your example
        int[][] matrix = {
            {-2, 4, 2},
            {3, 5, 4},
            {2, 3, 4}
        };

        int n = matrix.length;     // rows
        int m = matrix[0].length;  // columns

        int[] rowMinima = new int[n];
        int[] colMaxima = new int[m];

        // Step 1: Find row minima
        for (int i = 0; i < n; i++) {
            int min = matrix[i][0];
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
            rowMinima[i] = min;
        }

        // Step 2: Find column maxima
        for (int j = 0; j < m; j++) {
            int max = matrix[0][j];
            for (int i = 1; i < n; i++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            colMaxima[j] = max;
        }

        // Step 3: Find maximin and minimax
        int maximin = rowMinima[0];
        int rowIndex = 0;
        for (int i = 1; i < n; i++) {
            if (rowMinima[i] > maximin) {
                maximin = rowMinima[i];
                rowIndex = i;
            }
        }

        int minimax = colMaxima[0];
        int colIndex = 0;
        for (int j = 1; j < m; j++) {
            if (colMaxima[j] < minimax) {
                minimax = colMaxima[j];
                colIndex = j;
            }
        }

        // Step 4: Check if saddle point exists
        if (maximin == minimax) {
            System.out.println("Saddle Point exists!");
            System.out.println("Value of the Game: " + maximin);
            System.out.println("Saddle Point position: Row " + (rowIndex+1) + ", Column " + (colIndex+1));
        } else {
            System.out.println("No Saddle Point (solve with Mixed Strategy).");
        }
    }
}