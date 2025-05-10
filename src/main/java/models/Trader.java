package main.java.models;

import main.java.exceptions.*;

/**
 * Represents a trader in the stock market simulation.
 * A trader can buy and sell stocks, manage a portfolio, and maintain a transaction history.
 */
public class Trader extends User implements TradeActions {
    private double balance;
    private TransactionHistory transactionHistory;

    /**
     * Constructs a Trader with the given name and initial balance.
     *
     * @param name           the trader's name
     * @param initialBalance the starting balance
     */
    public Trader(String name, double initialBalance) {
        super(name, UserType.TRADER);
        this.balance = initialBalance;
        this.transactionHistory = new TransactionHistory();
    }

    /**
     * Gets the current balance of the trader.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns a copy of the transaction history for this trader.
     *
     * @return a copy of the transaction history
     */
    public TransactionHistory getTransactionHistory() {
        return new TransactionHistory(transactionHistory);
    }

    /**
     * Buys a specified quantity of stock if the trader has enough balance.
     *
     * @param stock    the stock to buy
     * @param quantity the number of shares to buy
     * @throws InsufficientFundsException if the trader cannot afford the purchase
     */
    public void buyStock(Stock stock, int quantity) throws InsufficientFundsException {
        double totalCost = quantity * stock.getCurrentPrice();
        if (totalCost > balance) {
            throw new InsufficientFundsException(
                    String.format("Insufficient funds: %.2f needed, but balance is %.2f", totalCost, balance));
        }

        balance -= totalCost;
        portfolio.addStock(String.valueOf(stock), quantity);
        transactionHistory.addTransaction(new Transaction(name, stock.getSymbol(), quantity, stock.getCurrentPrice(), true));
        System.out.printf("Bought %d shares of %s at %.2f each.\n", quantity, stock.getSymbol(), stock.getCurrentPrice());
    }

    /**
     * Sells a specified quantity of stock if available in the portfolio.
     *
     * @param stock    the stock to sell
     * @param quantity the number of shares to sell
     * @throws InvalidQuantityException     if the quantity is not valid
     * @throws StockNotAvailableException   if the stock is not in the portfolio
     */
    public void sellStock(Stock stock, int quantity) throws InvalidQuantityException, StockNotAvailableException {
        portfolio.removeStock(String.valueOf(stock), quantity);
        balance += quantity * stock.getCurrentPrice();
        transactionHistory.addTransaction(new Transaction(name, stock.getSymbol(), quantity, stock.getCurrentPrice(), false));
        System.out.printf("Sold %d shares of %s at %.2f each.\n", quantity, stock.getSymbol(), stock.getCurrentPrice());
    }

    /**
     * Displays the trader's current portfolio.
     */
    public void viewPortfolio() {
        System.out.println("--- Portfolio of " + getName() + " ---");
        portfolio.display();
    }

}
