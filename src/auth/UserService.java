package auth;

import java.sql.*;

public class UserService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/secure_file_vault";
    private static final String DB_USER = "root"; // change if needed
    private static final String DB_PASSWORD = "your_password"; // change this

    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if a match is found

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
