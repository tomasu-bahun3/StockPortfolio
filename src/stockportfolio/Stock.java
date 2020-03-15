/**
 *                  Revision History (Newest First)
 * **************************************************************
 * 
 * 10/5/19 - Finished project - Thomas Bahun
 * 10/4/19 - Worked on calc methods - Thomas Bahun
 * 10/2/19 - Worked on Stock class and testing - Thomas Bahun
 * 09/29/2019 - Worked on Stock class - Thomas Bahun
 * 09/27/2019 - Created Stock class and drafted pseudocode - Thomas Bahun
 * 
 */

package stockportfolio;

import java.util.*;
import java.io.*;

/**
 * Course: CSCI 160
 * Class Description: A class representing a Stock from a file that contains
 *  company name and price quotations.
 * Project Name: Stock Portfolio Manager
 * Due Date: October 5, 2019
 * Depends on:
 * Extends:
 * Implements:
 * @author thomas
 */
public class Stock {

    /**
     * String representing the company's name
     */
    private String coName;
    
    /**
     * ArrayList holding all price quotation Double values from the inFile
     */
    private double[] prQuotes;
    
    /**
     * double value representing the lowest price of prQ's
     */
    private double lowPr;
    
    /**
     * double value representing the highest price of prQ's
     */
    private double highPr;
    
    /**
     * double value representing the net change of prQ's
     */
    private double netChg;
    
    /**
     * double value representing the average price of prQ's
     */
    private double avgPr;
    
    /**
     * double value representing the standard deviation 
     */
    private double stdDev;
    
    /**
     * int value representing the longest upward trend
     */
    private int longUpTrend;
    
    /**
     * double value representing the best growth rate of the upward trend
     */
    private double bestGrowthRateUpTrend;
    
    /**
     * Parameterized Stock constructor
     * @param coName the String for the company's name
     * @param prQuotes the ArrayList holding Doubles for price quotations
     */
    public Stock(String coName, double[] prQuotes) {    
        this.coName = coName;
        this.prQuotes = prQuotes;
        
        // call calc methods to set values
        calcLowPr();
        // System.out.println(lowPr); // testing
        calcHighPr();
        // System.out.println(highPr); // testing
        calcNetChg();
        // System.out.println(netChg); // testing
        calcAvgPr();
        // System.out.println(avgPr); // testing
        calcStdDev();
        // System.out.println(stdDev); // testing
        calcLongUpTrend();
        // System.out.println(longUpTrend); // testing
        calcBestGrowthRateUpTrend();
    }

    // calc methods
    
    /**
     * calcLowPr
     * Calculates the lowest price using Collections
     */
    private void calcLowPr() {
        double min = prQuotes[0];
        for(int i = 0; i < prQuotes.length; i++) {
            if(prQuotes[i] < min) {
                min = prQuotes[i];
            }
        }
        lowPr = min;
    }
    
    /**
     * calcHighPr
     * Calculates the highest price using Collections
     */
    private void calcHighPr() {
        double max = prQuotes[0];
        for(int i = 0; i < prQuotes.length; i++) {
            if(prQuotes[i] > max) {
                max = prQuotes[i];
            }
        }
        highPr = max;
    }
    
    /**
     * calcNetChg
     * Calculates the net change using the highPr and lowPr
     */
    private void calcNetChg() {
        netChg = prQuotes[prQuotes.length-1] - prQuotes[0];
    }
    
    /**
     * calcAvgPr
     * Calculates the average price by iterating over all prices, summing
     * them and then dividing by the size of the ArrayList
     */
    private void calcAvgPr() {
        double sum = 0;
        for(int i = 0; i < prQuotes.length; i++) {
            sum += prQuotes[i];
        }
        avgPr = sum/(double)prQuotes.length;
    }
    
    /**
     * calcStdDev
     * Calculates the standard deviation by iterating through the ArrayList,
     * subtracting the avgPr from each quotation, and squaring that result.
     * Then, that result is summed with all other results and divided by
     * the size of the ArrayList. Finally, the result is square rooted.
     */
    private void calcStdDev() {
        double sumOfDiffSqrs = 0;
        for(int i = 0; i < prQuotes.length; i++) {
            double currentQuote = prQuotes[i];
            sumOfDiffSqrs += Math.pow(currentQuote - avgPr, 2);
        }
        stdDev = Math.sqrt((double)sumOfDiffSqrs/(double)prQuotes.length);
    }
    
    /**
     * calcLongUpTrend
     * Calculates the longest upward trend by iterating through the
     * ArrayList, and counting up each time the trend goes up. It results
     * in the longest upward trend. However, if the upward trend is negative
     * over the entire length, it results in 0.
     * Note: An upward trend is considered if the next value is not less
     * than the current.
     */
    private void calcLongUpTrend() {
       int streak = 0; // count how many times up
       int maxUp = 0; // count highest up trend
       
       for(int i = 1; i < prQuotes.length; i++) {
           if(prQuotes[i] >= prQuotes[i-1]) { // should this be >= ?
               streak++;
           } else {
               if(streak > maxUp) {
                   maxUp = streak;
               }
               streak = 0;
           }
           
           if(streak > maxUp) {
               maxUp = streak;
           }
       }
       
       // Should this be implemented?
       // if decline over entire period, set trend to 0
       //if(prQuotes[0] > prQuotes[prQuotes.length-1]) {
       //    maxUp = 0;
       //}
       
       longUpTrend = maxUp;
    }
    
    /**
     * calcBestGrowthRateUpTrend
     * Calculates the best growth rate of the upward trends
     */
    private void calcBestGrowthRateUpTrend() {
       // same as above but tracking... how to do this?
       int streak = 0;
       int maxUp = 0;
       double lowPrice = 0;
       double highPrice = 0;
       
       for(int i = 1; i < prQuotes.length; i++) {
           lowPrice = prQuotes[i-1];
           highPrice = prQuotes[i-1];
           
           if(prQuotes[i] >= prQuotes[i-1]) { // should this be >= ?
               streak++;
               if(prQuotes[i] <= lowPrice) {
                   lowPrice = prQuotes[i];
               }
               if(prQuotes[i] >= highPrice) {
                   highPrice = prQuotes[i];
               }
           } else {
               if(streak > maxUp) {
                   maxUp = streak;
               }
               streak = 0;
           }
           
           if(streak > maxUp) {
               maxUp = streak;
           }
           
           bestGrowthRateUpTrend = (highPrice - lowPrice) / streak;
       }

       // set to 0 if upTrend is 0
       if(longUpTrend == 0) {
           bestGrowthRateUpTrend = 0.00;
       } 
    }
    
    /**
     * Getter for lowPr
     * @return lowPr the lowest price
     */
    public double getLowPr() {    
        return lowPr;
    }
    
    /**
     * Getter for highPr
     * @return highPr the highest price
     */
    public double getHighPr() {
        return highPr;
    }
    
    /**
     * Getter for netChg
     * @return netChg the net change
     */
    public double getNetChg() {
        return netChg;
    }
    
    /**
     * Getter for avgPr
     * @return avgPr the average price
     */
    public double getAvgPr() {
        return avgPr;
    }
    
    /**
     * Getter for stdDev
     * @return stdDev the standard deviation
     */
    public double getStdDev() {
        return stdDev;
    }
    
    /**
     * Getter for longUpTrend
     * @return longUpTrend the longest upward trend
     */
    public double getLongUpTrend() {
        return longUpTrend;
    }
    
    /**
     * Getter for bestGrowthRateUpTrend
     * @return bestGrowthRateUpTrend the best growth rate for the up trend
     */
    public double getBestGrowthRateUpTrend() {
        return bestGrowthRateUpTrend;
    }
    
    // formatted toString method
    
    /**
     * toString allows an object to be directly printed by returning a String
     * that can be printed to the console or to a file.
     *
     * @return a formatted string representing the values of the attributes for
     * a stock object.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        str.append(String.format("%-20s", coName)); // name
        str.append(String.format("%8.2f%8.2f%8.2f%8.2f%8.2f",
                lowPr, highPr, netChg, avgPr, stdDev)); // decimal
        
        str.append(String.format("%4d", longUpTrend)); // int
        
        if(prQuotes.length > 1) { // more than 1 price quotes
            str.append(String.format("%9.2f", 
                    bestGrowthRateUpTrend)); // bestTrRt
        } else { // 1 or less price quotes
            str.append(String.format("%8s", 
                    "N/A")); // bestTrRt
        }
        
        return str.toString(); // return formatted string
    }
    
    /**
     * Unit test for the Stock class.
     * expected output is below
     * @param args the command line arguments
     */
    public static void main(String[] args){
        double [] quotes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        String name = "A";
        Stock s = new Stock(name, quotes);
        System.out.println(s);                
    }
}