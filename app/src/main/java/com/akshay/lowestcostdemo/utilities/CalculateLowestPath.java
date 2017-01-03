package com.akshay.lowestcostdemo.utilities;

import com.akshay.lowestcostdemo.module.DaggerLCPComponent;
import com.akshay.lowestcostdemo.module.LCPComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;

public class CalculateLowestPath {

    private int [][] matrix;
    private int numRows;
    private int numCols;

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

        List<UtilityBin> utilityBinList = new ArrayList<>();

        for(int i=0; i<numRows; i++){

            LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
            utilityBin = lcpComponent.provideUtilityBin();

            utilityBin.setTotalCost(matrix[i][numCols - 1]);
            utilityBin.setPathSequence("");

            utilityBinList.add(i, utilityBin);
        }

        for(int j= numCols-2; j>=0; j--){

            //Temporary list to store values for single column
            List<UtilityBin> utilityBinListTemp = new ArrayList<>();

            for(int i=0; i<numRows; i++){

                int m, n;

                if (i == 0) {
                    m = numRows - 1;
                } else {
                    m = i - 1;
                }

                if (i == numRows - 1) {
                    n = 0;
                } else {
                    n = i + 1;
                }

                int [] minArray = calculateMin(utilityBinList, m, i, n);

                LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
                utilityBinTemp = lcpComponent.provideUtilityBin();
                int cost = matrix[i][j] + minArray[0];
                utilityBinTemp.setTotalCost(cost);

                String pathSequence = "";

                if(j==0){
                    pathSequence = (i + 1)+" ";
                }

                pathSequence = pathSequence + (minArray[1] + 1)+" "+ utilityBinList.get(minArray[1]).getPathSequence();
                utilityBinTemp.setPathSequence(pathSequence);
                utilityBinListTemp.add(utilityBinTemp);
            }

            utilityBinList.clear();
            utilityBinList.addAll(utilityBinListTemp);
        }

        int index = 0;

        for(int i=1 ; i< utilityBinList.size(); i++){

            int val1 = utilityBinList.get(index).getTotalCost();
            int val2 =  utilityBinList.get(i).getTotalCost();

            if(val1 > val2){
                index = i;
            }
        }

        return checkValidPath(utilityBinList.get(index), matrix, numCols);
    }

    private UtilityBin checkValidPath(UtilityBin utilityBin, int [][] matrix, int numCols){

        int totalCost = utilityBin.getTotalCost();
        String pathSequence = utilityBin.getPathSequence();
        String pathMaid = LCPAppConstants.PATH_MAID_YES;

        if (totalCost > 50) {

            try {
                pathMaid = LCPAppConstants.PATH_MAID_NO;

                int cost = 0, tempCost;
                String tempPath = "";

                StringTokenizer st = new StringTokenizer(pathSequence, " ");

                for (int j = 0; j < numCols; j++) {

                    String iCount = st.nextToken();
                    int i = Integer.parseInt(iCount);

                    tempCost = cost + matrix[i - 1][j];

                    if (tempCost > 50) {

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
     * @param m first integers
     * @param i second integer
     * @param n third integer
     * @return int[] returns minimum cost and path value
     */
    private int[] calculateMin(List<UtilityBin> utilityBinList, int m, int i, int n){

        int[] min = new int[2];

        if (utilityBinList.get(m).getTotalCost() <= utilityBinList.get(i).getTotalCost()){

            if (utilityBinList.get(m).getTotalCost() <= utilityBinList.get(n).getTotalCost()){

                min[0] = utilityBinList.get(m).getTotalCost();
                min[1] = m;
            }else{
                min[0] = utilityBinList.get(n).getTotalCost();
                min[1] = n;
            }
        } else {

            if (utilityBinList.get(i).getTotalCost() <= utilityBinList.get(n).getTotalCost()){

                min[0] = utilityBinList.get(i).getTotalCost();
                min[1] = i;
            }else{
                min[0] = utilityBinList.get(n).getTotalCost();
                min[1] = n;
            }
        }

        return min;
    }
}
