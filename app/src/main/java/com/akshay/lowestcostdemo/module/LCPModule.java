package com.akshay.lowestcostdemo.module;

import com.akshay.lowestcostdemo.utilities.CalculateLowestPath;
import com.akshay.lowestcostdemo.utilities.InputDimensionValidator;
import com.akshay.lowestcostdemo.utilities.UtilityBin;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * LCPModule is used to implement Dagger library
 *
 * @author  Akshay Faye
 * @version 1.0
 */
@Module
public class LCPModule {

    @Provides
    @Singleton
    public CalculateLowestPath provideCalculateLowestPath(){
        return new CalculateLowestPath();
    }

    @Provides
    @Singleton
    public InputDimensionValidator provideInputDimensionValidator(){
        return new InputDimensionValidator();
    }

    @Provides
    public UtilityBin provideUtilityBin(){
        return new UtilityBin();
    }
}
