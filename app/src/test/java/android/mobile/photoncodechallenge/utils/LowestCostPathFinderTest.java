package android.mobile.photoncodechallenge.utils;

import android.mobile.photoncodechallenge.model.Output;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Raj
 * on 8/8/2017.
 */
public class LowestCostPathFinderTest {

    LeastCostPath leastCostPath;

    @Test
    public void checkMatrixNormalFlow1(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{3, 4 ,1 ,2, 8, 6},
                {6 ,1 ,8 ,2, 7 ,4},
                {5 ,9 ,3 ,9 ,9 ,5},
                {8 ,4 ,1 ,3 ,2 ,6}
                ,{3 ,7 ,2 ,8 ,6 ,4}};

        Output output =  leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(16,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 4, 5},output.getPath()));
    }



    @Test
    public void checkMatrixNormal2Flow(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{3, 4 ,1 ,2, 8, 6},
                {6 ,1 ,8 ,2, 7 ,4},
                {5 ,9 ,3 ,9 ,9 ,5},
                {8 ,4 ,1 ,3 ,2 ,6}
                ,{3 ,7 ,2 ,1,2,3}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(11,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{1, 2, 1, 5, 4, 5},output.getPath()));

    }

    @Test
    public void check1X5Matrix(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{5 ,8 ,5 ,3, 5}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(26,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{1, 1, 1, 1, 1},output.getPath()));
    }

    @Test
    public void check5X1Matrix(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{5} ,{8} ,{5} ,{3}, {5}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(3,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{4},output.getPath()));
    }

   /* @Test
    public void checkNonNumericInput(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{5, 4, 'H'},
                {8, 'M' ,7},
                {5, 7, 5}};
        Output output = leastCostPath.findLeastCostPath(grid);
        Assert.assertTrue(output.isCompletePath());
        assertEquals(0,output.getLowestPathCost());
        //Assert.assertTrue(Arrays.equals(new int[]{1, 1, 1, 1, 1},output.getPath()));
    }*/

    @Test
    public void checkStartingValueAbove50(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{69, 10, 19, 10, 19},
                {51, 23, 20, 19, 12},
        {60, 12, 20, 11, 10}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertFalse(output.isCompletePath());
        assertEquals(0,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{},output.getPath()));
    }
    @Test
    public void checkOneValueAbove50(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{60, 3, 3, 6},
                {6, 3, 7, 9},
        {5, 6, 8, 3}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(14,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{3,2,1,3},output.getPath()));
    }

    @Test
    public void checkIsNegativeValue(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{6,3,-5,9},
                {-5,2,4,10},
                {3,-2,6,10},
                {6,-1,-2,10}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(0,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{2,3,4,1},output.getPath()));
    }

    @Test
    public void checkLargeNumberOfColumn(){
        leastCostPath=new LeastCostPath();
        int [][] grid={{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};
        Output output = leastCostPath.findLeastCostPath(grid);
        assertTrue(output.isCompletePath());
        assertEquals(20,output.getLowestPathCost());
        assertTrue(Arrays.equals(new int[]{1 ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},output.getPath()));
    }
}