package com.akshay.lowestcostdemo;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.akshay.lowestcostdemo.components.LowCostPathActivity;
import com.akshay.lowestcostdemo.components.ShowLCPFragment;
import com.akshay.lowestcostdemo.utilities.UtilityBin;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
@RunWith(AndroidJUnit4.class)
@SmallTest
public class FindLowCostPathTest extends ActivityInstrumentationTestCase2<LowCostPathActivity>{

    private LowCostPathActivity lowCostPathActivity;
    private ShowLCPFragment showLCPFragment;

    public FindLowCostPathTest(){
        super(LowCostPathActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        lowCostPathActivity = getActivity();

        showLCPFragment = new ShowLCPFragment();
    }

    @Test
    public void findLowCostPath_1() {

        int [][] matrix = {{3,4,1,2,8,6},
                {6,1,8,2,7,4},
                {5,9,3,9,9,5},
                {8,4,1,3,2,6},
                {3,7,2,8,6,4}};

        UtilityBin utilityBin = showLCPFragment.calculatePath(matrix, 5, 6);
        assertEquals(utilityBin.getTotalCost(), 16);
        assertEquals(utilityBin.getPathSequence(), "1 2 3 4 4 5");
    }

    @Test
      public void findLowCostPath_2() {

        int [][] matrix = {{3,4,1,2,8,6},
                {6,1,8,2,7,4},
                {5,9,3,9,9,5},
                {8,4,1,3,2,6},
                {3,7,2,1,2,3}};

        UtilityBin utilityBin = showLCPFragment.calculatePath(matrix,5,6);
        assertEquals(utilityBin.getTotalCost(), 11);
        assertEquals(utilityBin.getPathSequence(), "1 2 1 5 4 5");
    }

    @Test
    public void findLowCostPath_3() {

        int [][] matrix = {{19,10,19,10,19},
                {21,23,20,19,12},
                {20,12,20,11,10}};

        UtilityBin utilityBin = showLCPFragment.calculatePath(matrix,3,5);
        assertEquals(utilityBin.getTotalCost(), 48);
        assertEquals(utilityBin.getPathSequence(), "1 1 1");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        showLCPFragment = null;
    }
}*/
