import java.util.ArrayList;
import java.util.List;

public class Trader extends User {
    private double balance;
    //private Portfolio portfolio;
    private List<Transaction> transactionHistory;

    public Trader(String name, double initialBalance) {
        super(name, UserType.TRADER);
        this.balance = initialBalance;
        //this.portfolio = new Portfolio();
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    //public Portfolio getPortfolio() {
        return portfolio;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void buyStock(Stock stock, int quantity, double pricePerUnit) throws InsufficientFundsException {
        double totalCost = quantity * pricePerUnit;
        if (totalCost > balance) {
            // throw new InsufficientFundsException("Not enough funds to buy " + quantity + " shares of " + stock.getSymbol());
        }
        balance -= totalCost;
        // portfolio.addStock(stock.getSymbol(), quantity);
        Transaction transaction = new Transaction(name, stock.getSymbol(), quantity, pricePerUnit);
        transactionHistory.add(transaction);
    }
    // Shoud me implented Stock class
    // public void sellStock(Stock stock, int quantity, double pricePerUnit) throws StockNotAvailableException {
    //     if (portfolio.hasStock(stock.getSymbol(), quantity)) {
    //         portfolio.removeStock(stock.getSymbol(), quantity);
    //         double totalGain = quantity * pricePerUnit;
    //         balance += totalGain;
    //         Transaction transaction = new Transaction(name, stock.getSymbol(), quantity, pricePerUnit);
    //         transactionHistory.add(transaction);
    //     } else {
    //         throw new StockNotAvailableException("You don't have enough shares of " + stock.getSymbol() + " to sell.");
    //     }
    // }

    @Override
    public String toString() {
        return super.toString() + "\nBalance: " + balance;
    }

    // public void viewPortfolio() {
    //     portfolio.display();
    // }
}
