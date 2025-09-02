package VCashApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VC2UserAuthentication {
    private Connection conn;

    public UserAuthentication() {
        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    /**
     * Registers a new user in the database.
     * @param id The user's ID.
     * @param name The user's name.
     * @param email The user's email.
     * @param number The user's phone number.
     * @param pin The user's PIN.
     * @return true if registration is successful, false otherwise.
     */
    public boolean registration(String id, String name, String email, String number, String pin) {
        String sql = "INSERT INTO users (ID, Name, Email, Number, PIN) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Add validation for each field
            if (id == null || id.isEmpty() || name == null || name.isEmpty() || email == null || email.isEmpty() || number == null || number.isEmpty() || pin == null || pin.isEmpty()) {
                System.err.println("All fields must be filled out for registration.");
                return false;
            }

            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, number);
            pstmt.setString(5, pin);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the user's ID and PIN are valid for login.
     * @param id The user's ID.
     * @param pin The user's PIN.
     * @return The user's ID if login is successful, null otherwise.
     */
    public String login(String id, String pin) {
        String sql = "SELECT ID FROM users WHERE ID = ? AND PIN = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, pin);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("ID");
            }
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
        }
        return null;
    }

    /**
     * Changes the user's PIN.
     * @param id The user's ID.
     * @param newPin The new PIN.
     * @return true if the PIN is changed successfully, false otherwise.
     */
    public boolean changePin(String id, String newPin) {
        String sql = "UPDATE users SET PIN = ? WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPin);
            pstmt.setString(2, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error changing PIN: " + e.getMessage());
            return false;
        }
    }

    /**
     * Handles user logout. For this simple app, it just prints a message.
     */
    public void logout() {
        System.out.println("You have been logged out.");
    }
}
