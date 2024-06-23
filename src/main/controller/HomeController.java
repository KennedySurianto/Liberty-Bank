package main.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.model.DatabaseConnection;
import main.model.User;

public class HomeController {
	private User currentUser;

	public HomeController() {
		this.currentUser = UserManager.getInstance().getCurrentUser();
	}
	
	private boolean isBalanceEnough(double transferAmount) {
		return currentUser.getBalance() >= transferAmount;
	}
	
	private int getId(String username) {
		int id = -1;
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DatabaseConnection.getConnection();
			
			String query = "SELECT user_id FROM users WHERE username = ?;";
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, username);
			
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				id = rs.getInt("user_id");
			}
			
			stm.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void transfer(String receiverUsername, double transferAmount, Label balanceAmountLabel, TextField recipientField, TextField amountField) {
		if (!isBalanceEnough(transferAmount)) {
			return;
		}
		
		int senderId = currentUser.getUserId();
		int receiverId = getId(receiverUsername);
		if (receiverId == -1) {
			return;
		}
		
		Connection con = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DatabaseConnection.getConnection();
			con.setAutoCommit(false);
			
			String updateSenderQuery = "UPDATE users SET balance = balance - ? WHERE user_id = ?;";
			PreparedStatement updateSenderStatement = con.prepareStatement(updateSenderQuery);
			updateSenderStatement.setDouble(1, transferAmount);
			updateSenderStatement.setInt(2, senderId);
			updateSenderStatement.executeUpdate();
			
			String updateReceiverQuery = "UPDATE users SET balance = balance + ? WHERE user_id = ?;";
			PreparedStatement updateReceiverStatement = con.prepareStatement(updateReceiverQuery);
			updateReceiverStatement.setDouble(1, transferAmount);
			updateReceiverStatement.setInt(2, receiverId);
			updateReceiverStatement.executeUpdate();
			
			String insertTransactionQuery = "INSERT INTO transactions (sender_id, receiver_id, amount) VALUES (?, ?, ?);";
			PreparedStatement insertTransactionStatement = con.prepareStatement(insertTransactionQuery);
			insertTransactionStatement.setInt(1, senderId);
			insertTransactionStatement.setInt(2, receiverId);
			insertTransactionStatement.setDouble(3, transferAmount);
			insertTransactionStatement.executeUpdate();
			
			con.commit();
			
			currentUser.setBalance(currentUser.getBalance() - transferAmount);
			balanceAmountLabel.setText(String.valueOf(currentUser.getBalance()));
			recipientField.clear();
			amountField.clear();
			
			updateSenderStatement.close();
			updateReceiverStatement.close();
			insertTransactionStatement.close();
			
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}
}
