package main.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.DatabaseConnection;
import main.model.Transaction;

public class TransactionController {
	
	public TableColumn<Transaction, String> createColumn(String colName, String propName) {
		TableColumn<Transaction, String> col = new TableColumn<Transaction, String>(colName);
		col.setCellValueFactory(new PropertyValueFactory<Transaction, String>(propName));
		
		return col;
	}
	
	public ArrayList<Transaction> getTransactions() {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		Connection con = null;
		PreparedStatement stm = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DatabaseConnection.getConnection();
			
			String query = "SELECT * FROM transactions ORDER BY transaction_date;";
			stm = con.prepareStatement(query);
			
			ResultSet rs = stm.executeQuery(); 
			
			while (rs.next()) {
				Integer transactionId = rs.getInt("transaction_id"); 
				Integer senderId = rs.getInt("sender_id");
				Integer receiverId = rs.getInt("receiver_id");
				LocalDate transactionDate = rs.getDate("transaction_date").toLocalDate();
				Double amount = rs.getDouble("amount");
				String message = rs.getString("message");
				
				transactions.add(new Transaction(transactionId, senderId, receiverId, transactionDate, amount, message));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		return transactions;
	}
}
