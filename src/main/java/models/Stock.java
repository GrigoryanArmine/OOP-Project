import java.util.ArrayList;
import java.util.Random;
import java.util.Exceptions;

public class Stock {
    private final String symbol;
    private final String companyName;
    private double currentPrice;
    private final ArrayList<Double> priceHistory;
    private final Random random;
    private final double volatility;

    public Stock(String symbol, String companyName, double initialPrice, double volatility) {
        if (volatility < 0 || volatility > 1) {
            throw new IllegalArgumentException("Volatility must be between 0 and 1");
        }
        if (initialPrice <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = initialPrice;
        this.volatility = volatility;
        this.random = new Random();
        this.priceHistory = new ArrayList<>();
        this.priceHistory.add(initialPrice);
    }

    public synchronized void updatePrice() {
        double changePercent = (random.nextDouble() * 2 - 1) * volatility;
        double changeAmount = currentPrice * changePercent;
        currentPrice = Math.max(0.01, currentPrice + changeAmount);
        priceHistory.add(currentPrice);
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public ArrayList<Double> getHistoricalPrices() {
        return new ArrayList<>(priceHistory);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f", companyName, symbol, currentPrice);
    }
}
