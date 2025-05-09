package main.models;

import main.exceptions.*;

public interface TradeActions {
    void buyStock(Stock stock, int quantity) throws InsufficientFundsException;
    void sellStock(Stock stock, int quantity) throws InvalidQuantityException, StockNotAvailableException;
    void viewPortfolio();
}
