public class NorthWest {

    public static void main(String[] args) {
        int[] supply = { 500, 300, 200 };

        int[] demand = { 180, 150, 350, 320 };

        int[][] cost = {
                { 12, 10, 12, 13 },
                { 7, 11, 8, 14 },
                { 6, 16, 11, 7 }
        };

     
        int[][] allocation = new int[3][4];

        int i = 0, j = 0;

    
        while (i < supply.length && j < demand.length) {
            int allocated = Math.min(supply[i], demand[j]);
            allocation[i][j] = allocated;

            supply[i] -= allocated;
            demand[j] -= allocated;

          
            if (supply[i] == 0) {
                i++;
            } if (demand[j] == 0) {
                j++;
            }
        }

        System.out.println("Allocation Table (North-West Corner Method):");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.printf("%5d", allocation[r][c]);
            }
            System.out.println();
        }

      
        int totalCost = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                totalCost += allocation[r][c] * cost[r][c];
            }
        }

        System.out.println("Total Transportation Cost: ₹" + totalCost);
    }
}