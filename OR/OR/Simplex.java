//Z = 2x1 + 3x2 + x3

// 1x1 + 4x2 + x3         ≤ 24
// 3x1 + 1x2       + x4   ≤ 21
// 1x1 + 1x2            + x5 ≤ 9










public class Simplex {
    public static void main(String[] args) {
        int A[][] = {
            {1,4,1,0,0,24},
            {3,1,0,1,0,21},
            {1,1,0,0,1,9}
         };

        int B[] = {24,21,9}; 
        int Cj[] = {2,3,1,0,0};  
        int Cb[] = {0,0,0};  
        double Zj[] = new double[A[0].length]; 
        double ratio[] = new double[A.length];  
        
       
        int[] basicVars = {2, 3, 4}; 
        boolean optimal = false;
        int iteration = 1;
        
        System.out.println("Initial Simplex Tableau:");
        printTableau(A, B, Cj, Cb, Zj);
        
        while (!optimal) {
            System.out.println("\nIteration " + iteration++);
            
            calculateZj(A, Cb, Zj);
            double[] CjMinusZj = calculateCjMinusZj(Cj, Zj);
            
            int pivotCol = findPivotColumn(CjMinusZj);
            if (pivotCol == -1) {
                optimal = true;
                continue;
            }
            
            calculateRatios(A, B, ratio, pivotCol);
            int pivotRow = findPivotRow(ratio);
            if (pivotRow == -1) {
                System.out.println("Problem is unbounded!");
                return;
            }
            
            basicVars[pivotRow] = pivotCol;
            Cb[pivotRow] = Cj[pivotCol];
            
            performPivotOperation(A, B, pivotRow, pivotCol);
            
            System.out.println("\nAfter pivot operation:");
            printTableau(A, B, Cj, Cb, Zj);
        }
        
        printSolution(A, B, Cj, Cb, basicVars, Zj);
    }
    
    private static void calculateZj(int[][] A, int[] Cb, double[] Zj) {
        for (int j = 0; j < A[0].length; j++) {
            Zj[j] = 0;
            for (int i = 0; i < A.length; i++) {
                Zj[j] += Cb[i] * A[i][j];
            }
        }
    }
    
    private static double[] calculateCjMinusZj(int[] Cj, double[] Zj) {
        double[] diff = new double[Cj.length];
        for (int j = 0; j < Cj.length; j++) {
            diff[j] = Cj[j] - Zj[j];
        }
        return diff;
    }
    
    private static int findPivotColumn(double[] CjMinusZj) {
        int pivotCol = -1;
        double maxPositive = 0;
        for (int j = 0; j < CjMinusZj.length; j++) {
            if (CjMinusZj[j] > maxPositive) {
                maxPositive = CjMinusZj[j];
                pivotCol = j;
            }
        }
        return pivotCol;
    }
    
    private static void calculateRatios(int[][] A, int[] B, double[] ratio, int pivotCol) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][pivotCol] > 0) {
                ratio[i] = (double) B[i] / A[i][pivotCol];
            } else {
                ratio[i] = Double.POSITIVE_INFINITY;
            }
        }
    }
    
    private static int findPivotRow(double[] ratio) {
        int pivotRow = -1;
        double minRatio = Double.POSITIVE_INFINITY;
        for (int i = 0; i < ratio.length; i++) {
            if (ratio[i] < minRatio && ratio[i] > 0) {
                minRatio = ratio[i];
                pivotRow = i;
            }
        }
        return pivotRow;
    }
    
    private static void performPivotOperation(int[][] A, int[] B, int pivotRow, int pivotCol) {
        int pivotElement = A[pivotRow][pivotCol];
        for (int j = 0; j < A[0].length; j++) {
            A[pivotRow][j] /= pivotElement;
        }
        B[pivotRow] /= pivotElement;
        
        for (int i = 0; i < A.length; i++) {
            if (i != pivotRow) {
                int factor = A[i][pivotCol];
                for (int j = 0; j < A[0].length; j++) {
                    A[i][j] = A[i][j] - factor * A[pivotRow][j];
                }
                B[i] = B[i] - factor * B[pivotRow];
            }
        }
    }
    
    private static void printTableau(int[][] A, int[] B, int[] Cj, int[] Cb, double[] Zj) {
        System.out.print("Cj\t");
        for (int j : Cj) {
            System.out.print(j + "\t");
        }
        System.out.println();
        
        for (int i = 0; i < A.length; i++) {
            System.out.print(Cb[i] + "\t");
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.println(B[i]);
        }
        
        System.out.print("Zj\t");
        for (double z : Zj) {
            System.out.printf("%.2f\t", z);
        }
        System.out.println();
        
        System.out.print("Cj-Zj\t");
        for (int j = 0; j < Cj.length; j++) {
            System.out.printf("%.2f\t", Cj[j] - Zj[j]);
        }
        System.out.println();
    }
    
    private static void printSolution(int[][] A, int[] B, int[] Cj, int[] Cb, int[] basicVars, double[] Zj) {
        System.out.println("\nOptimal Solution:");
        double objectiveValue = 0;
        double[] values = new double[Cj.length];
        
        for (int i = 0; i < basicVars.length; i++) {
            values[basicVars[i]] = B[i];
            objectiveValue += Cb[i] * B[i];
        }
        
        System.out.println("x1 = " + values[0]);
        System.out.println("x2 = " + values[1]);
        
        for (int i = 2; i < values.length; i++) {
            System.out.println("s" + (i-1) + " = " + values[i]);
        }
        
        System.out.println("Maximum value of Z = " + objectiveValue);
    }
}