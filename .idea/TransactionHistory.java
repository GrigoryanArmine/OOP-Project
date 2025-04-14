import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) throws DuplicateTransactionException {
        if (transactions.contains(t)) {
            throw new DuplicateTransactionException("Duplicate transaction detected.");
        }
        transactions.add(t);
    }

    public List<Transaction> getTransactionsByTrader(String traderName) {
        return transactions.stream()
                .filter(t -> t.getTraderName().equals(traderName))
                .collect(Collectors.toList());
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.write(t.toString());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        transactions.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromString(line));
            }
        }
    }
}
