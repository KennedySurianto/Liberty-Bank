package main.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Label;
import main.Main;
import main.model.DatabaseConnection;

public class RegisterController {
	
	public boolean insertUser(String username, String password) {
		LoginController loginController = new LoginController();
		String hashPassword = loginController.hashPassword(password);
		String query = "INSERT INTO users (username, password_hash, balance) VALUES (?, ?, ?);";
		int rowsInserted = 0;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			statement.setString(2, hashPassword);
			statement.setInt(3, 10000);
			
			rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("new user registered successfully!");
			} else {
				System.out.println("user didn't get registered!");
			}
			
			connection.close();
			statement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowsInserted > 0;
	}
	
	public boolean isUsernameUnique(String username) {
		String query = "SELECT username FROM users WHERE username = ?";
		boolean isUnique = false;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			
			isUnique = !statement.executeQuery().next();
			
			connection.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isUnique;
	}
	
	public void register(Main main, Label messageLabel, String username, String password, String passwordConfirmation) {
		if (!isUsernameUnique(username)) {
			messageLabel.setText("Username already exists!");
			return;
		}
		
		if (password.length() < 8) {
			messageLabel.setText("Password must be at least 8 characters");
			return;
		}
		
		if (!password.equals(passwordConfirmation)) {
			messageLabel.setText("Password must match!");
			return;
		}
		
		insertUser(username, password);
		main.showHomeView();
	}
}
