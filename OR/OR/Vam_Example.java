import java.util.*;

public class Vam_Example
{
    public static void main(String[] args)
     {
        int[] supply = {500, 300, 200};
        int[] demand = {180, 150, 350, 320};

        int[][] cost = 
        {
            {12, 10, 12, 13},
            {7, 11, 8, 14},
            {6, 16, 11, 7}
        };

        int m = supply.length;
        int n = demand.length;

        int[][] allocation = new int[m][n];
        boolean[] rowDone = new boolean[m];
        boolean[] colDone = new boolean[n];

        int totalCost = 0;

        while (true)
        {
            int[] rowPenalty = new int[m];
            for (int i = 0; i < m; i++)
            {
                if (rowDone[i])
                 {
                    rowPenalty[i] = -1;
                    continue;
                }
                int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) 
                {
                    if (!colDone[j])
                     {
                        if (cost[i][j] < min1)
                         {
                            min2 = min1;
                            min1 = cost[i][j];
                        } else if (cost[i][j] < min2) 
                        {
                            min2 = cost[i][j];
                        }
                    }
                }
                rowPenalty[i] = min2 - min1;
            }

            int[] colPenalty = new int[n];
            for (int j = 0; j < n; j++) 
            {
                if (colDone[j])
                 {
                    colPenalty[j] = -1;
                    continue;
                }
                int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
                for (int i = 0; i < m; i++)
                 {
                    if (!rowDone[i]) 
                    {
                        if (cost[i][j] < min1)
                         {
                            min2 = min1;
                            min1 = cost[i][j];
                        } else if (cost[i][j] < min2) 
                        {
                            min2 = cost[i][j];
                        }
                    }
                }
                colPenalty[j] = min2 - min1;
            }

            int maxPenalty = -1;
            boolean isRow = true;
            int index = -1;

            for (int i = 0; i < m; i++)
             {
                if (rowPenalty[i] > maxPenalty) 
                {
                    maxPenalty = rowPenalty[i];
                    isRow = true;
                    index = i;
                }
            }

            for (int j = 0; j < n; j++) 
            {
                if (colPenalty[j] > maxPenalty) 
                {
                    maxPenalty = colPenalty[j];
                    isRow = false;
                    index = j;
                }
            }

            if (maxPenalty == -1)
                break; 

            int minCost = Integer.MAX_VALUE;
            int minI = -1, minJ = -1;

            if (isRow)
             {
                for (int j = 0; j < n; j++)
                 {
                    if (!colDone[j] && cost[index][j] < minCost) 
                    {
                        minCost = cost[index][j];
                        minI = index;
                        minJ = j;
                    }
                }
            } else 
            {
                for (int i = 0; i < m; i++) 
                {
                    if (!rowDone[i] && cost[i][index] < minCost) 
                    {
                        minCost = cost[i][index];
                        minI = i;
                        minJ = index;
                    }
                }
            }

            int qty = Math.min(supply[minI], demand[minJ]);
            allocation[minI][minJ] = qty;
            supply[minI] -= qty;
            demand[minJ] -= qty;
            totalCost += qty * cost[minI][minJ];

            if (supply[minI] == 0)
                rowDone[minI] = true;
            if (demand[minJ] == 0)
                colDone[minJ] = true;
        }

      

        System.out.println("Total Transportation Cost: " + totalCost);
    }
}