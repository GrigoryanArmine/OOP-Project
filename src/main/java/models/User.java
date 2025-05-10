package main.java.models;

import java.util.Random;

/**
 * Abstract base class representing a user in the stock market simulation.
 * Can be extended by specific user types such as Trader or Broker.
 */
public abstract class User {

    /**
     * Enumeration to define the type of user.
     */
    public enum UserType {TRADER, BROKER}

    private String name;
    private UserType type;
    private String userId;
    protected Portfolio portfolio;

    /**
     * Constructs a new User with the specified name and type.
     *
     * @param name the name of the user
     * @param type the type of the user (TRADER or BROKER)
     */
    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
        this.userId = generateID();
        this.portfolio = new Portfolio();
    }

    /**
     * Gets the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique user ID.
     *
     * @return the user's ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Generates a unique ID consisting of two random uppercase letters followed by six digits.
     *
     * @return a randomly generated user ID
     */
    private String generateID() {
        Random random = new Random();
        char firstLetter = (char) ('A' + random.nextInt(26));
        char secondLetter = (char) ('A' + random.nextInt(26));
        int digits = random.nextInt(1_000_000);
        String digitPart = String.format("%06d", digits);
        return "" + firstLetter + secondLetter + digitPart;
    }

    /**
     * Checks if two users are equal based on their user ID.
     *
     * @param o the object to compare with
     * @return true if the user IDs match, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    /**
     * Returns a string representation of the user including name, ID, and type.
     *
     * @return string representation of the user
     */
    @Override
    public String toString() {
        return "Name: " + name + ", UserID: " + userId + ", Type: " + type;
    }

    /**
     * Returns details specific to the user's role. Implemented by subclasses.
     *
     * @return a string describing role-specific details
     */
    public abstract String getRoleDetails();

    /**
     * Gets the user's portfolio.
     * 
     * @return user's actual portfolio
     */
    public Portfolio getPortfolio() {
        return new Portfolio(portfolio); 
    }
}
