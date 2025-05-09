package models;
import java.util.ArrayList;


public class Trader extends User {
    private double balance;
    private ArrayList<Stock> portfolio;
    private final TransactionHistory transactionHistory;

    public Trader(String name, double initialBalance) {
        super(name, UserType.TRADER);
        this.balance = initialBalance;
        this.portfolio = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }
    public ArrayList<Stock> getPortfolio() { return portfolio; }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory.getTransactionsByTrader(this.getName());
    }

    public void buyStock(Stock stock, int quantity, double pricePerUnit) throws InsufficientFundsException {
        double totalCost = quantity * pricePerUnit;
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
            pricePerUnit,             
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
