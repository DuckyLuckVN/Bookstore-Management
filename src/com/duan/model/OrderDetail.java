
package com.duan.model;

public class OrderDetail 
{
    private int orderId;
    private String bookId;
    private int amount;
    private double money;

    public OrderDetail(int orderId, String bookId, int amount, double money) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.amount = amount;
        this.money = money;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
    
}
