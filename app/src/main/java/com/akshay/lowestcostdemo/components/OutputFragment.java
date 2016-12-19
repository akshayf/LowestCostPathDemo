package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.AppConstants;
import com.akshay.lowestcostdemo.utilities.OutputBin;

import java.util.ArrayList;
import java.util.List;

public class OutputFragment extends Fragment {

    private View inflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView =  inflater.inflate(R.layout.fragment_output, container, false);

        Bundle bundle = getArguments();

        if(bundle != null) {

            int [][] inputMatrix = (int [][]) bundle.getSerializable(AppConstants.INPUT_ARRAY);
            int numRows = bundle.getInt(AppConstants.NUMBER_OF_ROWS);
            int numCols = bundle.getInt(AppConstants.NUMBER_OF_COLUMNS);

            calculatePath(inputMatrix, numRows, numCols);
        }

        return inflatedView;
    }

    /**
     * A path enters the grid from the left (at any point) and passes through
     * the grid to the right (to any point)
     * @param matrix 2*2 matrix
     * @param numRows rows in matrix
     * @param numCols columns in matrix
     * @return OutputBin object of OutputBin class
    */
    public OutputBin calculatePath(int [][] matrix, int numRows, int numCols){

        List<OutputBin> outputBinList = new ArrayList<>();
        OutputBin outputBin;

        for (int i = 0; i < numRows; i++) {
            outputBin = new OutputBin();
            int countTotal = matrix[i][0];
            outputBin.setPathSequence((i+1)+" ");
            int k = i;

            for (int j = 1; j < numCols; j++) {

                    int m, n;

                    if (k == 0) {
                        m = numRows - 1;
                    } else {
                        m = k - 1;
                    }

                    if (k == numRows - 1) {
                        n = 0;
                    } else {
                        n = k + 1;
                    }

                    int min[] = calculateMin(matrix, m, k, n, j);

                    int total = countTotal + min[0];

                    k = min[1];

                    String pathMaid;
                    if(total <= 50){
                        pathMaid = "YES";
                        countTotal = total;
                        outputBin.setPathMaid(pathMaid);
                        outputBin.setPathSequence(outputBin.getPathSequence() + (k + 1) + " ");
                    }else{
                        pathMaid = "NO";
                        outputBin.setPathMaid(pathMaid);
                        outputBin.setTotalCost(countTotal);
                        break;
                    }

                if(j == numCols -1){
                    outputBin.setTotalCost(countTotal);
                }
            }

            outputBinList.add(outputBin);
        }

        int index = 0;

        for(int i=1 ; i<outputBinList.size()-1; i++){

            int val1 = outputBinList.get(index).getTotalCost();
            int val2 =  outputBinList.get(i).getTotalCost();

            if(val1 > val2){
                index = i;
            }
        }

        setOutput(outputBinList.get(index).getPathMaid(), outputBinList.get(index).getTotalCost(),
                outputBinList.get(index).getPathSequence());

        return outputBinList.get(index);
    }

    private int[] calculateMin(int [][] matrix, int m, int k, int n, int j){

        int[] min = new int[2];

        if (matrix[m][j] <= matrix[k][j]){

            if (matrix[m][j] <= matrix[n][j]){

                min[0] = matrix[m][j];
                min[1] = m;
            }else{
                min[0] = matrix[n][j];
                min[1] = n;
            }
        } else {

            if (matrix[k][j] <= matrix[n][j]){

                min[0] = matrix[k][j];
                min[1] = k;
            }else{
                min[0] = matrix[n][j];
                min[1] = n;
            }
        }

        return min;
    }

    private void setOutput(String pathMaid, int totalCost, String pathSequence){

        if(inflatedView != null){
            List<String> pathList = new ArrayList<>();
            pathList.add(getResources().getString(R.string.path_maid));
            pathList.add(pathMaid);
            pathList.add(getResources().getString(R.string.total_cost));
            pathList.add(String.valueOf(totalCost));
            pathList.add(getResources().getString(R.string.path_sequence));
            pathList.add(pathSequence);

            GridView gridView = (GridView)inflatedView.findViewById(R.id.result_grid_view);
            gridView.setNumColumns(2);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, pathList);

            gridView.setAdapter(adapter);
        }
    }
}
