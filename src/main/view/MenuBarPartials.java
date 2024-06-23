package main.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import main.Main;

public class MenuBarPartials {
	BorderPane getMenuBar(Main main) {
		BorderPane view = new BorderPane();
		MenuBar menuBar =  new MenuBar();
		
		Menu homeMenu = new  Menu("Home");
		MenuItem homeItem = new MenuItem("Go to home page");
		homeItem.setOnAction(e -> {
			main.showHomeView();
		});
		homeMenu.getItems().add(homeItem);
		
		Menu transactionMenu = new Menu("Transaction");
		MenuItem transactionItem = new MenuItem("Go to transaction page");
		transactionItem.setOnAction(e -> {
			main.showTransactionView();
		});
		transactionMenu.getItems().add(transactionItem);
		
		menuBar.getMenus().addAll(homeMenu, transactionMenu);
		
		view.setTop(menuBar);
		
		return view;
	}
}
