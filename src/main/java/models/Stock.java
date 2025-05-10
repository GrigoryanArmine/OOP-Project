package main.java.models;

import java.util.ArrayList;

/**
 * Represents a stock with a symbol, current price, and price history.
 */
public class Stock {
    /** The ticker symbol of the stock (e.g., "AAPL"). */
    private String symbol;

    /** The most recent price of the stock. */
    private double currentPrice;

    /** A list of historical prices for the stock, including the initial price. */
    private ArrayList<Double> priceHistory;

    /** The number of price entries recorded for the stock. */
    private int historySize;

    /**
     * Constructs a Stock object with the given symbol and initial price.
     *
     * @param symbol       the ticker symbol of the stock
     * @param initialPrice the starting price of the stock
     */
    public Stock(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.currentPrice = initialPrice;
        this.priceHistory = new ArrayList<>();
        this.priceHistory.add(initialPrice);
        this.historySize = 1;
    }

    /**
     * Updates the current price of the stock and records it in the price history.
     *
     * @param newPrice the new price to set
     */
    public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
        this.priceHistory.add(newPrice);
        this.historySize++;
    }

    /**
     * Returns the stock's symbol.
     *
     * @return the ticker symbol of the stock
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the current price of the stock.
     *
     * @return the most recent price
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Returns the full list of historical prices of the stock.
     *
     * @return an ArrayList of prices
     */
    public ArrayList<Double> getPriceHistory() {
        return priceHistory;
    }

    /**
     * Returns the total number of price updates recorded for the stock.
     *
     * @return the number of entries in the price history
     */
    public int getHistorySize() {
        return historySize;
    }
}
