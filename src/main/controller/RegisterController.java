package main.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.model.DatabaseConnection;

public class RegisterController {
	
	public boolean insertUser(String username, String password) {
		LoginController loginController = new LoginController();
		String hashPassword = loginController.hashPassword(password);
		String query = "INSERT INTO users (username, password_hash) VALUES (?, ?);";
		int rowsInserted = 0;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, username);
			statement.setString(2, hashPassword);
			
			rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("new user registered successfully!");
				return true;
			} else {
				System.out.println("user didn't get registered!");
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowsInserted > 0;
	}
}
