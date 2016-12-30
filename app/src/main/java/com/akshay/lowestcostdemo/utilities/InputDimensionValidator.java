package com.akshay.lowestcostdemo.utilities;

public class InputDimensionValidator {

    public boolean validateEnteredRows(int numRows){
        return (numRows < 1 || numRows > 10);
    }

    public boolean validateEnteredColumns(int numCols){
        return (numCols < 5 || numCols > 100);
    }
}
