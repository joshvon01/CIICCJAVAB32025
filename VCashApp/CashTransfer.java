package GcashApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CashTransfer {
    private Connection conn;
    private CheckBalance checkBalance;

    public CashTransfer() {
        try {
            this.conn = DatabaseConnection.getConnection();
            this.checkBalance = new CheckBalance();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    /**
     * Transfers money from one account to another.
     * @param fromUserId The user ID of the sender.
     * @param toUserId The user ID of the recipient.
     * @param amount The amount to transfer.
     * @return true if the transfer is successful, false otherwise.
     */
    public boolean transfer(String fromUserId, String toUserId, double amount) {
        // Check if sender has sufficient balance
        double senderBalance = checkBalance.getBalance(fromUserId);
        if (senderBalance < amount) {
            System.out.println("Insufficient balance.");
            return false;
        }

        // Use a transaction to ensure both updates succeed or fail together.
        try {
            conn.setAutoCommit(false); // Start transaction

            // Reduce balance of sender
            String deductSql = "UPDATE Balance SET amount = amount - ? WHERE user_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deductSql)) {
                pstmt.setDouble(1, amount);
                pstmt.setString(2, fromUserId);
                pstmt.executeUpdate();
            }

            // Add balance to recipient
            String addSql = "UPDATE Balance SET amount = amount + ? WHERE user_ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(addSql)) {
                pstmt.setDouble(1, amount);
                pstmt.setString(2, toUserId);
                pstmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback transaction on error
            } catch (SQLException ex) {
                System.err.println("Rollback failed: " + ex.getMessage());
            }
            System.err.println("Error during cash transfer: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException e) {
                System.err.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }
}
