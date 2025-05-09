package main.models;

import main.exceptions.*;

public class Trader extends User {
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
                String.format("Insufficient funds: %.2f needed, but balance is %.2f", 
                              totalCost, balance));
        }
        
        balance -= totalCost;
        portfolio.addStock(stock.getSymbol(), quantity);
        transactionHistory.addTransaction(new Transaction(
            this.getName(),           
            stock.getSymbol(),        
            quantity,
                stock.getCurrentPrice(),
            true                    
        ));
    }

    public void sellStock(Stock stock, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity: " + quantity);
        }

        portfolio.removeStock(stock.getSymbol(), quantity);
        double totalRevenue = quantity * stock.getCurrentPrice();
        balance += totalRevenue;
        transactionHistory.addTransaction(new Transaction(
            this.getName(),          
            stock.getSymbol(),        
            quantity,                
            stock.getCurrentPrice(), 
            false                   
        ));
    }
    
    @Override
    public String getRoleDetails() {
        return "Professional stock trader with portfolio management capabilities";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nBalance: $%.2f", balance);
    }

}

