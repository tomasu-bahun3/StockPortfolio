/**
 *                  Revision History (Newest First)
 * **************************************************************
 * 
 * 10/05/19 - finished driver and project - Thomas Bahun
 * 10/04/19 - created the driver class - Thomas Bahun
 * 
 */

package stockportfolio;

import java.util.*;
import java.io.*;

/**
 * Course: CSCI 160
 * Class Description: A class to call and run the other classes
 * Project Name: Stock Portfolio Manager
 * Due Date: October 5, 2019
 * Depends on:
 * Extends:
 * Implements:
 * @author thomas
 */
public class StockPortfolioDriver {
    
    /**
     * StockPortfolio holding all Stock objects
     */
    private StockPortfolio portfolio = new StockPortfolio();
    
    /**
     * The driver for the program, reads in and stores data from file
     * @param fileName filename from the command line arguments
     */
    public void run(String fileName) {
        // now try to connect the sybolic name to the physical file
        try {
            // if the physical file doesn't exist it throws an exception
            Scanner inFile = new Scanner(new FileReader(fileName));
            String[] tokens = null;
            
            // while the file has more data
            while (inFile.hasNext()) {
                String name = inFile.nextLine(); // takes name
                String line = inFile.nextLine(); // takes all prices
                tokens = line.split("[ \\s]"); // splits line                
                double[] prices = new double[tokens.length];
                
                for (int j = 0; j < tokens.length; j++) {
                    prices[j] = Double.parseDouble(tokens[j]);
                    // System.out.println(prices[j]); // Testing
                }
                
                portfolio.addStock(new Stock(name, prices));
                
            }
            inFile.close();
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.out.println("File data.txt not found");
            // and exit in a controlled manner
            System.exit(1);
        }
        
        StringBuilder str = new StringBuilder(); // str for header
        
        str.append(String.format("%-20s%8s%8s%8s%8s%8s%5s%10s",
                "Company", "Low", "High", "Net", "Avg", "Dev",
                "Lng", "BestTrRt"));
        
        System.out.println(str); // print header
        
        // loop to print each stock in the portfolio
        for(int i = 0; i < portfolio.getSize(); i++) {
            System.out.println(portfolio.getStockAt(i));
        }
    }
    
    /**
     * Main method for StockPortfolioDriver Class
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: progname inputFile");
            System.exit(1);
        }
        StockPortfolioDriver driver = new StockPortfolioDriver();
        driver.run(args[0]);
    }
}
