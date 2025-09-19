public class Lab_3 {

    public static void main(String[] args) {
        int [] supply = {500, 300, 200};
        int [] demand = {180, 150, 350, 320};
        int [][] cost = {
            {12, 10, 12, 13},
            {7, 11, 8, 14},
            {6, 16, 11, 7}
        };
        int m = supply.length;
        int n = demand.length;
        int [][] allocation = new int[m][n];
        boolean [] rowDone = new boolean[m];
        boolean [] colDone = new boolean[n];
        totalCost = 0;


    }
}