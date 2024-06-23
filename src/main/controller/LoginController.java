package main.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import main.Main;
import main.model.DatabaseConnection;
import main.model.User;

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
	
	public void login(Main main, String username, String password) {
		String hashedPassword = hashPassword(password);
		String query = "SELECT * FROM users WHERE username = ? AND password_hash = ?;";
		
		User user = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement stm = con.prepareStatement(query);

			stm.setString(1, username);
			stm.setString(2, hashedPassword);
			
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				Integer userId = rs.getInt("user_id");
				String userUsername = rs.getString("username");
				String userHashedPassword = rs.getString("password_hash");
				double userBalance = rs.getDouble("balance");
				
				user = new User(userId, userUsername, userHashedPassword, userBalance);
			}
			
			con.close();
			stm.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			UserManager.getInstance().setCurrentUser(user);
			main.showHomeView();
		}
	}
	
}
