package main.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import main.model.DatabaseConnection;

public class LoginController {
	
	// Method to hash a password using SHA-256
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing SHA-256 algorithm", e);
        }
    }
    
    private String getStoredHashedPassword(String username) {
    	String query = "SELECT password_hash FROM users WHERE username = ?;";
    	String password_hash = null;
    	
    	try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				password_hash = rs.getString("password_hash");
			}
			
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return password_hash;
    }
	
	public boolean validateLogin(String username, String password) {
		return getStoredHashedPassword(username) == hashPassword(password);
	}
	
}
