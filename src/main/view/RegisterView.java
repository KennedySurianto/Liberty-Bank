package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Main;
import main.controller.RegisterController;

public class RegisterView {
	public VBox getView(Main main) {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		vbox.setSpacing(10);

		Label titleLabel = new Label("Register");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		Label usernameLabel = new Label("Username:");
		TextField usernameField = new TextField();
		usernameField.setPromptText("Enter your username");

		Label passwordLabel = new Label("Password:");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter your password");

		Label confirmPasswordLabel = new Label("Confirm Password:");
		PasswordField confirmPasswordField = new PasswordField();
		confirmPasswordField.setPromptText("Confirm your password");

		Label messageLabel = new Label();
		messageLabel.setStyle("-fx-text-fill: red;");

		Button registerButton = new Button("Register");
		registerButton.setOnAction(e -> {
			RegisterController registerController = new RegisterController();
			registerController.register(main, messageLabel, usernameField.getText(), passwordField.getText(), confirmPasswordField.getText());
		});

		Text loginText = new Text("Already have an account? ");
		Hyperlink loginLink = new Hyperlink("Login");
		loginLink.setOnAction(e -> main.showLoginView());

		HBox hbox = new HBox();
		hbox.getChildren().addAll(loginText, loginLink);
		hbox.setAlignment(Pos.CENTER);

		gridPane.add(usernameLabel, 0, 0);
		gridPane.add(usernameField, 1, 0);
		gridPane.add(passwordLabel, 0, 1);
		gridPane.add(passwordField, 1, 1);
		gridPane.add(confirmPasswordLabel, 0, 2);
		gridPane.add(confirmPasswordField, 1, 2);
		gridPane.add(registerButton, 1, 3);

		vbox.getChildren().addAll(titleLabel, gridPane, messageLabel, hbox);

		return vbox;
	}
}
