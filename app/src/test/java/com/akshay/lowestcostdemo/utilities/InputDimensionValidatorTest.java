package com.akshay.lowestcostdemo.utilities;

import com.akshay.lowestcostdemo.module.DaggerLCPComponent;
import com.akshay.lowestcostdemo.module.LCPComponent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class InputDimensionValidatorTest {

    @Inject
    InputDimensionValidator inputValidator;

    @Before
    public void setUp() throws Exception {

        LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
        inputValidator = lcpComponent.provideInputDimen();
    }

    @Test
    public void validateEnteredRowsLessThan_1(){

        boolean rowsFlag = inputValidator.validateEnteredRows(0);
        Assert.assertTrue(rowsFlag);
    }

    @Test
    public void validateEnteredRowsGreaterThan_10(){

        boolean rowsFlag = inputValidator.validateEnteredRows(11);
        Assert.assertTrue(rowsFlag);
    }

    @Test
    public void validateEnteredRowsMatched(){

        boolean rowsFlag = inputValidator.validateEnteredRows(2);
        Assert.assertFalse(rowsFlag);
    }

    @Test
    public void validateEnteredColumnsLessThan_5(){

        boolean colsFlag = inputValidator.validateEnteredColumns(4);
        Assert.assertTrue(colsFlag);
    }

    @Test
    public void validateEnteredColumnsGreaterThan_100(){

        boolean colsFlag = inputValidator.validateEnteredColumns(111);
        Assert.assertTrue(colsFlag);
    }

    @Test
    public void validateEnteredColumnsMatched(){

        boolean colsFlag = inputValidator.validateEnteredColumns(10);
        Assert.assertFalse(colsFlag);
    }

    @After
    public void tearDown() throws Exception {

    }
}