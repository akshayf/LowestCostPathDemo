package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.AppConstants;

import java.util.StringTokenizer;

public class GridFragment extends Fragment {

    private View inflatedView;
    private final int MAX_VALUE = 8;
    private boolean COLS_FLAG = true;
    private int totalNumRows;
    private int totalNumCols;
    private GridLayout inputGridLayout;
    private int[][] inputMatrix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_grid, container, false);

        Bundle bundle = getArguments();

        if (bundle != null) {

            totalNumRows = bundle.getInt(AppConstants.NUMBER_OF_ROWS);
            totalNumCols = bundle.getInt(AppConstants.NUMBER_OF_COLUMNS);

            ((TextView) inflatedView.findViewById(R.id.text_header))
                    .setText(getResources().getString(R.string.enter_values) +" "+ totalNumRows + "*" + totalNumCols + " Matrix");

            inputMatrix = new int[totalNumRows][totalNumCols];

            setLayout();

            inflatedView.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    validateDetails(v);
                }
            });

        }
        return inflatedView;
    }

    private void setLayout() {

        if (totalNumRows <= MAX_VALUE && totalNumCols <= MAX_VALUE) {

            inputGridLayout = (GridLayout) inflatedView.findViewById(R.id.input_grid_layout);
            inputGridLayout.setRowCount(totalNumRows);
            inputGridLayout.setColumnCount(totalNumCols);

            inflatedView.findViewById(R.id.input_grid_layout).setVisibility(View.VISIBLE);
            inflatedView.findViewById(R.id.input_edit_text).setVisibility(View.GONE);

            addEditTextInGrid();

            COLS_FLAG = true;

        } else {

            inflatedView.findViewById(R.id.input_grid_layout).setVisibility(View.GONE);
            inflatedView.findViewById(R.id.input_edit_text).setVisibility(View.VISIBLE);

            COLS_FLAG = false;
        }
    }

    private void addEditTextInGrid() {

        LayoutInflater inflater;
        EditText view;

        for (int i = 0; i < (totalNumRows * totalNumCols); i++) {

            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = (EditText)inflater.inflate(R.layout.grid_child_layout, null);
            view.setTag(i);
            inputGridLayout.addView(view);
        }
    }

    private void validateDetails(View v) {

        boolean cancelFlag;

        if (COLS_FLAG) {

            cancelFlag = calculateMatrixValuesFromGrid();
        } else {
            cancelFlag = calculateMatrixValuesFromEditText();
        }

        if(!cancelFlag){

            sendMatrixValues();
        }
    }

    private boolean calculateMatrixValuesFromGrid() {

        boolean cancelFlag = false;

        int k = 0;

        for (int i = 0; i < totalNumRows; i++) {

            for (int j = 0; j < totalNumCols; j++) {

                String text = ((EditText) inflatedView.findViewWithTag(k)).getText().toString();
                k++;

                if(!text.equalsIgnoreCase("")){

                    try {
                        inputMatrix[i][j] = Integer.parseInt(text);

                    }catch (Exception e){

                        Toast.makeText(getActivity(), getResources().getString(R.string.enter_valid_values), Toast.LENGTH_LONG).show();

                        cancelFlag = true;

                        e.printStackTrace();
                        break;
                    }
                }else{

                    Toast.makeText(getActivity(), getResources().getString(R.string.enter_valid_values), Toast.LENGTH_LONG).show();

                    cancelFlag = true;
                    break;
                }
            }
        }

        return cancelFlag;
    }

    private boolean calculateMatrixValuesFromEditText(){

        boolean cancelFlag = false;

        EditText inputEditText = (EditText)inflatedView.findViewById(R.id.input_edit_text);

        String matrixText = inputEditText.getText().toString();

        if (TextUtils.isEmpty(matrixText)) {
            inputEditText.setError(getString(R.string.enter_valid_values));
            inputEditText.requestFocus();
            cancelFlag = true;
        }else{

            try{
                StringTokenizer st = new StringTokenizer(matrixText, ",");

                for (int i = 0; i < totalNumRows; i++) {

                    for (int j = 0; j < totalNumCols; j++) {

                        if (st.hasMoreTokens()) {

                            String text = st.nextToken();

                            try {
                                inputMatrix[i][j] = Integer.parseInt(text);
                            } catch (Exception e) {

                                inputEditText.setError(getString(R.string.enter_valid_values));
                                inputEditText.requestFocus();
                                cancelFlag = true;
                                e.printStackTrace();
                                break;
                            }
                        }else{

                            inputEditText.setError(getString(R.string.enter_valid_values));
                            inputEditText.requestFocus();
                            cancelFlag = true;
                        }
                    }
                }
            }catch(Exception e){

                inputEditText.setError(getString(R.string.enter_valid_values));
                inputEditText.requestFocus();
                cancelFlag = true;
            }
        }

        return cancelFlag;
    }

    private void sendMatrixValues() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.INPUT_ARRAY, inputMatrix);
        bundle.putInt(AppConstants.NUMBER_OF_ROWS, totalNumRows);
        bundle.putInt(AppConstants.NUMBER_OF_COLUMNS, totalNumCols);
        ((MainActivity) getActivity()).switchFragment(AppConstants.GRID_FRAGMENT, bundle);
    }

}
