/**
 *                  Revision History (Newest First)
 * **************************************************************
 * 
 * 10/4/2019 - Added methods and finished class - Thomas Bahun
 * 10/2/2019 - Worked on Portfolio class and testing - Thomas Bahun
 * 09/29/2019 - Started work on Portfolio class - Thomas Bahun
 * 09/27/2019 - Created Portfolio class and drafted pseudocode - Thomas Bahun
 * 
 */

package stockportfolio;

import java.util.*;
import java.io.*;

/**
 * Course: CSCI 160
 * Class Description: A class representing a portfolio of Stocks, utilizing
 *  an ArrayList to hold Stock objects. It further outputs these object's
 *  content and the calculations done on their data. Before that it
 *  reads in a file with the data of the stocks and stores it in an
 *  ArrayList.
 * Project Name: Stock Portfolio Manager
 * Due Date: October 5, 2019
 * Depends on:
 * Extends:
 * Implements:
 * @author thomas
 */
public class StockPortfolio {
    
    /**
     * ArrayList holding all Stock objects
     */
    private ArrayList<Stock> portfolioOfStocks = new ArrayList<>();
    
    /**
     * Adds a new Stock to the ArrayList portfolioOfStocks
     * @param s a Stock object.
     */
    public void addStock(Stock s) {
        this.portfolioOfStocks.add(s);
    }
    
    /**
     * Accessor for the ArrayList
     * @param i the index of the element we want
     * @return the Stock object at location i
     */
    public Stock getStockAt(int i) {
        return portfolioOfStocks.get(i);
    }
    
    /**
     * Gets the count, number of objects in the portfolio
     * @return portfolioOfStocks.size() the number of objects in the portfolio
     */
    public int getSize() {
        return portfolioOfStocks.size();
    }
    
    /**
     * Unit test for Portfolio class
     * expected output is below
     * @param args
     */
    public static void main(String[] args){
        StockPortfolio portfolio = new StockPortfolio();
        double [] quotes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        String name = "A";
        portfolio.addStock(new Stock(name, quotes));
        name = "B";
        double[] quotes2 = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
        portfolio.addStock(new Stock(name, quotes2));
        System.out.println(portfolio);
    }
}
