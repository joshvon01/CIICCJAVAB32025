package VCashAppArray;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles cash-in transactions.
 */
public class CashIn {
    public static void performCashIn(User user, ArrayList<Transaction> transactions, Scanner scanner) {
        System.out.print("Enter amount to cash in: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            if (amount <= 0) {
                System.out.println("Invalid amount. Must be a positive number.");
                return;
            }
            user.setBalance(user.getBalance() + amount);
            transactions.add(new Transaction(user.getId(), "cash-in", amount));
            System.out.println("Cash-in of " + amount + " successful. New balance is " + user.getBalance());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }
}
