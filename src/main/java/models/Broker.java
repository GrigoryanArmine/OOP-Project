package main.java.models;

import java.util.ArrayList;

/**
 * Represents a broker in the stock market simulation.
 * A broker can manage multiple trader clients and perform trades on their behalf.
 */
public class Broker extends User implements TradeActions {

    private ArrayList<Trader> clients = new ArrayList<>();

    /**
     * Constructs a broker with the given name.
     * Initializes the broker with default demo clients.
     *
     * @param name the name of the broker
     */
    public Broker(String name) {
        super(name, UserType.BROKER);
        initializeDefaultTraders();
    }

    /**
     * Initializes the broker with a default list of clients (for demo purposes).
     */
    private void initializeDefaultTraders() {
        String[] names = {"Alice", "Charlie", "Dave", "Eve", "Brian"};
        for (String clientName : names) {
            double randomBalance = 10000 + (Math.random() * 90000);
            clients.add(new Trader(clientName, randomBalance));
        }
    }

    /**
     * Adds a trader as a client if not already managed by the broker.
     *
     * @param trader the trader to add
     */
    public void addClient(Trader trader) {
        if (!clients.contains(trader)) {
            clients.add(trader);
            System.out.println("Added client: " + trader.getName());
        }
    }

    /**
     * Removes a trader from the list of clients.
     *
     * @param trader the trader to remove
     */
    public void removeClient(Trader trader) {
        if (clients.remove(trader)) {
            System.out.println("Removed client: " + trader.getName());
        }
    }

    /**
     * Buys a stock for a specific client.
     *
     * @param trader   the client
     * @param stock    the stock to buy
     * @param quantity the quantity to buy
     */
    public void buyStockForClient(Trader trader, Stock stock, int quantity) {
        if (clients.contains(trader)) {
            try {
                trader.buyStock(stock, quantity);
            } catch (Exception e) {
                System.out.println("Failed to buy stock for client: " + e.getMessage());
            }
        }
    }

    /**
     * Sells a stock for a specific client.
     *
     * @param trader   the client
     * @param stock    the stock to sell
     * @param quantity the quantity to sell
     */
    public void sellStockForClient(Trader trader, Stock stock, int quantity) {
        if (clients.contains(trader)) {
            try {
                trader.sellStock(stock, quantity);
            } catch (Exception e) {
                System.out.println("Failed to sell stock for client: " + e.getMessage());
            }
        }
    }

    /**
     * Returns a copy of the list of all clients managed by the broker.
     *
     * @return list of traders
     */
    public ArrayList<Trader> getClients() {
        return new ArrayList<>(clients);
    }

    /**
     * Displays the portfolio details of a specific client.
     *
     * @param trader the client whose portfolio to display
     */
    public void viewClientPortfolio(Trader trader) {
        if (!clients.contains(trader)) {
            System.out.println("Trader " + trader.getName() + " is not a client");
            return;
        }
        System.out.printf("\n=== %s's PORTFOLIO DETAILS ===\n", trader.getName());
        System.out.println("Client ID: " + trader.getUserId());
        System.out.println("-----------------------------");
        trader.getPortfolio().display();
        System.out.println("-----------------------------");
        System.out.printf("Cash Balance: $%.2f\n", trader.getBalance());
        System.out.println("-----------------------------");
    }

    /**
     * Lists all clients managed by the broker in a formatted summary.
     */
    public void listAllClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients available.");
            return;
        }

        System.out.println("\n=== CLIENT SUMMARY LIST ===");
        System.out.println("------------------------------------------------");
        System.out.printf("%-5s | %-20s | %-10s | %-15s\n", "No.", "Name", "ID", "Balance");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < clients.size(); i++) {
            Trader client = clients.get(i);
            System.out.printf("%-5d | %-20s | %-10s | $%-15.2f\n",
                    i + 1,
                    client.getName(),
                    client.getUserId(),
                    client.getBalance());
        }

        System.out.println("------------------------------------------------");
        System.out.println("Total clients: " + clients.size());
    }

    /**
     * Returns a description of the broker's role, including number of clients managed.
     *
     * @return broker role details
     */
    public String getRoleDetails() {
        return "Licensed broker managing " + clients.size() + " clients";
    }

    /**
     * Returns a string representation of the broker, including number of clients.
     *
     * @return string with broker details
     */
    @Override
    public String toString() {
        return super.toString()
             + String.format("\nClients managed: %d", clients.size());
    }
}
