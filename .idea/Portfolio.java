import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks; // Maps stock symbol to quantity

    public Portfolio() {
        stocks = new HashMap<>();
    }

    public void addStock(String symbol, int quantity) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
    }

    public void removeStock(String symbol, int quantity) {
        int current = stocks.getOrDefault(symbol, 0);
        if (current >= quantity) {
            stocks.put(symbol, current - quantity);
        }
    }

    public boolean hasStock(String symbol, int quantity) {
        return stocks.getOrDefault(symbol, 0) >= quantity;
    }

    public void display() {
        System.out.println("Portfolio Holdings:");
        stocks.forEach((symbol, qty) -> 
            System.out.println(symbol + ": " + qty + " shares")
        );
    }
}
