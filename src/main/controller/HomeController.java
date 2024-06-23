package main.controller;

import javafx.scene.control.Label;
import main.model.User;

public class HomeController {
	private Label welcomeLabel;
	
	
	public Label getWelcomeLabel() {
		return this.welcomeLabel;
	}
	
	public void initialize() {
		User currentUser = UserManager.getInstance().getCurrentUser();
		welcomeLabel = new Label();
        if (currentUser != null) {
            this.welcomeLabel.setText("Welcome, " + currentUser.getUsername() + "!");
        } else {
            this.welcomeLabel.setText("Welcome!");
        }
	}
}
