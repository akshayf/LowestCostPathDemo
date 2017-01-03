package com.akshay.lowestcostdemo.components;

import com.akshay.lowestcostdemo.BuildConfig;
import com.akshay.lowestcostdemo.utilities.FragmentTransactionUtility;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

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
public class ShowLCPFragmentTest {

    private LowCostPathActivity lcpActivity;

    @Before
    public void setup()  {
        lcpActivity = Robolectric.setupActivity(LowCostPathActivity.class);
    }

    @Test
    public void ShowLCPFragmentIsNull() throws Exception {
        ShowLCPFragment fragment = new ShowLCPFragment();

        startFragment(LCPAppConstants.SHOW_LCP_FRAGMENT);
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