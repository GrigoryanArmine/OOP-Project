package models;

import java.time.LocalDateTime;
import exceptions.InvalidQuantityException;

public class Transaction {
    private String traderName;
    private String stockSymbol;
    private int quantity;
    private double pricePerShare;
    private LocalDateTime time;
    private boolean isBuy;

    public Transaction(String traderName, String stockSymbol, int quantity, double pricePerShare, boolean isBuy) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException();
        this.quantity = quantity;
        this.stockSymbol = stockSymbol;
        this.traderName = traderName;
        this.pricePerShare = pricePerShare;
        this.time = LocalDateTime.now();
        this.isBuy = isBuy;
    }

    public String toString() {
        return "[" + time + "]" + traderName + (isBuy ? " bought " : " sold ") + quantity + " of " + stockSymbol + " at $" + pricePerShare;
    }
}
    public String getTraderName() {
        return traderName;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}

