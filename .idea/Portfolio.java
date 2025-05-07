// Portfolio.java
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<String, Integer> holdings;  // Stock symbol -> quantity
    private double totalValue;

    public Portfolio() {
        this.holdings = new HashMap<>();
        this.totalValue = 0.0;
    }

    public void addStock(String symbol, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        
        int currentQuantity = holdings.getOrDefault(symbol, 0);
        holdings.put(symbol, currentQuantity + quantity);
        updateTotalValue();
    }

    public void removeStock(String symbol, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        
        int currentQuantity = holdings.getOrDefault(symbol, 0);
        
        if (currentQuantity < quantity) {
            throw new InvalidQuantityException(
                String.format("Not enough shares. Requested: %d, Available: %d", 
                              quantity, currentQuantity)
            );
        }
        
        holdings.put(symbol, currentQuantity - quantity);
        if (holdings.get(symbol) == 0) {
            holdings.remove(symbol);
        }
        updateTotalValue();
    }

    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    public boolean containsStock(String symbol) {
        return holdings.containsKey(symbol);
    }

    public Map<String, Integer> getHoldings() {
        return new HashMap<>(holdings); // Return copy for encapsulation
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void updateTotalValue() {
        // This would typically be called with current market prices
        // For now, we'll just track the book value
        // In a real system, you'd inject a MarketPriceService here
        totalValue = holdings.entrySet().stream()
            .mapToDouble(entry -> {
                // In real implementation: market.getCurrentPrice(entry.getKey()) * entry.getValue()
                return entry.getValue(); // Placeholder - needs price integration
            })
            .sum();
    }

    public void display() {
        if (holdings.isEmpty()) {
            System.out.println("Portfolio is empty");
            return;
        }
        
        System.out.println("\n=== Portfolio Summary ===");
        System.out.printf("%-10s | %-10s | %-15s\n", "Symbol", "Quantity", "Value");
        System.out.println("----------------------------------");
        
        holdings.forEach((symbol, qty) -> 
            System.out.printf("%-10s | %-10d | $%-15.2f\n", 
                            symbol, qty, qty * 0.0) // Placeholder for price
        );
        
        System.out.println("----------------------------------");
        System.out.printf("Total Value: $%.2f\n\n", totalValue);
    }
}
