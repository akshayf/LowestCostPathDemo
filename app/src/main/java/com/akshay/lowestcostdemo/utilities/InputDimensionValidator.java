package com.akshay.lowestcostdemo.utilities;

/**
 * InputDimensionValidator use to validate the given input.
 * It validates rows from 1 to 10 and columns from 5 to 100
 *
 * @author  Akshay Faye
 * @version 1.0
 */
public class InputDimensionValidator {

    public boolean validateEnteredRows(int numRows){
        return (numRows < 1 || numRows > 10);
    }

    public boolean validateEnteredColumns(int numCols){
        return (numCols < 5 || numCols > 100);
    }
}
