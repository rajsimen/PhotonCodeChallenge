package android.mobile.photoncodechallenge.utils;

import android.mobile.photoncodechallenge.model.Output;

/**
 * Created by JMI Guest on 8/8/2017.
 */

public class LowestCostPathFinder {

    private Output output;
    private  boolean isCompleted=true;

    public Output findLowestCostPath(int[][] grid) {
        //minCost1(grid,0,0);
        int lowestPathCost=minCost1(grid,0,0);

        output = new Output(isCompletePath(), lowestPathCost, new int[]{1, 2, 3, 4, 4, 5});


        return output;
    }


    private int minCost1(int cost[][], int m, int n)
    {
        int row =cost.length;
        int isMoreGreater50=0;
        int minX=cost[m][n];
        int column=0;
        if (row>0){
            column =cost[0].length;
        }
        if (column==1){
            for (int rowInteger=0;rowInteger<row;rowInteger++){

                if (cost[rowInteger][column-1]<minX){
                    // will return 0 the Grid Of XxY if no row
                    minX=cost[rowInteger][column-1];
                }
            }
            return minX;

        }
        if (column>0){
            for (int rowInteger=0;rowInteger<row;rowInteger++){
                if (cost[rowInteger][column-1]>='A' && cost[rowInteger][column-1]<='Z'){
                    // will return 0 the Grid Of XxY if character present
                    return 0;
                }

                if (cost[rowInteger][0]>50){
                    // will return 0 the Grid Of XxY if 1st column all elements >50
                    isMoreGreater50++;
                   // return 0;
                }
            }
        }
        if (isMoreGreater50>1){
            return 0;
        }

        // will return the Grid Of XxY

        if (n < 0 || m < 0)
            return Integer.MAX_VALUE;
        else if ((m == row-1 && n == column-1)|| n+1>=column)
            return cost[m][n];
        else
            return cost[m][n] + min( minCost1(cost, m+1>=row?row-1:m+1,n+1),
                    minCost1(cost, m,n+1),
                    minCost1(cost, m-1>=0?m-1:row-1,n+1));
    }

    private boolean isCompletePath(){
        return isCompleted;
    }

    private  int min(int x, int y, int z)
    {
        if (x < y) {
            return (x < z) ? x : z;
        }
        else {
            return (y < z) ? y : z;
        }
    }
}
