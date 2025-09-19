import java.util.*;

public class Assignment_max {
    static final int N = 4;

    public static void main(String[] args) {
        int[][] p = {
            {240, 300, 225, 300},
            {360, 450, 250, 300},
            {144, 180, 150, 200},
            {240, 300, 225, 180}
        };

        // Convert profit to cost
        int[][] c = new int[N][N];
        int mx = maxVal(p);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                c[i][j] = mx - p[i][j];

        System.out.println("Cost Matrix:");
        print(c);

        // Row reduction
        for (int i = 0; i < N; i++) {
            int min = c[i][0];
            for (int j = 1; j < N; j++) 
                if (c[i][j] < min) min = c[i][j];
            for (int j = 0; j < N; j++) c[i][j] -= min;
        }
        System.out.println("After Row Reduction:");
        print(c);

        // Column reduction
        for (int j = 0; j < N; j++) {
            int min = c[0][j];
            for (int i = 1; i < N; i++) if (c[i][j] < min) min = c[i][j];
            for (int i = 0; i < N; i++) c[i][j] -= min;
        }
        System.out.println("After Column Reduction:");
        print(c);

        // Find best assignment
        int[] asg = new int[N];
        boolean[] used = new boolean[N];
        int best = solve(p, 0, used, asg, 0);

        // Show result
        System.out.println("Best Assignment:");
        for (int i = 0; i < N; i++)
            System.out.println((i + 1) + " -> " + (char) ('A' + asg[i]) + " -> " + p[i][asg[i]]);
        System.out.println("Max Profit = " + best);
    }

    static int solve(int[][] p, int row, boolean[] used, int[] asg, int sum) {
        if (row == N) return sum;
        int best = Integer.MIN_VALUE;
        for (int col = 0; col < N; col++) {
            if (!used[col]) {
                used[col] = true;
                asg[row] = col;
                best = Math.max(best, solve(p, row + 1, used, asg, sum + p[row][col]));
                used[col] = false;
            }
        }
        return best;
    }

    static int maxVal(int[][] m) {
        int mx = Integer.MIN_VALUE;
        for (int[] r : m) for (int v : r) if (v > mx) mx = v;
        return mx;
    }

    static void print(int[][] m) {
        System.out.println("    A   B   C   D");
        for (int i = 0; i < N; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < N; j++) System.out.printf("%4d", m[i][j]);
            System.out.println();
        }
    }
}













    // import java.util.*;

    // public class Assignment_max {
    //     public static void main(String[] args) {
    //         int[][] profit = {
    //             {240, 300, 225, 300},
    //             {360, 450, 250, 300},
    //             {144, 180, 150, 200},
    //             {240, 300, 225, 180}
    //         };

    //         int n = profit.length;
    //         int[] bestAssign = new int[n]; 
            
    //         boolean[] used = new boolean[n];
    //         int[] maxProfit = {0}; 
    //         findMaxProfit(profit, 0, used, new int[n], 0, bestAssign);

    //         System.out.println("\nFinal Best Assignment:");
    //         int total = 0;
    //         for (int i = 0; i < n; i++) {
    //             char product = (char) ('A' + bestAssign[i]);
    //             int p = profit[i][bestAssign[i]];
    //             System.out.println("Operator " + (i + 1) + " -> Product " + product + " -> ₹" + p);
    //             total += p;
    //         }
    //         System.out.println("Total Maximum Profit = ₹" + total);
    //     }

    //     // Try all combinations and store the best one
    //     static void findMaxProfit(int[][] profit, int row, boolean[] used, int[] assign, int current, int[] bestAssign) {
    //         int n = profit.length;
    //         if (row == n) {
    //             int sum = 0;
    //             for (int i = 0; i < n; i++) sum += profit[i][assign[i]];
    //             if (sum > getSum(bestAssign, profit)) {
    //                 System.arraycopy(assign, 0, bestAssign, 0, n);
    //             }
    //             return;
    //         }

    //         for (int j = 0; j < n; j++) {
    //             if (!used[j]) {
    //                 used[j] = true;
    //                 assign[row] = j;
    //                 findMaxProfit(profit, row + 1, used, assign, current + profit[row][j], bestAssign);
    //                 used[j] = false;
    //             }
    //         }
    //     }

    //     // Get sum of profits for an assignment
    //     static int getSum(int[] assign, int[][] profit) {
    //         int sum = 0;
    //         for (int i = 0; i < assign.length; i++) {
    //             sum += profit[i][assign[i]];
    //         }
    //         return sum;
    //     }
    // }
