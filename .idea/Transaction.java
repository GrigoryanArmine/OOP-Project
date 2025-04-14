import java.time.LocalDateTime;

public class Transaction {
    private String traderName;
    private String stockSymbol;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;

    public Transaction(String traderName, String stockSymbol, int quantity, double price) {
        this.traderName = traderName;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public String getTraderName() {
        return traderName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return traderName + "," + stockSymbol + "," + quantity + "," + price + "," + timestamp;
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        return new Transaction(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Double.parseDouble(parts[3])
        );
    }
}
