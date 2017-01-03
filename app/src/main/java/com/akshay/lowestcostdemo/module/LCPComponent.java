package com.akshay.lowestcostdemo.module;

import com.akshay.lowestcostdemo.components.LowCostPathActivity;
import com.akshay.lowestcostdemo.utilities.CalculateLowestPath;
import com.akshay.lowestcostdemo.utilities.InputDimensionValidator;
import com.akshay.lowestcostdemo.utilities.UtilityBin;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {LCPModule.class})
public interface LCPComponent {

    CalculateLowestPath provideCalculateLP();
    InputDimensionValidator provideInputDimen();
    UtilityBin provideUtilityBin();
}