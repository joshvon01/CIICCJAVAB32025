package VCashAppArray;

import java.util.UUID;

/**
 * Represents a user object with a unique ID, name, PIN, and balance.
 */
public class User {
    private String id;
    private String name;
    private String pin;
    private double balance;

    public User(String name, String pin) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.pin = pin;
        this.balance = 0.0;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
}
