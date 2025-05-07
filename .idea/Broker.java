
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Broker extends User {
    private final List<Trader> clients;

    public Broker(String name) {
        super(name, UserType.BROKER);
        this.clients = new ArrayList<>();
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

    public List<Trader> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public void viewClientPortfolio(Trader trader) {
        if (clients.contains(trader)) {
            System.out.println("\nPortfolio for " + trader.getName() + ":");
            trader.viewPortfolio();
            System.out.println("Current balance: $" + trader.getBalance());
        } else {
            System.out.println("Trader " + trader.getName() + " is not a client");
        }
    }

    @Override
    public String getRoleDetails() {
        return "Licensed broker managing " + clients.size() + " clients";
    }

    @Override
    public String toString() {
        return super.toString() + "\nClients managed: " + clients.size();
    }
}

