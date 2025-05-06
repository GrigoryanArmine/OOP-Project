package exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(double balance, double required) {
        super(String.format("Insufficient funds. Balance: $%.2f, Required: $%.2f", balance, required));
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException() {
        this("Insufficient funds for this transaction");
    }
}