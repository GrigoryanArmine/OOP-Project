package main.java.models;

import main.java.exceptions.StockNotAvailableException;
import java.util.ArrayList;

/**
 * Represents a stock market containing a list of stocks.
 * Allows adding stocks, retrieving them by symbol, and simulating price changes.
 */
public class Market {
    private ArrayList<Stock> stocks;

    /**
     * Constructs an empty Market.
     */
    public Market() {
        this.stocks = new ArrayList<>();
    }

    /**
     * Adds a stock to the market.
     *
     * @param stock the stock to add
     */
    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    /**
     * Returns a copy of the list of all stocks in the market.
     *
     * @return list of stocks
     */
    public ArrayList<Stock> getStocks() {
        return new ArrayList<>(stocks);
    }

    /**
     * Retrieves a stock from the market by its symbol.
     *
     * @param symbol the stock symbol
     * @return the corresponding stock
     * @throws StockNotAvailableException if no stock with the given symbol exists
     */
    public Stock getStock(String symbol) throws StockNotAvailableException {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) return stock;
        }
        throw new StockNotAvailableException(symbol);
    }

    /**
     * Simulates random price changes for all stocks in the market.
     * Each stock's price is randomly increased or decreased slightly.
     */
    public void simulatePriceChanges() {
        for (Stock stock : stocks) {
            double change = (Math.random() - 0.5) * 2; // Change between -1.0 and +1.0
            stock.updatePrice(stock.getCurrentPrice() + change);
        }
    }
}
