import java.util.ArrayList;

public class Market {
    private ArrayList<Stock> stocks = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Market() {
        // Initialize with 3 sample stocks
        stocks.add(new Stock("AAPL", "Apple", 150.0, 0.05));
        stocks.add(new Stock("GOOG", "Google", 2800.0, 0.04));
        stocks.add(new Stock("MSFT", "Microsoft", 300.0, 0.03));
    }

    public void updateAllPrices() {
        for (Stock stock : stocks) {
            stock.updatePrice();
        }
    }

    public Stock getStock(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null; // Stock not found
    }

    public boolean buy(String traderId, String symbol, int shares) {
        Stock stock = getStock(symbol);
        if (stock == null || shares <= 0) return false;
        
        transactions.add(new Transaction(traderId, stock, shares, stock.getCurrentPrice(), Transaction.Type.BUY));
        return true;
    }

    public boolean sell(String traderId, String symbol, int shares) {
        Stock stock = getStock(symbol);
        if (stock == null || shares <= 0) return false;
        
        transactions.add(new Transaction(traderId, stock, shares, stock.getCurrentPrice(), Transaction.Type.SELL));
        return true;
    }

    public void printStocks() {
        System.out.println("\n=== MARKET STOCKS ===");
        for (Stock stock : stocks) {
            System.out.printf("%s (%s): $%.2f%n", 
                stock.getCompanyName(), 
                stock.getSymbol(), 
                stock.getCurrentPrice());
        }
    }

    public void printTransactions() {
        System.out.println("\n=== TRANSACTION HISTORY ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
