package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.FragmentTransactionUtility;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

import java.util.StringTokenizer;

/**
 * MatrixInputFragment use to handle matrix input
 * Here we set the limit for matrix input to 8 rows and 8 columns.
 * If it get more rows and columns then it will display edit text where
 * you can enter comma separated values.
 *
 * @version 1.0
 */
public class MatrixInputFragment extends Fragment implements View.OnClickListener{

    private View inflatedView;
    private final int MAX_VALUE = 8;
    private boolean GRID_FLAG = true;
    private int totalNumRows;
    private int totalNumCols;
    private GridLayout inputGridLayout;
    private int[][] inputMatrix;

    /**
     * Default constructor so that fragment should get recreated if gets destroyed by os in memory issue.
     */
    public MatrixInputFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.fragment_matrix_input, container, false);

        initializeLayout();

        return inflatedView;
    }

    private void initializeLayout() {

        Bundle bundle = getArguments();

        if (bundle != null) {

            totalNumRows = bundle.getInt(LCPAppConstants.NUMBER_OF_ROWS);
            totalNumCols = bundle.getInt(LCPAppConstants.NUMBER_OF_COLUMNS);

            ((TextView) inflatedView.findViewById(R.id.text_header))
                    .setText(getResources().getString(R.string.enter_values) + " " + totalNumRows + "*" + totalNumCols + " "+getResources().getString(R.string.matrix));

            inputMatrix = new int[totalNumRows][totalNumCols];

            inflatedView.findViewById(R.id.submit_button).setOnClickListener(this);

            if (totalNumRows <= MAX_VALUE && totalNumCols <= MAX_VALUE) {

                inputGridLayout = (GridLayout) inflatedView.findViewById(R.id.input_grid_layout);
                inputGridLayout.setRowCount(totalNumRows);
                inputGridLayout.setColumnCount(totalNumCols);

                inflatedView.findViewById(R.id.input_grid_layout).setVisibility(View.VISIBLE);
                inflatedView.findViewById(R.id.input_edit_text).setVisibility(View.GONE);

                addEditTextInGridLayout();

                GRID_FLAG = true;

            } else {

                inflatedView.findViewById(R.id.input_grid_layout).setVisibility(View.GONE);
                inflatedView.findViewById(R.id.input_edit_text).setVisibility(View.VISIBLE);

                GRID_FLAG = false;
            }
        }
    }

    /**
     * Add edit text in grid layout for all matrix cells
     */
    private void addEditTextInGridLayout() {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        EditText matrixChild;

        for (int i = 0; i < (totalNumRows * totalNumCols); i++) {

            matrixChild = (EditText)inflater.inflate(R.layout.matrix_child_layout, null);
            matrixChild.setTag(i);
            inputGridLayout.addView(matrixChild);
        }
    }

    /**
     * Validate values getting from matrix or editText are valid to process
     */
    private void validateDetails() {

        boolean errorFlag;

        if (GRID_FLAG) {
            errorFlag = getMatrixValuesFromGrid();
        } else {
            errorFlag = getMatrixValuesFromEditText();
        }

        if(!errorFlag){
            sendMatrixValues();
        }
    }

    /**
     * Get all values from Grid view (matrix cells)
     * @return errorFlag detect error while getting values
     */
    private boolean getMatrixValuesFromGrid() {

        boolean errorFlag = false;

        int cellCount = 0;

        for (int row = 0; row < totalNumRows; row++) {

            for (int col = 0; col < totalNumCols; col++) {

                String matrixCellText = ((EditText) inflatedView.findViewWithTag(cellCount)).getText().toString();
                cellCount++;

                if(!matrixCellText.equalsIgnoreCase("") && matrixCellText.length() <=3){

                    try {
                        inputMatrix[row][col] = Integer.parseInt(matrixCellText);

                    }catch (Exception e){

                        errorFlag = true;
                        e.printStackTrace();
                        break;
                    }
                }else{
                    errorFlag = true;
                    break;
                }
            }
        }

        if(errorFlag){
            Toast.makeText(getActivity(), getResources().getString(R.string.enter_valid_values), Toast.LENGTH_SHORT).show();
        }

        return errorFlag;
    }

    /**
     * Get all comma separated values from Edit text
     * @return errorFlag detect error while getting values
     */
    private boolean getMatrixValuesFromEditText(){

        boolean errorFlag = false;

        EditText inputEditText = (EditText)inflatedView.findViewById(R.id.input_edit_text);

        String matrixText = inputEditText.getText().toString();

        if (TextUtils.isEmpty(matrixText)) {
            inputEditText.setError(getString(R.string.enter_valid_values));
            inputEditText.requestFocus();
            errorFlag = true;
        }else{

            try{
                StringTokenizer st = new StringTokenizer(matrixText, ",");

                for (int row = 0; row < totalNumRows; row++) {

                    for (int col = 0; col < totalNumCols; col++) {

                        if (st.hasMoreTokens()) {

                            String matrixCellText = st.nextToken();

                            if(!matrixCellText.equalsIgnoreCase("") && matrixCellText.length() <=3) {

                                try {
                                    inputMatrix[row][col] = Integer.parseInt(matrixCellText);
                                } catch (Exception e) {
                                    errorFlag = true;
                                    e.printStackTrace();
                                    break;
                                }
                            }else{
                                errorFlag = true;
                                break;
                            }
                        }else{
                            errorFlag = true;
                            break;
                        }
                    }
                }
            }catch(Exception e){
                errorFlag = true;
            }
        }

        if(errorFlag){

            inputEditText.setError(getString(R.string.enter_valid_values));
            inputEditText.requestFocus();
        }

        return errorFlag;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.submit_button){
            validateDetails();
        }
    }

    /**
     * Send matrix values to DisplayLCPFragment for processing
     */
    private void sendMatrixValues() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(LCPAppConstants.INPUT_ARRAY, inputMatrix);
        bundle.putInt(LCPAppConstants.NUMBER_OF_ROWS, totalNumRows);
        bundle.putInt(LCPAppConstants.NUMBER_OF_COLUMNS, totalNumCols);
        FragmentTransactionUtility fragmentUtility = new FragmentTransactionUtility(getActivity());
        fragmentUtility.switchFragment(DisplayLCPFragment.class.getSimpleName(), bundle);

        clearInputValues();
    }

    /**
     * Clear the matrix or comma separated edit text on switching the fragment
     */
    private void clearInputValues(){

        EditText inputEditText = (EditText)inflatedView.findViewById(R.id.input_edit_text);

        if(inputEditText.isShown()){

            inputEditText.setText("");

        }else{
            int cellCount = 0;

            for (int row = 0; row < totalNumRows; row++) {

                for (int col = 0; col < totalNumCols; col++) {

                    ((EditText) inflatedView.findViewWithTag(cellCount)).setText("");
                    cellCount++;
                }
            }
        }
    }
}
