import main.models.Market;
import main.models.Stock;
import main.models.Trader;

public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        market.addStock(new Stock("AAPL",150.0));
        market.addStock(new Stock("MSFT",250.0));

        Trader trader = new Trader("Harut", 1000.0);
        try {
            Stock apple = market.getStock("AAPL");
            trader.buyStock(apple, 2);
            System.out.println("Bought 2 AAPL. New balance: $" + trader.getBalance());

            market.simulatePriceChanges();
            System.out.println("AAPL new price: $" + apple.getCurrentPrice());

            trader.sellStock(apple, 2);
            System.out.println("Sold 1 AAPL. Balance: $" + trader.getBalance());
            apple.updatePrice(152.5);

            System.out.println(apple.getSymbol());
            System.out.println(apple.getCurrentPrice());
            System.out.println(apple.getPriceHistory());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
