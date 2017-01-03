package com.akshay.lowestcostdemo.utilities;

import com.akshay.lowestcostdemo.BuildConfig;
import com.akshay.lowestcostdemo.module.DaggerLCPComponent;
import com.akshay.lowestcostdemo.module.LCPComponent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UtilityBinTest {

    @Inject
    UtilityBin utilityBin;

    @Before
    public void setUp() throws Exception {

        LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
        utilityBin = lcpComponent.provideUtilityBin();
    }

    @Test
    public void testPathMaid() {
        utilityBin.setPathMaid("yes");
        assertTrue(utilityBin.getPathMaid().equals("yes"));
    }

    @Test
    public void testPathSequence() {
        utilityBin.setPathSequence("12111");
        assertTrue(utilityBin.getPathSequence().equals("12111"));
    }

    @Test
    public void testTotalCost() {
        utilityBin.setTotalCost(20);
        assertTrue(utilityBin.getTotalCost() == 20);
    }
}