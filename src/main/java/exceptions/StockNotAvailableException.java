package main.java.exceptions;
public class StockNotAvailableException extends RuntimeException {
    public StockNotAvailableException(String symbol) {
        super("Stock not available: " + symbol);
    }

    public StockNotAvailableException() {
        super("Stock not available");
    }
}
