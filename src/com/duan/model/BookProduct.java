package com.duan.model;

public class BookProduct 
{
	private Book book;
	private int amount;
	
	public BookProduct()
	{
		
	}
	
	public BookProduct(Book book, int amount) {
		super();
		this.book = book;
		this.amount = amount;
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
	
	
}
