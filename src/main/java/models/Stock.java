package main.models;

import java.util.ArrayList;

public class Stock {
    private String symbol;
    private double currentPrice;
    private ArrayList<Double> priceHistory;
    private int historySize;

    public Stock(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.currentPrice = initialPrice;
        this.priceHistory = new ArrayList<>();
        this.priceHistory.add(initialPrice);
        this.historySize = 1;
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
        this.priceHistory.add(newPrice);
        this.historySize++;
    }


    public String getSymbol() { return symbol; }
    public double getCurrentPrice() { return currentPrice; }
    public ArrayList<Double> getPriceHistory() { return priceHistory; }
    public int getHistorySize() {return historySize;}
}

