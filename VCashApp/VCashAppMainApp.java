package VCashApp;

import java.util.Scanner;
import java.util.List;

public class VCashAppMainApp {
    private static VCA2UserAuthentication auth = new VCA2UserAuthentication();
    private static VCA3CheckBalance balanceChecker = new VCA3CheckBalance();
    private static VCA4Cashin cashInHandler = new VCA4Cashin();
    private static VCA5CashTransfer transferHandler = new VCA5CashTransfer();
    private static VCA6ViewTransaction transactionViewer = new VCA6ViewTransaction();
    private static String loggedInUserId = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (loggedInUserId == null) {
                // Not logged in, show login/registration menu
                System.out.println("--- VCashApp Menu ---");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 1) {
                    handleLogin(scanner);
                } else if (choice == 2) {
                    handleRegistration(scanner);
                } else if (choice == 3) {
                    System.out.println("Exiting application.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                // Logged in, show main menu
                System.out.println("\n--- Welcome, " + loggedInUserId + "! ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Cash-in");
                System.out.println("3. Cash-in or Transfer");
                System.out.println("4. View All Transactions");
                System.out.println("5. View My Transactions");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        handleCheckBalance();
                        break;
                    case 2:
                        handleCashIn(scanner);
                        break;
                    case 3:
                        handleTransfer(scanner);
                        break;
                    case 4:
                        handleViewAllTransactions();
                        break;
                    case 5:
                        handleViewMyTransactions();
                        break;
                    case 6:
                        auth.logout();
                        loggedInUserId = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        scanner.close();
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter your User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();
        loggedInUserId = auth.login(id, pin);
        if (loggedInUserId != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid ID or PIN.");
        }
    }

    private static void handleRegistration(Scanner scanner) {
        System.out.println("--- User Registration ---");
        System.out.print("Enter a new User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your Number: ");
        String number = scanner.nextLine();
        System.out.print("Enter a 4-digit PIN: ");
        String pin = scanner.nextLine();

        if (auth.registration(id, name, email, number, pin)) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private static void handleCheckBalance() {
        double balance = balanceChecker.getBalance(loggedInUserId);
        if (balance != -1) {
            System.out.println("Your current balance is: " + balance);
        } else {
            System.out.println("Failed to retrieve balance.");
        }
    }

    private static void handleCashIn(Scanner scanner) {
        System.out.print("Enter amount to cash-in: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (cashInHandler.cashIn(loggedInUserId, amount)) {
                System.out.println("Cash-in successful!");
            } else {
                System.out.println("Cash-in failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
        }
    }

    private static void handleTransfer(Scanner scanner) {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (transferHandler.transfer(loggedInUserId, recipientId, amount)) {
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Transfer failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
        }
    }

    private static void handleViewAllTransactions() {
        List<String> transactions = transactionViewer.viewAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("--- All Transactions ---");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

    private static void handleViewMyTransactions() {
        List<String> transactions = transactionViewer.viewUserTransactions(loggedInUserId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for your account.");
        } else {
            System.out.println("--- Your Transactions ---");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}
