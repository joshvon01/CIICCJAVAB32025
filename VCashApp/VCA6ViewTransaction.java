package VCashApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VCA6ViewTransaction {
    private Connection conn;

    public VCA6ViewTransaction() {
        try {
            this.conn = VCA1DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    /**
     * Returns all transactions from the database.
     * @return A list of transaction strings.
     */
    public List<String> viewAllTransactions() {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                transactions.add(
                    "ID: " + rs.getString("ID") +
                    ", Amount: " + rs.getDouble("amount") +
                    ", Name: " + rs.getString("name") +
                    ", Date: " + rs.getDate("date") +
                    ", Account ID: " + rs.getString("account_ID") +
                    ", Transfer To ID: " + rs.getString("transferToID") +
                    ", Transfer From ID: " + rs.getString("transferFromID")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error viewing all transactions: " + e.getMessage());
        }
        return transactions;
    }

    /**
     * Returns all transactions for a specific user.
     * @param userId The ID of the user.
     * @return A list of transaction strings for the specified user.
     */
    public List<String> viewUserTransactions(String userId) {
        List<String> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction WHERE account_ID = ? OR transferToID = ? OR transferFromID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, userId);
            pstmt.setString(3, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                transactions.add(
                    "ID: " + rs.getString("ID") +
                    ", Amount: " + rs.getDouble("amount") +
                    ", Name: " + rs.getString("name") +
                    ", Date: " + rs.getDate("date") +
                    ", Account ID: " + rs.getString("account_ID") +
                    ", Transfer To ID: " + rs.getString("transferToID") +
                    ", Transfer From ID: " + rs.getString("transferFromID")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error viewing user transactions: " + e.getMessage());
        }
        return transactions;
    }

    /**
     * Returns a specific transaction by its ID.
     * @param transactionId The ID of the transaction.
     * @return The transaction string, or null if not found.
     */
    public String viewTransaction(String transactionId) {
        String sql = "SELECT * FROM Transaction WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return "ID: " + rs.getString("ID") +
                       ", Amount: " + rs.getDouble("amount") +
                       ", Name: " + rs.getString("name") +
                       ", Date: " + rs.getDate("date") +
                       ", Account ID: " + rs.getString("account_ID") +
                       ", Transfer To ID: " + rs.getString("transferToID") +
                       ", Transfer From ID: " + rs.getString("transferFromID");
            }
        } catch (SQLException e) {
            System.err.println("Error viewing specific transaction: " + e.getMessage());
        }
        return null;
    }
}
