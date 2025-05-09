package main.exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(double quantity, double current) {
        super(String.format("Insufficient quantity. Current: $%.2f, Required: $%.2f", current, quantity));
    }
    public InvalidQuantityException(double quantity) {
        super("Invalid quantity: " + quantity);
    }

    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException() {
        this("Quantity must be a positive number");
    }
}
