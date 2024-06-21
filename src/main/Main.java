package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.view.LoginView;
import main.view.RegisterView;

public class Main extends Application {
	Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.stage.setTitle("Liberty Bank");
		
		showLoginView();
	}
	
	public void showLoginView() {
		LoginView loginView = new LoginView();
		this.stage.setScene(new Scene(loginView.getView(this), 400, 300));
		this.stage.show();
	}
	
	public void showRegisterView() {
		RegisterView registerView = new RegisterView();
		this.stage.setScene(new Scene(registerView.getView(this), 400, 300));
		this.stage.show();
	}
	
}