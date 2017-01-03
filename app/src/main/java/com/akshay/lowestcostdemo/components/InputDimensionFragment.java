package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.module.DaggerLCPComponent;
import com.akshay.lowestcostdemo.module.LCPComponent;
import com.akshay.lowestcostdemo.utilities.FragmentTransactionUtility;
import com.akshay.lowestcostdemo.utilities.InputDimensionValidator;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

import javax.inject.Inject;

public class InputDimensionFragment extends Fragment implements View.OnClickListener{

    @Inject
    InputDimensionValidator inputValidator;
    private View inflatedView;

    public InputDimensionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflatedView =  inflater.inflate(R.layout.fragment_input_dimen, container, false);

        initializeLayout();

        return inflatedView;
    }

    private void initializeLayout(){

        inflatedView.findViewById(R.id.submit_button).setOnClickListener(this);
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

        LCPComponent lcpComponent = DaggerLCPComponent.builder().build();
        inputValidator = lcpComponent.provideInputDimen();

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
            FragmentTransactionUtility fragmentUtility = new FragmentTransactionUtility(getActivity());
            fragmentUtility.switchFragment(LCPAppConstants.MATRIX_INPUT_FRAGMENT, bundle);
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.submit_button){
            validateInput();
        }
    }

    private void setErrorText(EditText editText, String message){
        editText.setError(message);
        editText.requestFocus();
    }
}
