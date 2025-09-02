package VCashApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VCA3CheckBalance {
    private Connection conn;

    public VCA3CheckBalance() {
        try {
            this.conn = VCA1DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    /**
     * Retrieves the user's account balance.
     * @param userId The ID of the user.
     * @return The user's balance, or -1 if an error occurs or the user is not found.
     */
    public double getBalance(String userId) {
        String sql = "SELECT amount FROM Balance WHERE user_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("amount");
            }
        } catch (SQLException e) {
            System.err.println("Error checking balance: " + e.getMessage());
        }
        return -1; // Return a negative value to indicate an error or not found
    }
}
