import java.util.*;

public abstract class User {
    public enum UserType {TRADER, BROKER}
    private String name;
    private UserType type;
    private String userId;

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
        this.userId = generateID();
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public String toString {
        return "Name: " + name + ", userID = " + userId;
    }
}



    public String generateID() {
        // Generate two random uppercase letters
        char firstLetter = (char) ('A' + random.nextInt(26));
        char secondLetter = (char) ('A' + random.nextInt(26));

        // Generate six random digits
        int digits = random.nextInt(1_000_000); // from 0 to 999999
        String digitPart = String.format("%06d", digits);

        return "" + firstLetter + secondLetter + digitPart;
}
