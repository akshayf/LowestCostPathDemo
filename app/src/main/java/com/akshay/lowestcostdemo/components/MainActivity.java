package com.akshay.lowestcostdemo.components;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.akshay.lowestcostdemo.R;
import com.akshay.lowestcostdemo.utilities.AppConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();
    }

    public void addFragment(){

        InputFragment inputFragment = new InputFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, inputFragment);
        fragmentTransaction.commit();
    }

    public void switchFragment(int fromFragment, Bundle bundle){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fromFragment == AppConstants.INPUT_FRAGMENT){
            GridFragment gridFragment = new GridFragment();
            gridFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, gridFragment);
        }else if(fromFragment == AppConstants.GRID_FRAGMENT){
            OutputFragment outputFragment = new OutputFragment();
            outputFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, outputFragment);
        }else {
            InputFragment inputFragment = new InputFragment();
            inputFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container, inputFragment);
        }

        fragmentTransaction.commit();
    }
}
