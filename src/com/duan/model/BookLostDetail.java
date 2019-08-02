package com.duan.model;

public class BookLostDetail 
{
	private int rentbookId;
	private String bookId;
	private int amount;
	private double cost;
	
	public BookLostDetail()
	{
		
	}
	
	public BookLostDetail(int rentbookId, String bookId, int amount, double cost) 
	{
		this.rentbookId = rentbookId;
		this.bookId = bookId;
		this.amount = amount;
		this.cost = cost;
	}

	public int getRentbookId() 
	{
		return rentbookId;
	}
	
	public void setRentbookId(int rentbookId) 
	{
		this.rentbookId = rentbookId;
	}
	
	public String getBookId() 
	{
		return bookId;
	}
	
	public void setBookId(String bookId) 
	{
		this.bookId = bookId;
	}
	
	public int getAmount() 
	{
		return amount;
	}
	
	public void setAmount(int amount) 
	{
		this.amount = amount;
	}
	
	public double getCost() 
	{
		return cost;
	}
	
	public void setCost(double cost) 
	{
		this.cost = cost;
	}
	
}
