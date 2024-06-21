package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.Main;

public class HomeView {
	
	public BorderPane getView(Main main) {
        BorderPane view = new BorderPane();

        // Title
        Label titleLabel = new Label("Welcome to Liberty Bank");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-padding: 10px;");
        
        // Transfer form
        GridPane transferForm = new GridPane();
        transferForm.setAlignment(Pos.CENTER);
        transferForm.setHgap(10);
        transferForm.setVgap(10);
        transferForm.setPadding(new Insets(20, 20, 20, 20));
        
        Label recipientLabel = new Label("Recipient Account:");
        TextField recipientField = new TextField();
        recipientField.setPromptText("Enter recipient account number");
        
        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount to transfer");
        
        Button transferButton = new Button("Transfer");
        transferButton.setOnAction(e -> {
            String recipient = recipientField.getText();
            String amount = amountField.getText();
            
            // TODO: Add transfer logic here
            System.out.println("Transferring " + amount + " to " + recipient);
        });
        
        transferForm.add(recipientLabel, 0, 0);
        transferForm.add(recipientField, 1, 0);
        transferForm.add(amountLabel, 0, 1);
        transferForm.add(amountField, 1, 1);
        transferForm.add(transferButton, 1, 2);
        
        view.setTop(titleLabel);
        view.setCenter(transferForm);
        
        // Setting margin for title
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(10, 0, 10, 0));
        
        return view;
    }
}
