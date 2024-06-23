package main.model;

import java.time.LocalDate;

public class Transaction {
	private Integer transactionId, senderId, receiverId;
	private LocalDate transactionDate;
	private Double amount;
	private String message;
	
	public Transaction(Integer transactionId, Integer senderId, Integer receiverId, LocalDate transactionDate,
			Double amount, String message) {
		this.transactionId = transactionId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.message = message;
	}
	
	public Integer getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
	public Integer getSenderId() {
		return senderId;
	}
	
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	
	public Integer getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
