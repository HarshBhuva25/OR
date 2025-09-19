public class VAM {
    public static void main(String[] args) {
        int[] supply = {500, 300, 200};
        int[] demand = {180, 150, 350, 320};

        int[][] cost = {
            {12, 10, 12, 13},
            {7, 11, 8, 14},
            {6, 16, 11, 7}
        };

        int m = supply.length;
        int n = demand.length;

        int[][] allocation = new int[m][n];

        boolean[] rowDone = new boolean[m];
        boolean[] colDone = new boolean[n];

        while (true) {
            // calculate row penalties
            int[] rowPenalty = new int[m];
            for (int i = 0; i < m; i++) {
                if (rowDone[i]) continue;
                int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (colDone[j]) continue;
                    if (cost[i][j] < min1) {
                        min2 = min1;
                        min1 = cost[i][j];
                    } else if (cost[i][j] < min2) {
                        min2 = cost[i][j];
                    }
                }
                rowPenalty[i] = (min2 == Integer.MAX_VALUE) ? min1 : (min2 - min1);
            }

            // calculate column penalties
            int[] colPenalty = new int[n];
            for (int j = 0; j < n; j++) {
                if (colDone[j]) continue;
                int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
                for (int i = 0; i < m; i++) {
                    if (rowDone[i]) continue;
                    if (cost[i][j] < min1) {
                        min2 = min1;
                        min1 = cost[i][j];
                    } else if (cost[i][j] < min2) {
                        min2 = cost[i][j];
                    }
                }
                colPenalty[j] = (min2 == Integer.MAX_VALUE) ? min1 : (min2 - min1);
            }

            // find maximum penalty
            int maxPenalty = -1;
            boolean isRow = true;
            int idx = -1;

            for (int i = 0; i < m; i++) {
                if (!rowDone[i] && rowPenalty[i] > maxPenalty) {
                    maxPenalty = rowPenalty[i];
                    isRow = true;
                    idx = i;
                }
            }
            for (int j = 0; j < n; j++) {
                if (!colDone[j] && colPenalty[j] > maxPenalty) {
                    maxPenalty = colPenalty[j];
                    isRow = false;
                    idx = j;
                }
            }

            if (idx == -1) break; // done

            // allocate in the cell with minimum cost in that row/column
            int minCost = Integer.MAX_VALUE;
            int x = -1, y = -1;
            if (isRow) {
                for (int j = 0; j < n; j++) {
                    if (!colDone[j] && cost[idx][j] < minCost) {
                        minCost = cost[idx][j];
                        x = idx;
                        y = j;
                    }
                }
            } else {
                for (int i = 0; i < m; i++) {
                    if (!rowDone[i] && cost[i][idx] < minCost) {
                        minCost = cost[i][idx];
                        x = i;
                        y = idx;
                    }
                }
            }

            // allocate as much as possible
            int alloc = Math.min(supply[x], demand[y]);
            allocation[x][y] = alloc;

            supply[x] -= alloc;
            demand[y] -= alloc;

            if (supply[x] == 0) rowDone[x] = true;
            if (demand[y] == 0) colDone[y] = true;
        }

        // print result
        int totalCost = 0;
        System.out.println("Allocation Table (Vogel’s Approximation Method):");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d", allocation[i][j]);
                totalCost += allocation[i][j] * cost[i][j];
            }
            System.out.println();
        }
        System.out.println("Total Transportation Cost: ₹" + totalCost);
    }
}
