package com.akshay.lowestcostdemo.utilities;

import com.akshay.lowestcostdemo.module.DaggerLCPComponent;
import com.akshay.lowestcostdemo.module.LCPComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

/**
 * CalculateLowestPath class to find low cost path
 * Calculates the TotalCost, PathSequence and PathMaid for the given matrix.
 *
 * @version 1.0
 */
public class CalculateLowestPath {

    private int [][] matrix;
    private int numRows;
    private int numCols;
    private final int MAX_COST = 50;

    @Inject
    UtilityBin utilityBin, utilityBinTemp;

    public void setMatrixDetails(int [][] matrix, int numRows, int numCols) {

        this.matrix = matrix;
        this.numRows = numRows;
        this.numCols = numCols;
    }

    /**
     * Method to find the low cost path in the given matrix
     * @return UtilityBin object having low cost path and cost for it
     */
    public UtilityBin findLowestCostPath() {

        //ArrayList to store all formed utility bin objects
        List<UtilityBin> utilityBinList = new ArrayList<>();

        for(int row=0; row<numRows; row++){

            LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
            utilityBin = lcpComponent.provideUtilityBin();

            utilityBin.setTotalCost(matrix[row][numCols - 1]);
            utilityBin.setPathSequence("");

            utilityBinList.add(row, utilityBin);
        }

        //the revers loop for columns i.e. right to left approach
        for(int col= numCols-2; col>=0; col--){

            //Temporary list to store values for single column
            List<UtilityBin> utilityBinListTemp = new ArrayList<>();

            for(int row=0; row<numRows; row++){

                int prevRow, nextRow;

                if (row == 0) {
                    prevRow = numRows - 1;
                } else {
                    prevRow = row - 1;
                }

                if (row == numRows - 1) {
                    nextRow = 0;
                } else {
                    nextRow = row + 1;
                }

                int [] minArray = calculateMin(utilityBinList, prevRow, row, nextRow);

                LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
                utilityBinTemp = lcpComponent.provideUtilityBin();
                int cost = matrix[row][col] + minArray[0];
                utilityBinTemp.setTotalCost(cost);

                String pathSequence = "";

                if(col==0){
                    pathSequence = (row + 1)+" ";
                }

                pathSequence = pathSequence + (minArray[1] + 1)+" "+ utilityBinList.get(minArray[1]).getPathSequence();
                utilityBinTemp.setPathSequence(pathSequence);
                utilityBinListTemp.add(utilityBinTemp);
            }

            utilityBinList.clear();
            utilityBinList.addAll(utilityBinListTemp);
        }

        int index = 0;

        //Calculates lowest cost UtilityBin object
        for(int i = 1 ; i< utilityBinList.size(); i++){

            int val1 = utilityBinList.get(index).getTotalCost();
            int val2 =  utilityBinList.get(i).getTotalCost();

            if(val1 > val2){
                index = i;
            }
        }

        return checkValidPath(utilityBinList.get(index), matrix, numCols);
    }

    /**
     * To check the path is valid
     * i.e. to validate totalCost is less than 50 otherwise no path get maid
     * @param utilityBin object of UtilityBin class
     * @param matrix the matrix values
     * @param numCols number of columns
     * @return UtilityBin modified object contains path maid parameter
     */
    private UtilityBin checkValidPath(UtilityBin utilityBin, int [][] matrix, int numCols){

        int totalCost = utilityBin.getTotalCost();
        String pathSequence = utilityBin.getPathSequence();
        String pathMaid = LCPAppConstants.PATH_MAID_YES;

        if (totalCost > MAX_COST) {

            try {
                pathMaid = LCPAppConstants.PATH_MAID_NO;

                int cost = 0, tempCost;
                String tempPath = "";

                StringTokenizer st = new StringTokenizer(pathSequence, " ");

                for (int col = 0; col < numCols; col++) {

                    String iCount = st.nextToken();
                    int row = Integer.parseInt(iCount);

                    tempCost = cost + matrix[row - 1][col];

                    if (tempCost > MAX_COST) {

                        totalCost = cost;
                        pathSequence = tempPath;

                        break;
                    } else {
                        tempPath = tempPath + iCount + " ";
                    }

                    cost = tempCost;
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        utilityBin.setPathMaid(pathMaid);
        utilityBin.setPathSequence(pathSequence.trim());
        utilityBin.setTotalCost(totalCost);

        return utilityBin;
    }

    /**
     * Calculates minimum value from given integer values
     * @param utilityBinList List of utilityBin objects
     * @param prevRow previous row element
     * @param row current row element
     * @param nextRow next row element
     * @return int[] returns minimum cost and path sequence
     */
    private int[] calculateMin(List<UtilityBin> utilityBinList, int prevRow, int row, int nextRow){

        int[] minCost = new int[2];

        if (utilityBinList.get(prevRow).getTotalCost() <= utilityBinList.get(row).getTotalCost()){

            if (utilityBinList.get(prevRow).getTotalCost() <= utilityBinList.get(nextRow).getTotalCost()){

                minCost[0] = utilityBinList.get(prevRow).getTotalCost();
                minCost[1] = prevRow;
            }else{
                minCost[0] = utilityBinList.get(nextRow).getTotalCost();
                minCost[1] = nextRow;
            }
        } else {

            if (utilityBinList.get(row).getTotalCost() <= utilityBinList.get(nextRow).getTotalCost()){

                minCost[0] = utilityBinList.get(row).getTotalCost();
                minCost[1] = row;
            }else{
                minCost[0] = utilityBinList.get(nextRow).getTotalCost();
                minCost[1] = nextRow;
            }
        }

        return minCost;
    }
}
