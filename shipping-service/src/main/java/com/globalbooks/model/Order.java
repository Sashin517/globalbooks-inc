package com.globalbooks.model;
import java.util.List;

public class Order {
    private String id;
    private String customerId;
    private List<String> bookIds;
    private double totalAmount;

    // Default constructor is required for JSON deserialization
    public Order() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<String> getBookIds() { return bookIds; }
    public void setBookIds(List<String> bookIds) { this.bookIds = bookIds; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}