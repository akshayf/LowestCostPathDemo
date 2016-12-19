package com.akshay.lowestcostdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.akshay.lowestcostdemo.components.MainActivity;
import com.akshay.lowestcostdemo.components.OutputFragment;
import com.akshay.lowestcostdemo.utilities.OutputBin;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class FindLowCostPathTest extends ActivityInstrumentationTestCase2<MainActivity>{

    private MainActivity mainActivity;
    private OutputFragment outputFragment;

    public FindLowCostPathTest(){
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mainActivity = getActivity();

        startFragment();
    }

    private void startFragment() {

        outputFragment = new OutputFragment();
        outputFragment.setArguments(null);

        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(outputFragment, null);
        fragmentTransaction.commit();
    }

    @Test
    public void findLowCostPath1() {

        int [][] matrix = {{3,4,1,2,8,6},
                {6,1,8,2,7,4},
                {5,9,3,9,9,5},
                {8,4,1,3,2,6},
                {3,7,2,8,6,4}};

        OutputBin outputBin = outputFragment.calculatePath(matrix, 5, 6);
        assertEquals(outputBin.getTotalCost(), 16);
        assertEquals(outputBin.getPathSequence(), "1 2 3 4 4 5 ");
    }

    @Test
      public void findLowCostPath2() {

        int [][] matrix = {{3,4,1,2,8,6},
                {6,1,8,2,7,4},
                {5,9,3,9,9,5},
                {8,4,1,3,2,6},
                {3,7,2,1,2,3}};

        OutputBin outputBin = outputFragment.calculatePath(matrix,5,6);
        assertEquals(outputBin.getTotalCost(), 11);
        assertEquals(outputBin.getPathSequence(), "1 2 1 5 4 5 ");
    }

    @Test
    public void findLowCostPath3() {

        int [][] matrix = {{19,10,19,10,19},
                {21,23,20,19,12},
                {20,12,20,11,10}};

        OutputBin outputBin = outputFragment.calculatePath(matrix,3,5);
        assertEquals(outputBin.getTotalCost(), 48);
        assertEquals(outputBin.getPathSequence(), "1 1 1 ");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}