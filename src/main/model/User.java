package main.model;

public class User {
	private Integer userId;
	private String username, password;
	private Double balance;
	
	public User(Integer userId, String username, String password, Double balance) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
