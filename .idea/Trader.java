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

    @Override
    public String toString() {
        return super.toString() + "\nBalance: " + balance;
    }

    // public void viewPortfolio() {
    //     portfolio.display();
    // }
}
