import java.util.*;

public class Saddle {
      public static void main(String[] args) {
        int[][] matrix = {
            {-5, 2, 0},
            {5, 6, 4},
            {4, 0, 2}
        };
            int n = matrix.length;
            int m = matrix[0].length;

            int[] rowMinima = new int[n];
            int[] colMaxima = new int[m];

     

            //row minima
            for (int i = 0; i < n; i++) {
            int min = matrix[i][0];
                 for (int j=1; j<m; j++) {
                    if (matrix[i][j] < min) {
                        min = matrix[i][j];
                    }
            }
            rowMinima[i] = min;
        }


         //col maxima
          for(int j=0 ;j<n; j++){
            int max=matrix[0][j];
               for(int i=1 ; i<n ; i++){
                   if(matrix[i][j]>max){
                     max=matrix[i][j];
                   }
               }
                 colMaxima[j]=max;
          }
        
          //maximin
          int maximin=rowMinima[0];
          int row=0;
           for(int i=1;i<n;i++){
              if(rowMinima[i]>maximin){
                  maximin=rowMinima[i];
                  row=i;

              }
           }

           //minimax
           int minimax=colMaxima[0];
           int col=0;
           for(int j=1;j<m;j++){
            if(colMaxima[j]<minimax){
                 minimax=colMaxima[j];
                 col=j;

              }
           }


            System.out.println(" Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

           if(maximin == minimax){
                System.out.println("saddle point found...");
                System.err.println("SADDLE POINT" + maximin);

           }
           else{
            System.err.println("no saddle point found...");
           }
           
           


            
            
        }
    }