package main.models;

import main.exceptions.*;

public class Trader extends User implements TradeActions {
    private double balance;
    private TransactionHistory transactionHistory;

    public Trader(String name, double initialBalance) {
        super(name, UserType.TRADER);
        this.balance = initialBalance;
        this.transactionHistory = new TransactionHistory();
    }

    public double getBalance() {
        return balance;
    }

    public TransactionHistory getTransactionHistory() {
        return new TransactionHistory(transactionHistory);
    }

 
    public void buyStock(Stock stock, int quantity) throws InsufficientFundsException {
        double totalCost = quantity * stock.getCurrentPrice();
        if (totalCost > balance) {
            throw new InsufficientFundsException(
                    String.format("Insufficient funds: %.2f needed, but balance is %.2f", totalCost, balance));
        }

        balance -= totalCost;
        portfolio.addStock(String.valueOf(stock), quantity);
        transactionHistory.addTransaction(new Transaction(stock, quantity, stock.getCurrentPrice(), "BUY"));
        System.out.printf("Bought %d shares of %s at %.2f each.\n", quantity, stock.getSymbol(), stock.getCurrentPrice());
    }

   
    public void sellStock(Stock stock, int quantity) throws InvalidQuantityException, StockNotAvailableException {
        portfolio.removeStock(String.valueOf(stock), quantity);
        balance += quantity * stock.getCurrentPrice();
        transactionHistory.addTransaction(new Transaction(stock, quantity, stock.getCurrentPrice(), "SELL"));
        System.out.printf("Sold %d shares of %s at %.2f each.\n", quantity, stock.getSymbol(), stock.getCurrentPrice());
    }

   
    public void viewPortfolio() {
        System.out.println("--- Portfolio of " + getName() + " ---");
        portfolio.display();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("%s deposited %.2f. New balance: %.2f\n", getName(), amount, balance);
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Cannot withdraw more than current balance.");
        }
        balance -= amount;
        System.out.printf("%s withdrew %.2f. New balance: %.2f\n", getName(), amount, balance);
    }

   
    public String getRoleDetails() {
        return null;
    }

   
    public void executeBuy(Stock stock, int quantity) throws InsufficientFundsException {

    }

    
    public void executeSell(Stock stock, int quantity) throws InvalidQuantityException {

    }

    
    public double calculateCommission(double tradeValue) {
        return 0;
    }
}
