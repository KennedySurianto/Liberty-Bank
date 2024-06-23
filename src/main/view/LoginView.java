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
import main.controller.LoginController;

public class LoginView {
	public VBox getView(Main main) {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		vbox.setSpacing(10);

		Label titleLabel = new Label("Login");
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

		Button loginButton = new Button("Login");
		loginButton.setOnAction(e -> {
			LoginController loginController = new LoginController();
			loginController.login(main, usernameField.getText(), passwordField.getText());
		});
		
		Text registerText = new Text("Don't have an account? ");
		Hyperlink registerLink = new Hyperlink("Register");
		registerLink.setOnAction(e -> main.showRegisterView());

		HBox hbox = new HBox();
		hbox.getChildren().addAll(registerText, registerLink);
		hbox.setAlignment(Pos.CENTER);

		gridPane.add(usernameLabel, 0, 0);
		gridPane.add(usernameField, 1, 0);
		gridPane.add(passwordLabel, 0, 1);
		gridPane.add(passwordField, 1, 1);
		gridPane.add(loginButton, 1, 2);

		vbox.getChildren().addAll(titleLabel, gridPane, hbox);

		return vbox;
	}
}
