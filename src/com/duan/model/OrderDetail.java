
package com.duan.model;

public class OrderDetail 
{
    private int orderId;
    private String bookId;
    private int amount;
    private double price;

    public OrderDetail(int orderId, String bookId, int amount, double price) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.amount = amount;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
