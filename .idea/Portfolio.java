// Portfolio.java
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private final ArrayList<String> symbols;
    private final List<Integer> quantities;
    private double totalValue;

    public Portfolio() {
        this.symbols = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.totalValue = 0.0;
    }

    public void addStock(String symbol, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be positive");
        int index = symbols.indexOf(symbol);
        if (index >= 0)
            quantities.set(index, quantities.get(index) + quantity);
        else {
            symbols.add(symbol);
            quantities.add(quantity);
        }
        updateTotalValue();
    }

    public void removeStock(String symbol, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be positive");
        
        int index = symbols.indexOf(symbol);
        if (index < 0) {
            throw new IllegalArgumentException("Stock symbol not found: " + symbol);
        }

        int current = quantities.get(index);
        if (current < quantity) {
            throw new IllegalArgumentException(Not enough shares. Requested: " + quantity + " , Available: " + current)
            );
        }

        int updated = current - quantity;
        if (updated > 0) {
            quantities.set(index, updated);
        } else {
            symbols.remove(index);
            quantities.remove(index);
        }

        updateTotalValue();
    }

    public int getQuantity(String symbol) {
        int index = symbols.indexOf(symbol);
        return (index >= 0) ? quantities.get(index) : 0;
    }
    
    public boolean containsStock(String symbol) {
        return symbols.contains(symbol);
    }
    public List<String> getSymbols() {
        return new ArrayList<>(symbols);
    }
    public double getTotalValue() {
        return totalValue;
    }

    public void updateTotalValue() {
        totalValue = 0.0;
        for (int qty : quantities) {
            // Placeholder: add quantity; integrate price lookup as needed
            totalValue += qty;
        }
    }

    public void display() {
        if (symbols.isEmpty()) {
            System.out.println("Portfolio is empty");
            return;
        }
        System.out.println("\n=== Portfolio Summary ===");
        System.out.printf("%-10s | %-10s\n", "Symbol", "Quantity");
        System.out.println("----------------------");
        for (int i = 0; i < symbols.size(); i++) {
            System.out.printf("%-10s | %-10d\n", symbols.get(i), quantities.get(i));
        }
        System.out.println("----------------------");
        System.out.printf("Total Value: %.2f\n\n", totalValue);
    }
}
