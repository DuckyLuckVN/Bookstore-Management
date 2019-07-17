
package com.duan.model;


public class RentBookDetail 
{
    private int rentbookId;
    private String bookId;
    private int amount;
    private double price;

    public RentBookDetail(int rentbookId, String bookId, int amount, double price) {
        this.rentbookId = rentbookId;
        this.bookId = bookId;
        this.amount = amount;
        this.price = price;
    }

    public int getRentbookId() {
        return rentbookId;
    }

    public void setRentbookId(int rentbookId) {
        this.rentbookId = rentbookId;
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
