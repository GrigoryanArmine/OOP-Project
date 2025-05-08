// Broker.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Broker extends User {
    private final List<Trader> clients = new ArrayList<>();

    public Broker(String name) {
        super(name, UserType.BROKER);
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

    /** Read‐only view of clients */
    public List<Trader> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public void viewClientPortfolio(Trader trader) {
        if (!clients.contains(trader)) {
            System.out.println("Trader " + trader.getName() + " is not a client");
            return;
        }
        System.out.printf("\n--- %s's Portfolio ---\n", trader.getName());
        trader.getPortfolio().display();
        System.out.printf("Current balance: $%.2f\n\n", trader.getBalance());
    }

    @Override
    public String getRoleDetails() {
        return "Licensed broker managing " + clients.size() + " clients";
    }

    @Override
    public String toString() {
        return super.toString()
             + String.format("\nClients managed: %d", clients.size());
    }
}


