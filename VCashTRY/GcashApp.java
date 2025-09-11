package VCashTRY;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

// User class to hold user information
class User {
    private String id;
    private String name;
    private String pin;
    private double balance;

    public User(String name, String pin) {
        this.id = UUID.randomUUID().toString(); // Generates a unique ID
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

// Transaction class to record transactions
class Transaction {
    private String transactionId;
    private String userId;
    private String type; // "cash-in" or "transfer"
    private double amount;
    private String senderId; 
    private String recipientId;

    // Constructor for cash-in transactions
    public Transaction(String userId, String type, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }
    
    // Constructor for transfer transactions
    public Transaction(String senderId, String recipientId, String type, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.type = type;
        this.amount = amount;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public String getUserId() { return userId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getSenderId() { return senderId; }
    public String getRecipientId() { return recipientId; }
}

// Main application class
public class GcashApp {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("Welcome to GcashApp!");
        
        while (currentUser == null) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0); // Terminate the program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Main menu after successful login
        mainMenu();
    }

    private static void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name) && user.getPin().equals(pin)) {
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getName() + "!");
                return;
            }
        }
        System.out.println("Login failed. Invalid name or PIN.");
    }

    private static void register() {
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        System.out.print("Create a 4-digit PIN: ");
        String pin = scanner.nextLine();

        // Basic PIN validation
        if (pin.length() != 4 || !pin.matches("\\d+")) {
            System.out.println("Invalid PIN. It must be a 4-digit number.");
            return;
        }

        User newUser = new User(name, pin);
        users.add(newUser);
        System.out.println("Registration successful! Your user ID is: " + newUser.getId());
    }

    private static void mainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Cash-in");
            System.out.println("3. Cash-transfer");
            System.out.println("4. View all transactions");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    performCashIn();
                    break;
                case "3":
                    performCashTransfer();
                    break;
                case "4":
                    viewAllTransactions();
                    break;
                case "5":
                    System.out.println("Logging out...");
                    currentUser = null;
                    main(null); // Restart the program from the beginning
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: " + currentUser.getBalance());
    }

    private static void performCashIn() {
        System.out.print("Enter amount to cash in: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            if (amount <= 0) {
                System.out.println("Invalid amount. Must be a positive number.");
                return;
            }
            currentUser.setBalance(currentUser.getBalance() + amount);
            transactions.add(new Transaction(currentUser.getId(), "cash-in", amount));
            System.out.println("Cash-in of " + amount + " successful. New balance is " + currentUser.getBalance());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void performCashTransfer() {
        System.out.print("Enter recipient's ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            if (amount <= 0) {
                System.out.println("Invalid amount. Must be a positive number.");
                return;
            }

            if (currentUser.getBalance() < amount) {
                System.out.println("Insufficient funds for transfer.");
                return;
            }

            User recipient = findUserById(recipientId);
            if (recipient == null) {
                System.out.println("Recipient not found.");
                return;
            }

            // Display current balance before transfer
            System.out.println("Current balance: " + currentUser.getBalance());

            // Perform transfer
            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);

            // Record a single transaction for the transfer
            transactions.add(new Transaction(currentUser.getId(), recipient.getId(), "transfer", amount));
            
            // Display new balance after transfer
            System.out.println("Transfer of " + amount + " to " + recipient.getName() + " successful.");
            System.out.println("New balance: " + currentUser.getBalance());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void viewAllTransactions() {
        System.out.println("\n--- Your Transaction History ---");
        boolean hasTransactions = false;
        for (Transaction txn : transactions) {
            if (txn.getUserId() != null && txn.getUserId().equals(currentUser.getId())) {
                // This is a cash-in transaction
                System.out.println("Type: Cash-In, Amount: " + txn.getAmount());
                hasTransactions = true;
            } else if (txn.getSenderId() != null && txn.getSenderId().equals(currentUser.getId())) {
                // This is a transfer sent
                User recipient = findUserById(txn.getRecipientId());
                System.out.println("Type: Transfer Sent, Amount: " + txn.getAmount() + ", To: " + (recipient != null ? recipient.getName() : "Unknown User"));
                hasTransactions = true;
            } else if (txn.getRecipientId() != null && txn.getRecipientId().equals(currentUser.getId())) {
                // This is a transfer received
                User sender = findUserById(txn.getSenderId());
                System.out.println("Type: Transfer Received, Amount: " + txn.getAmount() + ", From: " + (sender != null ? sender.getName() : "Unknown User"));
                hasTransactions = true;
            }
        }
        if (!hasTransactions) {
            System.out.println("No transactions found.");
        }
    }

    private static User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}