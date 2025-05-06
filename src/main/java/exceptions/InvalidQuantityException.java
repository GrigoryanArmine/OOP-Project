package exceptions;

public class InvalidQuantityException extends RuntimeException {
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