public class LeastCost {
    public static void main(String[] args) {
        int[] supply = {500, 300, 200};
        int[] demand = {180, 150, 350, 320};

        int[][] cost = {
            {12, 10, 12, 13},
            {7, 11, 8, 14},
            {6, 16, 11, 7}
        };

        int[][] result = new int[3][4];
        boolean[][] used = new boolean[3][4];
        int total = 0;

        while (true) {
            int min = Integer.MAX_VALUE;
            int r = -1, c = -1;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!used[i][j] && cost[i][j] < min) {
                        min = cost[i][j];
                        r = i;
                        c = j;
                    }
                }
            }

            if (r == -1) break; // All done

            int x = Math.min(supply[r], demand[c]);
            result[r][c] = x;
            total += x * cost[r][c];

            supply[r] -= x;
            demand[c] -= x;

            if (supply[r] == 0) for (int j = 0; j < 4; j++) used[r][j] = true;
            if (demand[c] == 0) for (int i = 0; i < 3; i++) used[i][c] = true;
        }

        // Output
        System.out.println("Allocation:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%4d ", result[i][j]);
            }
            System.out.println();
        }

        System.out.println("Total Cost = " + total);
    }
}






