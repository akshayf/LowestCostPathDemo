package com.akshay.lowestcostdemo.utilities;

import android.app.Activity;
import android.app.Fragment;
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
    public void switchFragment(String fragmentTag, Bundle bundle) {

        FragmentManager fragmentManager = lcpActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment == null){
            if (fragmentTag.equals(MatrixInputFragment.class.getSimpleName())) {
                fragment = new MatrixInputFragment();
            } else if (fragmentTag.equals(DisplayLCPFragment.class.getSimpleName())) {
                    fragment = new DisplayLCPFragment();
            } else {
                    fragment = new InputDimensionFragment();
            }
        }

        if(fragment instanceof MatrixInputFragment || fragment instanceof DisplayLCPFragment){
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
        }else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTag);
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
