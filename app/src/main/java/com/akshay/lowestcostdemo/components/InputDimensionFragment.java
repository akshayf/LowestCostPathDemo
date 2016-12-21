package com.akshay.lowestcostdemo.components;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.akshay.lowestcostdemo.R;
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

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(rows) || numRows < 1 || numRows > 10) {
            rowEditText.setError(getString(R.string.enter_rows));
            focusView = rowEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(cols) || numCols < 5 || numCols > 100) {
            colEditText.setError(getString(R.string.error_cols));
            focusView = colEditText;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            Bundle bundle = new Bundle();
            bundle.putInt(LCPAppConstants.NUMBER_OF_ROWS, numRows);
            bundle.putInt(LCPAppConstants.NUMBER_OF_COLUMNS, numCols);
            ((LowCostPathActivity) getActivity()).switchFragment(LCPAppConstants.INPUT_DIMENSION_FRAGMENT, bundle);
        }
    }
}
