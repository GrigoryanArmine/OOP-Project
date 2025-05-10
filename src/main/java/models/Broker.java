package main.models;
import java.util.ArrayList;

public class Broker extends User implements TradeActions {
    private ArrayList<Trader> clients = new ArrayList<>();

    public Broker(String name) {
        super(name, UserType.BROKER);
        ArrayList<Trader> clients = new ArrayList<>();
        initializeDefaultTraders();
    }

    private void initializeDefaultTraders() {
        String[] names = {"Alice", "Charlie", "Dave", "Eve", "Brian"};
        for (int i = 0; i < names.length; i++) {
            double randomBalance = 10000 + (Math.random() * 90000);
            clients.add(new Trader(names[i], randomBalance));
        }

    }

    public void addClient(Trader trader) {
        if (!clients.contains(trader)) {
            clients.add(trader);
            System.out.println("Added client: " + trader.getName());
        }
    }

    public void removeClient(Trader trader) {
        if (clients.remove(trader)) {
            System.out.println("Removed client: " + trader.getName());
        }
    }

     public void buyStockForClient(Trader trader, Stock stock, int quantity) {
        if (clients.contains(trader)) {
            try {
                trader.buyStock(stock, quantity);
            } catch (Exception e) {
                System.out.println("Failed to buy stock for client: " + e.getMessage());
            }
        }
    }

    public void sellStockForClient(Trader trader, Stock stock, int quantity) {
        if (clients.contains(trader)) {
            try {
                trader.sellStock(stock, quantity);
            } catch (Exception e) {
                System.out.println("Failed to sell stock for client: " + e.getMessage());
            }
        }
    }
  
    public ArrayList<Trader> getClients() {
        return new ArrayList<>(clients);
    }

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
                    i + 1,  // Index number starting from 1
                    client.getName(),
                    client.getUserId(),
                    client.getBalance());
        }
        System.out.println("------------------------------------------------");
        System.out.println("Total clients: " + clients.size());
    }
    
    public String getRoleDetails() {
        return "Licensed broker managing " + clients.size() + " clients";
    }

    public String toString() {
        return super.toString()
             + String.format("\nClients managed: %d", clients.size());
    }
}

