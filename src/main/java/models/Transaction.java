package main.java.models;

import main.java.exceptions.InvalidQuantityException;
import java.time.LocalDateTime;

/**
 * Represents a stock transaction performed by a trader.
 * Includes details such as trader name, stock symbol, quantity, price, timestamp, and whether it is a buy or sell.
 */
public class Transaction {
    /** The name of the trader who performed the transaction. */
    private String traderName;

    /** The symbol of the stock being traded. */
    private String stockSymbol;

    /** The number of shares involved in the transaction. */
    private int quantity;

    /** The price per share at the time of the transaction. */
    private double pricePerShare;

    /** The timestamp when the transaction was created. */
    private LocalDateTime time;

    /** Indicates whether the transaction is a buy (true) or sell (false). */
    private boolean isBuy;

    /**
     * Constructs a new Transaction object.
     *
     * @param traderName     the name of the trader
     * @param stockSymbol    the stock symbol involved in the transaction
     * @param quantity       the number of shares traded (must be positive)
     * @param pricePerShare  the price per share at the time of transaction
     * @param isBuy          true if it's a buy transaction, false if it's a sell
     * @throws InvalidQuantityException if the quantity is not positive
     */
    public Transaction(String traderName, String stockSymbol, int quantity, double pricePerShare, boolean isBuy) throws InvalidQuantityException {
        if (quantity <= 0) throw new InvalidQuantityException("Quantity must be positive");
        this.traderName = traderName;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.time = LocalDateTime.now();
        this.isBuy = isBuy;
    }

    /** @return the trader's name */
    public String getTraderName() {
        return traderName;
    }

    /** @return the stock symbol involved in the transaction */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /** @return the quantity of shares traded */
    public int getQuantity() {
        return quantity;
    }

    /** @return the price per share at the time of the transaction */
    public double getPricePerShare() {
        return pricePerShare;
    }

    /** @return the timestamp of the transaction */
    public LocalDateTime getTime() {
        return time;
    }

    /** @return true if it was a buy transaction, false otherwise */
    public boolean isBuy() {
        return isBuy;
    }

    /**
     * Sets the trader's name.
     * @param traderName the trader's name
     */
    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    /**
     * Sets the stock symbol.
     * @param stockSymbol the stock's symbol
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     * Sets the quantity of shares. Must be positive.
     *
     * @param quantity the number of shares
     * @throws IllegalArgumentException if quantity is not positive
     */
    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.quantity = quantity;
    }

    /**
     * Sets the price per share.
     * @param pricePerShare the price per share
     */
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    /**
     * Sets the transaction time.
     * @param time the time of the transaction
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Sets whether the transaction is a buy or sell.
     * @param isBuy true if buy, false if sell
     */
    public void setBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return a string describing the transaction details
     */
    @Override
    public String toString() {
        return "[" + time + "] " + traderName + (isBuy ? " bought " : " sold ") +
               quantity + " of " + stockSymbol + " at $" + pricePerShare;
    }
}
