package com.akshay.lowestcostdemo.utilities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.components.InputDimensionFragment;
import com.akshay.lowestcostdemo.components.MatrixInputFragment;
import com.akshay.lowestcostdemo.components.DisplayLCPFragment;

/**
 * FragmentTransactionUtility use to handle fragment transactions and
 * sharing data between them
 *
 * @author  Akshay Faye
 * @version 1.0
 */
public class FragmentTransactionUtility {

    private Activity lcpActivity;

    public FragmentTransactionUtility(Activity activity){

        this.lcpActivity = activity;
    }

    /**
     * Method use to switch fragments and share a data between them
     * @param fragmentTag flag use to specify switching fragment
     * @param bundle bundle object to share data between fragments
     */
    public void switchFragment(String fragmentTag, Bundle bundle){

        FragmentManager fragmentManager = lcpActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fragmentTag.equals(MatrixInputFragment.class.getSimpleName())){

            MatrixInputFragment matrixInputFragment = (MatrixInputFragment)fragmentManager.findFragmentByTag(fragmentTag);

            if(matrixInputFragment == null)
                matrixInputFragment = new MatrixInputFragment();

            matrixInputFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, matrixInputFragment, fragmentTag);

        }else if(fragmentTag.equals(DisplayLCPFragment.class.getSimpleName())){

            DisplayLCPFragment displayLCPFragment = (DisplayLCPFragment)fragmentManager.findFragmentByTag(fragmentTag);

            if(displayLCPFragment == null)
                displayLCPFragment = new DisplayLCPFragment();

            displayLCPFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, displayLCPFragment, fragmentTag);

        }else {

            InputDimensionFragment inputDimensionFragment = (InputDimensionFragment)fragmentManager.findFragmentByTag(fragmentTag);

            if(inputDimensionFragment == null)
                inputDimensionFragment = new InputDimensionFragment();

            fragmentTransaction.add(R.id.fragment_container, inputDimensionFragment, fragmentTag);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
