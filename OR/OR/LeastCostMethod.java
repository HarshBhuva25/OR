public class LeastCostMethod {
    public static void main(String[] args){
        int[]supply ={500,300,200};
        int[]demand ={180,150,350,320}; 
        int cost[][]={
            {12,10,12,13},
            {7,11,8,14},
            {6,16,11,7}
        };
        int allocation[][]=new int[3][4];

        int i = 0, j = 0;
        while (i < supply.length && j < demand.length) {
            int allocation = Math.min(supply[i],demand[j]);
            allocation [i][j]=allocation;
            if(supply[i]==0){
                i++;
            }
            else if(demand[j]==0){
                j++;
            }
            supply[i] -=allocation;
            demand[j] -= allocation;
                
           
        }
    }
}
