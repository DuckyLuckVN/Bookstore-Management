package com.duan.model;

import java.util.Date;

public class Book 
{
	private String id;
	private String title;
	private String categoryID;
	private int pageNum;
	private String author;
	private int amount;
	private String publisher;
	private int publicationYear;
	private double price;
	private String image;
	private String description;
	private Date createdDate;
	
	public Book()
	{
		
	}

	public Book(String id, String title, String categoryID, int pageNum, String author, int amount, String publisher,
			int publicationYear, double price, String image, String description, Date createdDate) {
		super();
		this.id = id;
		this.title = title;
		this.categoryID = categoryID;
		this.pageNum = pageNum;
		this.author = author;
		this.amount = amount;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.setPrice(price);
		this.image = image;
		this.description = description;
		this.createdDate = createdDate;
	}


	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	
	
}
