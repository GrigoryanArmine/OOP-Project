import java.util.Random;

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

    public String getUserId() {
        return userId;
    }

    private String generateID() {
        Random random = new Random();
        char firstLetter = (char) ('A' + random.nextInt(26));
        char secondLetter = (char) ('A' + random.nextInt(26));
        int digits = random.nextInt(1_000_000);
        String digitPart = String.format("%06d", digits);
        return "" + firstLetter + secondLetter + digitPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", UserID: " + userId + ", Type: " + type;
    }

    public abstract String getRoleDetails();
}
