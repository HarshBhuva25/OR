// public class MinTime {
//   public static void main(String[] args) 
//   {
//     int[] tasks = {10, 20, 30};
//     int total = 0;

//     for (int t : tasks) 
//     {
//       total += t;
//     }

//     int half = total / 2;
//     boolean[] dp = new boolean[half + 1];
//     dp[0] = true;

//     for (int t : tasks) {
//       for (int j = half; j >= t; j--) {
//         dp[j] = dp[j] || dp[j - t];
//       }
//     }

//     int best = 0;
//     for (int i = half; i >= 0; i--) {
//       if (dp[i]) {
//         best = i;
//         break;
//       }
//     }

//     int minTime = Math.max(best, total - best);
//     System.out.println("Minimum time required: " + minTime);
//   }
// }


import java.util.Scanner;

public class MinTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("task:");
        int n = scanner.nextInt();

        int[] tasks = new int[n];
        System.out.println("Enter the task durations:");

        int total = 0;
        for (int i = 0; i < n; i++) {
            tasks[i] = scanner.nextInt();
            total += tasks[i];
        }

        int half = total / 2;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        for (int t : tasks) {
            for (int j = half; j >= t; j--) {
                if (dp[j - t]) {
                    dp[j] = true;
                }
            }
        }

        int best = 0;
        for (int i = half; i >= 0; i--) {
            if (dp[i]) {
                best = i;
                break;
            }
        }

        int minTime = Math.max(best, total - best);
        System.out.println("Minimum time required: " + minTime);

        scanner.close();
    }
}
