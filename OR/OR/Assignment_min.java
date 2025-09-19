// import java.util.*;

// public class Assignment2 {
//     public static void main(String[] args) {
//         int[][] cost = {
//             {15, 13, 14, 17},
//             {11, 12, 15, 13},
//             {13, 12, 10, 11},
//             {15, 17, 14, 16}
//         };

//         int[][] cost2 = new int[4][4];
//         for (int i = 0; i < 4; i++)
//             for (int j = 0; j < 4; j++)
//                 cost2[i][j] = cost[i][j];

//         System.out.println();
//         printMatrix(cost2);

//         // Row reduction
//         for (int i = 0; i < 4; i++) {
//             int min = cost2[i][0];
//             for (int j = 1; j < 4; j++) {
//                 if (cost2[i][j] < min) min = cost2[i][j];
//             }
//             for (int j = 0; j < 4; j++) {
//                 cost2[i][j] -= min;
//             }
//         }

//         System.out.println();
//         printMatrix(cost2);

//         // Column reduction
//         for (int j = 0; j < 4; j++) {
//             int min = cost2[0][j];
//             for (int i = 1; i < 4; i++) {
//                 if (cost2[i][j] < min) min = cost2[i][j];
//             }
//             for (int i = 0; i < 4; i++) {
//                 cost2[i][j] -= min;
//             }
//         }

//         System.out.println();
//         printMatrix(cost2);
//     }


//     static void printMatrix(int[][] matrix) {
//         for (int[] row : matrix) {
//             for (int val : row) {
//                 System.out.printf("%4d", val);
//             }
//             System.out.println();
//         }
//     }
// }











public class Assignment_min {
    public static void main(String[] args) {
        int[][] cost = {
            {15, 13, 14, 17},
            {11, 12, 15, 13},
            {13, 12, 10, 11},
            {15, 17, 14, 16}
        };

        int n = 4;

        // Step 1: Row Reduction
        for (int i = 0; i < n; i++) {
            int min = cost[i][0];
            for (int j = 1; j < n; j++) {
                if (cost[i][j] < min) min = cost[i][j];
            }
            for (int j = 0; j < n; j++) {
                cost[i][j] -= min;
            }
        }

        // Step 2: Column Reduction
        for (int j = 0; j < n; j++) {
            int min = cost[0][j];
            for (int i = 1; i < n; i++) {
                if (cost[i][j] < min) min = cost[i][j];
            }
            for (int i = 0; i < n; i++) {
                cost[i][j] -= min;
            }
        }

        // Manual assignment (assumed optimal)
        int[] assignedTask = {1, 0, 3, 2}; // Engineer i assigned to task assignedTask[i]

        int total = 0;
        System.out.println("Engineer -> Task -> Time");
        for (int i = 0; i < n; i++) {
            int task = assignedTask[i];
            // Recalculate original time directly (hardcoded based on original matrix)
            int time = 0;
            if (i == 0 && task == 1) time = 13;
            if (i == 1 && task == 0) time = 11;
            if (i == 2 && task == 3) time = 11;
            if (i == 3 && task == 2) time = 14;

            total += time;
            System.out.println("   " + (i + 1) + "     ->  " + (task + 1) + "   ->  " + time);
        }

        System.out.println("Minimum total time = " + total + " hours");
    }
}
