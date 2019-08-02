package com.duan.model;

public class StorageDetail 
{
	private int storageId;
	private String bookId;
	private int amount;
	private double price;
	
	
	
	public StorageDetail(int storageId, String bookId, int amount, double price) {
		super();
		this.storageId = storageId;
		this.bookId = bookId;
		this.amount = amount;
		this.price = price;
	}
	
	public int getStorageId() {
		return storageId;
	}
	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
