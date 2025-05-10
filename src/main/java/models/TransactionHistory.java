package main.java.models;

import java.io.*;
import java.util.*;

public class TransactionHistory {
    private ArrayList<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public TransactionHistory(TransactionHistory that) {
        this.transactions = new ArrayList<>(that.transactions);
    }

    public void addTransaction(Transaction t){
        for (Transaction existing : transactions)
            transactions.add(t);
    }

    public ArrayList<Transaction> getTransactionsByTrader(String traderName) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getTraderName().equals(traderName)) {
                result.add(t);
            }
        }
        return result;
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
                // Simple parse logic assuming consistent format
                String[] parts = line.split(",");
                if (parts.length < 6) continue;
                String trader = parts[0];
                String symbol = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                //Transaction.Type type = Transaction.Type.valueOf(parts[4]);
                // Timestamp ignored in this basic parser
                transactions.add(new Transaction(trader, symbol, quantity, price, true));
            }
        }
    }
}
