package VCashAppArray;

import java.util.ArrayList;

/**
 * Handles viewing a user's transaction history.
 */
public class ViewTransaction {
    public static void viewAllTransactions(User currentUser, ArrayList<User> users, ArrayList<Transaction> transactions) {
        System.out.println("\n--- Your Transaction History ---");
        boolean hasTransactions = false;
        for (Transaction txn : transactions) {
            if (txn.getUserId() != null && txn.getUserId().equals(currentUser.getId())) {
                System.out.println("Type: Cash-In, Amount: " + txn.getAmount());
                hasTransactions = true;
            } else if (txn.getSenderId() != null && txn.getSenderId().equals(currentUser.getId())) {
                User recipient = findUserById(users, txn.getRecipientId());
                System.out.println("Type: Transfer Sent, Amount: " + txn.getAmount() + ", To: " + (recipient != null ? recipient.getName() : "Unknown User"));
                hasTransactions = true;
            } else if (txn.getRecipientId() != null && txn.getRecipientId().equals(currentUser.getId())) {
                User sender = findUserById(users, txn.getSenderId());
                System.out.println("Type: Transfer Received, Amount: " + txn.getAmount() + ", From: " + (sender != null ? sender.getName() : "Unknown User"));
                hasTransactions = true;
            }
        }
        if (!hasTransactions) {
            System.out.println("No transactions found.");
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
