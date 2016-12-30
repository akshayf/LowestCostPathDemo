package com.akshay.lowestcostdemo.utilities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculateLowestPathTest {

    private CalculateLowestPath calculateLP;

    @Before
    public void setUp() throws Exception {

        calculateLP = new CalculateLowestPath();
    }

    @Test
    public void findTheLowestCostPathForMatrix_1(){

        int [][] matrix = {{3,4,1,2,8,6},
                {6,1,8,2,7,4},
                {5,9,3,9,9,5},
                {8,4,1,3,2,6},
                {3,7,2,8,6,4}};

        calculateLP.setMatrixDetails(matrix, 5, 6);
        UtilityBin utilityBin = calculateLP.findLowestCostPath();
        Assert.assertEquals("1 2 3 4 4 5", utilityBin.getPathSequence());
        Assert.assertEquals(16, utilityBin.getTotalCost());
        Assert.assertEquals(LCPAppConstants.PATH_MAID_YES, utilityBin.getPathMaid());
    }

    @Test
    public void findTheLowestCostPathForMatrix_2(){

        int [][] matrix = {{3,4,1,2,8,6},
                {6,1,8,2,7,4},
                {5,9,3,9,9,5},
                {8,4,1,3,2,6},
                {3,7,2,1,2,3}};

        calculateLP.setMatrixDetails(matrix, 5, 6);
        UtilityBin utilityBin = calculateLP.findLowestCostPath();
        Assert.assertEquals("1 2 1 5 4 5", utilityBin.getPathSequence());
        Assert.assertEquals(11, utilityBin.getTotalCost());
        Assert.assertEquals(LCPAppConstants.PATH_MAID_YES, utilityBin.getPathMaid());
    }

    @Test
    public void findTheLowestCostPathForMatrix_3(){

        int [][] matrix = {{19,10,19,10,19},
                {21,23,20,19,12},
                {20,12,20,11,10}};

        calculateLP.setMatrixDetails(matrix, 3, 5);
        UtilityBin utilityBin = calculateLP.findLowestCostPath();
        Assert.assertEquals("1 1 1", utilityBin.getPathSequence());
        Assert.assertEquals(48, utilityBin.getTotalCost());
        Assert.assertEquals(LCPAppConstants.PATH_MAID_NO, utilityBin.getPathMaid());
    }

    @After
    public void tearDown() throws Exception {

    }
}