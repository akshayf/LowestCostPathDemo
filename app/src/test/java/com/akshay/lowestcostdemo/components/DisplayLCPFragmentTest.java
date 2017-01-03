package com.akshay.lowestcostdemo.components;

import com.akshay.lowestcostdemo.BuildConfig;
import com.akshay.lowestcostdemo.utilities.FragmentTransactionUtility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DisplayLCPFragmentTest {

    private LowCostPathActivity lcpActivity;

    @Before
    public void setup()  {
        lcpActivity = Robolectric.setupActivity(LowCostPathActivity.class);
    }

    @Test
    public void ShowLCPFragmentIsNull() throws Exception {
        DisplayLCPFragment fragment = new DisplayLCPFragment();

        startFragment(DisplayLCPFragment.class.getSimpleName());
        assertNotNull(fragment);
    }

    public void startFragment(String fragmentTag) {

        FragmentTransactionUtility fragmentUtility = new FragmentTransactionUtility(lcpActivity);
        fragmentUtility.switchFragment(fragmentTag, null);
    }

    @After
    public void tearDown() throws Exception {

    }
}