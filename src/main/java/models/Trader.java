package models;

import exceptions.*;
import java.util.ArrayList;

public class Trader {
    private String name;
    private double balance;
    private ArrayList<Stock> portfolio;

    public Trader(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) throws InsufficientFundsException, InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException(quantity);
        double totalCost = stock.getCurrentPrice() * quantity;
        if (balance < totalCost) throw new InsufficientFundsException(balance, totalCost);

        balance -= totalCost;
        for (int i = 0; i < quantity; i++) {
            portfolio.add(stock);
        }
    }
    public void sellStock(Stock stock, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException(quantity);

        int owned = 0;
        for (Stock s : portfolio) {
            if (s.getSymbol().equals(stock.getSymbol())) owned++;
        }
        if (owned < quantity) throw new InvalidQuantityException("Not enough shares to sell");

        int removed = 0;
        for (int i = 0; i < portfolio.size() && removed < quantity; i++) {
            if (portfolio.get(i).getSymbol().equals(stock.getSymbol())) {
                portfolio.remove(i);
                removed++;
                i--;
            }
        }
        balance += stock.getCurrentPrice() * quantity;
    }

    public double getBalance() { return balance; }
    public ArrayList<Stock> getPortfolio() { return portfolio; }
}