
package com.oopproject;

import java.time.LocalDateTime;

public class Transaction
{
    public enum Type {
        BUY, SELL
    }

    private String traderName;
    private Stock stock;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;
    private Type type;

    public Transaction(String traderName, Stock stock, int quantity, double price, Type type) {
        this.traderName = traderName;
        this.stock = stock;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.timestamp = LocalDateTime.now();
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

    @Override
    public String toString() {
        return traderName + "," + stock.getSymbol() + "," + quantity + "," + price + "," + type + "," + timestamp;
    }
}

