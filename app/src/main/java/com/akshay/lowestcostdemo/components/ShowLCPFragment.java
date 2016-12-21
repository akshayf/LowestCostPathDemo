package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;
import com.akshay.lowestcostdemo.utilities.UtilityBin;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ShowLCPFragment extends Fragment {

    private View inflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView =  inflater.inflate(R.layout.fragment_show_lcp, container, false);

        Bundle bundle = getArguments();

        if(bundle != null) {

            int [][] inputMatrix = (int [][]) bundle.getSerializable(LCPAppConstants.INPUT_ARRAY);
            int numRows = bundle.getInt(LCPAppConstants.NUMBER_OF_ROWS);
            int numCols = bundle.getInt(LCPAppConstants.NUMBER_OF_COLUMNS);

            calculatePath(inputMatrix, numRows, numCols);
        }

        return inflatedView;
    }

    /**
     * Method to find low cost path
     * A path enters the grid from the left (at any point) and passes through
     * the grid to the right (to any point)
     * @param matrix 2*2 matrix
     * @param numRows rows in matrix
     * @param numCols columns in matrix
     * @return UtilityBin object of UtilityBin class
     */
    public UtilityBin calculatePath(int [][] matrix, int numRows, int numCols){

        List<UtilityBin> utilityBinList = new ArrayList<>();
        UtilityBin utilityBin;

        for(int i=0; i<numRows; i++){

            utilityBin = new UtilityBin();

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

                UtilityBin utilityBinTemp = new UtilityBin();
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

        UtilityBin validUtilityBin = checkValidPath(utilityBinList.get(index), matrix, numCols);

        showPathDetails(validUtilityBin);

        return validUtilityBin;
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

    private void showPathDetails(UtilityBin utilityBin){

        if(inflatedView != null){

            List<String> pathList = new ArrayList<>();
            pathList.add(getResources().getString(R.string.path_maid));
            pathList.add(utilityBin.getPathMaid());
            pathList.add(getResources().getString(R.string.total_cost));
            pathList.add(String.valueOf(utilityBin.getTotalCost()));
            pathList.add(getResources().getString(R.string.path_sequence));
            pathList.add(utilityBin.getPathSequence());

            GridView gridView = (GridView)inflatedView.findViewById(R.id.result_grid_view);
            gridView.setNumColumns(2);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, pathList);

            gridView.setAdapter(adapter);
        }
    }
}
