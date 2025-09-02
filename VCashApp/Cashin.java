package GcashApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cashin {
    private Connection conn;

    public Cashin() {
        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    /**
     * Adds an amount to the current balance of an account.
     * @param accountId The account ID to cash-in to.
     * @param amount The amount to cash-in.
     * @return true if the cash-in is successful, false otherwise.
     */
    public boolean cashIn(String accountId, double amount) {
        String sql = "UPDATE Balance SET amount = amount + ? WHERE user_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error during cash-in: " + e.getMessage());
            return false;
        }
    }
}
