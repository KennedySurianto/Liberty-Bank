package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
	
	public static Connection getConnection() throws SQLException {
		Dotenv dotenv = Dotenv.configure().load();
		
		final String URL = dotenv.get("DB_URL");
		final String USER = dotenv.get("DB_USER");
		final String PASS = dotenv.get("DB_PASS");
		
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
}
