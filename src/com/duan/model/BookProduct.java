package com.duan.model;

public class BookProduct 
{
	private Book book;
	private int amount;
	private double price;
	
	public BookProduct()
	{
		
	}
	
	public BookProduct(Book book, int amount, double price) {
		super();
		this.book = book;
		this.amount = amount;
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
