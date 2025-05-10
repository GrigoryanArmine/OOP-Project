package main.java;

import main.java.models.*;
import main.java.exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class serves as the entry point for the Stock Market Simulation application.
 * Users can log in as Traders or Brokers and interact with a simulated market.
 */
public class Main {
    private Market market;
    private Scanner scanner;
    private User currentUser;

    /**
     * Launches the stock market simulation.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }

    /**
     * Starts the system by initializing components and displaying the welcome menu.
     */
    private void start() {
        initializeSystem();
        showWelcomeMenu();
    }

    /**
     * Initializes the market with sample stocks and prepares input handling.
     */
    private void initializeSystem() {
        market = new Market();
        scanner = new Scanner(System.in);

        market.addStock(new Stock("AAPL", 150.0));
        market.addStock(new Stock("MSFT", 250.0));
        market.addStock(new Stock("GOOGL", 2800.0));
        market.addStock(new Stock("AMZN", 3300.0));
        market.addStock(new Stock("TSLA", 700.0));
    }

    /**
     * Displays the main menu for user login and system exit options.
     */
    private void showWelcomeMenu() {
        while (true) {
            System.out.println("\n=== Stock Market Simulation ===");
            System.out.println("1. Login as Trader");
            System.out.println("2. Login as Broker");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createTrader();
                case 2 -> createBroker();
                case 3 -> {
                    System.out.println("Exiting system...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prompts the user to create a Trader profile and launches the Trader menu.
     */
    private void createTrader() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        currentUser = new Trader(name, balance);
        System.out.println("\nWelcome, Trader " + name + "!");
        System.out.println("Your ID: " + currentUser.getUserId());
        traderMainMenu();
    }

    /**
     * Prompts the user to create a Broker profile and launches the Broker menu.
     */
    private void createBroker() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        currentUser = new Broker(name);
        System.out.println("\nWelcome, Broker " + name + "!");
        System.out.println("Your ID: " + currentUser.getUserId());
        brokerMainMenu();
    }

    /**
     * Displays and handles the Trader-specific options menu.
     */
    private void traderMainMenu() {
        Trader trader = (Trader) currentUser;

        while (true) {
            System.out.println("\n=== TRADER MENU ===");
            System.out.println("1. View Market Prices");
            System.out.println("2. View Portfolio");
            System.out.println("3. Buy Stocks");
            System.out.println("4. Sell Stocks");
            System.out.println("5. Transaction History");
            System.out.println("6. Advance to Next Day");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewMarketPrices();
                case 2 -> viewPortfolio(trader);
                case 3 -> buyStocks(trader);
                case 4 -> sellStocks(trader);
                case 5 -> viewTransactionHistory(trader);
                case 6 -> advanceMarket();
                case 7 -> {
                    currentUser = null;
                    showWelcomeMenu();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays and handles the Broker-specific options menu.
     */
    private void brokerMainMenu() {
        Broker broker = (Broker) currentUser;

        while (true) {
            System.out.println("\n=== BROKER MENU ===");
            System.out.println("1. View Market Prices");
            System.out.println("2. Add Client");
            System.out.println("3. Remove Client");
            System.out.println("4. View Client Portfolio");
            System.out.println("5. List All Clients");
            System.out.println("6. Advance to Next Day");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewMarketPrices();
                case 2 -> addClient(broker);
                case 3 -> removeClient(broker);
                case 4 -> viewClientPortfolio(broker);
                case 5 -> broker.listAllClients();
                case 6 -> advanceMarket();
                case 7 -> {
                    currentUser = null;
                    showWelcomeMenu();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Displays current stock prices along with price history information.
     */
    private void viewMarketPrices() {
        System.out.println("\n=== CURRENT MARKET PRICES ===");
        ArrayList<Stock> stocks = market.getStocks();
        for (Stock stock : stocks) {
            System.out.printf("%-6s: $%-10.2f (History: %d entries)\n",
                    stock.getSymbol(),
                    stock.getCurrentPrice(),
                    stock.getHistorySize());
        }
    }

    /**
     * Advances the simulation by one day and updates stock prices.
     */
    private void advanceMarket() {
        market.simulatePriceChanges();
        System.out.println("\nMarket prices updated for the new day!");
        viewMarketPrices();
    }

    /**
     * Displays the Trader's balance and portfolio holdings.
     *
     * @param trader The Trader whose portfolio is being viewed
     */
    private void viewPortfolio(Trader trader) {
        System.out.println("\n=== PORTFOLIO OVERVIEW ===");
        System.out.printf("Cash Balance: $%.2f\n", trader.getBalance());
        trader.getPortfolio().display();
    }

    /**
     * Allows the Trader to purchase stocks from the market.
     *
     * @param trader The Trader performing the purchase
     */
    private void buyStocks(Trader trader) {
        viewMarketPrices();
        System.out.print("\nEnter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();

        try {
            Stock stock = market.getStock(symbol);
            System.out.print("Enter quantity to buy: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            trader.buyStock(stock, quantity);
            System.out.printf("Successfully purchased %d shares of %s\n", quantity, symbol);
        } catch (StockNotAvailableException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Allows the Trader to sell stocks from their portfolio.
     *
     * @param trader The Trader performing the sale
     */
    private void sellStocks(Trader trader) {
        System.out.println("\n=== CURRENT HOLDINGS ===");
        trader.getPortfolio().display();

        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();

        try {
            Stock stock = market.getStock(symbol);
            System.out.print("Enter quantity to sell: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            trader.sellStock(stock, quantity);
            System.out.printf("Successfully sold %d shares of %s\n", quantity, symbol);
        } catch (StockNotAvailableException | InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Displays all transactions made by the Trader.
     *
     * @param trader The Trader whose transactions are being displayed
     */
    private void viewTransactionHistory(Trader trader) {
        System.out.println("\n=== TRANSACTION HISTORY ===");
        List<Transaction> transactions = trader.getTransactionHistory().getTransactionsByTrader(trader.getName());

        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }

        transactions.forEach(System.out::println);
    }

    /**
     * Allows the Broker to add a new Trader client.
     *
     * @param broker The Broker performing the operation
     */
    private void addClient(Broker broker) {
        System.out.print("\nEnter new trader's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Trader newTrader = new Trader(name, balance);
        broker.addClient(newTrader);
        System.out.println("Added new client: " + name);
    }

    /**
     * Allows the Broker to remove an existing Trader client.
     *
     * @param broker The Broker performing the operation
     */
    private void removeClient(Broker broker) {
        List<Trader> clients = broker.getClients();
        if (clients.isEmpty()) {
            System.out.println("No clients to remove.");
            return;
        }

        System.out.println("\nCurrent Clients:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, clients.get(i).getName());
        }

        System.out.print("Select client to remove (number): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= clients.size()) {
            broker.removeClient(clients.get(choice - 1));
        } else {
            System.out.println("Invalid selection.");
        }
    }

    /**
     * Allows the Broker to view a specific client's portfolio.
     *
     * @param broker The Broker performing the operation
     */
    private void viewClientPortfolio(Broker broker) {
        ArrayList<Trader> clients = broker.getClients();
        System.out.print("\nSelect client to view details (0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice > 0 && choice <= clients.size()) {
            Trader client = clients.get(choice - 1);
            broker.viewClientPortfolio(client);
        } else if (choice != 0) {
            System.out.println("Invalid selection.");
        }
    }
}
