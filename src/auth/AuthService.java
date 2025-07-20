/*package auth;

import model.User;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private Map<String, User> users = new HashMap<>();

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("User already exists!");
            return false;
        }

        String hashed = hashPassword(password);
        User user = new User(username, hashed);
        users.put(username, user);
        System.out.println("User registered successfully!");
        return true;
    }

    public boolean loginUser(String username, String password) {
        if (!users.containsKey(username)) {
            System.out.println("User not found.");
            return false;
        }

        String hashed = hashPassword(password);
        return users.get(username).getHashedPassword().equals(hashed);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
*/

package auth;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, String> userDatabase;

    public AuthService() {
        userDatabase = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists. Try another one.");
        } else {
            userDatabase.put(username, password);
            System.out.println("User registered successfully!");
        }
    }

    public boolean loginUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            if (userDatabase.get(username).equals(password)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Incorrect password.");
                return false;
            }
        } else {
            System.out.println("User not found.");
            return false;
        }
    }
}
