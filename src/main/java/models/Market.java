package models;

import exceptions.StockNotAvailableException;
import java.util.ArrayList;

public class Market {
    private ArrayList<Stock> stocks;

    public Market() {
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public Stock getStock(String symbol) throws StockNotAvailableException {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) return stock;
        }
        throw new StockNotAvailableException(symbol);
    }

    public void simulatePriceChanges() {
        for (Stock stock : stocks) {
            double change = (Math.random() - 0.5) * 2;
            stock.updatePrice(stock.getCurrentPrice() + change);
        }
    }
}