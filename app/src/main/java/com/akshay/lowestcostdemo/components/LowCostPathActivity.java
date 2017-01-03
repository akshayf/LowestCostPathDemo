package com.akshay.lowestcostdemo.components;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.FragmentTransactionUtility;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

/**
 * LowCostPathActivity main activity to handle all the fragments
 *
 * @version 1.0
 */
public class LowCostPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcp);

        FragmentTransactionUtility fragmentUtility = new FragmentTransactionUtility(this);
        fragmentUtility.switchFragment(InputDimensionFragment.class.getSimpleName(), null);
    }
}
