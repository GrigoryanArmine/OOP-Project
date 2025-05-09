package main.models;


import main.exceptions.InvalidQuantityException;
import java.time.LocalDateTime;

public class Transaction {
    private String traderName;
    private String stockSymbol;
    private int quantity;
    private double pricePerShare;
    private LocalDateTime time;
    private boolean isBuy;

    public Transaction(String traderName, String stockSymbol, int quantity, double pricePerShare, boolean isBuy) throws InvalidQuantityException{
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be positive");
        this.traderName = traderName;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.time = LocalDateTime.now();
        this.isBuy = isBuy;
    }

    // Getters
    public String getTraderName() {
        return traderName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isBuy() {
        return isBuy;
    }

    // Setters
    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.quantity = quantity;
    }

    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    @Override
    public String toString() {
        return "[" + time + "] " + traderName + (isBuy ? " bought " : " sold ") + quantity + " of " + stockSymbol + " at $" + pricePerShare;
    }
}
