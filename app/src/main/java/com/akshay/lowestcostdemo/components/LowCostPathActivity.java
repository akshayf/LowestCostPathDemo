package com.akshay.lowestcostdemo.components;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.FragmentTransactionUtility;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

public class LowCostPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcp);

        FragmentTransactionUtility fragmentUtility = new FragmentTransactionUtility(this);
        fragmentUtility.switchFragment(LCPAppConstants.INPUT_DIMENSION_FRAGMENT, null);
    }
}
