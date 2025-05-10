package main.java.models;

import main.java.exceptions.InvalidQuantityException;
import java.util.ArrayList;

/**
 * Represents a portfolio of stocks held by a user.
 * Maintains a list of stock symbols and their corresponding quantities.
 */
public class Portfolio {
    private ArrayList<String> symbols;
    private ArrayList<Integer> quantities;
    private double totalValue;

    /**
     * Constructs an empty portfolio.
     */
    public Portfolio() {
        this.symbols = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.totalValue = 0.0;
    }

    /**
     * Constructs a copy of an existing portfolio.
     *
     * @param that the portfolio to copy
     */
    public Portfolio(Portfolio that) {
        this.symbols = new ArrayList<>(that.symbols);
        this.quantities = new ArrayList<>(that.quantities);
        this.totalValue = that.totalValue;
    }

    /**
     * Adds the specified quantity of a stock to the portfolio.
     * If the stock already exists, its quantity is increased.
     *
     * @param symbol   the stock symbol
     * @param quantity the number of shares to add
     * @throws InvalidQuantityException if the quantity is non-positive
     */
    public void addStock(String symbol, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be positive");
        int index = symbols.indexOf(symbol);
        if (index >= 0)
            quantities.set(index, quantities.get(index) + quantity);
        else {
            symbols.add(symbol);
            quantities.add(quantity);
        }
        updateTotalValue();
    }

    /**
     * Removes the specified quantity of a stock from the portfolio.
     * If the quantity reaches zero, the stock is removed entirely.
     *
     * @param symbol   the stock symbol
     * @param quantity the number of shares to remove
     * @throws InvalidQuantityException if the quantity is non-positive,
     *                                  stock does not exist, or not enough shares are held
     */
    public void removeStock(String symbol, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be positive");

        int index = symbols.indexOf(symbol);
        if (index < 0) {
            throw new InvalidQuantityException(symbol);
        }

        int current = quantities.get(index);
        if (current < quantity) throw new InvalidQuantityException(current, quantity);

        int updated = current - quantity;
        if (updated > 0) {
            quantities.set(index, updated);
        } else {
            symbols.remove(index);
            quantities.remove(index);
        }

        updateTotalValue();
    }

    /**
     * Returns the quantity of shares held for the given stock symbol.
     *
     * @param symbol the stock symbol
     * @return the number of shares held, or 0 if not owned
     */
    public int getQuantity(String symbol) {
        int index = symbols.indexOf(symbol);
        return (index >= 0) ? quantities.get(index) : 0;
    }

    /**
     * Checks if a specific stock is in the portfolio.
     *
     * @param symbol the stock symbol
     * @return true if the stock exists in the portfolio, false otherwise
     */
    public boolean containsStock(String symbol) {
        return symbols.contains(symbol);
    }

    /**
     * Returns a copy of the list of stock symbols in the portfolio.
     *
     * @return a list of stock symbols
     */
    public ArrayList<String> getSymbols() {
        return new ArrayList<>(symbols);
    }

    /**
     * Returns the total value of the portfolio.
     * @return total portfolio value
     */
    public double getTotalValue() {
        return totalValue;
    }

    /**
     * Updates the total value of the portfolio.
     */
    private void updateTotalValue() {
        totalValue = 0.0;
        for (int qty : quantities) {
            totalValue += qty; // Placeholder logic
        }
    }

    /**
     * Displays the contents of the portfolio to the console.
     * Shows each stock symbol with its quantity and the total value.
     */
    public void display() {
        if (symbols.isEmpty()) {
            System.out.println("Portfolio is empty");
            return;
        }
        System.out.println("\n=== Portfolio Summary ===");
        System.out.println("----------------------");
        for (int i = 0; i < symbols.size(); i++) {
            System.out.printf("%-10s | %-10d\n", symbols.get(i), quantities.get(i));
        }
        System.out.println("----------------------");
        System.out.printf("Total Value: %.2f\n\n", totalValue);
    }
}
