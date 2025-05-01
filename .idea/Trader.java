import java.util.ArrayList;
import java.util.List;

public class Trader extends User {
    private double balance;
    //private Portfolio portfolio;
    private ArrayList<Transaction> transactionHistory;

    public Trader(String name, String userId, double initialBalance) {
        super(name, userId);
        this.balance = initialBalance;
        //this.portfolio = new Portfolio();
        //this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void buyStock(Stock stock, int quantity, double pricePerUnit) {
        double totalCost = quantity * pricePerUnit;
        if (totalCost >= balance) {
            //throws exception "Not enough funds to buy " + quantity + " shares of " + stock.getSymbol()
        }
        balance -= totalCost;
//        portfolio.addStock(stock, quantity);
//        Transaction transaction = new Transaction(this, stock, quantity, pricePerUnit, "BUY");
//        transactionHistory.add(transaction);

    }

    public void sellStock(Stock stock, int quantity, double pricePerUnit) throws StockNotAvailableException {
        if (portfolio.hasStock(stock, quantity)) {
            portfolio.removeStock(stock, quantity);
            double totalGain = quantity * pricePerUnit;
            balance += totalGain;
            //Transaction transaction = new Transaction(this, stock, quantity, pricePerUnit, "SELL");
            // transactionHistory.add(transaction);
        } else {
            throw new StockNotAvailableException("You don't have enough shares of " + stock.getSymbol() + " to sell.");
        }
    }

    public String toString {
        return super.toString + "/n Balance: " + balance);
    }

    public void viewPortfolio() {
        portfolio.display();
    }
}
