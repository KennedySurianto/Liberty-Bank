package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.Main;
import main.controller.HomeController;
import main.controller.UserManager;

public class HomeView {
	
	public BorderPane getView(Main main) {
        BorderPane view = new BorderPane();
        HomeController homeController = new HomeController();
        
        // Title
        Label titleLabel = new Label("Welcome, " + UserManager.getInstance().getCurrentUser().getUsername());
        titleLabel.setStyle("-fx-font-size: 24px; -fx-padding: 10px;");
        
        // Transfer form
        GridPane transferForm = new GridPane();
        transferForm.setAlignment(Pos.CENTER);
        transferForm.setHgap(10);
        transferForm.setVgap(10);
        transferForm.setPadding(new Insets(20, 20, 20, 20));
        
        Label balanceLabel = new Label("Your Balance:");
        Label balanceAmountLabel = new Label(String.valueOf(homeController.getCurrentUser().getBalance()));
        
        Label recipientLabel = new Label("Recipient Username:");
        TextField recipientField = new TextField();
        recipientField.setPromptText("Enter recipient username");
        
        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount to transfer");
        
        Button transferButton = new Button("Transfer");
        transferButton.setOnAction(e -> {
            String recipient = recipientField.getText();
            String amount = amountField.getText();
            
            homeController.transfer(recipient, Double.parseDouble(amount), balanceAmountLabel, recipientField, amountField);
        });
        
        transferForm.add(balanceLabel, 0, 0);
        transferForm.add(balanceAmountLabel, 1, 0);
        transferForm.add(recipientLabel, 0, 1);
        transferForm.add(recipientField, 1, 1);
        transferForm.add(amountLabel, 0, 2);
        transferForm.add(amountField, 1, 2);
        transferForm.add(transferButton, 1, 3);
        
        view.setTop(titleLabel);
        view.setCenter(transferForm);
        
        // Setting margin for title
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(10, 0, 10, 0));
        
        return view;
    }
}
