import java.util.*;

public class LeastCostMethod {
    
    public static void main(String[] args) {
        
        int[] supply = {500, 300, 200};

        
        int[] demand = {180, 150, 350, 320};

        
        int[][] cost = {

            {12, 10, 12, 13},
            {7, 11, 8, 14},
            {6, 16, 11, 7}
        };

        int[][] allocation = new int[3][4];

        
        solveLeastCostMethod(supply.clone(), demand.clone(), cost, allocation);

        
        System.out.println("Allocation Table (Least Cost Method):");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(allocation[r][c] + " ");
            }
            System.out.println();
        }

        int totalCost = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                totalCost += allocation[r][c] * cost[r][c];
            }
        }

        System.out.println("Total Transportation Cost: " + totalCost);
    }

    
    public static void solveLeastCostMethod(int[] supply, int[] demand, int[][] cost, int[][] allocation) {
        int rows = supply.length;
        int cols = demand.length;
        
        
        while (true) {
           
            int minCost = Integer.MAX_VALUE;
            int minRow = -1, minCol = -1;
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                
                    if (supply[i] > 0 && demand[j] > 0) {
                        if (cost[i][j] < minCost) {
                            minCost = cost[i][j];
                            minRow = i;
                            minCol = j;
                        }
                    }
                }
            }
            
            // If no cell found, we're done
            if (minRow == -1) {
                break;
            }
            
            // Allocate maximum possible amount at minimum cost cell
            int allocated = Math.min(supply[minRow], demand[minCol]);
            allocation[minRow][minCol] = allocated;
            

            supply[minRow] -= allocated;
            demand[minCol] -= allocated;
        }
    }
}
