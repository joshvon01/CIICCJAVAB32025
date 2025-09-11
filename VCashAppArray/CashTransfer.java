package VCashAppArray;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles cash-transfer transactions.
 */
public class CashTransfer {

    public static void performCashTransfer(User currentUser, ArrayList<User> users, ArrayList<Transaction> transactions, Scanner scanner) {
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

            User recipient = findUserById(users, recipientId);
            if (recipient == null) {
                System.out.println("Recipient not found.");
                return;
            }

            System.out.println("Current balance: " + currentUser.getBalance());

            currentUser.setBalance(currentUser.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);

            transactions.add(new Transaction(currentUser.getId(), recipient.getId(), "transfer", amount));
            
            System.out.println("Transfer of " + amount + " to " + recipient.getName() + " successful.");
            System.out.println("New balance: " + currentUser.getBalance());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }
    
    // A helper method to find a user by their ID
    private static User findUserById(ArrayList<User> users, String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
