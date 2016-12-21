package com.akshay.lowestcostdemo.components;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.LCPAppConstants;

public class LowCostPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcp);

        addFragment();
    }

    private void addFragment(){

        InputDimensionFragment inputDimensionFragment = new InputDimensionFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, inputDimensionFragment);
        fragmentTransaction.commit();
    }

    /**
     * Method use to switch fragments and share a data between them
     * @param fromFragment fragment flag to switch from
     * @param bundle bundle object to share data
     */
    public void switchFragment(int fromFragment, Bundle bundle){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fromFragment == LCPAppConstants.INPUT_DIMENSION_FRAGMENT){

            MatrixInputFragment matrixInputFragment = new MatrixInputFragment();
            matrixInputFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, matrixInputFragment);
            fragmentTransaction.addToBackStack(null);
        }else if(fromFragment == LCPAppConstants.MATRIX_INPUT_FRAGMENT){

            ShowLCPFragment showLCPFragment = new ShowLCPFragment();
            showLCPFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, showLCPFragment);
        }else {

            InputDimensionFragment inputDimensionFragment = new InputDimensionFragment();
            //inputDimensionFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, inputDimensionFragment);
        }

        fragmentTransaction.commit();
    }
}
