//Z=3x1+2x2 ,x1+x2<=4,x1,x2>=0


import java.util.*;
public class Equation {
    public static void main(String[] args) {
        int Z = 0;
        int X1 = 0;
        int X2 = 0;

        for (int x1 = 0; x1 <= 4; x1++) {
            for (int x2 = 0; x2 <= 4; x2++) {
                if (x1 + x2 <= 4) {
                    int z = 3 * x1 + 2 * x2;

                    System.out.println("x1 = " + x1 + ", x2 = " + x2 + ", Z = " + z);

                    if (z > Z) {
                        Z = z;
                        X1 = x1;
                        X2 = x2;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("Maximum Z = " + Z);
        System.out.println("possible values: x1 = " + X1 + ", x2 = " + X2);
    }
}




   



// public class Equation {
//     public static void main(String[] args) {
//         // Z = 3x1 + 2x2
//         // Condition: x1 + x2 <= 4, and x1, x2 >= 0

//         int maxZ = 0;     // to store maximum value of Z
//         int bestX1 = 0;   // to store best x1
//         int bestX2 = 0;   // to store best x2

//         // Try all values of x1 from 0 to 4
//         for (int x1 = 0; x1 <= 4; x1++) {
//             // Try all values of x2 from 0 to 4
//             for (int x2 = 0; x2 <= 4; x2++) {

//                 // Check if x1 + x2 is less than or equal to 4
//                 if (x1 + x2 <= 4) {
//                     // Calculate Z
//                     int Z = 3 * x1 + 2 * x2;

//                     // Show this result
//                     System.out.println("x1 = " + x1 + ", x2 = " + x2 + ", Z = " + Z);

//                     // Check if this Z is the biggest so far
//                     if (Z > maxZ) {
//                         maxZ = Z;
//                         bestX1 = x1;
//                         bestX2 = x2;
//                     }
//                 }
//             }
//         }

//         // Show final answer
//         System.out.println();
//         System.out.println("Maximum Z = " + maxZ);
//         System.out.println("Best values: x1 = " + bestX1 + ", x2 = " + bestX2);
//     }
// }
