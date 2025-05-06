import java.util.List;

public class Broker extends User {
    private List<Trader> clients;

    public Broker(String name) {
        super(name, UserType.BROKER);
        this.clients = new ArrayList<>();
    }

    public void addClient(Trader trader) {
        if (!clients.contains(trader)) {
            clients.add(trader);
        }
    }

    public void removeClient(Trader trader) {
        clients.remove(trader);
    }

    public List<Trader> getClients() {
        return clients;
    }

    public void viewClientPortfolio(Trader trader) {
        if (clients.contains(trader)) {
            //trader.viewPortfolio();
        } else {
            System.out.println("Trader " + trader.getName() + " is not a client of this broker.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Role: BROKER, Clients: " + clients.size();
    }
}
