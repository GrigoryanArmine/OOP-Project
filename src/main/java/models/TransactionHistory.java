package main.java.models;

import java.io.*;
import java.util.*;
/**
 * Represents a history of stock transactions.
 * Supports adding transactions, filtering by trader name,
 * and saving/loading to/from a file.
 */
public class TransactionHistory {
     /** List of all transactions recorded. */
    private ArrayList<Transaction> transactions;
    
    /**
     * Constructs an empty transaction history.
     */
    public TransactionHistory() {
        transactions = new ArrayList<>();
    }
    
    /**
     * Constructs a copy of another TransactionHistory.
     *
     * @param that the TransactionHistory to copy
     */
    public TransactionHistory(TransactionHistory that) {
        this.transactions = new ArrayList<>(that.transactions);
    }
    
    /**
     * Adds a new transaction to the history.
     *
     * @param t the transaction to add
     */
    public void addTransaction(Transaction t){
        transactions.add(t);
    }
    
    /**
     * Returns a list of transactions made by a specific trader.
     *
     * @param traderName the name of the trader
     * @return a list of transactions associated with the given trader
     */
    public ArrayList<Transaction> getTransactionsByTrader(String traderName) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getTraderName().equals(traderName)) {
                result.add(t);
            }
        }
        return result;
    }
    
    /**
     * Saves all transactions to a file.
     * Each transaction is written using its {@code toString()} representation.
     *
     * @param filename the name of the file to write to
     * @throws IOException if an I/O error occurs
     */
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.write(t.toString());
                writer.newLine();
            }
        }
    }
    
    /**
     * Loads transactions from a file.
     * Note: This uses a basic parser and assumes a consistent format.
     * The current implementation ignores the transaction timestamp and always marks transactions as "buy".
     *
     * @param filename the name of the file to read from
     * @throws IOException if an I/O error occurs
     */
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
                transactions.add(new Transaction(trader, symbol, quantity, price, true));
            }
        }
    }
}
