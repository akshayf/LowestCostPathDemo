package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.InputDimensionValidator;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

public class InputDimensionFragment extends Fragment {

    private View inflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView =  inflater.inflate(R.layout.fragment_input_dimen, container, false);

        inflatedView.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });

        return inflatedView;
    }

    private void validateInput() {

        EditText rowEditText = (EditText)inflatedView.findViewById(R.id.rows_edit_text);
        EditText colEditText = (EditText)inflatedView.findViewById(R.id.cols_edit_text);

        rowEditText.setError(null);
        colEditText.setError(null);

        String rows = rowEditText.getText().toString();
        String cols = colEditText.getText().toString();

        int numRows = Integer.parseInt(rows);
        int numCols = Integer.parseInt(cols);

        InputDimensionValidator inputValidator = new InputDimensionValidator();

        if(TextUtils.isEmpty(rows)){
            setErrorText(rowEditText, getString(R.string.error_rows));
        }else if(TextUtils.isEmpty(cols)){
            setErrorText(colEditText, getString(R.string.error_cols));
        }else if(inputValidator.validateEnteredRows(numRows)){
            setErrorText(rowEditText, getString(R.string.error_rows));
        }else if(inputValidator.validateEnteredColumns(numCols)){
            setErrorText(colEditText, getString(R.string.error_cols));
        }else{
            Bundle bundle = new Bundle();
            bundle.putInt(LCPAppConstants.NUMBER_OF_ROWS, numRows);
            bundle.putInt(LCPAppConstants.NUMBER_OF_COLUMNS, numCols);
            ((LowCostPathActivity) getActivity()).switchFragment(LCPAppConstants.INPUT_DIMENSION_FRAGMENT, bundle);
        }
    }

    private void setErrorText(EditText editText, String message){
        editText.setError(message);
        editText.requestFocus();
    }
}
