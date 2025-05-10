package main.models;
import main.exceptions.InvalidQuantityException;
import java.util.ArrayList;

public class Portfolio {
    private ArrayList<String> symbols;
    private ArrayList<Integer> quantities;
    private double totalValue;

    public Portfolio() {
        this.symbols = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.totalValue = 0.0;
    }
    public Portfolio(Portfolio that) {
        this.symbols = new ArrayList<>(that.symbols);
        this.quantities = new ArrayList<>(that.quantities);
        this.totalValue = that.totalValue;
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
            throw new InvalidQuantityException(symbol);
        }

        int current = quantities.get(index);
        if (current < quantity) throw new InvalidQuantityException(current, quantity);

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
    public ArrayList<String> getSymbols() {
        return new ArrayList<>(symbols);
    }
    public double getTotalValue() {
        return totalValue;
    }

    private void updateTotalValue() {
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
        System.out.println("----------------------");
        for (int i = 0; i < symbols.size(); i++) {
            System.out.printf("%-10s | %-10d\n", symbols.get(i), quantities.get(i));
        }
        System.out.println("----------------------");
        System.out.printf("Total Value: %.2f\n\n", totalValue);
    }
}
