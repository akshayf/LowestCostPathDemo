package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.CalculateLowestPath;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;
import com.akshay.lowestcostdemo.utilities.UtilityBin;
import java.util.ArrayList;
import java.util.List;

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

            CalculateLowestPath calculateLP = new CalculateLowestPath();
            calculateLP.setMatrixDetails(inputMatrix, numRows, numCols);
            UtilityBin utilityBin = calculateLP.findLowestCostPath();
            showPathDetails(utilityBin);
        }

        return inflatedView;
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
