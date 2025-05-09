import main.models.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        market.addStock(new Stock("AAPL", 150.0));
        market.addStock(new Stock("MSFT", 250.0));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Choose role (1-Trader, 2-Broker): ");
        int roleChoice = scanner.nextInt();

        User user;
        if (roleChoice == 1) {
            System.out.print("Enter initial balance: ");
            double balance = scanner.nextDouble();
            user = new Trader(name, balance);
        } else {
            user = new Broker(name);
        }

        System.out.println("Welcome, " + user.getRoleDetails());

        if (user instanceof Trader trader) {
            handleTraderLogic(trader, market);
        } else {
            System.out.println("Broker functionality coming soon!");
        }
    }

    private static void handleTraderLogic(Trader trader, Market market) {
        try {
            Stock apple = market.getStock("AAPL");
            trader.buyStock(apple, 2);
            System.out.println("Bought 2 AAPL. New balance: $" + trader.getBalance());

            market.simulatePriceChanges();
            System.out.println("AAPL new price: $" + apple.getCurrentPrice());

            trader.sellStock(apple, 2);
            System.out.println("Sold 2 AAPL. Balance: $" + trader.getBalance());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
