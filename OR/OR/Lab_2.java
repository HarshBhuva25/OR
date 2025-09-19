public class Lab_2 {
    public static void main(String[] args) {
        int maxZ = 0;
        int bestX1 = 0;
        int bestX2 = 0;

            for (int x1 = 0; x1 <= 10; x1++) {
            for (int x2 = 0; x2 <= 10; x2++) {
               
                if (x1 + x2 <= 4) {
                    int z = 3 * x1 + 2 * x2;
                    if (z > maxZ) {
                        maxZ = z;
                        bestX1 = x1;
                        bestX2 = x2;
                    }
                }
            }
        }
        System.out.println("Maximum Z = " + maxZ);
        System.out.println("At x1 = " + bestX1 + ", x2 = " + bestX2);
    }
}