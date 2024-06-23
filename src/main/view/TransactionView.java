package main.view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.controller.TransactionController;
import main.model.Transaction;

public class TransactionView {
	@SuppressWarnings("unchecked")
	public BorderPane getView(Main main) {
		BorderPane view = new MenuBarPartials().getMenuBar(main);
		BorderPane borderPane = new BorderPane();
		TransactionController tController = new TransactionController();
		
		// Title
		Label titleLabel = new Label("Recent Transactions");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-padding: 10px;");
		
		// Columns
		TableColumn<Transaction, String> transactionIdCol = tController.createColumn("ID", "transactionId");
		TableColumn<Transaction, String> transactionDateCol = tController.createColumn("Date", "transactionDate");
		TableColumn<Transaction, String> senderIdCol = tController.createColumn("Sender", "senderId");
		TableColumn<Transaction, String> receiverIdCol = tController.createColumn("Receiver", "receiverId");
		TableColumn<Transaction, String> amountCol = tController.createColumn("Amount", "amount");
		TableColumn<Transaction, String> messageCol = tController.createColumn("Message", "message");
		
		// Table
		TableView<Transaction> transactionTable = new TableView<>();
		transactionTable.getColumns().addAll(transactionIdCol, transactionDateCol, senderIdCol, receiverIdCol, amountCol, messageCol);
		
		// Bind the width of each column to a portion of the TableView width
		transactionIdCol.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.1));
		transactionDateCol.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.2));
		senderIdCol.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.1));
		receiverIdCol.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.1));
		amountCol.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.2));
		messageCol.prefWidthProperty().bind(transactionTable.widthProperty().multiply(0.3));
		
		// Insert
		ArrayList<Transaction> transactions = tController.getTransactions();
		for (Transaction t : transactions) {
			transactionTable.getItems().add(new Transaction(t.getTransactionId(), t.getSenderId(), t.getReceiverId(), t.getTransactionDate(), t.getAmount(), t.getMessage()));
		}
		
		borderPane.setTop(titleLabel);
		borderPane.setCenter(transactionTable);
		
		// Setting margin for title
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(10, 0, 10, 0));
		
		view.setCenter(borderPane);
		
		return view;
	}
}
