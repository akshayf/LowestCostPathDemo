package com.akshay.lowestcostdemo.utilities;

/**
 * UtilityBin class to store values for lowest cost path
 *
 * @version 1.0
 */
public class UtilityBin {

    private int totalCost;
    private String pathSequence;
    private String pathMaid;

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getPathSequence() {
        return pathSequence;
    }

    public void setPathSequence(String pathSequence) {
        this.pathSequence = pathSequence;
    }

    public String getPathMaid() {
        return pathMaid;
    }

    public void setPathMaid(String pathMaid) {
        this.pathMaid = pathMaid;
    }
}
